package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderAbsenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderAbsenceDAO extends JpaRepository<ServiceProviderAbsenceEntity, Integer>, ServiceProviderAbsenceDAOCustom {

}
