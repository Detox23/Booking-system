package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.ServiceProviderSourceEntity;
import Shared.ToReturn.ServiceProviderSourceDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceProviderSourceDAOImpl implements ServiceProviderSourceDAOCustom {


    private ServiceProviderSourceDAO serviceProviderSourceDAO;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;


    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderSourceDAO(ServiceProviderSourceDAO serviceProviderSourceDAO) {
        this.serviceProviderSourceDAO = serviceProviderSourceDAO;
    }


    @Override
    public ServiceProviderSourceDto addServiceProviderSource(ServiceProviderSourceEntity serviceProviderSourceEntity) {
        try {
            if (serviceProviderSourceDAO.countAllByProviderSourceIs(serviceProviderSourceEntity.getProviderSource()) > 0) {
                throw new DuplicateException(String.format("There is already a provider source with %s name.", serviceProviderSourceEntity.getProviderSource()));
            }
            ServiceProviderSourceEntity saved = serviceProviderSourceDAO.save(serviceProviderSourceEntity);
            return modelMapper.map(saved, ServiceProviderSourceDto.class);
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceProviderSourceDto updateServiceProviderSource(ServiceProviderSourceEntity serviceProviderSourceEntity) {
        try {
            ServiceProviderSourceEntity found = findIfExistsAndReturn(serviceProviderSourceEntity.getId());
            patcherHandler.fillNotNullFields(found, serviceProviderSourceEntity);
            ServiceProviderSourceEntity updated = serviceProviderSourceDAO.save(found);
            return modelMapper.map(updated, ServiceProviderSourceDto.class);
        } catch (IntrospectionException e) {
            throw new UpdatePatchException("There was an error while updating a competency. [PATCHING] ");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceProviderSourceDto findServiceProviderSource(int id) {
        try{
            ServiceProviderSourceEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, ServiceProviderSourceDto.class);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<ServiceProviderSourceDto> listServiceProviderSources(boolean showDeleted) {
        if(showDeleted){
            try {
                Type listType = new TypeToken<List<ServiceProviderSourceDto>>() {}.getType();
                return modelMapper.map(serviceProviderSourceDAO.findAll(), listType);
            } catch (Exception e) {
                throw e;
            }
        }else{
            try {
                Type listType = new TypeToken<List<ServiceProviderSourceDto>>() {}.getType();
                return modelMapper.map(serviceProviderSourceDAO.findAllByDeletedIsFalse(), listType);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public boolean deleteServiceProviderSource(int id) {
        try {
            ServiceProviderSourceEntity toDelete = findIfExistsAndReturn(id);
            toDelete.setDeleted(true);
            ServiceProviderSourceEntity deletionResult = serviceProviderSourceDAO.save(toDelete);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    private ServiceProviderSourceEntity findIfExistsAndReturn(int id) {
        Optional<ServiceProviderSourceEntity> found = serviceProviderSourceDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Service provider source with id: %d was not found.", id));
        }
        return found.get();
    }
}
