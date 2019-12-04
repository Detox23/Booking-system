package API.Repository.ServiceUser;

import API.Models.Database_Entities.ServiceUserPreferencesEntity;
import Shared.ToReturn.ServiceUserPreferencesDto;

import java.util.List;

public interface ServiceUserPreferencesDAOCustom {
    ServiceUserPreferencesDto addServiceUserPreference(ServiceUserPreferencesEntity a);
    boolean deleteServiceUserPreference(int Id);
    List<ServiceUserPreferencesDto> listServiceUserPreferences(int id);
    ServiceUserPreferencesDto findServiceProviderAndUser(int serviceProviderId, int serviceUserId);
    ServiceUserPreferencesDto updateServiceUserPreference(ServiceUserPreferencesEntity a);

}
