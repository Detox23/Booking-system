package API.Services.ServiceProviderService;

import API.Configurations.Encryption.EncryptionHandler;
import API.Database_Entities.ServiceProviderEntity;
import API.Exceptions.NotFoundException;
import API.Repository.ServiceProvider.ServiceProviderDAO;
import API.Repository.ServiceProvider.ServiceProvider_ServiceProviderCompetencyDAO;
import API.Repository.ServiceProvider.ServiceProvider_ServiceProviderTypeDAO;
import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.ServiceProviderForUpdateDto;
import Shared.ToReturn.ServiceProviderDto;
import Shared.ToReturn.ServiceProviderServiceProviderCompetencyDto;
import Shared.ToReturn.ServiceProviderServiceProviderTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ServiceProviderService implements IServiceProviderService {

    private ServiceProviderDAO serviceProviderDAO;

    private ModelMapper modelMapper;

    private EncryptionHandler encryptionHandler;

    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

    private ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO;

    @Autowired
    public void setEncryptionHandler(EncryptionHandler encryptionHandler) {
        this.encryptionHandler = encryptionHandler;
    }

    @Autowired
    public void setServiceProviderServiceProviderTypeDAO(ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO) {
        this.serviceProviderServiceProviderTypeDAO = serviceProviderServiceProviderTypeDAO;
    }

    @Autowired
    public void setServiceProviderServiceProviderCompetencyDAO(ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO) {
        this.serviceProviderServiceProviderCompetencyDAO = serviceProviderServiceProviderCompetencyDAO;
    }

    @Autowired
    public void setServiceProviderDAO(ServiceProviderDAO serviceProviderDAO) {
        this.serviceProviderDAO = serviceProviderDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ServiceProviderDto> list() {
        return serviceProviderDAO.listAll();
    }

    @Override
    public ServiceProviderDto findServiceProvider(int id) {
        try {
            ServiceProviderDto found = serviceProviderDAO.findOne(id);
            return fillListsToReturn(found);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Service provider not found");
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderDto addServiceProvider(ServiceProviderForCreationDto serviceProvider) {
        ServiceProviderDto added = serviceProviderDAO.addServiceProvider(modelMapper.map(serviceProvider, ServiceProviderEntity.class), serviceProvider.getCompetences(), serviceProvider.getTypes());
        return fillListsToReturn(added);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderDto updateServiceProvider(ServiceProviderForUpdateDto serviceProvider) {
        ServiceProviderDto updated = serviceProviderDAO.updateServiceProvider(modelMapper.map(serviceProvider, ServiceProviderEntity.class), serviceProvider.getCompetences(), serviceProvider.getTypes());
        return fillListsToReturn(updated);
    }

    @Override
    public boolean deleteServiceProvider(int id) {
        return serviceProviderDAO.deleteServiceProvider(id);
    }

    private ServiceProviderDto fillListsToReturn(ServiceProviderDto serviceProvider){
            String decrypted = encryptionHandler.decrypt(serviceProvider.getCpr());
            serviceProvider.setCpr(decrypted);
            serviceProvider.setCompetences(new ArrayList<>());
            serviceProvider.setTypes(new ArrayList<>());
            List<ServiceProviderServiceProviderCompetencyDto> listCompetency = serviceProviderServiceProviderCompetencyDAO.listAllCompetenciesOfServiceProvider(serviceProvider.getId());
            List<ServiceProviderServiceProviderTypeDto> listTypes = serviceProviderServiceProviderTypeDAO.listServiceProviderServiceProviderTypes(serviceProvider.getId());
            for (ServiceProviderServiceProviderCompetencyDto item : listCompetency) {
                serviceProvider.getCompetences().add(item.getCompetencyId());
            }
            for (ServiceProviderServiceProviderTypeDto item : listTypes) {
                serviceProvider.getTypes().add(item.getServiceProviderTypeId());
            }
            return serviceProvider;
    }

}
