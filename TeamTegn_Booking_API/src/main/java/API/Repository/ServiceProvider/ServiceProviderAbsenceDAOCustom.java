package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderAbsenceEntity;
import Shared.ToReturn.ServiceProviderAbsenceDto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface ServiceProviderAbsenceDAOCustom {
    ServiceProviderAbsenceDto addServiceProviderAbsence(ServiceProviderAbsenceEntity serviceProviderAbsenceEntity);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProvider(int serviceProviderID);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInPeriod(Date startDate, Date endDate, int serviceProviderID);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInPeriod(Date startDate, Date endDate);

    List<ServiceProviderAbsenceDto> listAllServiceProviderAbsences();

    boolean deleteServiceProviderAbsence(int id);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInTime(Time startTime, Time endTime);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInTime(Time fromTime, Time toTime, int serviceProviderID);

}
