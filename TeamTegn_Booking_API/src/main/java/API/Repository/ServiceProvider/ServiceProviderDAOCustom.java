package API.Repository.ServiceProvider;
import API.Database_Entities.ServiceProviderEntity;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.ServiceProviderDto;

import java.util.List;

public interface ServiceProviderDAOCustom {
    List<ServiceProviderDto> listAll();
    ServiceProviderDto findOne(int id);
    ServiceProviderDto addServiceProvider(ServiceProviderEntity serviceProvider);
    ServiceProviderDto updateServiceProvider(ServiceProviderEntity serviceProvider);
    boolean deleteServiceProvider(int id);

}
