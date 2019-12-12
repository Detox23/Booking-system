package API.Services.ServiceProviderService;


import Shared.ForCreation.ServiceProviderPreferredNotificationForCreationDto;
import Shared.ForCreation.ServiceProviderPreferredNotificationForUpdateDto;
import Shared.ToReturn.ServiceProviderPreferredNotificationDto;

import java.util.List;

public interface IServiceProviderPreferredNotificationService {
    ServiceProviderPreferredNotificationDto addServiceProviderNotification(ServiceProviderPreferredNotificationForCreationDto serviceProviderNotification);

    ServiceProviderPreferredNotificationDto updateServiceProviderNotification(ServiceProviderPreferredNotificationForUpdateDto serviceProviderNotification);

    boolean deleteServiceProviderNotification(int id);

    List<ServiceProviderPreferredNotificationDto> listServiceProviderNotifications(boolean showDelete);

    ServiceProviderPreferredNotificationDto findServiceProviderNotification(int id);
}
