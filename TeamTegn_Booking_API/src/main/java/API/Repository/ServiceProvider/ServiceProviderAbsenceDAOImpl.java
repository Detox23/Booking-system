package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.ServiceProviderAbsenceEntity;
import API.Database_Entities.ServiceProviderEntity;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Repository.AbsenceType.AbsenceTypeDAO;
import Shared.ToReturn.ServiceProviderAbsenceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        Optional<AbsenceTypeEntity> foundAbsenceType = absenceTypeDAO.findById(serviceProviderAbsenceEntity.getAbsenceTypeId());
        if(!foundAbsenceType.isPresent()){
            throw new NotFoundException("There is no such absence type id.");
        }
        Optional<ServiceProviderEntity> foundServiceProvider = serviceProviderDAO.findById(serviceProviderAbsenceEntity.getServiceProviderId());
        if(!foundServiceProvider.isPresent()){
            throw new NotFoundException("There is no such service provider with the id in a database.");
        }
        if(serviceProviderAbsenceDAO.findAllByFromDateIsGreaterThanEqualAndToDateIsLessThanEqualAndServiceProviderIdIs(serviceProviderAbsenceEntity.getFromDate(), serviceProviderAbsenceEntity.getToDate(), serviceProviderAbsenceEntity.getServiceProviderId()).size() > 0){
            throw new DuplicateException("There is already absence registered between the dates for the service provider.");
        }
        serviceProviderAbsenceEntity.setAbsenceDays((int)((serviceProviderAbsenceEntity.getToDate().getTime() - serviceProviderAbsenceEntity.getFromDate().getTime())/86400000));
        ServiceProviderAbsenceEntity saved = serviceProviderAbsenceDAO.save(serviceProviderAbsenceEntity);
        if(saved.getId()> 0){
            return modelMapper.map(saved, ServiceProviderAbsenceDto.class);
        }else{
            throw new UnknownAddingException("There was a problem with adding.");
        }
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
