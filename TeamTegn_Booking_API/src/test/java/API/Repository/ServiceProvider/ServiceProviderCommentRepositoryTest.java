package API.Repository.ServiceProvider;

import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.Department.DepartmentDAO;
import Shared.ToReturn.ServiceProviderCommentDto;
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
public class ServiceProviderCommentRepositoryTest {

    @Autowired
    private ServiceProviderDAO serviceProviderDAO;

    @Autowired
    private TransportTypeDAO transportTypeDAO;

    @Autowired
    private ServiceProviderPreferredNotificationDAO serviceProviderPreferredNotificationDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private ServiceProviderSourceDAO serviceProviderSourceDAO;

    @Autowired
    private ServiceProviderCompetencyDAO serviceProviderCompetencyDAO;

    @Autowired
    private ServiceProviderTypeDAO serviceProviderTypeDAO;

    @Autowired
    private ServiceProviderCommentDAO serviceProviderCommentDAO;


    private TransportTypeEntity transportTypeOne;
    private ServiceProviderPreferredNotificationEntity serviceProviderPreferredNotificationOne;
    private DepartmentEntity departmentOne;
    private ServiceProviderEntity serviceProviderOne;
    private ServiceProviderSourceEntity serviceProviderSourceOne;
    private ServiceProviderCompetencyEntity serviceProviderCompetencyOne;
    private ServiceProviderCompetencyEntity serviceProviderCompetencyTwo;
    private ServiceProviderTypeEntity serviceProviderTypeOne;
    private ServiceProviderTypeEntity serviceProviderTypeTwo;
    private ServiceProviderCommentEntity serviceProviderCommentOne;


    private void setUp() {
        ServiceProviderSourceEntity serviceProviderSourceEntity = new ServiceProviderSourceEntity();
        serviceProviderSourceEntity.setProviderSource("TestServiceProviderSource");
        serviceProviderSourceEntity.setDeleted(false);
        serviceProviderSourceOne = serviceProviderSourceDAO.save(serviceProviderSourceEntity);

        TransportTypeEntity transportTypeEntity = new TransportTypeEntity();
        transportTypeEntity.setTransport("TestTransport");
        transportTypeEntity.setDeleted(false);
        transportTypeOne = transportTypeDAO.save(transportTypeEntity);

        ServiceProviderPreferredNotificationEntity providerPreferredNotificationEntity = new ServiceProviderPreferredNotificationEntity();
        providerPreferredNotificationEntity.setNotificationType("TestNotificationType");
        providerPreferredNotificationEntity.setDeleted(false);
        serviceProviderPreferredNotificationOne = serviceProviderPreferredNotificationDAO.save(providerPreferredNotificationEntity);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName("TestDepartmentNameOne");
        departmentEntity.setPostcode("9200");
        departmentOne = departmentDAO.save(departmentEntity);

        ServiceProviderCompetencyEntity serviceProviderCompetencyEntity = new ServiceProviderCompetencyEntity();
        serviceProviderCompetencyEntity.setCompetency("TestCompetency");
        serviceProviderCompetencyEntity.setDeleted(false);
        serviceProviderCompetencyOne = serviceProviderCompetencyDAO.save(serviceProviderCompetencyEntity);

        ServiceProviderCompetencyEntity serviceProviderCompetencyEntity2 = new ServiceProviderCompetencyEntity();
        serviceProviderCompetencyEntity2.setCompetency("TestCompetency2");
        serviceProviderCompetencyEntity2.setDeleted(false);
        serviceProviderCompetencyTwo = serviceProviderCompetencyDAO.save(serviceProviderCompetencyEntity2);


        ServiceProviderTypeEntity serviceProviderTypeEntity = new ServiceProviderTypeEntity();
        serviceProviderTypeEntity.setProviderType("TestProviderType");
        serviceProviderTypeEntity.setDeleted(false);
        serviceProviderTypeOne = serviceProviderTypeDAO.save(serviceProviderTypeEntity);

        ServiceProviderTypeEntity serviceProviderTypeEntity2 = new ServiceProviderTypeEntity();
        serviceProviderTypeEntity2.setProviderType("TestProviderType");
        serviceProviderTypeEntity2.setDeleted(false);
        serviceProviderTypeTwo = serviceProviderTypeDAO.save(serviceProviderTypeEntity2);

        ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
        serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
        serviceProviderEntity.setFirstName("TestFirstName");
        serviceProviderEntity.setLastName("TestLastName");
        serviceProviderEntity.setDepartmentId(departmentOne.getId());
        serviceProviderEntity.setStatus(false);
        serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
        serviceProviderEntity.setTransportId(transportTypeOne.getId());
        serviceProviderOne = serviceProviderDAO.save(serviceProviderEntity);

        ServiceProviderCommentEntity serviceProviderCommentEntity = new ServiceProviderCommentEntity();
        serviceProviderCommentEntity.setCommentText("Comment text test");
        serviceProviderCommentEntity.setServiceProviderId(serviceProviderOne.getId());
        serviceProviderCommentOne = serviceProviderCommentDAO.save(serviceProviderCommentEntity);

    }

    private void tearDown() {
        serviceProviderCommentDAO.deleteAllInBatch();
        serviceProviderCommentDAO.flush();
        serviceProviderDAO.deleteAllInBatch();
        serviceProviderDAO.flush();
        serviceProviderTypeDAO.deleteAllInBatch();
        serviceProviderTypeDAO.flush();
        serviceProviderCompetencyDAO.deleteAllInBatch();
        serviceProviderCompetencyDAO.flush();
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
        serviceProviderPreferredNotificationDAO.deleteAllInBatch();
        serviceProviderPreferredNotificationDAO.flush();
        transportTypeDAO.deleteAllInBatch();
        transportTypeDAO.flush();
        serviceProviderSourceDAO.deleteAllInBatch();
        serviceProviderSourceDAO.flush();
    }

    @Test
    public void addCommentEntityNameShouldMatch() {
        setUp();
        try {
            ServiceProviderCommentEntity serviceProviderCommentEntity = new ServiceProviderCommentEntity();
            serviceProviderCommentEntity.setCommentText("Comment text test second");
            serviceProviderCommentEntity.setServiceProviderId(serviceProviderOne.getId());
            ServiceProviderCommentDto added = serviceProviderCommentDAO.addServiceProviderComment(serviceProviderCommentEntity);
            Assert.assertEquals("Comment text test second", serviceProviderCommentDAO.findById(added.getId()).get().getCommentText());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void deleteCommentListShouldEmpty() {
        setUp();
        try {
            serviceProviderCommentDAO.deleteServiceProviderComment(serviceProviderCommentOne.getId());
            Assert.assertEquals(0, serviceProviderCommentDAO.findAll().size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void listServiceProviderCommentsList() {
        setUp();
        try {
            ServiceProviderCommentEntity serviceProviderCommentEntity = new ServiceProviderCommentEntity();
            serviceProviderCommentEntity.setCommentText("Comment text test second");
            serviceProviderCommentEntity.setServiceProviderId(serviceProviderOne.getId());
            serviceProviderCommentDAO.addServiceProviderComment(serviceProviderCommentEntity);
            Assert.assertEquals(2, serviceProviderCommentDAO.findServiceProviderComments(serviceProviderOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void findServiceProviderCommentTextShouldMatch() {
        setUp();
        try {
            Assert.assertEquals("Comment text test", serviceProviderCommentDAO.findServiceProviderComment(serviceProviderCommentOne.getId()).getCommentText());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void findServiceProviderCommentWithIdThatDoesNotExistShouldThrowException() {
        setUp();
        try {
            serviceProviderCommentDAO.findServiceProviderComment(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void updateServiceProviderCommentNameShouldBeChanged() {
        setUp();
        try {
            serviceProviderCommentOne.setCommentText("Updated text.");
            serviceProviderCommentDAO.updateServiceProviderComment(serviceProviderCommentOne);
            Assert.assertEquals("Updated text.", serviceProviderCommentDAO.findById(serviceProviderCommentOne.getId()).get().getCommentText());
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void updateServiceProviderShouldNotAdd() {
        setUp();
        try {
            serviceProviderCommentOne.setCommentText("Updated text.");
            serviceProviderCommentDAO.updateServiceProviderComment(serviceProviderCommentOne);
            Assert.assertEquals(1, serviceProviderCommentDAO.findAll().size());
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeleteServiceProviderCommentWithNotExistingIdShouldThrowException(){
        setUp();
        try {
            serviceProviderCommentDAO.deleteServiceProviderComment(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

}
