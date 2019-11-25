package API.Services.ServiceProviderService;

import API.Database_Entities.ServiceProviderAbsenceEntity;
import API.Repository.ServiceProvider.ServiceProviderAbsenceDAO;
import Shared.ForCreation.ServiceProviderAbsenceForCreationDto;
import Shared.ForCreation.ServiceProviderAbsenceForUpdateDto;
import Shared.ToReturn.ServiceProviderAbsenceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class ServiceProviderAbsence implements IServiceProviderAbsence {

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
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderAbsenceDto updateServiceProviderAbsence(ServiceProviderAbsenceForUpdateDto serviceProviderAbsenceEntity) {
        return null;
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProvider(int serviceProviderID) {
        return null;
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInPeriod(int serviceProviderID, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInPeriod(Date startDate, Date endDate) {
        return null;
    }

    @Override
    public List<ServiceProviderAbsenceDto> listAllServiceProviderAbsences() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceProviderAbsence(int id) {
        return false;
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesInTime(Time startTime, Time endTime) {
        return null;
    }

    @Override
    public List<ServiceProviderAbsenceDto> findServiceProviderAbsencesForServiceProviderInTime(int serviceProviderID, Time startTime, Time endTime) {
        return null;
    }


}
