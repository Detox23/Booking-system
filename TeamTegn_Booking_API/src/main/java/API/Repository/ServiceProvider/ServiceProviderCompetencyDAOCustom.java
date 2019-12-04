package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderCompetencyEntity;
import Shared.ToReturn.ServiceProviderCompetencyDto;

import java.util.List;

public interface ServiceProviderCompetencyDAOCustom {
    List<ServiceProviderCompetencyDto> listServiceProviderCompetencies(boolean showDeleted);

    ServiceProviderCompetencyDto findServiceProviderCompetency(int id);

    ServiceProviderCompetencyDto addServiceProviderCompetency(ServiceProviderCompetencyEntity serviceProviderCompetency);

    ServiceProviderCompetencyDto updateServiceProviderCompetency(ServiceProviderCompetencyEntity serviceProviderCompetency);

    boolean deleteServiceProviderCompetency(int id);
}
