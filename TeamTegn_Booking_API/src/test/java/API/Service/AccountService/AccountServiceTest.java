package API.Service.AccountService;

import API.Configurations.SpringBeanMockUtil;
import API.Database_Entities.AccountEanEntity;
import API.Database_Entities.AccountEntity;
import API.Database_Entities.AccountTypeEntity;
import API.Exceptions.NotFoundException;
import API.MainApplicationClass;
import API.Repository.Account.AccountDAO;
import API.Repository.Account.AccountDAOImpl;
import API.Repository.Account.AccountEanDAO;
import API.Repository.Account.AccountTypeDAO;
import API.Services.AccountService.AccountService;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.AccountForUpdateDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.AccountEanDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class AccountServiceTest {


    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountDAOImpl accountDAOImpl;

    private int idAccountType;
    private int idAccountType2;
    private AccountDto addedOne;
    private AccountDto addedTwo;

    private void setUp() {

        AccountForCreationDto accountOne = new AccountForCreationDto();
        accountOne.setAccountName("TestSetUpAccountName");
        accountOne.setCity("TestCity");


        AccountForCreationDto accountTwo = new AccountForCreationDto();
        accountTwo.setAccountName("TestSetUpAccountName2");

        AccountTypeEntity accountTypeEntity = new AccountTypeEntity();
        accountTypeEntity.setAccountType("TestSetUpAccountType");
        accountTypeEntity.setId(1);
        AccountTypeEntity result = accountTypeDAO.saveAndFlush(accountTypeEntity);

        idAccountType = result.getId();

        addedOne = accountDAO.addOneAccount(modelMapper.map(accountOne, AccountEntity.class), null, result.getId());
        addedTwo = accountDAO.addOneAccount(modelMapper.map(accountTwo, AccountEntity.class), null, result.getId());
    }


    private void setDown() {
        accountDAO.deleteAllInBatch();
        accountDAO.flush();
        accountTypeDAO.deleteAllInBatch();
        accountTypeDAO.flush();
    }

    private AccountForCreationDto getAccountForCreation() {
        AccountForCreationDto accountForCreationDto = new AccountForCreationDto();
        accountForCreationDto.setEan(new ArrayList<>());
        return accountForCreationDto;
    }

    private AccountForUpdateDto getAccountForUpdate() {
        AccountForUpdateDto accountForUpdateDto = new AccountForUpdateDto();
        accountForUpdateDto.setAccountTypeID(1);
        return accountForUpdateDto;
    }

    private AccountDto returnedAccount() {
        AccountDto returned = new AccountDto();
        returned.setId(654);
        returned.setAccountTypeID(5);
        returned.setCity("TestCity");
        return returned;
    }

    private List<AccountEanDto> getListOfAccountEanDTOs() {
        List<AccountEanDto> eans = new ArrayList<>();
        AccountEanDto eanDto = new AccountEanDto();
        eanDto.setAccountId(0);
        eanDto.setEanNumber("123456");
        eans.add(eanDto);
        AccountEanDto eanDto1 = new AccountEanDto();
        eanDto1.setAccountId(0);
        eanDto1.setEanNumber("654321");
        eans.add(eanDto1);
        return eans;
    }

    @Test
    public void testAddAccountShouldReturnObjectWithEANS() {
        try {
            setUp();
            AccountDAO mockAccountDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountDAO.class);
            AccountEanDAO mockAccountEANDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountEanDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(returnedAccount()).when(mockAccountDAO).addOneAccount(any(AccountEntity.class), anyList(), anyInt());
            Mockito.doReturn(getListOfAccountEanDTOs()).when(mockAccountEANDAO).findListOfAccountEANNumbers(anyInt());
            AccountDto result = accountService.addAccount(getAccountForCreation());
            Assert.assertEquals(2, result.getEan().size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            setDown();
        }
    }

    @Test
    public void testAddAccountShouldRollbackAddingAccountAndSizeShouldBeTwo() {
        try {
            setUp();
            AccountEanDAO mockAccountEANDAO = SpringBeanMockUtil.mockFieldOnBean(accountDAOImpl, AccountEanDAO.class);
            MockitoAnnotations.initMocks(this);
            List<String> eans = new ArrayList<>();
            eans.add("123456");
            AccountForCreationDto accountForCreationDto = new AccountForCreationDto();
            accountForCreationDto.setEan(eans);
            accountForCreationDto.setAccountTypeId(1);
            accountForCreationDto.setAccountName("TestRollbackAdding");
            Mockito.doThrow(NoSuchElementException.class).when(mockAccountEANDAO).addOneEanNumber(any(AccountEanEntity.class));
            accountService.addAccount(accountForCreationDto);
            Assert.fail();
        } catch (NotFoundException expectedException) {
            Assert.assertEquals(accountService.list().size(), 2);
        } catch (Exception e) {
            Assert.fail();
        } finally {
            setDown();
        }
    }

    @Test
    public void testFindAccountServiceAccounts() {
        try {
            AccountDAO mockAccountDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountDAO.class);
            AccountEanDAO mockAccountEANDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountEanDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(returnedAccount()).when(mockAccountDAO).getOneAccount(anyInt());
            Mockito.doReturn(getListOfAccountEanDTOs()).when(mockAccountEANDAO).findListOfAccountEANNumbers(anyInt());
            Assert.assertEquals(2, accountService.findAccount(5).getEan().size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testUpdateAccountService() {
        try {
            AccountTypeDAO mockAccountTypeDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountTypeDAO.class);
            AccountDAO mockAccountDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountDAO.class);
            AccountEanDAO mockAccountEANDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountEanDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(Optional.of((Object) new AccountTypeEntity())).when(mockAccountTypeDAO).findById(anyInt());
            Mockito.doReturn(returnedAccount()).when(mockAccountDAO).updateOneAccount(any(AccountEntity.class));
            Mockito.doReturn(getListOfAccountEanDTOs()).when(mockAccountEANDAO).findListOfAccountEANNumbers(anyInt());
            AccountDto result = accountService.update(getAccountForUpdate());
            Assert.assertEquals("TestCity", result.getCity());
        } catch (Exception e) {
            Assert.fail();
        }

    }

    @Test
    public void testAccountServiceUpdateHandleNotFoundException(){
        try {
            AccountTypeDAO mockAccountTypeDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountTypeDAO.class);
            AccountDAO mockAccountDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountDAO.class);
            AccountEanDAO mockAccountEANDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountEanDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doThrow(new NoSuchElementException()).when(mockAccountTypeDAO).findById(anyInt());
            Mockito.doReturn(returnedAccount()).when(mockAccountDAO).updateOneAccount(any(AccountEntity.class));
            Mockito.doReturn(getListOfAccountEanDTOs()).when(mockAccountEANDAO).findListOfAccountEANNumbers(anyInt());
            AccountDto result = accountService.update(getAccountForUpdate());
            Assert.fail();
        } catch (NotFoundException e) {
            Assert.assertEquals("Account type is not found. Update cancelled.", e.getMessage());
        }
    }

    @Test
    public void testAccountShouldReturnNull(){
        try {
            AccountTypeDAO mockAccountTypeDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountTypeDAO.class);
            AccountDAO mockAccountDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountDAO.class);
            AccountEanDAO mockAccountEANDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountEanDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(Optional.of((Object) new AccountTypeEntity())).when(mockAccountTypeDAO).findById(anyInt());
            Mockito.doReturn(null).when(mockAccountDAO).updateOneAccount(any(AccountEntity.class));
            Mockito.doReturn(getListOfAccountEanDTOs()).when(mockAccountEANDAO).findListOfAccountEANNumbers(anyInt());
            AccountDto result = accountService.update(getAccountForUpdate());
            Assert.assertNull(result);
        } catch (NotFoundException e) {
            Assert.fail();
        }
    }

}
