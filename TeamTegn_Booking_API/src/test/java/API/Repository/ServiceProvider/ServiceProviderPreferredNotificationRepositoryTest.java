package API.Repository.ServiceProvider;

import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderPreferredNotificationEntity;
import Shared.ToReturn.ServiceProviderPreferredNotificationDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class ServiceProviderPreferredNotificationRepositoryTest {

    @Autowired
    private ServiceProviderPreferredNotificationDAO serviceProviderPreferredNotificationDAO;

    private ServiceProviderPreferredNotificationEntity serviceProviderPreferredNotificationOne;

    private void setUp(){
        ServiceProviderPreferredNotificationEntity providerPreferredNotificationEntity = new ServiceProviderPreferredNotificationEntity();
        providerPreferredNotificationEntity.setNotificationType("NotificationTypeEntity");
        providerPreferredNotificationEntity.setDeleted(false);
        serviceProviderPreferredNotificationOne = serviceProviderPreferredNotificationDAO.save(providerPreferredNotificationEntity);
    }

    private void tearDown(){
        serviceProviderPreferredNotificationDAO.deleteAllInBatch();
        serviceProviderPreferredNotificationDAO.flush();
    }

    @Test
    public void testAddServiceProviderNotificationNameShouldMatch() {
        setUp();
        try {
            ServiceProviderPreferredNotificationEntity providerPreferredNotificationEntity = new ServiceProviderPreferredNotificationEntity();
            providerPreferredNotificationEntity.setNotificationType("NotificationTypeEntitySecond");
            providerPreferredNotificationEntity.setDeleted(false);
            ServiceProviderPreferredNotificationDto added = serviceProviderPreferredNotificationDAO.addServiceProviderNotification(providerPreferredNotificationEntity);
            Assert.assertEquals("NotificationTypeEntitySecond", serviceProviderPreferredNotificationDAO.findById(added.getId()).get().getNotificationType());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddServiceProviderNotificationWithExistingNameShouldThrowException() {
        setUp();
        try {
            ServiceProviderPreferredNotificationEntity providerPreferredNotificationEntity = new ServiceProviderPreferredNotificationEntity();
            providerPreferredNotificationEntity.setNotificationType("NotificationTypeEntity");
            providerPreferredNotificationEntity.setDeleted(false);
            serviceProviderPreferredNotificationDAO.addServiceProviderNotification(providerPreferredNotificationEntity);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindServiceProviderNotificationShouldMatch() {
        setUp();
        try {
            Assert.assertEquals("NotificationTypeEntity", serviceProviderPreferredNotificationDAO.findServiceProviderNotification(serviceProviderPreferredNotificationOne.getId()).getNotificationType());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindServiceProviderWithNotExistingIdShouldThrowException() {
        setUp();
        try {
            serviceProviderPreferredNotificationDAO.findServiceProviderNotification(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderNotificationsAll(){
        setUp();
        try {
            Assert.assertEquals(1, serviceProviderPreferredNotificationDAO.listServiceProviderNotifications(true).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderNotificationsAllAfterDeleting(){
        setUp();
        try {
            serviceProviderPreferredNotificationDAO.deleteServiceProviderNotification(serviceProviderPreferredNotificationOne.getId());
            Assert.assertEquals(1, serviceProviderPreferredNotificationDAO.listServiceProviderNotifications(true).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderNotificationsDoNotShowDeleted(){
        setUp();
        try {
            serviceProviderPreferredNotificationDAO.deleteServiceProviderNotification(serviceProviderPreferredNotificationOne.getId());
            Assert.assertEquals(0, serviceProviderPreferredNotificationDAO.listServiceProviderNotifications(false).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingServiceProviderNotificationShouldThrowException(){
        setUp();
        try{
            ServiceProviderPreferredNotificationEntity providerPreferredNotificationEntity = new ServiceProviderPreferredNotificationEntity();
            providerPreferredNotificationEntity.setNotificationType("TestNotificationSecond");
            providerPreferredNotificationEntity.setDeleted(false);
            ServiceProviderPreferredNotificationDto added = serviceProviderPreferredNotificationDAO.addServiceProviderNotification(providerPreferredNotificationEntity);
            ServiceProviderPreferredNotificationEntity serviceProviderNotificationEntityFound = serviceProviderPreferredNotificationDAO.findById(added.getId()).get();
            serviceProviderNotificationEntityFound.setNotificationType("NotificationTypeEntity");
            serviceProviderPreferredNotificationDAO.updateServiceProviderNotification(serviceProviderNotificationEntityFound);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }


    @Test
    public void testUpdatingServiceProviderNotificationNameWasChanged(){
        setUp();
        try{
            ServiceProviderPreferredNotificationEntity providerPreferredNotificationEntity = new ServiceProviderPreferredNotificationEntity();
            providerPreferredNotificationEntity.setNotificationType("TestNotificationSecond");
            providerPreferredNotificationEntity.setDeleted(false);
            ServiceProviderPreferredNotificationDto added = serviceProviderPreferredNotificationDAO.addServiceProviderNotification(providerPreferredNotificationEntity);
            ServiceProviderPreferredNotificationEntity serviceProviderNotificationEntityFound = serviceProviderPreferredNotificationDAO.findById(added.getId()).get();
            serviceProviderNotificationEntityFound.setNotificationType("NotificationTypeEntityUpdated");
            ServiceProviderPreferredNotificationDto updated = serviceProviderPreferredNotificationDAO.updateServiceProviderNotification(serviceProviderNotificationEntityFound);
            Assert.assertEquals("NotificationTypeEntityUpdated", updated.getNotificationType());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingServiceProviderNotificationWithNotExistingId(){
        setUp();
        try{
            serviceProviderPreferredNotificationDAO.deleteServiceProviderNotification(-1);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

}
