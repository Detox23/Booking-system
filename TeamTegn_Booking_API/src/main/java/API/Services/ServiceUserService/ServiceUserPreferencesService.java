package API.Services.ServiceUserService;

import API.Models.Database_Entities.ServiceUserPreferencesEntity;
import API.Repository.ServiceUser.ServiceUserPreferencesDAO;
import API.Services.ServiceProviderService.ServiceProviderService;
import Shared.ForCreation.ServiceUserPreferencesForCreationDto;
import Shared.ForCreation.ServiceUserPreferencesForUpdateDto;
import Shared.ToReturn.ServiceUserPreferencesDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceUserPreferencesService implements IServiceUserPreferencesService {

    private ServiceUserPreferencesDAO userPreferencesDAO;
    private ModelMapper modelMapper;
    private ServiceProviderService serviceProviderService;

    @Autowired
    public void setUserPreferencesDAO(ServiceUserPreferencesDAO userPreferencesDAO) {
        this.userPreferencesDAO = userPreferencesDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    public void setProviderService(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceUserPreferencesDto addServiceUserPreference(int serviceUserId, ServiceUserPreferencesForCreationDto serviceUserPreferences) {
        return userPreferencesDAO.addServiceUserPreference(serviceUserId, modelMapper.map(serviceUserPreferences, ServiceUserPreferencesEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceUserPreferencesDto updateServiceUserPreference(ServiceUserPreferencesForUpdateDto serviceUserPreferences) {
        return userPreferencesDAO.updateServiceUserPreference(modelMapper.map(serviceUserPreferences, ServiceUserPreferencesEntity.class));
    }

    @Override
    public ServiceUserPreferencesDto findServiceProviderAndUser(int serviceUser, int serviceProvider) {
        ServiceUserPreferencesDto found = userPreferencesDAO.findServiceProviderAndUser(serviceUser, serviceProvider);
        found.setServiceProvider(serviceProviderService.findServiceProvider(found.getServiceProviderId()));
        return found;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceUserPreference(int id) {
        return userPreferencesDAO.deleteServiceUserPreference(id);
    }

    @Override
    public List<ServiceUserPreferencesDto> listServiceUserPreferences(int serviceUserId)
    {
        List<ServiceUserPreferencesDto> foundList = userPreferencesDAO.listServiceUserPreferences(serviceUserId);
        for(ServiceUserPreferencesDto preference: foundList){
            preference.setServiceProvider(serviceProviderService.findServiceProvider(preference.getServiceProviderId()));
        }
        return foundList;
    }
}

