package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.ServiceProviderAbsenceEntity;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Repository.AbsenceType.AbsenceTypeDAO;
import Shared.ToReturn.ServiceProviderAbsenceDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceProviderAbsenceDAOImpl implements ServiceProviderAbsenceDAOCustom {

    private ModelMapper modelMapper;

    private ServiceProviderAbsenceDAO serviceProviderAbsenceDAO;

    private AbsenceTypeDAO absenceTypeDAO;

    private ServiceProviderDAO serviceProviderDAO;

    private PatcherHandler patcherHandler;

    @Autowired
    public void setServiceProviderDAO(ServiceProviderDAO serviceProviderDAO) {
        this.serviceProviderDAO = serviceProviderDAO;
    }

    @Autowired
    public void setAbsenceTypeDAO(AbsenceTypeDAO absenceTypeDAO) {
        this.absenceTypeDAO = absenceTypeDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderAbsenceDAO(ServiceProviderAbsenceDAO serviceProviderAbsenceDAO) {
        this.serviceProviderAbsenceDAO = serviceProviderAbsenceDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Override
    public ServiceProviderAbsenceDto addServiceProviderAbsence(ServiceProviderAbsenceEntity serviceProviderAbsenceEntity) {
        try {
            if (serviceProviderAbsenceDAO.findAllByFromDateIsGreaterThanEqualAndToDateIsLessThanEqualAndServiceProviderIdIs(
                    serviceProviderAbsenceEntity.getFromDate(),
                    serviceProviderAbsenceEntity.getToDate(),
                    serviceProviderAbsenceEntity.getServiceProviderId()
            ).size() > 0) {
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
        return modelMapper.map(serviceProviderAbsenceDAO.findAllByFromDateIsGreaterThanEqualAndToDateIsLessThanEqualAndServiceProviderIdIs(startDate, endDate, serviceProviderID), listType);
    }


    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInPeriod(Date startDate, Date endDate) {
        Type listType = new TypeToken<List<ServiceProviderAbsenceDto>>() {
        }.getType();
        return modelMapper.map(serviceProviderAbsenceDAO.findAllByFromDateIsGreaterThanEqualAndToDateIsLessThanEqual(
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
        Optional<ServiceProviderAbsenceEntity> found = serviceProviderAbsenceDAO.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException("The absence with ID does not exist.");
        }
        serviceProviderAbsenceDAO.deleteById(id);
        return true;
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInTime(Time startTime, Time endTime) {
        Type listType = new TypeToken<List<ServiceProviderAbsenceDto>>() {
        }.getType();
        return modelMapper.map(serviceProviderAbsenceDAO.findAllByFromTimeIsGreaterThanEqualAndToTimeIsLessThanEqual(
                startTime,
                endTime
        ), listType);
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInTime(Time fromTime, Time toTime, int serviceProviderID) {
        Type listType = new TypeToken<List<ServiceProviderAbsenceDto>>() {
        }.getType();
        return modelMapper.map(
                serviceProviderAbsenceDAO.findAllByFromTimeIsGreaterThanEqualAndToTimeIsLessThanEqualAndServiceProviderId(
                        fromTime, toTime, serviceProviderID)
                , listType);
    }


    private float calculateHoursFromDates(Date fromDate, Time fromTime, Date toDate, Time toTime) {
        return (((toDate.getTime() + toTime.getTime()) - (fromDate.getTime() + fromTime.getTime())) / 3600000);
    }

}
