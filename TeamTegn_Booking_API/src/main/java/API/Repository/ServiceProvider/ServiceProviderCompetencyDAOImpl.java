package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.ServiceProviderCompetencyEntity;
import Shared.ToReturn.ServiceProviderCompetencyDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceProviderCompetencyDAOImpl implements ServiceProviderCompetencyDAOCustom {

    private ServiceProviderCompetencyDAO serviceProviderCompetencyDAO;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;

    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

    @Autowired
    public void setServiceProviderServiceProviderCompetencyDAO(ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO) {
        this.serviceProviderServiceProviderCompetencyDAO = serviceProviderServiceProviderCompetencyDAO;
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
    public void setServiceProviderCompetencyDAO(ServiceProviderCompetencyDAO serviceProviderCompetencyDAO) {
        this.serviceProviderCompetencyDAO = serviceProviderCompetencyDAO;
    }

    @Override
    public List<ServiceProviderCompetencyDto> listServiceProviderCompetencies(boolean showDeleted) {
        if (showDeleted) {
            try {
                Type listType = new TypeToken<List<ServiceProviderCompetencyDto>>() {
                }.getType();
                return modelMapper.map(serviceProviderCompetencyDAO.findAllByDeletedIsFalse(), listType);
            } catch (Exception e) {
                throw e;
            }
        } else {
            try {
                Type listType = new TypeToken<List<ServiceProviderCompetencyDto>>() {
                }.getType();
                return modelMapper.map(serviceProviderCompetencyDAO.findAll(), listType);
            } catch (Exception e) {
                throw e;
            }
        }

    }

    @Override
    public ServiceProviderCompetencyDto findServiceProviderCompetency(int id) {
        try {
            ServiceProviderCompetencyEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, ServiceProviderCompetencyDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error.");
        }
    }

    @Override
    public ServiceProviderCompetencyDto addServiceProviderCompetency(ServiceProviderCompetencyEntity serviceProviderCompetency) {
        try {
            if (serviceProviderCompetencyDAO.countAllByCompetencyIs(serviceProviderCompetency.getCompetency()) > 0) {
                throw new DuplicateException(String.format("Competency with name: %s already exists.", serviceProviderCompetency.getCompetency()));
            }
            ServiceProviderCompetencyEntity saved = serviceProviderCompetencyDAO.save(serviceProviderCompetency);
            return modelMapper.map(saved, ServiceProviderCompetencyDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceProviderCompetencyDto updateServiceProviderCompetency(ServiceProviderCompetencyEntity serviceProviderCompetency) {
        try {
            ServiceProviderCompetencyEntity found = findIfExistsAndReturn(serviceProviderCompetency.getId());
            patcherHandler.fillNotNullFields(found, serviceProviderCompetency);
            ServiceProviderCompetencyEntity updated = serviceProviderCompetencyDAO.save(found);
            return modelMapper.map(updated, ServiceProviderCompetencyDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating a competency [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteServiceProviderCompetency(int id) {
        try {
            ServiceProviderCompetencyEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            ServiceProviderCompetencyEntity deletionResult = serviceProviderCompetencyDAO.save(found);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    private ServiceProviderCompetencyEntity findIfExistsAndReturn(int id) {
        Optional<ServiceProviderCompetencyEntity> found = serviceProviderCompetencyDAO.findByIdIsAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Service provider competency with id: %d was not found.", id));
        }
        return found.get();
    }


}
