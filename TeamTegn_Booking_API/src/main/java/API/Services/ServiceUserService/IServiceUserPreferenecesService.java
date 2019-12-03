package API.Services.ServiceUserService;

import Shared.ForCreation.ServiceUserPreferencesForCreationDto;
import Shared.ForCreation.ServiceUserPreferenesForUpdateDto;
import Shared.ToReturn.ServiceUserPreferencesDto;

import java.util.List;

public interface IServiceUserPreferenecesService {
    ServiceUserPreferencesDto add(ServiceUserPreferencesForCreationDto prefferencesForCreationDto);

    ServiceUserPreferencesDto update(int id, ServiceUserPreferenesForUpdateDto pereferencesForUpdate);

    ServiceUserPreferencesDto findByServiceProviderAndUser(int serviceProvider, int serviceUser);

    boolean delete(int id);

    List<ServiceUserPreferencesDto> findAllByServiceUser(int serviceUserId);


}
