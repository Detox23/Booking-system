package API.Services.ServiceProviderService;

import API.Repository.ServiceProvider.ServiceProviderTypeDAO;
import Shared.ForCreation.ServiceProviderTypeForCreationDto;
import Shared.ForCreation.ServiceProviderTypeForUpdateDto;
import Shared.ToReturn.ServiceProviderTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceProviderTypeService implements IServiceProviderTypeService {

    private ServiceProviderTypeDAO serviceProviderTypeDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setServiceProviderTypeDAO(ServiceProviderTypeDAO serviceProviderTypeDAO) {
        this.serviceProviderTypeDAO = serviceProviderTypeDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderTypeDto addServiceProviderType(ServiceProviderTypeForCreationDto serviceProviderType) {
        return serviceProviderTypeDAO.addServiceProviderType(modelMapper.map(serviceProviderType,
                ServiceProviderTypeEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderTypeDto updateServiceProviderType(ServiceProviderTypeForUpdateDto serviceProviderType) {
        return serviceProviderTypeDAO.updateServiceProviderType(modelMapper.map(serviceProviderType,
                ServiceProviderTypeEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceProviderType(int id) {
        return serviceProviderTypeDAO.deleteServiceProviderType(id);
    }

    @Override
    public ServiceProviderTypeDto findServiceProviderType(int id) {
        return serviceProviderTypeDAO.findServiceProviderType(id);
    }

    @Override
    public List<ServiceProviderTypeDto> listServiceProviderTypes() {
        return serviceProviderTypeDAO.listServiceProviderTypes();
    }
}
