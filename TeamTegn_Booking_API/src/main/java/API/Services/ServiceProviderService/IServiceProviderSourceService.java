package API.Services.ServiceProviderService;

import Shared.ForCreation.ServiceProviderSourceForCreationDto;
import Shared.ForCreation.ServiceProviderSourceForUpdateDto;
import Shared.ToReturn.ServiceProviderSourceDto;

import java.util.List;


public interface IServiceProviderSourceService {
    ServiceProviderSourceDto addServiceProviderSource(ServiceProviderSourceForCreationDto serviceProviderSource);
    ServiceProviderSourceDto findServiceProviderSource(int id);
    List<ServiceProviderSourceDto> listServiceProviderSources();
    boolean deleteServiceProviderSource(int id);
    ServiceProviderSourceDto updateServiceProviderSource(ServiceProviderSourceForUpdateDto serviceProviderSource);
}
