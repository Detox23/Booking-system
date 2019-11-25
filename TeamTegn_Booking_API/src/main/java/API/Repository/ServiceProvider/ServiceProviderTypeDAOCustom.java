package API.Repository.ServiceProvider;

import Shared.ToReturn.ServiceProviderTypeDto;

import java.util.List;

public interface ServiceProviderTypeDAOCustom {
    ServiceProviderTypeDto addServiceProviderType(ServiceProviderTypeEntity serviceProviderTypeEntity);

    ServiceProviderTypeDto updateServiceProviderType(ServiceProviderTypeEntity serviceProviderTypeEntity);

    boolean deleteServiceProviderType(int id);

    ServiceProviderTypeDto findServiceProviderType(int id);

    List<ServiceProviderTypeDto> listServiceProviderTypes();
}
