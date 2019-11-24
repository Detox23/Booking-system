package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderServiceProviderTypeEntity;
import Shared.ToReturn.ServiceProviderServiceProviderTypeDto;

import java.util.List;

public interface ServiceProvider_ServiceProviderTypeCustom {
    boolean addServiceProviderServiceProviderType(ServiceProviderServiceProviderTypeEntity serviceProviderServiceProviderTypeEntity);

    List<ServiceProviderServiceProviderTypeDto> listServiceProviderServiceProviderTypes(int serviceProviderID);
}
