package API.Repository.ServiceProvider;

import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderTypeEntity;
import Shared.ToReturn.ServiceProviderTypeDto;
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
public class ServiceProviderTypeRepositoryTest {

    @Autowired
    private ServiceProviderTypeDAO serviceProviderTypeDAO;

    private ServiceProviderTypeEntity serviceProviderTypeOne;

    private void setUp(){
        ServiceProviderTypeEntity serviceProviderTypeEntity = new ServiceProviderTypeEntity();
        serviceProviderTypeEntity.setProviderType("ProviderTypeTest");
        serviceProviderTypeEntity.setDeleted(false);
        serviceProviderTypeOne = serviceProviderTypeDAO.save(serviceProviderTypeEntity);
    }

    private void tearDown(){
        serviceProviderTypeDAO.deleteAllInBatch();
        serviceProviderTypeDAO.flush();
    }

    @Test
    public void testAddServiceProviderSourceNameShouldMatch() {
        setUp();
        try {
            ServiceProviderTypeEntity serviceProviderTypeEntity = new ServiceProviderTypeEntity();
            serviceProviderTypeEntity.setProviderType("ProviderTypeTestSecond");
            serviceProviderTypeEntity.setDeleted(false);
            ServiceProviderTypeDto added = serviceProviderTypeDAO.addServiceProviderType(serviceProviderTypeEntity);
            Assert.assertEquals("ProviderTypeTestSecond", serviceProviderTypeDAO.findById(added.getId()).get().getProviderType());
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
            ServiceProviderTypeEntity serviceProviderTypeEntity = new ServiceProviderTypeEntity();
            serviceProviderTypeEntity.setProviderType("ProviderTypeTest");
            serviceProviderTypeEntity.setDeleted(false);
            serviceProviderTypeDAO.addServiceProviderType(serviceProviderTypeEntity);
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
            Assert.assertEquals("ProviderTypeTest", serviceProviderTypeDAO.findServiceProviderType(serviceProviderTypeOne.getId()).getProviderType());
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
            serviceProviderTypeDAO.findServiceProviderType(-1);
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
            Assert.assertEquals(1, serviceProviderTypeDAO.listServiceProviderTypes(true).size());
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
            serviceProviderTypeDAO.deleteServiceProviderType(serviceProviderTypeOne.getId());
            Assert.assertEquals(1, serviceProviderTypeDAO.listServiceProviderTypes(true).size());
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
            serviceProviderTypeDAO.deleteServiceProviderType(serviceProviderTypeOne.getId());
            Assert.assertEquals(0, serviceProviderTypeDAO.listServiceProviderTypes(false).size());
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
            ServiceProviderTypeEntity serviceProviderTypeEntity = new ServiceProviderTypeEntity();
            serviceProviderTypeEntity.setProviderType("ProviderTypeTestSecond");
            serviceProviderTypeEntity.setDeleted(false);
            ServiceProviderTypeDto added = serviceProviderTypeDAO.addServiceProviderType(serviceProviderTypeEntity);
            ServiceProviderTypeEntity serviceProviderSourceEntityFound = serviceProviderTypeDAO.findById(added.getId()).get();
            serviceProviderSourceEntityFound.setProviderType("ProviderTypeTest");
            serviceProviderTypeDAO.updateServiceProviderType(serviceProviderSourceEntityFound);
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
            ServiceProviderTypeEntity serviceProviderTypeEntity = new ServiceProviderTypeEntity();
            serviceProviderTypeEntity.setProviderType("ProviderTypeTestSecond");
            serviceProviderTypeEntity.setDeleted(false);
            ServiceProviderTypeDto added = serviceProviderTypeDAO.addServiceProviderType(serviceProviderTypeEntity);
            ServiceProviderTypeEntity serviceProviderSourceEntityFound = serviceProviderTypeDAO.findById(added.getId()).get();
            serviceProviderSourceEntityFound.setProviderType("ProviderTypeTestSecondUpdated");
            ServiceProviderTypeDto updated = serviceProviderTypeDAO.updateServiceProviderType(serviceProviderSourceEntityFound);
            Assert.assertEquals("ProviderTypeTestSecondUpdated", updated.getProviderType());
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
            serviceProviderTypeDAO.deleteServiceProviderType(-1);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
