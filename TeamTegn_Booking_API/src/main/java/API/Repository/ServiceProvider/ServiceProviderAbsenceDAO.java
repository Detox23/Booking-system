package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderAbsenceEntity;
import API.Services.ServiceProviderService.ServiceProviderAbsence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface ServiceProviderAbsenceDAO extends JpaRepository<ServiceProviderAbsenceEntity, Integer>, ServiceProviderAbsenceDAOCustom {
    List<ServiceProviderAbsenceEntity> findAllByFromDateIsAfterAndToDateIsBeforeAndServiceProviderIdIsOrderByFromDateAsc(Date fromDate, Date toDate, int serviceProviderID);

    List<ServiceProviderAbsenceEntity> findAllByServiceProviderId(int serviceProviderID);

    List<ServiceProviderAbsenceEntity> findAllByFromDateIsAfterAndToDateIsBefore(Date fromDate, Date toDate);

    List<ServiceProviderAbsenceEntity> findAllByFromTimeIsAfterAndToTimeIsBefore(Time fromTime, Time toTime);

    List<ServiceProviderAbsenceEntity> findAllByFromTimeIsAfterAndToTimeIsBeforeAndServiceProviderIdIs(Time fromTime, Time toTime, int serviceProviderID);
}
