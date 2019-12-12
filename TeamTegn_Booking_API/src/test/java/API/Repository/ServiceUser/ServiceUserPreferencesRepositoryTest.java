package API.Repository.ServiceUser;


import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.Account.AccountDAO;
import API.Repository.Account.AccountTypeDAO;
import API.Repository.Department.DepartmentDAO;
import API.Repository.ServiceProvider.*;
import Shared.ToReturn.ServiceUserPreferencesDto;
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
public class ServiceUserPreferencesRepositoryTest {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private ServiceUserDAO serviceUserDAO;

    @Autowired
    private ServiceUserStatusDAO serviceUserStatusDAO;

    @Autowired
    private ServiceUserAccountsDAO serviceUserAccountsDAO;

    @Autowired
    private ServiceUserCommentDAO serviceUserCommentDAO;

    @Autowired
    private ServiceProviderDAO serviceProviderDAO;

    @Autowired
    private TransportTypeDAO transportTypeDAO;

    @Autowired
    private ServiceProviderPreferredNotificationDAO serviceProviderPreferredNotificationDAO;

    @Autowired
    private ServiceProviderSourceDAO serviceProviderSourceDAO;

    @Autowired
    private ServiceProviderCompetencyDAO serviceProviderCompetencyDAO;

    @Autowired
    private ServiceProviderTypeDAO serviceProviderTypeDAO;

    @Autowired
    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

    @Autowired
    private ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO;

    @Autowired
    private ServiceUserPreferencesDAO serviceUserPreferencesDAO;

    @Autowired
    private RatingDAO ratingDAO;

    private AccountTypeEntity accountTypeOne;
    private AccountEntity accountOne;
    private AccountEntity accountTwo;
    private DepartmentEntity departmentOne;
    private ServiceUserEntity serviceUserOne;
    private ServiceUserStatusEntity serviceUserStatusOne;
    private ServiceUserCommentEntity serviceUserCommentOne;
    private TransportTypeEntity transportTypeOne;
    private ServiceProviderPreferredNotificationEntity serviceProviderPreferredNotificationOne;
    private ServiceProviderEntity serviceProviderOne;
    private ServiceProviderSourceEntity serviceProviderSourceOne;
    private ServiceProviderCompetencyEntity serviceProviderCompetencyOne;
    private ServiceProviderCompetencyEntity serviceProviderCompetencyTwo;
    private ServiceProviderTypeEntity serviceProviderTypeOne;
    private ServiceProviderTypeEntity serviceProviderTypeTwo;
    private ServiceUserPreferencesEntity serviceUserPreferencesOne;
    private RatingEntity ratingOne;
    private RatingEntity ratingTwo;

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

        ServiceUserStatusEntity serviceUserStatusEntity = new ServiceUserStatusEntity();
        serviceUserStatusEntity.setStatus("TestStatus");
        serviceUserStatusEntity.setDeleted(false);
        serviceUserStatusOne = serviceUserStatusDAO.save(serviceUserStatusEntity);

        AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
        accountTypeEntity1.setAccountType("TestAccountType");
        accountTypeEntity1.setDeleted(false);
        accountTypeOne = accountTypeDAO.save(accountTypeEntity1);

        ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
        serviceUserEntity.setFirstName("TestFirstName");
        serviceUserEntity.setLastName("TestLastName");
        serviceUserEntity.setCpr("2308784512");
        serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
        serviceUserEntity.setDepartmentId(departmentOne.getId());
        serviceUserEntity.setDeleted(false);
        serviceUserOne = serviceUserDAO.save(serviceUserEntity);

        AccountEntity accountEntity1 = new AccountEntity();
        accountEntity1.setAccountName("TestAccount");
        accountEntity1.setCity("TestCity");
        accountEntity1.setParentId(1);
        accountEntity1.setAccountTypeId(accountTypeOne.getId());
        accountEntity1.setDepartmentId(departmentOne.getId());
        accountOne = accountDAO.save(accountEntity1);

        AccountEntity accountEntity2 = new AccountEntity();
        accountEntity2.setAccountName("TestAccountSecond");
        accountEntity2.setCity("TestCitySecond");
        accountEntity2.setParentId(1);
        accountEntity2.setAccountTypeId(accountTypeOne.getId());
        accountEntity2.setDepartmentId(departmentOne.getId());
        accountTwo = accountDAO.save(accountEntity2);

        ServiceUserCommentEntity serviceUserCommentEntity = new ServiceUserCommentEntity();
        serviceUserCommentEntity.setCommentText("Test service user comment text");
        serviceUserCommentEntity.setServiceUserId(serviceUserOne.getId());
        serviceUserCommentOne = serviceUserCommentDAO.save(serviceUserCommentEntity);

        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setRatingName("Wanted");
        ratingEntity.setDeleted(false);
        ratingOne = ratingDAO.save(ratingEntity);

        RatingEntity ratingEntity2 = new RatingEntity();
        ratingEntity2.setRatingName("Not Wanted");
        ratingEntity2.setDeleted(false);
        ratingTwo = ratingDAO.save(ratingEntity2);

        ServiceUserPreferencesEntity serviceUserPreferencesEntity = new ServiceUserPreferencesEntity();
        serviceUserPreferencesEntity.setServiceUserId(serviceUserOne.getId());
        serviceUserPreferencesEntity.setServiceProviderId(serviceProviderOne.getId());
        serviceUserPreferencesEntity.setRating(ratingTwo.getId());
        serviceUserPreferencesOne = serviceUserPreferencesDAO.save(serviceUserPreferencesEntity);


    }

    private void tearDown() {
        serviceUserPreferencesDAO.deleteAllInBatch();
        serviceUserPreferencesDAO.flush();
        serviceProviderServiceProviderCompetencyDAO.deleteAllInBatch();
        serviceProviderServiceProviderCompetencyDAO.flush();
        serviceProviderServiceProviderTypeDAO.deleteAllInBatch();
        serviceProviderServiceProviderTypeDAO.flush();
        serviceProviderDAO.deleteAllInBatch();
        serviceProviderDAO.flush();
        serviceProviderTypeDAO.deleteAllInBatch();
        serviceProviderTypeDAO.flush();
        serviceProviderCompetencyDAO.deleteAllInBatch();
        serviceProviderCompetencyDAO.flush();
        serviceProviderPreferredNotificationDAO.deleteAllInBatch();
        serviceProviderPreferredNotificationDAO.flush();
        transportTypeDAO.deleteAllInBatch();
        transportTypeDAO.flush();
        serviceProviderSourceDAO.deleteAllInBatch();
        serviceProviderSourceDAO.flush();
        serviceUserCommentDAO.deleteAllInBatch();
        serviceUserCommentDAO.flush();
        serviceUserAccountsDAO.deleteAllInBatch();
        serviceUserAccountsDAO.flush();
        serviceUserDAO.deleteAllInBatch();
        serviceUserDAO.flush();
        serviceUserStatusDAO.deleteAllInBatch();
        serviceUserStatusDAO.flush();
        accountDAO.deleteAllInBatch();
        accountDAO.flush();
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
        accountTypeDAO.deleteAllInBatch();
        accountTypeDAO.flush();
        ratingDAO.deleteAllInBatch();
        ratingDAO.flush();
    }

    @Test
    public void addingServiceUserPreference() {
        setUp();
        try {
            serviceUserPreferencesDAO.deleteAll();
            ServiceUserPreferencesEntity serviceUserPreferencesEntity = new ServiceUserPreferencesEntity();
            serviceUserPreferencesEntity.setServiceProviderId(serviceProviderOne.getId());
            serviceUserPreferencesEntity.setRating(ratingOne.getId());
            ServiceUserPreferencesDto added = serviceUserPreferencesDAO.addServiceUserPreference(serviceUserOne.getId(), serviceUserPreferencesEntity);
            Assert.assertEquals("Wanted", ratingDAO.findById(serviceUserPreferencesDAO.findById(added.getId()).get().getRating()).get().getRatingName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void addingServiceUserPreferenceForSameUsersShouldThrowException() {
        setUp();
        try {
            ServiceUserPreferencesEntity serviceUserPreferencesEntity = new ServiceUserPreferencesEntity();
            serviceUserPreferencesEntity.setServiceProviderId(serviceProviderOne.getId());
            serviceUserPreferencesEntity.setRating(ratingOne.getId());
            ServiceUserPreferencesDto added = serviceUserPreferencesDAO.addServiceUserPreference(serviceUserOne.getId(), serviceUserPreferencesEntity);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingServiceuserPreferenceListShouldBeEmpty() {
        setUp();
        try {
            int size = serviceUserPreferencesDAO.findAll().size();
            serviceUserPreferencesDAO.deleteServiceUserPreference(serviceUserPreferencesOne.getId());
            Assert.assertNotEquals(0, size);
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testDeletingServiceuserPreferenceWithNotExistingIdThrowsException() {
        setUp();
        try {
            serviceUserPreferencesDAO.deleteServiceUserPreference(-5);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListServiceUserPreferences() {
        setUp();
        try{
            Assert.assertEquals(1, serviceUserPreferencesDAO.listServiceUserPreferences(serviceUserOne.getId()).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindByServiceUserAndServiceProvider(){
        setUp();
        try{
            Assert.assertEquals(ratingTwo.getId(), serviceUserPreferencesDAO.findServiceProviderAndUser(serviceUserOne.getId(), serviceProviderOne.getId()).getRating());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindByServiceUserAndServiceProviderWithIncorrectIdThrowsException(){
        setUp();
        try{
            serviceUserPreferencesDAO.findServiceProviderAndUser(-1, -1);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingServiceUserPreference(){
        setUp();
        try{
            serviceUserPreferencesOne.setRating(ratingTwo.getId());
            ServiceUserPreferencesDto updated = serviceUserPreferencesDAO.updateServiceUserPreference(serviceUserPreferencesOne);
            Assert.assertEquals("Not Wanted", ratingDAO.findById(serviceUserPreferencesDAO.findById(updated.getId()).get().getRating()).get().getRatingName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

}
