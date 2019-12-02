package API.Services.ServiceProviderService;

import API.Models.Database_Entities.ServiceProviderEveningWorkEntity;
import API.Repository.ServiceProvider.ServiceProvider_EveningWorkDAO;
import Shared.ForCreation.ServiceProviderEveningWorkForUpdateDto;
import Shared.ToReturn.ServiceProviderEveningWorkDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderEveningWorkService implements IServiceProviderEveningWorkService{

    private ServiceProvider_EveningWorkDAO serviceProviderEveningWorkDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderEveningWorkDAO(ServiceProvider_EveningWorkDAO serviceProviderEveningWorkDAO) {
        this.serviceProviderEveningWorkDAO = serviceProviderEveningWorkDAO;
    }

    @Override
    public ServiceProviderEveningWorkDto addOrUpdateServiceProviderEveningWork(ServiceProviderEveningWorkForUpdateDto serviceProviderEveningWork) {
        return serviceProviderEveningWorkDAO.addOrUpdateServiceProviderEveningWork(modelMapper.map(serviceProviderEveningWork, ServiceProviderEveningWorkEntity.class));
    }

    @Override
    public List<ServiceProviderEveningWorkDto> listServiceProviderEveningWork(int serviceProviderID) {
        return serviceProviderEveningWorkDAO.listServiceProviderEveningWork(serviceProviderID);
    }

    @Override
    public ServiceProviderEveningWorkDto getServiceProviderEveningWorkForSpecificDay(String day, int serviceProviderID) {
        return serviceProviderEveningWorkDAO.getServiceProviderEveningWorkForSpecificDay(day, serviceProviderID);
    }
}
