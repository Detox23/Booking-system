package API.Repository.Account;


import API.MainApplicationClass;
import API.Models.Database_Entities.AccountCommentEntity;
import API.Models.Database_Entities.AccountEntity;
import API.Models.Database_Entities.AccountTypeEntity;
import API.Models.Database_Entities.DepartmentEntity;
import API.Repository.Department.DepartmentDAO;
import API.Repository.SystemUser.SystemUserDAO;
import Shared.ToReturn.AccountCommentDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
public class AccountCommentRepositoryTest {

    private DepartmentEntity departmentEntityOne;
    private AccountTypeEntity accountTypeOne;
    private AccountEntity accountOne;
    private AccountEntity accountTwo;
    private AccountCommentEntity commentOne;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountCommentDAO accountCommentDAO;

    @Autowired
    private SystemUserDAO systemUserDAO;

    private void setUp() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setPostcode("9200");
        departmentEntityOne = departmentDAO.save(departmentEntity);

        AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
        accountTypeEntity.setAccountType("TestAccountType");
        accountTypeEntity.setDeleted(false);
        accountTypeOne = accountTypeDAO.save(accountTypeEntity);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountName("TestAccount");
        accountEntity.setCity("TestCity");
        accountEntity.setParentId(1);
        accountEntity.setAccountTypeId(accountTypeOne.getId());
        accountEntity.setDepartmentId(departmentEntityOne.getId());
        accountOne = accountDAO.save(accountEntity);

        AccountEntity accountEntity2 = new AccountEntity();
        accountEntity2.setAccountName("TestAccount");
        accountEntity2.setCity("TestCity");
        accountEntity2.setParentId(1);
        accountEntity2.setAccountTypeId(accountTypeOne.getId());
        accountEntity2.setDepartmentId(departmentEntityOne.getId());
        accountTwo = accountDAO.save(accountEntity);

        AccountCommentEntity accountCommentEntity = new AccountCommentEntity();
        accountCommentEntity.setAccountId(accountOne.getId());
        accountCommentEntity.setCommentText("Sample comment text.");
        commentOne = accountCommentDAO.save(accountCommentEntity);
    }

    private void tearDown() {
        systemUserDAO.deleteAllByIdIsGreaterThan(1);
        accountCommentDAO.deleteAllInBatch();
        accountCommentDAO.flush();
        accountDAO.deleteAllInBatch();
        accountDAO.flush();
        accountTypeDAO.deleteAllInBatch();
        accountTypeDAO.flush();
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
    }

    @Test
    public void testAddingAccountCommentShouldPass() {
        setUp();
        try {
            AccountCommentEntity accountCommentEntity = new AccountCommentEntity();
            accountCommentEntity.setCommentText("Test text");
            accountCommentEntity.setAccountId(accountOne.getId());
            AccountCommentDto added = accountCommentDAO.addAccountComment(accountCommentEntity);
            Assert.assertEquals("Test text", added.getCommentText());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAccountCommentNameShouldMatch() {
        setUp();
        try {
            Assert.assertEquals("Sample comment text.", accountCommentDAO.findAccountComment(commentOne.getId()).getCommentText());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void listAccountCommentsListSizeShouldBeTwo() {
        setUp();
        try {
            AccountCommentEntity accountCommentEntity = new AccountCommentEntity();
            accountCommentEntity.setCommentText("Test text");
            accountCommentEntity.setAccountId(accountOne.getId());
            accountCommentDAO.addAccountComment(accountCommentEntity);
            Assert.assertEquals(2, accountCommentDAO.listAccountComments(accountOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void findAccountWithIdThatDoesNotExistsShouldThrowException() {
        setUp();
        try {
            accountCommentDAO.findAccountComment(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void deleteAccountCommentListShouldBeEmpty() {
        setUp();
        try {
            accountCommentDAO.deleteAccountComment(accountOne.getId(), commentOne.getId());
            Assert.assertEquals(0, accountCommentDAO.findAllByAccountIdIs(accountOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void deleteAccountCommentWithNotExistingAccountIDShouldThrowException() {
        setUp();
        try {
            accountCommentDAO.deleteAccountComment(-1, commentOne.getId());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void updateAccountCommentShouldReturnWithNewName() {
        setUp();
        try {
            commentOne.setCommentText("Updated test text");
            accountCommentDAO.updateAccountComment(commentOne);
            Assert.assertEquals("Updated test text", accountCommentDAO.findAccountComment(commentOne.getId()).getCommentText());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void updateAccountCommentShouldNotAddNew() {
        setUp();
        try {
            commentOne.setCommentText("Updated test text");
            accountCommentDAO.updateAccountComment(commentOne);
            Assert.assertEquals(1, accountCommentDAO.listAccountComments(accountOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void updateAccountCommentWithUnknownAccountIdShouldThrowException() {
        setUp();
        try {
            commentOne.setCommentText("Updated test text");
            commentOne.setAccountId(-1);
            accountCommentDAO.updateAccountComment(commentOne);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }
}
