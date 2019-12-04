package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderSourceEntity;
import Shared.ToReturn.ServiceProviderSourceDto;

import java.util.List;

public interface ServiceProviderSourceDAOCustom {
    ServiceProviderSourceDto addServiceProviderSource(ServiceProviderSourceEntity serviceProviderSourceEntity);

    ServiceProviderSourceDto updateServiceProviderSource(ServiceProviderSourceEntity serviceProviderSourceEntity);

    ServiceProviderSourceDto findServiceProviderSource(int id);

    List<ServiceProviderSourceDto>listServiceProviderSources(boolean showDeleted);

    boolean deleteServiceProviderSource(int id);
}
