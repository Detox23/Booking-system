package API.Repository.ServiceUser;

import API.Models.Database_Entities.ServiceUserPreferencesEntity;

import java.util.List;

public interface ServiceUserPreferencesDAOCustom {
    ServiceUserPreferencesEntity add(ServiceUserPreferencesEntity a);
    boolean deleteById(int Id);
    List<ServiceUserPreferencesEntity> findAllByServiceUser(int id);
    ServiceUserPreferencesEntity findByServiceProviderAndUser(int serviceProviderId, int serviceUserId);
    ServiceUserPreferencesEntity update(ServiceUserPreferencesEntity a);

}
