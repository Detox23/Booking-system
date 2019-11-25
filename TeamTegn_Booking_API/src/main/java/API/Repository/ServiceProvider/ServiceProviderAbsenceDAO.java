package API.Repository.ServiceProvider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ServiceProviderAbsenceDAO extends JpaRepository<ServiceProviderAbsenceEntity, Integer>, ServiceProviderAbsenceDAOCustom {
    List<ServiceProviderAbsenceEntity> findAllByFromDateIsAfterAndToDateIsBeforeAndServiceProviderIdIs(Date fromDate, Date toDate, int serviceProviderID);
    List<ServiceProviderAbsenceEntity> findAllByFromDateIsGreaterThanEqualAndToDateIsLessThanEqualAndServiceProviderIdIs(Date fromDate, Date toDate, int serviceProviderID);
}
