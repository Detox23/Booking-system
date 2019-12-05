package API.Services.ServiceProviderService;

import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.ServiceProviderForUpdateDto;
import Shared.ToReturn.ServiceProviderDto;

import java.util.List;


public interface IServiceProviderService {
    List<ServiceProviderDto> listAllServiceProviders(boolean showDeleted);

    ServiceProviderDto findServiceProvider(int id);

    ServiceProviderDto addServiceProvider(ServiceProviderForCreationDto serviceProvider);

    ServiceProviderDto updateServiceProvider(ServiceProviderForUpdateDto serviceProvider);

    boolean deleteServiceProvider(int id);

}
