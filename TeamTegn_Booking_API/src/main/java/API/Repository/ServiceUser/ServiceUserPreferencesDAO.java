package API.Repository.ServiceUser;

import API.Models.Database_Entities.ServiceUserPreferencesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceUserPreferencesDAO extends JpaRepository<ServiceUserPreferencesEntity, Integer>, ServiceUserPreferencesDAOCustom {

    List<ServiceUserPreferencesEntity> findAllByServiceUserIdOrderByRatingAsc(int id);

    Optional<ServiceUserPreferencesEntity> findByServiceUserIdAndServiceProviderId(int sUserId, int sProviderId);

    int countAllByServiceUserIdIsAndServiceProviderIdIs(int serviceUserId, int serviceProviderId);


}
