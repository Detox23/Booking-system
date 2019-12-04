package API.Services.ServiceProviderService;

import Shared.ForCreation.ServiceProviderCompetencyForCreationDto;
import Shared.ForCreation.ServiceProviderCompetencyForUpdateDto;
import Shared.ToReturn.ServiceProviderCompetencyDto;

import java.util.List;


public interface IServiceProviderCompetencyService {
    List<ServiceProviderCompetencyDto> listServiceProviderCompetencies(boolean showDeleted);

    ServiceProviderCompetencyDto findServiceProviderCompetency(int id);

    ServiceProviderCompetencyDto addServiceProviderCompetency(ServiceProviderCompetencyForCreationDto serviceProviderCompetency);

    ServiceProviderCompetencyDto updateServiceProviderCompetency(ServiceProviderCompetencyForUpdateDto serviceProviderCompetency);

    boolean deleteServiceProviderCompetency(int id);
}
