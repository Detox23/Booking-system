package API.Services.ServiceProviderService;

import Shared.ForCreation.ServiceProviderCompetencyForCreationDto;
import Shared.ForCreation.ServiceProviderCompetencyForUpdateDto;
import Shared.ToReturn.ServiceProviderCompetencyDto;

import java.util.List;


public interface IServiceProviderCompetencyService {
    List<ServiceProviderCompetencyDto> listAllCompetencies();

    ServiceProviderCompetencyDto getOneCompetency(int id);

    ServiceProviderCompetencyDto addOneCompetency(ServiceProviderCompetencyForCreationDto serviceProviderCompetency);

    ServiceProviderCompetencyDto updateOneCompetency(ServiceProviderCompetencyForUpdateDto serviceProviderCompetency);

    boolean deleteOneCompetency(int id);
}
