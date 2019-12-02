package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Models.Database_Entities.ServiceProviderSourceEntity;
import API.Exceptions.*;
import Shared.ToReturn.ServiceProviderSourceDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
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
        if (serviceProviderSourceDAO.findAllByProviderSource(serviceProviderSourceEntity.getProviderSource()).size() > 0) {
            throw new DuplicateException("There is already a provider source with exact name.");
        }
        if (serviceProviderSourceEntity.getProviderSource() == null || serviceProviderSourceEntity.getProviderSource().length() == 0) {
            throw new NotEnoughDataException("You provided to little information to create the provider source.");
        }
        ServiceProviderSourceEntity saved = serviceProviderSourceDAO.save(serviceProviderSourceEntity);
        if (saved.getId() > 0) {
            return modelMapper.map(saved, ServiceProviderSourceDto.class);
        } else {
            throw new UnknownAddingException("There was a problem with adding.");
        }
    }

    @Override
    public ServiceProviderSourceDto updateServiceProviderSource(ServiceProviderSourceEntity serviceProviderSourceEntity) {
        try {
            ServiceProviderSourceEntity found = serviceProviderSourceDAO.findById(serviceProviderSourceEntity.getId()).get();
            patcherHandler.fillNotNullFields(found, serviceProviderSourceEntity);
            ServiceProviderSourceEntity updated = serviceProviderSourceDAO.save(found);
            return modelMapper.map(updated, ServiceProviderSourceDto.class);
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException("Provider source was not found while an attempt of making update.");
        } catch (IntrospectionException e) {
            throw new UpdatePatchException("There was an error while updating a competency. [PATCHING] ");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ServiceProviderSourceDto findServiceProviderSource(int id) {
        return modelMapper.map(serviceProviderSourceDAO.findById(id).get(), ServiceProviderSourceDto.class);
    }

    @Override
    public List<ServiceProviderSourceDto> listServiceProviderSources() {
        try {
            Type listType = new TypeToken<List<ServiceProviderSourceDto>>() {
            }.getType();
            return modelMapper.map(serviceProviderSourceDAO.findAll(), listType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error.");
        }
    }

    @Override
    public boolean deleteServiceProviderSource(int id) {
        try {
            Optional<ServiceProviderSourceEntity> found = serviceProviderSourceDAO.findById(id);
            if (!found.isPresent()) {
                throw new NotFoundException("The service provider source was not found.");
            }
            ServiceProviderSourceEntity toDelete = found.get();
            toDelete.setDeleted(true);
            ServiceProviderSourceEntity deletionResult = serviceProviderSourceDAO.save(toDelete);
            if (deletionResult.isDeleted()) {
                return true;
            } else {
                return false;
            }
        }catch (NotFoundException notFoundException){
            throw notFoundException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }

}
