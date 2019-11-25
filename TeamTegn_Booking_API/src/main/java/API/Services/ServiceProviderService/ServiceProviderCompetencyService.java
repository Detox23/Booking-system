package API.Services.ServiceProviderService;

import API.Exceptions.NotFoundException;
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
    public List<ServiceProviderCompetencyDto> listAllCompetencies() {
        return serviceProviderCompetencyDAO.listAllCompetencies();
    }

    @Override
    public ServiceProviderCompetencyDto getOneCompetency(int id) {
        try {
            return serviceProviderCompetencyDAO.getOneCompetency(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Competency was not found");
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderCompetencyDto addOneCompetency(ServiceProviderCompetencyForCreationDto serviceProviderCompetency) {
        return serviceProviderCompetencyDAO.addOneCompetency(modelMapper.map(serviceProviderCompetency, ServiceProviderCompetencyEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderCompetencyDto updateOneCompetency(ServiceProviderCompetencyForUpdateDto serviceProviderCompetency) {
        return serviceProviderCompetencyDAO.updateOneCompetency(modelMapper.map(serviceProviderCompetency, ServiceProviderCompetencyEntity.class));
    }

    @Override
    @Transactional(noRollbackFor = NoSuchElementException.class, rollbackFor = NotFoundException.class)
    public boolean deleteOneCompetency(int id) {
        return serviceProviderCompetencyDAO.deleteOneCompetency(id);
    }
}
