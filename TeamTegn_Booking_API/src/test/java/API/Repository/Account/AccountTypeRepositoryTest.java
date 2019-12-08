package API.Repository.Account;


import API.MainApplicationClass;
import API.Models.Database_Entities.AccountTypeEntity;
import Shared.ToReturn.AccountTypeDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
public class AccountTypeRepositoryTest {

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    private AccountTypeEntity accountTypeOne;
    private AccountTypeEntity accountTypeTwo;

    private void setUp() {
        AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
        accountTypeEntity1.setAccountType("TestAccountType");
        accountTypeEntity1.setDeleted(false);
        accountTypeOne = accountTypeDAO.save(accountTypeEntity1);

        AccountTypeEntity accountTypeEntity2 = new AccountTypeEntity();
        accountTypeEntity2.setAccountType("TestAccountType11");
        accountTypeEntity2.setDeleted(false);
        accountTypeTwo = accountTypeDAO.save(accountTypeEntity2);
    }

    private void tearDown() {
        accountTypeDAO.deleteAllInBatch();
        accountTypeDAO.flush();
    }

    @Test
    public void addingAccountTypeShouldPass(){
        setUp();
        try{
            AccountTypeEntity accountTypeEntity2 = new AccountTypeEntity();
            accountTypeEntity2.setAccountType("TestAccountType2");
            accountTypeEntity2.setDeleted(false);
            AccountTypeDto added = accountTypeDAO.addAccountType(accountTypeEntity2);
            Assert.assertEquals("TestAccountType2", accountTypeDAO.findById(added.getId()).get().getAccountType());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void addingAccountTypeWithNameThatAlreadyExistsShouldThrowException(){
        setUp();
        try{
            AccountTypeEntity accountTypeEntity2 = new AccountTypeEntity();
            accountTypeEntity2.setAccountType("TestAccountType");
            accountTypeEntity2.setDeleted(false);
            accountTypeDAO.addAccountType(accountTypeEntity2);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void findingAccountTypeShouldPass(){
        setUp();
        try{
            AccountTypeDto found = accountTypeDAO.findAccountType(accountTypeOne.getId());
            Assert.assertEquals("TestAccountType", found.getAccountType());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void findingAccountThatDoesNotExistShouldThrowException(){
        setUp();
        try{
            accountTypeDAO.findAccountType(-1);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void updatingAccountTypeShouldChangeItsName(){
        setUp();
        try{
            accountTypeOne.setAccountType("Updated");
            accountTypeDAO.updateAccountType(accountTypeOne);
            Assert.assertEquals("Updated", accountTypeDAO.findAccountType(accountTypeOne.getId()).getAccountType());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void updatingAccountForANameThatOtherExistsShouldThrowException(){
        setUp();
        try{
            accountTypeOne.setAccountType("TestAccountType11");
            accountTypeDAO.updateAccountType(accountTypeOne);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void listAllWithoutDeletedShouldReturnOneInList(){
        setUp();
        try{
            accountTypeDAO.deleteAccountType(accountTypeOne.getId());
            Assert.assertEquals(1, accountTypeDAO.listAccountTypes(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void listAllWithDeletedShouldReturnListOfSizeTwo(){
        setUp();
        try{
            accountTypeDAO.deleteAccountType(accountTypeOne.getId());
            Assert.assertEquals(2, accountTypeDAO.listAccountTypes(true).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void listAllShouldReturnListOfSizeTwo(){
        setUp();
        try{
            Assert.assertEquals(2, accountTypeDAO.listAccountTypes(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }
}
