package API.Services.ServiceUserService;

import Shared.ForCreation.ServiceUserPreferencesForCreationDto;
import Shared.ForCreation.ServiceUserPreferencesForUpdateDto;
import Shared.ToReturn.ServiceUserPreferencesDto;

import java.util.List;

public interface IServiceUserPreferencesService {
    ServiceUserPreferencesDto addServiceUserPreference(int serviceUserId, ServiceUserPreferencesForCreationDto serviceUserPreferences);

    ServiceUserPreferencesDto updateServiceUserPreference(ServiceUserPreferencesForUpdateDto serviceUserPreferences);

    ServiceUserPreferencesDto findServiceProviderAndUser(int serviceUser, int serviceProvider);

    boolean deleteServiceUserPreference(int id);

    List<ServiceUserPreferencesDto> listServiceUserPreferences(int serviceUserId);


}
