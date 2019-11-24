package API.Services.ServiceProviderService;

import Shared.ForCreation.ServiceProviderTypeForCreationDto;
import Shared.ForCreation.ServiceProviderTypeForUpdateDto;
import Shared.ToReturn.ServiceProviderTypeDto;

import java.util.List;

public interface IServiceProviderTypeService {
    ServiceProviderTypeDto addServiceProviderType(ServiceProviderTypeForCreationDto serviceProviderType);

    ServiceProviderTypeDto updateServiceProviderType(ServiceProviderTypeForUpdateDto serviceProviderType);

    boolean deleteServiceProviderType(int id);

    ServiceProviderTypeDto findServiceProviderType(int id);

    List<ServiceProviderTypeDto> listServiceProviderTypes();

}
