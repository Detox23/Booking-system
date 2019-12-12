package API.Repository.ServiceUser;

import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.Account.AccountDAO;
import API.Repository.Account.AccountTypeDAO;
import API.Repository.Department.DepartmentDAO;
import Shared.ToReturn.ServiceUserCommentDto;
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
public class ServiceUserCommentRepositoryTest {

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


    private AccountTypeEntity accountTypeOne;
    private AccountEntity accountOne;
    private AccountEntity accountTwo;
    private DepartmentEntity departmentOne;
    private ServiceUserEntity serviceUserOne;
    private ServiceUserStatusEntity serviceUserStatusOne;
    private ServiceUserCommentEntity serviceUserCommentOne;

    private void setUp() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName("TestDepartmentNameOne");
        departmentEntity.setPostcode("9200");
        departmentOne = departmentDAO.save(departmentEntity);

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
    }

    private void tearDown() {
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

    }

    @Test
    public void testAddingServiceUserComment() {
        setUp();
        try {
            ServiceUserCommentEntity serviceUserCommentEntity = new ServiceUserCommentEntity();
            serviceUserCommentEntity.setCommentText("Test service user comment text second");
            serviceUserCommentEntity.setServiceUserId(serviceUserOne.getId());
            ServiceUserCommentDto added = serviceUserCommentDAO.addServiceUserComment(serviceUserCommentEntity);
            Assert.assertEquals("Test service user comment text second", serviceUserCommentDAO.findById(added.getId()).get().getCommentText());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingServiceUserCommentNameShouldChange() {
        setUp();
        try {
            serviceUserCommentOne.setCommentText("Updated");
            ServiceUserCommentDto updated = serviceUserCommentDAO.updateServiceUserComment(serviceUserCommentOne);
            Assert.assertEquals("Updated", serviceUserCommentDAO.findById(updated.getId()).get().getCommentText());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingServiceUserCommentSizeShouldBeOne() {
        setUp();
        try {
            serviceUserCommentOne.setCommentText("Updated");
            ServiceUserCommentDto updated = serviceUserCommentDAO.updateServiceUserComment(serviceUserCommentOne);
            Assert.assertEquals(1, serviceUserCommentDAO.findAll().size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingServiceUserCommentSizeShouldBeOne() {
        setUp();
        try {
            serviceUserCommentDAO.deleteServiceUserComment(serviceUserCommentOne.getId());
            Assert.assertEquals(0, serviceUserCommentDAO.findAll().size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingServiceUserWithNotExistingIdShouldThrowError() {
        setUp();
        try {
            serviceUserCommentDAO.deleteServiceUserComment(-5);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingServiceUserCommentsSizeShouldBeTwo() {
        setUp();
        try {
            ServiceUserCommentEntity serviceUserCommentEntity = new ServiceUserCommentEntity();
            serviceUserCommentEntity.setCommentText("Test service user comment text second");
            serviceUserCommentEntity.setServiceUserId(serviceUserOne.getId());
            serviceUserCommentDAO.addServiceUserComment(serviceUserCommentEntity);
            Assert.assertEquals(2, serviceUserCommentDAO.listServiceUserComments(serviceUserOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingServiceProviderCommentByCommentId(){
        setUp();
        try {
            ServiceUserCommentDto found = serviceUserCommentDAO.findServiceUserComment(serviceUserCommentOne.getId());
            Assert.assertEquals("Test service user comment text", found.getCommentText());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testFindingServiceProviderCommentWithNotExistingIdShouldThrowException(){
        setUp();
        try {
            ServiceUserCommentDto found = serviceUserCommentDAO.findServiceUserComment(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }
}
