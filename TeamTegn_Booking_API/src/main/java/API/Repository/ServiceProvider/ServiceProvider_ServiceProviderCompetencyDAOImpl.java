package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderServiceProviderCompetencyEntity;
import Shared.ToReturn.ServiceProviderServiceProviderCompetencyDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.lang.reflect.Type;
import java.util.List;

@Component
public class ServiceProvider_ServiceProviderCompetencyDAOImpl implements ServiceProvider_ServiceProviderCompetencyCustom {

    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderServiceProviderCompetencyDAO(ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO) {
        this.serviceProviderServiceProviderCompetencyDAO = serviceProviderServiceProviderCompetencyDAO;
    }


    @Override
    public boolean addServiceProvider_ServiceProviderCompetency(ServiceProviderServiceProviderCompetencyEntity serviceProviderServiceProviderCompetencyEntity) {
        try {
            ServiceProviderServiceProviderCompetencyEntity result = serviceProviderServiceProviderCompetencyDAO.save(serviceProviderServiceProviderCompetencyEntity);
            if(result != null) {
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteServiceProvider_ServiceProviderCompetency(int serviceProviderID, int competency) {
        return false;
    }

    @Override
    public List<ServiceProviderServiceProviderCompetencyDto> listAllCompetenciesOfServiceProvider(int serviceProviderID) {
        Type listType = new TypeToken<List<ServiceProviderServiceProviderCompetencyDto>>() {}.getType();
        return modelMapper.map(serviceProviderServiceProviderCompetencyDAO.findByServiceProviderId(serviceProviderID), listType);
    }


}
