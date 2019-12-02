package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Models.Database_Entities.ServiceProviderTypeEntity;
import API.Exceptions.*;
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
        if (serviceProviderTypeDAO.findAllByProviderType(serviceProviderTypeEntity.getProviderType()).size() > 0) {
            throw new DuplicateException("There is already a provider type with exact name.");
        }
        if (serviceProviderTypeEntity.getProviderType() == null || serviceProviderTypeEntity.getProviderType().length() == 0) {
            throw new NotEnoughDataException("You provided to little information to create the provider type.");
        }
        ServiceProviderTypeEntity saved = serviceProviderTypeDAO.save(serviceProviderTypeEntity);
        if (saved.getId() > 0) {
            return modelMapper.map(saved, ServiceProviderTypeDto.class);
        } else {
            throw new UnknownAddingException("There was a problem with adding.");
        }
    }

    @Override
    public ServiceProviderTypeDto updateServiceProviderType(ServiceProviderTypeEntity serviceProviderTypeEntity) {
        try {
            Optional<ServiceProviderTypeEntity> found = serviceProviderTypeDAO.findById(serviceProviderTypeEntity.getId());
            if (!found.isPresent()) {
                throw new NotFoundException("Provider type was not found while an attempt of making update.");
            }
            patcherHandler.fillNotNullFields(found.get(), serviceProviderTypeEntity);
            ServiceProviderTypeEntity updated = serviceProviderTypeDAO.save(found.get());
            return modelMapper.map(updated, ServiceProviderTypeDto.class);
        } catch (IntrospectionException e) {
            throw new UpdatePatchException("There was an error while updating a type. [PATCHING] ");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ServiceProviderTypeDto findServiceProviderType(int id) {
        Optional<ServiceProviderTypeEntity> found = serviceProviderTypeDAO.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException("Service provider type with the ID does not exist.");
        }
        return modelMapper.map(found.get(), ServiceProviderTypeDto.class);
    }

    @Override
    public List<ServiceProviderTypeDto> listServiceProviderTypes() {
        try {
            Type listType = new TypeToken<List<ServiceProviderTypeDto>>() {
            }.getType();
            return modelMapper.map(serviceProviderTypeDAO.findAll(), listType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error.");
        }
    }

    @Override
    public boolean deleteServiceProviderType(int id) {
        try {
            Optional<ServiceProviderTypeEntity> found = serviceProviderTypeDAO.findById(id);
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("The service provider type was not found.");
            }
            ServiceProviderTypeEntity toDelete = found.get();
            toDelete.setDeleted(true);
            ServiceProviderTypeEntity deletionResult = serviceProviderTypeDAO.save(toDelete);
            if (deletionResult.isDeleted()) {
                return true;
            } else {
                return false;
            }
        } catch (NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }
}
