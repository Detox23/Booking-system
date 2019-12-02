package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderEveningWorkEntity;
import Shared.ToReturn.ServiceProviderEveningWorkDto;

import java.util.List;

public interface ServiceProvider_EveningWorkDAOCustom {
    ServiceProviderEveningWorkDto addOrUpdateServiceProviderEveningWork(ServiceProviderEveningWorkEntity serviceProviderEveningWork);

    List<ServiceProviderEveningWorkDto> listServiceProviderEveningWork(int serviceProviderID);

    ServiceProviderEveningWorkDto getServiceProviderEveningWorkForSpecificDay(String day, int serviceProviderID);
}
