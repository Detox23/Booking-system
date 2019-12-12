package API.Repository.ServiceUser;

import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceUserStatusEntity;
import Shared.ToReturn.ServiceUserStatusDto;
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
public class ServiceUserStatusRepositoryTest {
    @Autowired
    private ServiceUserStatusDAO serviceUserStatusDAO;

    private ServiceUserStatusEntity serviceUserStatusOne;

    private void setUp(){
        ServiceUserStatusEntity serviceUserStatusEntity = new ServiceUserStatusEntity();
        serviceUserStatusEntity.setStatus("TestStatus");
        serviceUserStatusEntity.setDeleted(false);
        serviceUserStatusOne = serviceUserStatusDAO.save(serviceUserStatusEntity);
    }

    private void tearDown(){
        serviceUserStatusDAO.deleteAllInBatch();
        serviceUserStatusDAO.flush();
    }

    @Test
    public void testAddServiceProviderSourceNameShouldMatch() {
        setUp();
        try {
            ServiceUserStatusEntity serviceUserStatusEntity = new ServiceUserStatusEntity();
            serviceUserStatusEntity.setStatus("TestStatusSecond");
            serviceUserStatusEntity.setDeleted(false);
            ServiceUserStatusDto added = serviceUserStatusDAO.addServiceUserStatus(serviceUserStatusEntity);
            Assert.assertEquals("TestStatusSecond", serviceUserStatusDAO.findById(added.getId()).get().getStatus());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddServiceProviderSourceWithExistingNameShouldThrowException() {
        setUp();
        try {
            ServiceUserStatusEntity serviceUserStatusEntity = new ServiceUserStatusEntity();
            serviceUserStatusEntity.setStatus("TestStatus");
            serviceUserStatusEntity.setDeleted(false);
            serviceUserStatusDAO.addServiceUserStatus(serviceUserStatusEntity);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindServiceProviderSourceShouldMatch() {
        setUp();
        try {
            Assert.assertEquals("TestStatus", serviceUserStatusDAO.findServiceUserStatus(serviceUserStatusOne.getId()).getStatus());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindServiceProviderSourceWithNotExistingIdShouldThrowException() {
        setUp();
        try {
            serviceUserStatusDAO.findServiceUserStatus(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderSourcesAll(){
        setUp();
        try {
            Assert.assertEquals(1, serviceUserStatusDAO.listServiceUserStatuses(true).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderSourcesAllAfterDeleting(){
        setUp();
        try {
            serviceUserStatusDAO.deleteServiceUserStatus(serviceUserStatusOne.getId());
            Assert.assertEquals(1, serviceUserStatusDAO.listServiceUserStatuses(true).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderSourcesDoNotShowDeleted(){
        setUp();
        try {
            serviceUserStatusDAO.deleteServiceUserStatus(serviceUserStatusOne.getId());
            Assert.assertEquals(0, serviceUserStatusDAO.listServiceUserStatuses(false).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingServiceProviderSourceShouldThrowException(){
        setUp();
        try{
            ServiceUserStatusEntity serviceUserStatusEntity = new ServiceUserStatusEntity();
            serviceUserStatusEntity.setStatus("TestStatusSecond");
            serviceUserStatusEntity.setDeleted(false);
            ServiceUserStatusDto added = serviceUserStatusDAO.addServiceUserStatus(serviceUserStatusEntity);
            ServiceUserStatusEntity serviceUserStatusFound = serviceUserStatusDAO.findById(added.getId()).get();
            serviceUserStatusFound.setStatus("TestStatus");
            serviceUserStatusDAO.updateServiceUserStatus(serviceUserStatusFound);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }


    @Test
    public void testUpdatingServiceProviderSourceNameWasChanged(){
        setUp();
        try{
            ServiceUserStatusEntity serviceUserStatusEntity = new ServiceUserStatusEntity();
            serviceUserStatusEntity.setStatus("TestStatusSecond");
            serviceUserStatusEntity.setDeleted(false);
            ServiceUserStatusDto added = serviceUserStatusDAO.addServiceUserStatus(serviceUserStatusEntity);
            ServiceUserStatusEntity serviceUserStatusFound = serviceUserStatusDAO.findById(added.getId()).get();
            serviceUserStatusFound.setStatus("TestStatusUpdated");
            ServiceUserStatusDto updated =  serviceUserStatusDAO.updateServiceUserStatus(serviceUserStatusFound);
            Assert.assertEquals("TestStatusUpdated", updated.getStatus());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingServiceProviderSourceWithNotExistingId(){
        setUp();
        try{
            serviceUserStatusDAO.deleteServiceUserStatus(-1);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

}
