package API.Services.ServiceProviderService;

import Shared.ForCreation.ServiceProviderEveningWorkForUpdateDto;
import Shared.ToReturn.ServiceProviderEveningWorkDto;

import java.util.List;

public interface IServiceProviderEveningWorkService {
    ServiceProviderEveningWorkDto addOrUpdateServiceProviderEveningWork(ServiceProviderEveningWorkForUpdateDto serviceProviderEveningWork);
    List<ServiceProviderEveningWorkDto> listServiceProviderEveningWork(int serviceProviderID);
    ServiceProviderEveningWorkDto getServiceProviderEveningWorkForSpecificDay(String day, int serviceProviderID);

}
