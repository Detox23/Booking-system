package API.Repository.Account;


import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.Department.DepartmentDAO;
import API.Repository.ServiceUser.ServiceUserAccountsDAO;
import API.Repository.ServiceUser.ServiceUserDAO;
import API.Repository.ServiceUser.ServiceUserStatusDAO;
import Shared.ToReturn.AccountDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private AccountEanDAO accountEanDAO;

    @Autowired
    private ServiceUserDAO serviceUserDAO;

    @Autowired
    private ServiceUserAccountsDAO serviceUserAccountsDAO;

    @Autowired
    private ServiceUserStatusDAO serviceUserStatusDAO;

    private DepartmentEntity departmentEntityOne;
    private AccountTypeEntity accountTypeOne;
    private AccountTypeEntity accountTypeTwo;
    private AccountEntity accountOne;
    private AccountEntity accountTwo;
    private ServiceUserStatusEntity serviceUserStatus;
    private ServiceUserEntity serviceUserOne;
    private ServiceUserEntity serviceUserTwo;


    private void setUp() {


        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setPostcode("9200");
        departmentEntityOne = departmentDAO.save(departmentEntity);

        ServiceUserStatusEntity serviceUserStatusEntity = new ServiceUserStatusEntity();
        serviceUserStatusEntity.setStatus("TestStatus");
        serviceUserStatus = serviceUserStatusDAO.save(serviceUserStatusEntity);

        ServiceUserEntity serviceUserEntity1 = new ServiceUserEntity();
        serviceUserEntity1.setStatusId(serviceUserStatus.getId());
        serviceUserEntity1.setFirstName("TestServiceUser");
        serviceUserEntity1.setDepartmentId(departmentEntityOne.getId());
        serviceUserOne = serviceUserDAO.save(serviceUserEntity1);

        ServiceUserEntity serviceUserEntity2 = new ServiceUserEntity();
        serviceUserEntity2.setStatusId(serviceUserStatus.getId());
        serviceUserEntity2.setFirstName("TestServiceUser2");
        serviceUserEntity2.setDepartmentId(departmentEntityOne.getId());
        serviceUserTwo = serviceUserDAO.save(serviceUserEntity2);


        AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
        accountTypeEntity1.setAccountType("TestAccountType");
        accountTypeEntity1.setDeleted(false);
        accountTypeOne = accountTypeDAO.save(accountTypeEntity1);

        AccountTypeEntity accountTypeEntity2 = new AccountTypeEntity();
        accountTypeEntity2.setAccountType("TestAccountType2");
        accountTypeEntity2.setDeleted(false);
        accountTypeTwo = accountTypeDAO.save(accountTypeEntity2);

        AccountEntity accountEntity1 = new AccountEntity();
        accountEntity1.setAccountName("TestAccount");
        accountEntity1.setCity("TestCity");
        accountEntity1.setParentId(1);
        accountEntity1.setAccountTypeId(accountTypeOne.getId());
        accountEntity1.setDepartmentId(departmentEntityOne.getId());
        accountOne = accountDAO.save(accountEntity1);

        AccountEntity accountEntity2 = new AccountEntity();
        accountEntity2.setAccountName("TestAccount2");
        accountEntity2.setCity("TestCity2");
        accountEntity2.setParentId(2);
        accountEntity2.setAccountTypeId(accountTypeTwo.getId());
        accountEntity2.setDepartmentId(departmentEntityOne.getId());
        accountTwo = accountDAO.save(accountEntity2);
    }


    private void tearDown() {

        serviceUserAccountsDAO.deleteAllInBatch();
        serviceUserAccountsDAO.flush();
        serviceUserDAO.deleteAllInBatch();
        serviceUserDAO.flush();
        serviceUserStatusDAO.deleteAllInBatch();
        serviceUserStatusDAO.flush();
        accountEanDAO.deleteAllInBatch();
        accountEanDAO.flush();
        accountDAO.deleteAllInBatch();
        accountDAO.flush();
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
        accountTypeDAO.deleteAllInBatch();
        accountTypeDAO.flush();
    }

    @Test
    public void testListAllAccountsSizeShouldBeTwo() {
        setUp();
        try {
            Assert.assertEquals(2, accountDAO.listAccounts().size());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListAllAccountsAfterDeletionOneShouldBeOne() {
        setUp();
        try {
            accountDAO.deleteAccount(accountOne.getId());
            Assert.assertEquals(1, accountDAO.listAccounts().size());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindOneAccountShouldNotBeNull() {
        setUp();
        try {
            Assert.assertNotNull(accountDAO.findAccount(accountOne.getId()));
        } catch (Exception error) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindAccountWithNotExistingIdShouldThrowException() {
        setUp();
        try {
            accountDAO.findAccount(-1);
            Assert.fail();
        } catch (Exception error) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAccountWithNameThatAlreadyExistsShouldThrowException() {
        setUp();
        try {
            AccountEntity accountSameName = new AccountEntity();
            accountSameName.setAccountName("TestAccount");
            accountSameName.setCity("TestCity");
            accountSameName.setParentId(1);
            accountSameName.setAccountTypeId(accountTypeOne.getId());
            accountSameName.setDepartmentId(departmentEntityOne.getId());
            accountDAO.addAccount(accountSameName, null, null);
            Assert.fail();
        } catch (Exception error) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAccountWithEANNumberSizeOfEansListShouldBeTwo() {
        setUp();
        try {
            List<String> eans = new ArrayList<>();
            eans.add("123456789");
            eans.add("987654321");
            AccountEntity accountSameName = new AccountEntity();
            accountSameName.setAccountName("TestAccountEans");
            accountSameName.setCity("TestCity");
            accountSameName.setParentId(1);
            accountSameName.setAccountTypeId(accountTypeOne.getId());
            accountSameName.setDepartmentId(departmentEntityOne.getId());
            AccountDto added = accountDAO.addAccount(accountSameName, eans, null);
            Assert.assertEquals(2, accountEanDAO.findAllByAccountId(added.getId()).size());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingAccountShouldEqualsNewName() {
        setUp();
        try {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setId(accountTwo.getId());
            accountEntity.setAccountName("UpdatedName");
            AccountDto updated = accountDAO.updateAccount(accountEntity, null, null);
            Assert.assertEquals("UpdatedName", updated.getAccountName());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingAccountForSameNameThatAlreadyExistsShouldThrowException() {
        setUp();
        try {
            accountOne.setAccountName("TestAccount2");
            accountDAO.updateAccount(accountOne, null, null);
            Assert.fail();
        } catch (Exception error) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingSameAccountWithoutChangingNameShouldPass() {
        setUp();
        try {
            accountOne.setCity("City");
            AccountDto updated = accountDAO.updateAccount(accountOne, null, null);
            Assert.assertEquals("City", updated.getCity());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingUpdatesNotAddsListSizeShouldBeTwo() {
        setUp();
        try {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setId(accountTwo.getId());
            accountEntity.setAccountName("UpdatedName");
            AccountDto updated = accountDAO.updateAccount(accountEntity, null, null);
            Assert.assertEquals(2, accountDAO.listAccounts().size());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAccountWithServiceUsersSizeOfListShouldBeTwo() {
        setUp();
        try {
            List<Integer> users = new ArrayList<>();
            users.add(serviceUserOne.getId());
            users.add(serviceUserTwo.getId());
            AccountEntity accountSameName = new AccountEntity();
            accountSameName.setAccountName("TestAccountEans");
            accountSameName.setCity("TestCity");
            accountSameName.setParentId(1);
            accountSameName.setAccountTypeId(accountTypeOne.getId());
            accountSameName.setDepartmentId(departmentEntityOne.getId());
            AccountDto added = accountDAO.addAccount(accountSameName, null, users);
            Assert.assertEquals(2, serviceUserAccountsDAO.findAllByAccountIdIs(added.getId()).size());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

}
