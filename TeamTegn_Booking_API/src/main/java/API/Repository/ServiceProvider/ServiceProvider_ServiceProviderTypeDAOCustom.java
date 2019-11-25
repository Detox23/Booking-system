package API.Repository.ServiceProvider;

import Shared.ToReturn.ServiceProviderServiceProviderTypeDto;

import java.util.List;

public interface ServiceProvider_ServiceProviderTypeDAOCustom {
    boolean addServiceProviderServiceProviderType(ServiceProviderServiceProviderTypeEntity serviceProviderServiceProviderTypeEntity);

    List<ServiceProviderServiceProviderTypeDto> listServiceProviderServiceProviderTypes(int serviceProviderID);
}
