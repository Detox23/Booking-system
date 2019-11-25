package API.Services.ServiceProviderService;

import Shared.ForCreation.ServiceProviderAbsenceForCreationDto;
import Shared.ForCreation.ServiceProviderAbsenceForUpdateDto;
import Shared.ToReturn.ServiceProviderAbsenceDto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface IServiceProviderAbsence {
    ServiceProviderAbsenceDto addServiceProviderAbsence(ServiceProviderAbsenceForCreationDto serviceProviderAbsenceEntity);

    ServiceProviderAbsenceDto updateServiceProviderAbsence(ServiceProviderAbsenceForUpdateDto serviceProviderAbsenceEntity);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProvider(int serviceProviderID);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInPeriod(int serviceProviderID, Date startDate, Date endDate);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInPeriod(Date startDate, Date endDate);

    List<ServiceProviderAbsenceDto> listAllServiceProviderAbsences();

    boolean deleteServiceProviderAbsence(int id);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInTime(Time startTime, Time endTime);

    List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInTime(int serviceProviderID, Time startTime, Time endTime);

}
