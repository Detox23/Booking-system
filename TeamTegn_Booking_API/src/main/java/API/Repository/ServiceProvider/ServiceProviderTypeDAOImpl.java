package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.ServiceProviderTypeEntity;
import Shared.ToReturn.ServiceProviderTypeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceProviderTypeDAOImpl implements ServiceProviderTypeDAOCustom {

    private ModelMapper modelMapper;

    private ServiceProviderTypeDAO serviceProviderTypeDAO;

    private PatcherHandler patcherHandler;

    private ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO;

    @Autowired
    public void setServiceProviderServiceProviderTypeDAO(ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO) {
        this.serviceProviderServiceProviderTypeDAO = serviceProviderServiceProviderTypeDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderTypeDAO(ServiceProviderTypeDAO serviceProviderTypeDAO) {
        this.serviceProviderTypeDAO = serviceProviderTypeDAO;
    }

    @Override
    public ServiceProviderTypeDto addServiceProviderType(ServiceProviderTypeEntity serviceProviderTypeEntity) {
        try {
            checkIfProviderType(serviceProviderTypeEntity);
            ServiceProviderTypeEntity saved = serviceProviderTypeDAO.save(serviceProviderTypeEntity);
            return modelMapper.map(saved, ServiceProviderTypeDto.class);
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceProviderTypeDto updateServiceProviderType(ServiceProviderTypeEntity serviceProviderTypeEntity) {
        try {
            ServiceProviderTypeEntity found = findIfExistsAndReturn(serviceProviderTypeEntity.getId());
            patcherHandler.fillNotNullFields(found, serviceProviderTypeEntity);
            checkIfProviderType(found);
            ServiceProviderTypeEntity updated = serviceProviderTypeDAO.save(found);
            return modelMapper.map(updated, ServiceProviderTypeDto.class);
        } catch (IntrospectionException e) {
            throw new UpdatePatchException("There was an error while updating a type. [PATCHING] ");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceProviderTypeDto findServiceProviderType(int id) {
        try{
            ServiceProviderTypeEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, ServiceProviderTypeDto.class);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<ServiceProviderTypeDto> listServiceProviderTypes(boolean showDeleted) {
        if(showDeleted){
            try {
                Type listType = new TypeToken<List<ServiceProviderTypeDto>>() {}.getType();
                return modelMapper.map(serviceProviderTypeDAO.findAll(), listType);
            } catch (Exception e) {
                throw e;
            }
        }else{
            try {
                Type listType = new TypeToken<List<ServiceProviderTypeDto>>() {}.getType();
                return modelMapper.map(serviceProviderTypeDAO.findAllByDeletedIsFalse(), listType);
            } catch (Exception e) {
                throw e;
            }
        }

    }

    @Override
    public boolean deleteServiceProviderType(int id) {
        try {
            ServiceProviderTypeEntity toDelete = findIfExistsAndReturn(id);
            toDelete.setDeleted(true);
            ServiceProviderTypeEntity deletionResult = serviceProviderTypeDAO.save(toDelete);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    private void checkIfProviderType(ServiceProviderTypeEntity serviceProviderType) {
        if (serviceProviderType.getId() == 0) {
            if (serviceProviderTypeDAO.countAllByProviderTypeIs(serviceProviderType.getProviderType()) > 0) {
                throw new DuplicateException(String.format("The provider type: %s already exists", serviceProviderType.getProviderType()));
            }
        } else {
            if (serviceProviderTypeDAO.countAllByProviderTypeIsAndIdIsNot(serviceProviderType.getProviderType(), serviceProviderType.getId()) > 0) {
                throw new DuplicateException(String.format("The provider type: %s already exists", serviceProviderType.getProviderType()));
            }
        }
    }

    private ServiceProviderTypeEntity findIfExistsAndReturn(int id) {
        Optional<ServiceProviderTypeEntity> found = serviceProviderTypeDAO.findByIdIsAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Service provider source with id: %d was not found.", id));
        }
        return found.get();
    }
}
