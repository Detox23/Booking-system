package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderServiceProviderCompetencyEntity;
import Shared.ToReturn.ServiceProviderServiceProviderCompetencyDto;

import java.util.List;

public interface ServiceProvider_ServiceProviderCompetencyCustom {
    boolean addServiceProvider_ServiceProviderCompetency(ServiceProviderServiceProviderCompetencyEntity serviceProviderServiceProviderCompetencyEntity);
    boolean deleteServiceProvider_ServiceProviderCompetency(int serviceProviderID, int competency);
    List<ServiceProviderServiceProviderCompetencyDto> listAllCompetenciesOfServiceProvider(int serviceProviderID);
}
