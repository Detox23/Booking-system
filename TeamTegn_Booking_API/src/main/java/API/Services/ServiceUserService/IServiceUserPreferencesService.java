package API.Services.ServiceUserService;

import Shared.ForCreation.ServiceUserPreferencesForCreationDto;
import Shared.ForCreation.ServiceUserPreferencesForUpdateDto;
import Shared.ToReturn.ServiceUserPreferencesDto;

import java.util.List;

public interface IServiceUserPreferencesService {
    ServiceUserPreferencesDto addServiceUserPreference(ServiceUserPreferencesForCreationDto serviceUserPreferences);

    ServiceUserPreferencesDto updateServiceUserPreference(int id, ServiceUserPreferencesForUpdateDto serviceUserPreferences);

    ServiceUserPreferencesDto findServiceProviderAndUser(int serviceProvider, int serviceUser);

    boolean deleteServiceUserPreference(int id);

    List<ServiceUserPreferencesDto> listServiceUserPreferences(int serviceUserId);


}
