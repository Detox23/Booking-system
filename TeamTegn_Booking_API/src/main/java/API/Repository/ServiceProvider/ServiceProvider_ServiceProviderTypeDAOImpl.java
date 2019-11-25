package API.Repository.ServiceProvider;


import Shared.ToReturn.ServiceProviderServiceProviderTypeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceProvider_ServiceProviderTypeDAOImpl implements ServiceProvider_ServiceProviderTypeDAOCustom {

    private ModelMapper modelMapper;

    private ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO;

    private ServiceProviderTypeDAO serviceProviderTypeDAO;

    @Autowired
    public void setServiceProviderTypeDAO(ServiceProviderTypeDAO serviceProviderTypeDAO) {
        this.serviceProviderTypeDAO = serviceProviderTypeDAO;
    }

    @Autowired
    public void setServiceProviderServiceProviderTypeDAO(ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO) {
        this.serviceProviderServiceProviderTypeDAO = serviceProviderServiceProviderTypeDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addServiceProviderServiceProviderType(ServiceProviderServiceProviderTypeEntity serviceProviderServiceProviderTypeEntity) {
        try {
            Optional<ServiceProviderTypeEntity> foundResult = serviceProviderTypeDAO.findById(serviceProviderServiceProviderTypeEntity.getServiceProviderTypeId());
            if(!foundResult.isPresent()){
                return false;
            }
            ServiceProviderServiceProviderTypeEntity addingResult = serviceProviderServiceProviderTypeDAO.save(serviceProviderServiceProviderTypeEntity);
            return addingResult != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ServiceProviderServiceProviderTypeDto> listServiceProviderServiceProviderTypes(int serviceProviderID) {
        Type listType = new TypeToken<List<ServiceProviderServiceProviderTypeDto>>() {}.getType();
        return modelMapper.map(serviceProviderServiceProviderTypeDAO.findAllByServiceProviderId(serviceProviderID), listType);
    }
}
