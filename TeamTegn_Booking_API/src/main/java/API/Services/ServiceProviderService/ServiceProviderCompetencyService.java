package API.Services.ServiceProviderService;

import API.Exceptions.NotFoundException;
import API.Models.Database_Entities.ServiceProviderCompetencyEntity;
import API.Repository.ServiceProvider.ServiceProviderCompetencyDAO;
import Shared.ForCreation.ServiceProviderCompetencyForCreationDto;
import Shared.ForCreation.ServiceProviderCompetencyForUpdateDto;
import Shared.ToReturn.ServiceProviderCompetencyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ServiceProviderCompetencyService implements IServiceProviderCompetencyService {

    private ServiceProviderCompetencyDAO serviceProviderCompetencyDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setServiceProviderCompetencyDAO(ServiceProviderCompetencyDAO serviceProviderCompetencyDAO) {
        this.serviceProviderCompetencyDAO = serviceProviderCompetencyDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ServiceProviderCompetencyDto> listServiceProviderCompetencies(boolean showDeleted ) {
        return serviceProviderCompetencyDAO.listServiceProviderCompetencies(showDeleted);
    }

    @Override
    public ServiceProviderCompetencyDto findServiceProviderCompetency(int id) {
        try {
            return serviceProviderCompetencyDAO.findServiceProviderCompetency(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Competency was not found");
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderCompetencyDto addServiceProviderCompetency(ServiceProviderCompetencyForCreationDto serviceProviderCompetency) {
        return serviceProviderCompetencyDAO.addServiceProviderCompetency(modelMapper.map(serviceProviderCompetency, ServiceProviderCompetencyEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderCompetencyDto updateServiceProviderCompetency(ServiceProviderCompetencyForUpdateDto serviceProviderCompetency) {
        return serviceProviderCompetencyDAO.updateServiceProviderCompetency(modelMapper.map(serviceProviderCompetency, ServiceProviderCompetencyEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceProviderCompetency(int id) {
        return serviceProviderCompetencyDAO.deleteServiceProviderCompetency(id);
    }
}
