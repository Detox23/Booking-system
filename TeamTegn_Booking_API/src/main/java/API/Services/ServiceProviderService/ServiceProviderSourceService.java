package API.Services.ServiceProviderService;

import API.Database_Entities.ServiceProviderSourceEntity;
import API.Exceptions.DeletionException;
import API.Repository.ServiceProvider.ServiceProviderSourceDAO;
import Shared.ForCreation.ServiceProviderSourceForCreationDto;
import Shared.ForCreation.ServiceProviderSourceForUpdateDto;
import Shared.ToReturn.ServiceProviderSourceDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceProviderSourceService implements IServiceProviderSourceService {

    private ServiceProviderSourceDAO serviceProviderSourceDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderSourceDAO(ServiceProviderSourceDAO serviceProviderSourceDAO) {
        this.serviceProviderSourceDAO = serviceProviderSourceDAO;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderSourceDto addServiceProviderSource(ServiceProviderSourceForCreationDto serviceProviderSource) {
        return serviceProviderSourceDAO.addServiceProviderSource(modelMapper.map(serviceProviderSource,
                ServiceProviderSourceEntity.class));
    }

    @Override
    public ServiceProviderSourceDto findServiceProviderSource(int id) {
        return serviceProviderSourceDAO.findServiceProviderSource(id);
    }

    @Override
    public List<ServiceProviderSourceDto> listServiceProviderSources() {
        return modelMapper.map(serviceProviderSourceDAO.listServiceProviderSources(),
                new TypeToken<List<ServiceProviderSourceDto>>() {}.getType());
    }

    @Override
    @Transactional(rollbackFor = DeletionException.class)
    public boolean deleteServiceProviderSource(int id) {
        return serviceProviderSourceDAO.deleteServiceProviderSource(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderSourceDto updateServiceProviderSource(ServiceProviderSourceForUpdateDto serviceProviderSource) {
        return serviceProviderSourceDAO.updateServiceProviderSource(modelMapper.map(serviceProviderSource,
                ServiceProviderSourceEntity.class));
    }

}
