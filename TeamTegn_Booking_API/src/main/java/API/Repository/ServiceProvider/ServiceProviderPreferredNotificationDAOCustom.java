package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderPreferredNotificationEntity;
import Shared.ToReturn.ServiceProviderPreferredNotificationDto;

import java.util.List;

public interface ServiceProviderPreferredNotificationDAOCustom {
    ServiceProviderPreferredNotificationDto addServiceProviderNotification(ServiceProviderPreferredNotificationEntity serviceProviderNotification);

    ServiceProviderPreferredNotificationDto updateServiceProviderNotification(ServiceProviderPreferredNotificationEntity serviceProviderNotification);

    boolean deleteServiceProviderNotification(int id);

    List<ServiceProviderPreferredNotificationDto> listServiceProviderNotifications();

    ServiceProviderPreferredNotificationDto findServiceProviderNotification(int id);
}
