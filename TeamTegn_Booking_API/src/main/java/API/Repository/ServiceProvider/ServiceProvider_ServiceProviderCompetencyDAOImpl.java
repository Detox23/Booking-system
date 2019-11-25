package API.Repository.ServiceProvider;

import Shared.ToReturn.ServiceProviderServiceProviderCompetencyDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceProvider_ServiceProviderCompetencyDAOImpl implements ServiceProvider_ServiceProviderCompetencyDAOCustom {

    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

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

    @Autowired
    public void setServiceProviderServiceProviderCompetencyDAO(ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO) {
        this.serviceProviderServiceProviderCompetencyDAO = serviceProviderServiceProviderCompetencyDAO;
    }


    @Override
    public boolean addServiceProviderServiceProviderCompetency(ServiceProviderServiceProviderCompetencyEntity serviceProviderServiceProviderCompetencyEntity) {
        try {
            Optional<ServiceProviderCompetencyEntity> foundCompetency = serviceProviderCompetencyDAO.findById(serviceProviderServiceProviderCompetencyEntity.getCompetencyId());
            if(!foundCompetency.isPresent()){
                return false;
            }
            ServiceProviderServiceProviderCompetencyEntity addingResult = serviceProviderServiceProviderCompetencyDAO.save(serviceProviderServiceProviderCompetencyEntity);
            return addingResult != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ServiceProviderServiceProviderCompetencyDto> listAllCompetenciesOfServiceProvider(int serviceProviderID) {
        Type listType = new TypeToken<List<ServiceProviderServiceProviderCompetencyDto>>() {}.getType();
        return modelMapper.map(serviceProviderServiceProviderCompetencyDAO.findAllByServiceProviderId(serviceProviderID), listType);
    }


}
