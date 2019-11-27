package API.Services.ServiceProviderService;

import Shared.ForCreation.ServiceProviderAbsenceForCreationDto;
import Shared.ForCreation.ServiceProviderAbsenceForUpdateDto;
import Shared.ToReturn.ServiceProviderAbsenceDto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface IServiceProviderAbsenceService {
    ServiceProviderAbsenceDto addServiceProviderAbsence(ServiceProviderAbsenceForCreationDto serviceProviderAbsenceEntity);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProvider(int serviceProviderID);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInPeriod(Date startDate, Date endDate, int serviceProviderID);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInPeriod(Date startDate, Date endDate);

    List<ServiceProviderAbsenceDto> listAllServiceProviderAbsences();

    boolean deleteServiceProviderAbsence(int id);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInTime(Time startTime, Time endTime);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInTime(Time startTime, Time endTime,int serviceProviderID);

}
