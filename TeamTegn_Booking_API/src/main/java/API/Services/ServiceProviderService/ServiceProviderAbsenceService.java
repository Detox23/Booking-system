package API.Services.ServiceProviderService;

import API.Models.Database_Entities.ServiceProviderAbsenceEntity;
import API.Repository.ServiceProvider.ServiceProviderAbsenceDAO;
import Shared.ForCreation.ServiceProviderAbsenceForCreationDto;
import Shared.ToReturn.ServiceProviderAbsenceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class ServiceProviderAbsenceService implements IServiceProviderAbsenceService {

    private ServiceProviderAbsenceDAO serviceProviderAbsenceDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setServiceProviderAbsenceDAO(ServiceProviderAbsenceDAO serviceProviderAbsenceDAO) {
        this.serviceProviderAbsenceDAO = serviceProviderAbsenceDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderAbsenceDto addServiceProviderAbsence(ServiceProviderAbsenceForCreationDto serviceProviderAbsenceEntity) {
        return serviceProviderAbsenceDAO.addServiceProviderAbsence(modelMapper.map(serviceProviderAbsenceEntity, ServiceProviderAbsenceEntity.class));
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProvider(int serviceProviderID) {
        return serviceProviderAbsenceDAO.findServiceProviderAbsencesForServiceProvider(serviceProviderID);
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInPeriod(Date startDate, Date endDate, int serviceProviderID) {
        return serviceProviderAbsenceDAO.findServiceProviderAbsencesForServiceProviderInPeriod(startDate, endDate, serviceProviderID);
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInPeriod(Date startDate, Date endDate) {
        return serviceProviderAbsenceDAO.findServiceProviderAbsencesInPeriod(startDate, endDate);
    }

    @Override
    public List<ServiceProviderAbsenceDto> listAllServiceProviderAbsences() {
        return serviceProviderAbsenceDAO.listAllServiceProviderAbsences();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceProviderAbsence(int id) {
        return serviceProviderAbsenceDAO.deleteServiceProviderAbsence(id);
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInTime(Time startTime, Time endTime) {
        return serviceProviderAbsenceDAO.findServiceProviderAbsencesInTime(startTime, endTime);
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInTime(Time startTime, Time endTime, int serviceProviderID) {
        return serviceProviderAbsenceDAO.findServiceProviderAbsencesForServiceProviderInTime(startTime, endTime, serviceProviderID);
    }
}
