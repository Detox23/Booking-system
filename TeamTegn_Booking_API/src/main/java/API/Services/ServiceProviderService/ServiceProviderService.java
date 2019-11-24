package API.Services.ServiceProviderService;

import API.Database_Entities.ServiceProviderEntity;
import API.Exceptions.NotFoundException;
import API.Repository.ServiceProvider.ServiceProviderDAO;
import API.Repository.ServiceProvider.ServiceProvider_ServiceProviderCompetencyDAO;
import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.ServiceProviderForUpdateDto;
import Shared.ToReturn.ServiceProviderDto;
import Shared.ToReturn.ServiceProviderServiceProviderCompetencyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ServiceProviderService implements IServiceProviderService {

    private ServiceProviderDAO serviceProviderDAO;

    private ModelMapper modelMapper;

    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

    @Autowired
    public void setServiceProviderServiceProviderCompetencyDAO(ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO) {
        this.serviceProviderServiceProviderCompetencyDAO = serviceProviderServiceProviderCompetencyDAO;
    }

    @Autowired
    public void setServiceProviderDAO(ServiceProviderDAO serviceProviderDAO) {
        this.serviceProviderDAO = serviceProviderDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ServiceProviderDto> list() {
        return serviceProviderDAO.listAll();
    }

    @Override
    public ServiceProviderDto findServiceProvider(int id) {
        try {
            ServiceProviderDto found = serviceProviderDAO.findOne(id);
            found.setCompetences(new ArrayList<>());
            List<ServiceProviderServiceProviderCompetencyDto> list = serviceProviderServiceProviderCompetencyDAO.listAllCompetenciesOfServiceProvider(found.getId());
            for(ServiceProviderServiceProviderCompetencyDto item : list) {
                found.getCompetences().add(item.getCompetencyId());
            }
            return found;
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Service provider not found");
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderDto addServiceProvider(ServiceProviderForCreationDto serviceProvider) {
        return serviceProviderDAO.addServiceProvider(modelMapper.map(serviceProvider, ServiceProviderEntity.class),
                serviceProvider.getCompetencies(), serviceProvider.getTypes());
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderDto updateServiceProvider(ServiceProviderForUpdateDto serviceProvider) {
        return serviceProviderDAO.updateServiceProvider(modelMapper.map(serviceProvider, ServiceProviderEntity.class),
                serviceProvider.getCompetencies(), serviceProvider.getTypes());
    }

    @Override
    public boolean deleteServiceProvider(int id) {
        return serviceProviderDAO.deleteServiceProvider(id);
    }

}
