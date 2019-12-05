package API.Repository.Account;


import API.Exceptions.DuplicateException;
import API.Exceptions.NotEnoughDataException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdateErrorException;
import API.MainApplicationClass;
import API.Models.Database_Entities.AccountEanEntity;
import API.Models.Database_Entities.AccountEntity;
import API.Models.Database_Entities.AccountTypeEntity;
import API.Models.Database_Entities.DepartmentEntity;
import API.Repository.Department.DepartmentDAO;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.AccountEanDto;
import Shared.ToReturn.AccountTypeDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class AccountRepositoryTest {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private DepartmentDAO departmentDAO;


    private DepartmentEntity departmentEntityOne;
    private AccountTypeEntity accountTypeOne;
    private AccountTypeEntity accountTypeTwo;
    private AccountEntity accountOne;
    private AccountEntity accountTwo;


    private void setUp() {

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setCity("TestCity");
        departmentEntityOne = departmentDAO.save(departmentEntity);


        AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
        accountTypeEntity1.setAccountType("TestAccountType");
        accountTypeOne = accountTypeDAO.save(accountTypeEntity1);

        AccountTypeEntity accountTypeEntity2 = new AccountTypeEntity();
        accountTypeEntity2.setAccountType("TestAccountType2");
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
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
        accountDAO.deleteAllInBatch();
        accountDAO.flush();
        accountTypeDAO.deleteAllInBatch();
        accountTypeDAO.flush();
    }

    @Test
    public void testListAllAccountsSizeShouldBeTwo() {
        setUp();
        try {

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
            Assert.assertNotNull( accountDAO.findAccount(accountOne.getId()));
        } catch (Exception error) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


//    @Test
//    public void testFindAccountThatDoesNotExistsShouldRaiseNotFoundExceptionException() {
//        setUp();
//        try {
//            accountDAO.getOneAccount(1500);
//            Assert.fail();
//        } catch (NotFoundException error) {
//            Assert.assertEquals("Account was not found.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }
//
//
//    @Test
//    public void testAddingAccountWithoutEanShouldNotBeNull() {
//        setUp();
//        try {
//            AccountForCreationDto accountOne = new AccountForCreationDto();
//            accountOne.setAccountName("TestAccountName");
//            AccountDto addedAccount = accountDAO.addOneAccount(mapper.map(accountOne, AccountEntity.class), null, idAccountType);
//            Assert.assertNotNull(addedAccount);
//        } catch (Exception error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testAddingTwoSameAccountsShouldRaiseDuplicateException() {
//        setUp();
//        try {
//            AccountForCreationDto accountOne = new AccountForCreationDto();
//            accountOne.setAccountName("TestAccountName");
//            accountDAO.addOneAccount(mapper.map(accountOne, AccountEntity.class), null, idAccountType);
//            accountDAO.addOneAccount(mapper.map(accountOne, AccountEntity.class), null, idAccountType);
//            Assert.fail();
//        } catch (DuplicateException error) {
//            Assert.assertEquals("Account with exact name and CVR number already exists.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testAddingAccountWithEanListThrowsNoError() {
//        setUp();
//        try {
//            AccountForCreationDto accountOne = new AccountForCreationDto();
//            accountOne.setAccountName("TestAccountName");
//            List<String> eans = new ArrayList<>();
//            eans.add("123456789");
//            eans.add("987654231");
//            AccountDto addedAccount = accountDAO.addOneAccount(mapper.map(accountOne, AccountEntity.class), eans, idAccountType);
//            Assert.assertNotNull(addedAccount);
//        } catch (Error error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testAddingAccountWithNotEnoughInformationShouldRaiseDataIntegrityViolationException() {
//        setUp();
//        try {
//            AccountForCreationDto accountOne = new AccountForCreationDto();
//            accountDAO.addOneAccount(mapper.map(accountOne, AccountEntity.class), null, idAccountType);
//            Assert.fail();
//        } catch (NotEnoughDataException error) {
//            Assert.assertEquals("You provided to little information to create the account.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testAddingAccountPassingAccountTypeThatDoesNotExistsShouldRaiseNotFoundExceptionException() {
//        setUp();
//        try {
//            AccountForCreationDto accountOne = new AccountForCreationDto();
//            accountOne.setAccountName("TestAccountName");
//            accountDAO.addOneAccount(mapper.map(accountOne, AccountEntity.class), null, -1);
//            Assert.fail();
//        } catch (NotFoundException error) {
//            Assert.assertEquals("Account type was not found. Adding cancelled.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testUpdatingAccountNameShouldBeDifferent() {
//        setUp();
//        try {
//            AccountEntity accountOne = new AccountEntity();
//            accountOne.setId(addedOne.getId());
//            accountOne.setAccountName("UpdatedTestAccountName");
//            AccountDto updatedAccount = accountDAO.updateOneAccount(accountOne);
//            Assert.assertEquals("UpdatedTestAccountName", updatedAccount.getAccountName());
//        } catch (Error error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testUpdatingAccountLastUpdateShouldBeDifferent() {
//        setUp();
//        try {
//            Timestamp lastUpdatedBefore = addedOne.getLastModified();
//            AccountEntity accountOne = new AccountEntity();
//            accountOne.setId(addedOne.getId());
//            accountOne.setAccountName("UpdatedTestAccountName");
//            AccountDto updatedAccount = accountDAO.updateOneAccount(accountOne);
//            Assert.assertNotEquals(lastUpdatedBefore, updatedAccount.getLastModified());
//        } catch (Error error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testUpdatingAccountDoesNotOverwriteNotChangedPropertiesWithNulls() {
//        setUp();
//        try {
//            AccountEntity accountOne = new AccountEntity();
//            accountOne.setId(addedOne.getId());
//            accountOne.setAccountName("UpdatedTestAccountName");
//            AccountDto updatedAccount = accountDAO.updateOneAccount(accountOne);
//            Assert.assertNotEquals("TestCity", updatedAccount.getLastModified());
//        } catch (Error error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testUpdatingAccountThatDoesNotExistsShouldThrowNotFoundException() {
//        setUp();
//        try {
//            AccountEntity accountOne = new AccountEntity();
//            accountOne.setId(-1);
//            accountOne.setAccountName("UpdatedTestAccountName");
//            accountDAO.updateOneAccount(accountOne);
//            Assert.fail();
//        } catch (NotFoundException error) {
//            Assert.assertEquals("Account was not found while an attempt of making update.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testDeleteAccountPropertyShouldBeSetToTrue() {
//        setUp();
//        try {
//            accountDAO.deleteOneAccount(addedTwo.getId());
//            Assert.assertTrue(accountDAO.findById(addedTwo.getId()).get().isDeleted());
//        } catch (Error error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testDeleteAccountThatDoesNotExistsShouldThrowNotFoundException() {
//        setUp();
//        try {
//            accountDAO.deleteOneAccount(-1);
//            Assert.fail();
//        } catch (NotFoundException error) {
//            Assert.assertEquals("Account you wanted to delete does not exist.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testUpdateAccountThatWasDeletedShouldThrowUpdateErrorException() {
//        setUp();
//        try {
//            accountDAO.deleteOneAccount(addedOne.getId());
//            AccountEntity accountOne = new AccountEntity();
//            accountOne.setId(addedOne.getId());
//            accountOne.setAccountName("UpdatedTestAccountName");
//            accountDAO.updateOneAccount(accountOne);
//            Assert.fail();
//        } catch (UpdateErrorException error) {
//            Assert.assertEquals("You can't update deleted account.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testListAllEANNumbersForAccount() {
//        setUp();
//        try {
//            List<AccountEanDto> listOfEans = accountEanDAO.findListOfAccountEANNumbers(addedOne.getId());
//            Assert.assertEquals(2, listOfEans.size());
//        } catch (Exception error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testListAllEANNumberForAccountThatDoesNotExistsReturnsEmptyList() {
//        setUp();
//        try {
//            List<AccountEanDto> listOfEans = accountEanDAO.findListOfAccountEANNumbers(-1);
//            Assert.assertEquals(listOfEans.size(), 0);
//        } catch (Exception error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testAddEANNumberToAnAccount() {
//        setUp();
//        try {
//            AccountEanEntity accountEanEntity = new AccountEanEntity();
//            accountEanEntity.setEanNumber("854678985");
//            accountEanEntity.setAccountId(addedOne.getId());
//            List<AccountEanDto> accountEanDtoListBefore = accountEanDAO.findListOfAccountEANNumbers(addedOne.getId());
//            accountEanDAO.addOneEanNumber(accountEanEntity);
//            List<AccountEanDto> accountEanDtoListAfter = accountEanDAO.findListOfAccountEANNumbers(addedOne.getId());
//            Assert.assertNotEquals(accountEanDtoListBefore.size(), accountEanDtoListAfter.size());
//            Assert.assertEquals(3, accountEanDtoListAfter.size());
//        } catch (Exception error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testAddExistingEANNumberToTheSameAccountShouldThroDuplicateException() {
//        setUp();
//        try {
//            AccountEanEntity accountEanEntity = new AccountEanEntity();
//            accountEanEntity.setEanNumber("123456789");
//            accountEanEntity.setAccountId(addedOne.getId());
//            accountEanDAO.addOneEanNumber(accountEanEntity);
//            Assert.fail();
//        } catch (DuplicateException error) {
//            Assert.assertEquals("The EAN number for account already exists.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testAddingEANNumberToAnAccountThatDoesNotExist() {
//        setUp();
//        try {
//            AccountEanEntity accountEanEntity = new AccountEanEntity();
//            accountEanEntity.setEanNumber("123456789");
//            accountEanEntity.setAccountId(-1);
//            accountEanDAO.addOneEanNumber(accountEanEntity);
//            Assert.fail();
//        } catch (NotFoundException error) {
//            Assert.assertEquals("Account to which you wanted to add EAN was not found.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testDeleteEANNumberThatDoesNotExistThrowsNotFoundException() {
//        setUp();
//        try {
//            accountEanDAO.deleteOneEanNumber(-1, "-1");
//            Assert.fail();
//        } catch (NotFoundException error) {
//            Assert.assertEquals("No account found associated with provided ID and EAN number.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testDeleteEANNumberShouldBeTrue() {
//        setUp();
//        try {
//            boolean result = accountEanDAO.deleteOneEanNumber(addedOne.getId(), "123456789");
//            Assert.assertTrue(result);
//        } catch (Exception error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testFindingAccountType() {
//        setUp();
//        try {
//            AccountTypeDto found = accountTypeDAO.findOneAccountType(idAccountType2);
//            Assert.assertNotNull(found);
//        } catch (Exception error) {
//            Assert.fail();
//        } finally {
//            setDown();
//        }
//    }
//
//    @Test
//    public void testFindingAccountTypeThatDoesNotExistShouldThrowNotFoundException() {
//        setUp();
//        try {
//            AccountTypeDto found = accountTypeDAO.findOneAccountType(-1);
//            Assert.fail();
//        } catch (NotFoundException error) {
//            Assert.assertEquals("Account type no found for ID.", error.getMessage());
//        } finally {
//            setDown();
//        }
//    }


}
