package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderServiceProviderTypeEntity;
import Shared.ToReturn.ServiceProviderServiceProviderTypeDto;

import java.util.List;

public interface ServiceProvider_ServiceProviderTypeDAOCustom {
    boolean addServiceProviderServiceProviderType(ServiceProviderServiceProviderTypeEntity serviceProviderServiceProviderTypeEntity);

    List<ServiceProviderServiceProviderTypeDto> listServiceProviderServiceProviderTypes(int serviceProviderID);
}
