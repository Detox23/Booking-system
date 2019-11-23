package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderSourceEntity;
import Shared.ToReturn.ServiceProviderSourceDto;

import java.util.List;

public interface ServiceProviderSourceCustom {
    ServiceProviderSourceDto addServiceProviderSource(ServiceProviderSourceEntity serviceProviderSourceEntity);

    ServiceProviderSourceDto updateServiceProviderSource(ServiceProviderSourceEntity serviceProviderSourceEntity);

    ServiceProviderSourceDto findServiceProviderSource(int id);

    List<ServiceProviderSourceDto>listServiceProviderSources();

    boolean deleteServiceProviderSource(int id);
}
