package API.Repository.ServiceProvider;

import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Models.Database_Entities.ServiceProviderAbsenceEntity;
import Shared.ToReturn.ServiceProviderAbsenceDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceProviderAbsenceDAOImpl implements ServiceProviderAbsenceDAOCustom {

    private ModelMapper modelMapper;

    private ServiceProviderAbsenceDAO serviceProviderAbsenceDAO;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderAbsenceDAO(ServiceProviderAbsenceDAO serviceProviderAbsenceDAO) {
        this.serviceProviderAbsenceDAO = serviceProviderAbsenceDAO;
    }

    @Override
    public ServiceProviderAbsenceDto addServiceProviderAbsence(ServiceProviderAbsenceEntity serviceProviderAbsenceEntity) {
        try {
            List<ServiceProviderAbsenceEntity> list = serviceProviderAbsenceDAO.findAllByServiceProviderId(
                    serviceProviderAbsenceEntity.getServiceProviderId()
            );

            LocalDateTime dateTimeFrom = createDateTime(serviceProviderAbsenceEntity.getFromDate(), serviceProviderAbsenceEntity.getFromTime());
            LocalDateTime dateTimeTo = createDateTime(serviceProviderAbsenceEntity.getToDate(), serviceProviderAbsenceEntity.getToTime());

            List<ServiceProviderAbsenceEntity> filtered = new ArrayList<>();
            for (ServiceProviderAbsenceEntity w : list) {
                LocalDateTime dateTimeFromDB = createDateTime(w.getFromDate(), w.getFromTime());
                LocalDateTime dateTimeToDB = createDateTime(w.getToDate(), w.getToTime());
                if (dateTimeFrom.isBefore(dateTimeToDB) && dateTimeFromDB.isBefore(dateTimeTo)) {
                    filtered.add(w);
                }
            }
            if (filtered.size() > 0) {
                throw new DuplicateException("There is already absence registered between the dates for the service provider.");
            }
            serviceProviderAbsenceEntity.setAbsenceHours(calculateHoursFromDates(
                    serviceProviderAbsenceEntity.getFromDate(),
                    serviceProviderAbsenceEntity.getFromTime(),
                    serviceProviderAbsenceEntity.getToDate(),
                    serviceProviderAbsenceEntity.getToTime()));
            ServiceProviderAbsenceEntity saved = serviceProviderAbsenceDAO.save(serviceProviderAbsenceEntity);
            return modelMapper.map(saved, ServiceProviderAbsenceDto.class);

        } catch (ConstraintViolationException constraintViolationException) {
            throw new NotFoundException("The id of service provider or absence type is incorrect");
        } catch (DuplicateException duplicateException) {
            throw new DuplicateException(duplicateException.getMessage());
        } catch (Exception e) {
            throw new UnknownAddingException(e.getMessage());
        }
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProvider(int serviceProviderID) {
        Type listType = new TypeToken<List<ServiceProviderAbsenceDto>>() {
        }.getType();
        return modelMapper.map(serviceProviderAbsenceDAO.findAllByServiceProviderId(serviceProviderID), listType);
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInPeriod(Date startDate, Date endDate, int serviceProviderID) {
        Type listType = new TypeToken<List<ServiceProviderAbsenceDto>>() {
        }.getType();
        return modelMapper.map(
                serviceProviderAbsenceDAO.findAllByFromDateIsAfterAndToDateIsBeforeAndServiceProviderIdIsOrderByFromDateAsc(
                        startDate,
                        endDate,
                        serviceProviderID
                ),
                listType
        );
    }


    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInPeriod(Date startDate, Date endDate) {
        Type listType = new TypeToken<List<ServiceProviderAbsenceDto>>() {
        }.getType();
        return modelMapper.map(serviceProviderAbsenceDAO.findAllByFromDateIsAfterAndToDateIsBefore(
                startDate,
                endDate
        ), listType);
    }

    @Override
    public List<ServiceProviderAbsenceDto> listAllServiceProviderAbsences() {
        Type listType = new TypeToken<List<ServiceProviderAbsenceDto>>() {
        }.getType();
        return modelMapper.map(serviceProviderAbsenceDAO.findAll(), listType);
    }

    @Override
    public boolean deleteServiceProviderAbsence(int id) {
        try {
            Optional<ServiceProviderAbsenceEntity> found = serviceProviderAbsenceDAO.findById(id);
            if (!found.isPresent()) {
                throw new NotFoundException("Absence with ID does not exist.");
            }
            serviceProviderAbsenceDAO.deleteById(id);
            return true;
        } catch (NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInTime(Time startTime, Time endTime) {
        Type listType = new TypeToken<List<ServiceProviderAbsenceDto>>() {
        }.getType();
        return modelMapper.map(serviceProviderAbsenceDAO.findAllByFromTimeIsAfterAndToTimeIsBefore(
                startTime,
                endTime
        ), listType);
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInTime(Time fromTime, Time toTime, int serviceProviderID) {
        Type listType = new TypeToken<List<ServiceProviderAbsenceDto>>() {
        }.getType();
        return modelMapper.map(
                serviceProviderAbsenceDAO.findAllByFromTimeIsAfterAndToTimeIsBeforeAndServiceProviderIdIs(
                        fromTime, toTime, serviceProviderID)
                , listType);
    }


    private float calculateHoursFromDates(Date fromDate, Time fromTime, Date toDate, Time toTime) {
        return (float) (((toDate.getTime() + toTime.getTime()) - (fromDate.getTime() + fromTime.getTime())) / 3600000);
    }

    private LocalDateTime createDateTime(Date date, Time time) {
        LocalDate dateFrom = LocalDate.parse(date.toString());
        LocalTime timeFrom = LocalTime.parse(time.toString());
        return LocalDateTime.of(dateFrom, timeFrom);
    }
}
