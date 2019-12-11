package API.Service.ServiceProviderService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderPreferredNotificationEntity;
import API.Repository.ServiceProvider.ServiceProviderPreferredNotificationDAO;
import API.Services.ServiceProviderService.ServiceProviderPreferredNotificationService;
import Shared.ForCreation.ServiceProviderPreferredNotificationForCreationDto;
import Shared.ForCreation.ServiceProviderPreferredNotificationForUpdateDto;
import Shared.ToReturn.ServiceProviderPreferredNotificationDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class ServiceProviderPreferredNotificationServiceTest {

    @Autowired
    private ServiceProviderPreferredNotificationService serviceProviderPreferredNotificationService;

    private ServiceProviderPreferredNotificationDto getServiceProviderNotification(){
        ServiceProviderPreferredNotificationDto notificationDto = new ServiceProviderPreferredNotificationDto();
        notificationDto.setNotificationType("Notification");
        return notificationDto;
    }

    private List<ServiceProviderPreferredNotificationDto> getServiceProviderNotificationList(){
        List<ServiceProviderPreferredNotificationDto> returnList = new ArrayList<>();
        returnList.add(new ServiceProviderPreferredNotificationDto());
        returnList.add(new ServiceProviderPreferredNotificationDto());
        returnList.add(new ServiceProviderPreferredNotificationDto());
        returnList.add(new ServiceProviderPreferredNotificationDto());
        returnList.add(new ServiceProviderPreferredNotificationDto());
        return returnList;
    }

    @Test
    public void addServiceProviderNotification(){
        ServiceProviderPreferredNotificationDAO mockServiceProviderNotification = SpringBeanMockUtil.mockFieldOnBean(serviceProviderPreferredNotificationService, ServiceProviderPreferredNotificationDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderNotification()).when(mockServiceProviderNotification).addServiceProviderNotification(any(ServiceProviderPreferredNotificationEntity.class));
        Assert.assertEquals("Notification", serviceProviderPreferredNotificationService.addServiceProviderNotification(new ServiceProviderPreferredNotificationForCreationDto()).getNotificationType());
    }

    @Test
    public void updateServiceProviderNotification(){
        ServiceProviderPreferredNotificationDAO mockServiceProviderNotification = SpringBeanMockUtil.mockFieldOnBean(serviceProviderPreferredNotificationService, ServiceProviderPreferredNotificationDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderNotification()).when(mockServiceProviderNotification).updateServiceProviderNotification(any(ServiceProviderPreferredNotificationEntity.class));
        Assert.assertEquals("Notification", serviceProviderPreferredNotificationService.updateServiceProviderNotification(new ServiceProviderPreferredNotificationForUpdateDto()).getNotificationType());
    }

    @Test
    public void deleteServiceProviderNotification(){
        ServiceProviderPreferredNotificationDAO mockServiceProviderNotification = SpringBeanMockUtil.mockFieldOnBean(serviceProviderPreferredNotificationService, ServiceProviderPreferredNotificationDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceProviderNotification).deleteServiceProviderNotification(anyInt());
        Assert.assertTrue(serviceProviderPreferredNotificationService.deleteServiceProviderNotification(1));
    }

    @Test
    public void findServiceProviderNotification(){
        ServiceProviderPreferredNotificationDAO mockServiceProviderNotification = SpringBeanMockUtil.mockFieldOnBean(serviceProviderPreferredNotificationService, ServiceProviderPreferredNotificationDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderNotification()).when(mockServiceProviderNotification).findServiceProviderNotification(anyInt());
        Assert.assertEquals("Notification", serviceProviderPreferredNotificationService.findServiceProviderNotification(1).getNotificationType());
    }

    @Test
    public void listServiceProviderNotification(){
        ServiceProviderPreferredNotificationDAO mockServiceProviderNotification = SpringBeanMockUtil.mockFieldOnBean(serviceProviderPreferredNotificationService, ServiceProviderPreferredNotificationDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderNotificationList()).when(mockServiceProviderNotification).listServiceProviderNotifications(anyBoolean());
        Assert.assertEquals(5, serviceProviderPreferredNotificationService.listServiceProviderNotifications(true).size());
    }
}
