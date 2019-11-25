package API.Repository.ServiceProvider;

import Shared.ToReturn.ServiceProviderCompetencyDto;

import java.util.List;

public interface ServiceProviderCompetencyDAOCustom {
    List<ServiceProviderCompetencyDto> listAllCompetencies();

    ServiceProviderCompetencyDto getOneCompetency(int id);

    ServiceProviderCompetencyDto addOneCompetency(ServiceProviderCompetencyEntity serviceProviderCompetency);

    ServiceProviderCompetencyDto updateOneCompetency(ServiceProviderCompetencyEntity serviceProviderCompetency);

    boolean deleteOneCompetency(int id);
}
