package API.Services.ServiceProviderService;

import API.Database_Entities.ServiceProviderEntity;
import API.Repository.ServiceProvider.ServiceProviderDAO;
import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.ServiceProviderForUpdate;
import Shared.ToReturn.ServiceProviderDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceProviderService implements IServiceProviderService {

    private ServiceProviderDAO serviceProviderDAO;

    private ModelMapper modelMapper;

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
        return serviceProviderDAO.findOne(id);
    }

    @Override
    public ServiceProviderDto addServiceProvider(ServiceProviderForCreationDto serviceProvider) {
        ServiceProviderDto saved = serviceProviderDAO.addServiceProvider(modelMapper.map(serviceProvider, ServiceProviderEntity.class));
        if (saved != null){
            return saved;
        }else{
            return null;
        }
    }

    @Override
    public ServiceProviderDto updateServiceProvider(ServiceProviderForUpdate serviceProvider) {
        return serviceProviderDAO.updateServiceProvider(modelMapper.map(serviceProvider, ServiceProviderEntity.class));
    }

    @Override
    public boolean deleteServiceProvider(int id) {
        return serviceProviderDAO.deleteServiceProvider(id);
    }
}
