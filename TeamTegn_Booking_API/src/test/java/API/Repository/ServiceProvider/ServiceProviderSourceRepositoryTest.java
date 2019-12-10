package API.Repository.ServiceProvider;

import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderSourceEntity;
import Shared.ToReturn.ServiceProviderSourceDto;
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
public class ServiceProviderSourceRepositoryTest {

    @Autowired
    private ServiceProviderSourceDAO serviceProviderSourceDAO;

    private ServiceProviderSourceEntity serviceProviderSourceOne;

    private void setUp(){
        ServiceProviderSourceEntity serviceProviderSourceEntity = new ServiceProviderSourceEntity();
        serviceProviderSourceEntity.setProviderSource("TestProviderSource");
        serviceProviderSourceEntity.setDeleted(false);
        serviceProviderSourceOne = serviceProviderSourceDAO.save(serviceProviderSourceEntity);
    }

    private void tearDown(){
        serviceProviderSourceDAO.deleteAllInBatch();
        serviceProviderSourceDAO.flush();
    }


    @Test
    public void testAddServiceProviderSourceNameShouldMatch() {
        setUp();
        try {
            ServiceProviderSourceEntity serviceProviderSourceEntity = new ServiceProviderSourceEntity();
            serviceProviderSourceEntity.setProviderSource("TestProviderSourceSecond");
            serviceProviderSourceEntity.setDeleted(false);
            ServiceProviderSourceDto added = serviceProviderSourceDAO.addServiceProviderSource(serviceProviderSourceEntity);
            Assert.assertEquals("TestProviderSourceSecond", serviceProviderSourceDAO.findById(added.getId()).get().getProviderSource());
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
            ServiceProviderSourceEntity serviceProviderSourceEntity = new ServiceProviderSourceEntity();
            serviceProviderSourceEntity.setProviderSource("TestProviderSource");
            serviceProviderSourceEntity.setDeleted(false);
            serviceProviderSourceDAO.addServiceProviderSource(serviceProviderSourceEntity);
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
            Assert.assertEquals("TestProviderSource", serviceProviderSourceDAO.findServiceProviderSource(serviceProviderSourceOne.getId()).getProviderSource());
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
            serviceProviderSourceDAO.findServiceProviderSource(-1);
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
            Assert.assertEquals(1, serviceProviderSourceDAO.listServiceProviderSources(true).size());
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
            serviceProviderSourceDAO.deleteServiceProviderSource(serviceProviderSourceOne.getId());
            Assert.assertEquals(1, serviceProviderSourceDAO.listServiceProviderSources(true).size());
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
            serviceProviderSourceDAO.deleteServiceProviderSource(serviceProviderSourceOne.getId());
            Assert.assertEquals(0, serviceProviderSourceDAO.listServiceProviderSources(false).size());
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
            ServiceProviderSourceEntity serviceProviderSourceEntity = new ServiceProviderSourceEntity();
            serviceProviderSourceEntity.setProviderSource("TestSourceSecond");
            serviceProviderSourceEntity.setDeleted(false);
            ServiceProviderSourceDto added = serviceProviderSourceDAO.addServiceProviderSource(serviceProviderSourceEntity);
            ServiceProviderSourceEntity serviceProviderSourceEntityFound = serviceProviderSourceDAO.findById(added.getId()).get();
            serviceProviderSourceEntityFound.setProviderSource("TestProviderSource");
            serviceProviderSourceDAO.updateServiceProviderSource(serviceProviderSourceEntityFound);
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
            ServiceProviderSourceEntity serviceProviderSourceEntity = new ServiceProviderSourceEntity();
            serviceProviderSourceEntity.setProviderSource("TestSourceSecond");
            serviceProviderSourceEntity.setDeleted(false);
            ServiceProviderSourceDto added = serviceProviderSourceDAO.addServiceProviderSource(serviceProviderSourceEntity);
            ServiceProviderSourceEntity serviceProviderSourceEntityFound = serviceProviderSourceDAO.findById(added.getId()).get();
            serviceProviderSourceEntityFound.setProviderSource("TestSourceUpdated");
            ServiceProviderSourceDto updated = serviceProviderSourceDAO.updateServiceProviderSource(serviceProviderSourceEntityFound);
            Assert.assertEquals("TestSourceUpdated", updated.getProviderSource());
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
            serviceProviderSourceDAO.deleteServiceProviderSource(-1);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
