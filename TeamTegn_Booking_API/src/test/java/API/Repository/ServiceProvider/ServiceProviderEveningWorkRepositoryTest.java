package API.Repository.ServiceProvider;

import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
import API.Repository.Department.DepartmentDAO;
import API.Repository.EveningWorkPrioritisation.EveningWorkPrioritisationDAO;
import Shared.ToReturn.ServiceProviderEveningWorkDto;
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
public class ServiceProviderEveningWorkRepositoryTest {

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
    private ServiceProvider_EveningWorkDAO serviceProviderEveningWorkDAO;

    @Autowired
    private EveningWorkPrioritisationDAO eveningWorkPrioritisationDAO;

    private TransportTypeEntity transportTypeOne;
    private ServiceProviderPreferredNotificationEntity serviceProviderPreferredNotificationOne;
    private DepartmentEntity departmentOne;
    private ServiceProviderEntity serviceProviderOne;
    private ServiceProviderSourceEntity serviceProviderSourceOne;
    private ServiceProviderCompetencyEntity serviceProviderCompetencyOne;
    private ServiceProviderCompetencyEntity serviceProviderCompetencyTwo;
    private ServiceProviderTypeEntity serviceProviderTypeOne;
    private ServiceProviderTypeEntity serviceProviderTypeTwo;
    private ServiceProviderEveningWorkEntity serviceProviderEveningWorkOne;
    private EveningWorkPrioritisationEntity eveningWorkPrioritisationOne;
    private EveningWorkPrioritisationEntity eveningWorkPrioritisationTwo;

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

        EveningWorkPrioritisationEntity eveningWorkPrioritisationEntity = new EveningWorkPrioritisationEntity();
        eveningWorkPrioritisationEntity.setPrioritisation("TestPrioritisation");
        eveningWorkPrioritisationEntity.setDeleted(false);
        eveningWorkPrioritisationOne = eveningWorkPrioritisationDAO.save(eveningWorkPrioritisationEntity);

        EveningWorkPrioritisationEntity eveningWorkPrioritisationEntity2 = new EveningWorkPrioritisationEntity();
        eveningWorkPrioritisationEntity2.setPrioritisation("TestPrioritisation2");
        eveningWorkPrioritisationEntity2.setDeleted(false);
        eveningWorkPrioritisationTwo = eveningWorkPrioritisationDAO.save(eveningWorkPrioritisationEntity2);
    }

    private void tearDown() {
        serviceProviderEveningWorkDAO.deleteAllInBatch();
        serviceProviderEveningWorkDAO.deleteAllInBatch();
        serviceProviderDAO.deleteAllInBatch();
        serviceProviderDAO.flush();
        eveningWorkPrioritisationDAO.deleteAllInBatch();
        eveningWorkPrioritisationDAO.flush();
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
    public void testAddingServiceProviderEveningWork() {
        setUp();
        try {
            ServiceProviderEveningWorkEntity serviceProviderEveningWorkEntity = new ServiceProviderEveningWorkEntity();
            serviceProviderEveningWorkEntity.setServiceProviderId(serviceProviderOne.getId());
            serviceProviderEveningWorkEntity.setWeekDay("Saturday");
            serviceProviderEveningWorkEntity.setEveningWorkPrioritisationId(eveningWorkPrioritisationOne.getId());
            ServiceProviderEveningWorkDto added = serviceProviderEveningWorkDAO.addOrUpdateServiceProviderEveningWork(serviceProviderEveningWorkEntity);
            Assert.assertEquals("Saturday", serviceProviderEveningWorkDAO.findById(added.getId()).get().getWeekDay());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testAddingServiceProviderEveningWorkPassingWringDayNumberShouldFail() {
        setUp();
        try {
            ServiceProviderEveningWorkEntity serviceProviderEveningWorkEntity = new ServiceProviderEveningWorkEntity();
            serviceProviderEveningWorkEntity.setServiceProviderId(serviceProviderOne.getId());
            serviceProviderEveningWorkEntity.setWeekDay("TestWrong");
            serviceProviderEveningWorkEntity.setEveningWorkPrioritisationId(eveningWorkPrioritisationOne.getId());
            serviceProviderEveningWorkDAO.addOrUpdateServiceProviderEveningWork(serviceProviderEveningWorkEntity);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertEquals("Provided week day is not correct", e.getMessage());
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingProviderEveningWorkPrioritization() {
        setUp();
        try {
            ServiceProviderEveningWorkEntity serviceProviderEveningWorkEntity = new ServiceProviderEveningWorkEntity();
            serviceProviderEveningWorkEntity.setServiceProviderId(serviceProviderOne.getId());
            serviceProviderEveningWorkEntity.setWeekDay("Saturday");
            serviceProviderEveningWorkEntity.setEveningWorkPrioritisationId(eveningWorkPrioritisationOne.getId());
            ServiceProviderEveningWorkDto added = serviceProviderEveningWorkDAO.addOrUpdateServiceProviderEveningWork(serviceProviderEveningWorkEntity);
            ServiceProviderEveningWorkEntity toUpdate = serviceProviderEveningWorkDAO.findById(added.getId()).get();
            toUpdate.setWeekDay("Monday");
            serviceProviderEveningWorkDAO.addOrUpdateServiceProviderEveningWork(toUpdate);
            Assert.assertEquals("Monday", serviceProviderEveningWorkDAO.findById(toUpdate.getId()).get().getWeekDay());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingServiceProviderWorkPrioritizations(){
        setUp();
        try {
            ServiceProviderEveningWorkEntity serviceProviderEveningWorkEntity = new ServiceProviderEveningWorkEntity();
            serviceProviderEveningWorkEntity.setServiceProviderId(serviceProviderOne.getId());
            serviceProviderEveningWorkEntity.setWeekDay("Saturday");
            serviceProviderEveningWorkEntity.setEveningWorkPrioritisationId(eveningWorkPrioritisationOne.getId());

            ServiceProviderEveningWorkEntity serviceProviderEveningWorkEntity2 = new ServiceProviderEveningWorkEntity();
            serviceProviderEveningWorkEntity2.setServiceProviderId(serviceProviderOne.getId());
            serviceProviderEveningWorkEntity2.setWeekDay("Monday");
            serviceProviderEveningWorkEntity2.setEveningWorkPrioritisationId(eveningWorkPrioritisationOne.getId());

            serviceProviderEveningWorkDAO.addOrUpdateServiceProviderEveningWork(serviceProviderEveningWorkEntity);
            serviceProviderEveningWorkDAO.addOrUpdateServiceProviderEveningWork(serviceProviderEveningWorkEntity2);

            Assert.assertEquals(2, serviceProviderEveningWorkDAO.listServiceProviderEveningWork(serviceProviderOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingServiceProviderWorkPrioritization(){
        setUp();
        try {
            ServiceProviderEveningWorkEntity serviceProviderEveningWorkEntity = new ServiceProviderEveningWorkEntity();
            serviceProviderEveningWorkEntity.setServiceProviderId(serviceProviderOne.getId());
            serviceProviderEveningWorkEntity.setWeekDay("Saturday");
            serviceProviderEveningWorkEntity.setEveningWorkPrioritisationId(eveningWorkPrioritisationOne.getId());
            ServiceProviderEveningWorkDto added = serviceProviderEveningWorkDAO.addOrUpdateServiceProviderEveningWork(serviceProviderEveningWorkEntity);

            Assert.assertEquals("Saturday", serviceProviderEveningWorkDAO.getServiceProviderEveningWorkForSpecificDay("Saturday", serviceProviderOne.getId()).getWeekDay());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingServiceProviderWorkPrioritizationShouldThrowException(){
        setUp();
        try {
            ServiceProviderEveningWorkEntity serviceProviderEveningWorkEntity = new ServiceProviderEveningWorkEntity();
            serviceProviderEveningWorkEntity.setServiceProviderId(serviceProviderOne.getId());
            serviceProviderEveningWorkEntity.setWeekDay("Saturday");
            serviceProviderEveningWorkEntity.setEveningWorkPrioritisationId(eveningWorkPrioritisationOne.getId());
            ServiceProviderEveningWorkDto added = serviceProviderEveningWorkDAO.addOrUpdateServiceProviderEveningWork(serviceProviderEveningWorkEntity);
            serviceProviderEveningWorkDAO.getServiceProviderEveningWorkForSpecificDay("Sunday", serviceProviderOne.getId());
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }
}
