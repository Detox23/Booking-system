package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderServiceProviderCompetencyEntity;
import Shared.ToReturn.ServiceProviderServiceProviderCompetencyDto;

import java.util.List;

public interface ServiceProvider_ServiceProviderCompetencyDAOCustom {
    boolean addServiceProviderServiceProviderCompetency(ServiceProviderServiceProviderCompetencyEntity serviceProviderServiceProviderCompetencyEntity);

    List<ServiceProviderServiceProviderCompetencyDto> listAllCompetenciesOfServiceProvider(int serviceProviderID);
}
