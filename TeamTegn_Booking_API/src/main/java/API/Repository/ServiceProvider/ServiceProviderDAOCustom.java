package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderEntity;
import Shared.ToReturn.ServiceProviderDto;

import java.util.List;

public interface ServiceProviderDAOCustom {
    List<ServiceProviderDto> listAllServiceProvider(boolean showDeleted);

    ServiceProviderDto findServiceProvider(int id);

    ServiceProviderDto addServiceProvider(ServiceProviderEntity serviceProvider, List<Integer> competencies, List<Integer> types);

    ServiceProviderDto updateServiceProvider(ServiceProviderEntity serviceProvider, List<Integer> competencies, List<Integer> types);

    boolean deleteServiceProvider(int id);

}
