package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderPreferredNotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderPreferredNotificationDAO extends
        JpaRepository<ServiceProviderPreferredNotificationEntity, Integer>,
        ServiceProviderPreferredNotificationDAOCustom {

    List<ServiceProviderPreferredNotificationEntity> findAllByDeletedIsFalse();
    Optional<ServiceProviderPreferredNotificationEntity> findByNotificationTypeAndDeletedIsFalse(String notificationType);
}
