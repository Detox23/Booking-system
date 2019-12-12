package API.Repository.ServiceUser;

import API.Models.Database_Entities.ServiceUserPreferencesEntity;
import Shared.ToReturn.ServiceUserPreferencesDto;

import java.util.List;

public interface ServiceUserPreferencesDAOCustom {
    ServiceUserPreferencesDto addServiceUserPreference(int serviceUserId, ServiceUserPreferencesEntity a);

    boolean deleteServiceUserPreference(int Id);

    List<ServiceUserPreferencesDto> listServiceUserPreferences(int id);

    ServiceUserPreferencesDto findServiceProviderAndUser(int serviceUserId, int serviceProviderId);

    ServiceUserPreferencesDto updateServiceUserPreference(ServiceUserPreferencesEntity a);

}
