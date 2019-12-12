package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderEveningWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProvider_EveningWorkDAO extends JpaRepository<ServiceProviderEveningWorkEntity, Integer>, ServiceProvider_EveningWorkDAOCustom {
    ServiceProviderEveningWorkEntity findByWeekDayIsAndServiceProviderIdIs(String weekDay, int serviceProviderID);

    List<ServiceProviderEveningWorkEntity> findAllByWeekDayIsAndServiceProviderIdIs(String weekDay, int serviceProviderID);

    List<ServiceProviderEveningWorkEntity> findAllByServiceProviderIdIs(int serviceProviderID);
}
