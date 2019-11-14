package API.Services.ServiceProviderService;

import API.Exceptions.UpdatePatchException;
import Shared.ForCreation.ServiceProviderCompetencyForCreationDto;
import Shared.ForCreation.ServiceProviderCompetencyForUpdateDto;
import Shared.ToReturn.ServiceProviderCompetencyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IServiceProviderCompetencyService {
    List<ServiceProviderCompetencyDto> listAllCompetencies();
    ServiceProviderCompetencyDto getOneCompetency(int id);
    ServiceProviderCompetencyDto addOneCompetency(ServiceProviderCompetencyForCreationDto serviceProviderCompetency);
    ServiceProviderCompetencyDto updateOneCompetency(ServiceProviderCompetencyForUpdateDto serviceProviderCompetency);
    boolean deleteOneCompetency(int id);
}
