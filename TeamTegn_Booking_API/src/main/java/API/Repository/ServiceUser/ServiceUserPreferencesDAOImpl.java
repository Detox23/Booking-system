package API.Repository.ServiceUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.ServiceUserPreferencesEntity;
import Shared.ToReturn.ServiceUserPreferencesDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceUserPreferencesDAOImpl implements ServiceUserPreferencesDAOCustom {

    private ServiceUserPreferencesDAO serviceUserPreferencesDAO;
    private PatcherHandler patcherHandler;
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setServiceUserPreferencesDAO(ServiceUserPreferencesDAO serviceUserPreferencesDAO) {
        this.serviceUserPreferencesDAO = serviceUserPreferencesDAO;
    }

    @Override
    public ServiceUserPreferencesDto addServiceUserPreference(int serviceUserId, ServiceUserPreferencesEntity serviceUserPreferences) {
        try {
            if (serviceUserPreferencesDAO.countAllByServiceUserIdIsAndServiceProviderIdIs(serviceUserId, serviceUserPreferences.getServiceProviderId()) > 0) {
                throw new DuplicateException("There is already preference between the service provider and service user.");
            }
            serviceUserPreferences.setServiceUserId(serviceUserId);
            ServiceUserPreferencesEntity saved = serviceUserPreferencesDAO.save(serviceUserPreferences);
            return modelMapper.map(saved, ServiceUserPreferencesDto.class);

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteServiceUserPreference(int id) {
        try {
            ServiceUserPreferencesEntity found = findIfExistsAndReturn(id);
            serviceUserPreferencesDAO.deleteById(found.getId());
            Optional<ServiceUserPreferencesEntity> assure = serviceUserPreferencesDAO.findById(id);
            return !assure.isPresent();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ServiceUserPreferencesDto> listServiceUserPreferences(int id) {
        try{
            Type listType = new TypeToken<List<ServiceUserPreferencesDto>>() {}.getType();
            return modelMapper.map(serviceUserPreferencesDAO.findAllByServiceUserIdOrderByRatingAsc(id), listType);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public ServiceUserPreferencesDto findServiceProviderAndUser(int serviceUserId, int serviceProviderId) {
        try {
            Optional<ServiceUserPreferencesEntity> found = serviceUserPreferencesDAO.findByServiceUserIdAndServiceProviderId(serviceUserId, serviceProviderId);
            if (!found.isPresent()) {
                throw new NotFoundException("The preference between service provider and service user was not found");
            }
            return modelMapper.map(found.get(), ServiceUserPreferencesDto.class);
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public ServiceUserPreferencesDto updateServiceUserPreference(ServiceUserPreferencesEntity serviceUserPreference) {
        try {
            serviceUserPreference.setServiceUserId(null);
            ServiceUserPreferencesEntity found = findIfExistsAndReturn(serviceUserPreference.getId());
            patcherHandler.fillNotNullFields(found, serviceUserPreference);
            ServiceUserPreferencesEntity updated = serviceUserPreferencesDAO.save(found);
            return modelMapper.map(updated, ServiceUserPreferencesDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was a problem with updating the preference.[PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }

    private ServiceUserPreferencesEntity findIfExistsAndReturn(int id) {
        Optional<ServiceUserPreferencesEntity> found = serviceUserPreferencesDAO.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Service user preference with id: %d was not found.", id));
        }
        return found.get();
    }


}

