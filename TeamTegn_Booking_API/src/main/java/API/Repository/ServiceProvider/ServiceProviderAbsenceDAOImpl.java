package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.ServiceProviderAbsenceEntity;
import Shared.ToReturn.ServiceProviderAbsenceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Component
public class ServiceProviderAbsenceDAOImpl implements ServiceProviderAbsenceDAOCustom {

    private ModelMapper modelMapper;

    private ServiceProviderAbsenceDAO serviceProviderAbsenceDAO;

    private PatcherHandler patcherHandler;

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
        return null;
    }

    @Override
    public ServiceProviderAbsenceDto updateServiceProviderAbsence(ServiceProviderAbsenceEntity serviceProviderAbsenceEntity) {
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
