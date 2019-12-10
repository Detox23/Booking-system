package API.Repository.ServiceProvider;

import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderCompetencyEntity;
import Shared.ToReturn.ServiceProviderCompetencyDto;
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
public class ServiceProviderCompetencyRepositoryTest {

    @Autowired
    private ServiceProviderCompetencyDAO serviceProviderCompetencyDAO;

    private ServiceProviderCompetencyEntity serviceProviderCompetencyOne;

    private void setUp() {
        ServiceProviderCompetencyEntity serviceProviderCompetencyEntity = new ServiceProviderCompetencyEntity();
        serviceProviderCompetencyEntity.setCompetency("TestCompetency");
        serviceProviderCompetencyEntity.setDeleted(false);
        serviceProviderCompetencyOne = serviceProviderCompetencyDAO.save(serviceProviderCompetencyEntity);
    }

    private void tearDown() {
        serviceProviderCompetencyDAO.deleteAllInBatch();
        serviceProviderCompetencyDAO.flush();
    }

    @Test
    public void testAddServiceProviderCompetencyNameShouldMatch() {
        setUp();
        try {
            ServiceProviderCompetencyEntity serviceProviderCompetencyEntity = new ServiceProviderCompetencyEntity();
            serviceProviderCompetencyEntity.setCompetency("TestCompetencySecond");
            serviceProviderCompetencyEntity.setDeleted(false);
            ServiceProviderCompetencyDto added = serviceProviderCompetencyDAO.addServiceProviderCompetency(serviceProviderCompetencyEntity);
            Assert.assertEquals("TestCompetencySecond", serviceProviderCompetencyDAO.findById(added.getId()).get().getCompetency());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddServiceProviderCompetencyWithExistingNameShouldThrowException() {
        setUp();
        try {
            ServiceProviderCompetencyEntity serviceProviderCompetencyEntity = new ServiceProviderCompetencyEntity();
            serviceProviderCompetencyEntity.setCompetency("TestCompetency");
            serviceProviderCompetencyEntity.setDeleted(false);
            serviceProviderCompetencyDAO.addServiceProviderCompetency(serviceProviderCompetencyEntity);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindServiceProviderNameShouldMatch() {
        setUp();
        try {
            Assert.assertEquals("TestCompetency", serviceProviderCompetencyDAO.findServiceProviderCompetency(serviceProviderCompetencyOne.getId()).getCompetency());
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
            serviceProviderCompetencyDAO.findServiceProviderCompetency(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderCompetenciesAll(){
        setUp();
        try {
            Assert.assertEquals(1, serviceProviderCompetencyDAO.listServiceProviderCompetencies(true).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderCompetenciesAllAfterDeleting(){
        setUp();
        try {
            serviceProviderCompetencyDAO.deleteServiceProviderCompetency(serviceProviderCompetencyOne.getId());
            Assert.assertEquals(1, serviceProviderCompetencyDAO.listServiceProviderCompetencies(true).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceProviderCompetenciesDoNotShowDeleted(){
        setUp();
        try {
            serviceProviderCompetencyDAO.deleteServiceProviderCompetency(serviceProviderCompetencyOne.getId());
            Assert.assertEquals(0, serviceProviderCompetencyDAO.listServiceProviderCompetencies(false).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingServiceProviderCompetencyShouldThrowException(){
        setUp();
        try{
            ServiceProviderCompetencyEntity serviceProviderCompetencyEntity = new ServiceProviderCompetencyEntity();
            serviceProviderCompetencyEntity.setCompetency("TestCompetencySecond");
            serviceProviderCompetencyEntity.setDeleted(false);
            ServiceProviderCompetencyDto added = serviceProviderCompetencyDAO.addServiceProviderCompetency(serviceProviderCompetencyEntity);
            ServiceProviderCompetencyEntity serviceProviderCompetencyEntityFound = serviceProviderCompetencyDAO.findById(added.getId()).get();
            serviceProviderCompetencyEntityFound.setCompetency("TestCompetency");
            serviceProviderCompetencyDAO.updateServiceProviderCompetency(serviceProviderCompetencyEntityFound);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }


    @Test
    public void testUpdatingServiceProviderCompetencyNameWasChanged(){
        setUp();
        try{
            ServiceProviderCompetencyEntity serviceProviderCompetencyEntity = new ServiceProviderCompetencyEntity();
            serviceProviderCompetencyEntity.setCompetency("TestCompetencySecond");
            serviceProviderCompetencyEntity.setDeleted(false);
            ServiceProviderCompetencyDto added = serviceProviderCompetencyDAO.addServiceProviderCompetency(serviceProviderCompetencyEntity);
            ServiceProviderCompetencyEntity serviceProviderCompetencyEntityFound = serviceProviderCompetencyDAO.findById(added.getId()).get();
            serviceProviderCompetencyEntityFound.setCompetency("TestCompetencyUpdated");
            ServiceProviderCompetencyDto updated = serviceProviderCompetencyDAO.updateServiceProviderCompetency(serviceProviderCompetencyEntityFound);
            Assert.assertEquals("TestCompetencyUpdated", updated.getCompetency());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingServiceProviderCompetencyWithNotExistingId(){
        setUp();
        try{
            serviceProviderCompetencyDAO.deleteServiceProviderCompetency(-1);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
