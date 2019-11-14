package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderCompetencyEntity;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.ServiceProviderCompetencyDto;

import java.util.List;

public interface ServiceProviderCompetencyCustom {
    List<ServiceProviderCompetencyDto> listAllCompetencies();
    ServiceProviderCompetencyDto getOneCompetency(int id);
    ServiceProviderCompetencyDto addOneCompetency(ServiceProviderCompetencyEntity serviceProviderCompetency);
    ServiceProviderCompetencyDto updateOneCompetency(ServiceProviderCompetencyEntity serviceProviderCompetency);
    boolean deleteOneCompetency(int id);
}
