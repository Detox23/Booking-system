package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderEntity;
import Shared.ToReturn.ServiceProviderDto;

import java.util.List;

public interface ServiceProviderDAOCustom {
    List<ServiceProviderDto> listAll();

    ServiceProviderDto findOne(int id);

    ServiceProviderDto addServiceProvider(ServiceProviderEntity serviceProvider, List<Integer> competencies);

    ServiceProviderDto updateServiceProvider(ServiceProviderEntity serviceProvider, List<Integer> competencies);

    boolean deleteServiceProvider(int id);

}
