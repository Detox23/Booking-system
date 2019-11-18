package API.Repository.ServiceProvider;

import API.Configurations.Encryption.EncryptionHandler;
import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.ServiceProviderEntity;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.ServiceProviderDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;


@Component
public class ServiceProviderDAOImpl implements ServiceProviderDAOCustom {

    private ServiceProviderDAO serviceProviderDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    private EncryptionHandler encryptionHandler;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setEncryptionHandler(EncryptionHandler encryptionHandler) {
        this.encryptionHandler = encryptionHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderDAO(ServiceProviderDAO serviceProviderDAO) {
        this.serviceProviderDAO = serviceProviderDAO;
    }

    @Override
    public List<ServiceProviderDto> listAll() {
        Type listType = new TypeToken<List<ServiceProviderDto>>() {
        }.getType();
        return modelMapper.map(serviceProviderDAO.findAll(), listType);
    }

    @Override
    public ServiceProviderDto findOne(int id) {
        ServiceProviderEntity found = serviceProviderDAO.findById(id).get();
        found.setCpr(encryptionHandler.decrypt(found.getCpr()));
        return modelMapper.map(found, ServiceProviderDto.class);
    }

    @Override
    public ServiceProviderDto addServiceProvider(ServiceProviderEntity serviceProvider) {
        serviceProvider.setCpr(encryptionHandler.encrypt(serviceProvider.getCpr()));
        ServiceProviderEntity saved = serviceProviderDAO.saveAndFlush(serviceProvider);
        saved.setCpr(encryptionHandler.decrypt(saved.getCpr()));
        return modelMapper.map(saved, ServiceProviderDto.class);
    }

    @Override
    public ServiceProviderDto updateServiceProvider(ServiceProviderEntity serviceProvider) {
        try {
            ServiceProviderEntity found = serviceProviderDAO.findById(serviceProvider.getId()).get();
            if (serviceProvider.getCpr() != null) {
                serviceProvider.setCpr(encryptionHandler.encrypt(serviceProvider.getCpr()));
            }
            patcherHandler.fillNotNullFields(found, serviceProvider);
            ServiceProviderEntity updated = serviceProviderDAO.save(found);
            if (updated.getCpr() != null) {
                updated.setCpr(encryptionHandler.decrypt(updated.getCpr()));
            }
            return modelMapper.map(updated, ServiceProviderDto.class);
        } catch (IntrospectionException interspectionException) {
            throw new UpdatePatchException("There was an error while updating a service provider [PATCHING].");
        }
    }

    @Override
    public boolean deleteServiceProvider(int id) {
        ServiceProviderEntity found = serviceProviderDAO.findById(id).get();
        found.setDeleted(true);
        ServiceProviderEntity deleted = serviceProviderDAO.save(found);
        return deleted.isDeleted();
    }


}