package API.Service.AccountService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AccountEanEntity;
import API.Models.Database_Entities.AccountEntity;
import API.Models.Database_Entities.ServiceUserAccountEntity;
import API.Repository.Account.AccountDAO;
import API.Repository.Account.AccountEanDAO;
import API.Repository.ServiceUser.ServiceUserAccountsDAO;
import API.Repository.ServiceUser.ServiceUserDAO;
import API.Services.AccountService.AccountService;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.AccountForUpdateDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.ServiceUserAccountListDto;
import Shared.ToReturn.ServiceUserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    private AccountDto getAccountReturn() {
        AccountDto accountDto = new AccountDto();
        accountDto.setCity("CityTest");
        return accountDto;
    }

    private List<AccountDto> getAccountList() {
        List<AccountDto> toReturn = new ArrayList<>();
        toReturn.add(new AccountDto());
        toReturn.add(new AccountDto());
        toReturn.add(new AccountDto());
        toReturn.add(new AccountDto());
        toReturn.add(new AccountDto());
        toReturn.add(new AccountDto());
        toReturn.add(new AccountDto());
        return toReturn;
    }

    private List<AccountEanEntity> getAccountEanReturn() {
        List<AccountEanEntity> toReturn = new ArrayList<>();
        toReturn.add(new AccountEanEntity());
        toReturn.add(new AccountEanEntity());
        toReturn.add(new AccountEanEntity());
        return toReturn;
    }

    private List<ServiceUserAccountEntity> getServiceUserAccountsReturn() {
        List<ServiceUserAccountEntity> toReturn = new ArrayList<>();
        toReturn.add(new ServiceUserAccountEntity());
        toReturn.add(new ServiceUserAccountEntity());
        toReturn.add(new ServiceUserAccountEntity());
        toReturn.add(new ServiceUserAccountEntity());
        toReturn.add(new ServiceUserAccountEntity());
        toReturn.add(new ServiceUserAccountEntity());
        return toReturn;
    }

    private ServiceUserAccountListDto getServiceUserReturn() {
        return new ServiceUserAccountListDto();
    }

    @Test
    public void testAddingAccount() {
        try {
            AccountDAO mockAccountDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountDAO.class);
            AccountEanDAO mockAccountEans = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountEanDAO.class);
            ServiceUserAccountsDAO mockServiceUserAccounts = SpringBeanMockUtil.mockFieldOnBean(accountService, ServiceUserAccountsDAO.class);
            ServiceUserDAO mockServiceUserDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, ServiceUserDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountReturn()).when(mockAccountDAO).addAccount(any(AccountEntity.class), anyList(), anyList());
            Mockito.doReturn(getAccountEanReturn()).when(mockAccountEans).findAllByAccountId(anyInt());
            Mockito.doReturn(getServiceUserAccountsReturn()).when(mockServiceUserAccounts).findAllByAccountIdIs(anyInt());
            Mockito.doReturn(getServiceUserReturn()).when(mockServiceUserDAO).listFillMethod(anyInt());
            AccountForCreationDto accountForCreationDto = new AccountForCreationDto();
            accountForCreationDto.setAccountName("AccountName");
            accountForCreationDto.setEan(new ArrayList<>());
            accountForCreationDto.setServiceUsersIds(new ArrayList<>());
            accountForCreationDto.setAccountTypeId(1);
            accountForCreationDto.setParentId(1);
            accountForCreationDto.setPrimaryContactId(1);
            AccountDto result = accountService.addAccount(accountForCreationDto);
            Assert.assertEquals(3, result.getEan().size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testDeletingAccount() {
        try {
            AccountDAO mockAccountDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(true).when(mockAccountDAO).deleteAccount(anyInt());
            boolean result = accountService.deleteAccount(1);
            Assert.assertTrue(result);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testUpdatingAccount() {
        try {
            AccountDAO mockAccountDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountDAO.class);
            AccountEanDAO mockAccountEans = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountEanDAO.class);
            ServiceUserAccountsDAO mockServiceUserAccounts = SpringBeanMockUtil.mockFieldOnBean(accountService, ServiceUserAccountsDAO.class);
            ServiceUserDAO mockServiceUserDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, ServiceUserDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountReturn()).when(mockAccountDAO).updateAccount(any(AccountEntity.class), anyList(), anyList());
            Mockito.doReturn(getAccountEanReturn()).when(mockAccountEans).findAllByAccountId(anyInt());
            Mockito.doReturn(getServiceUserAccountsReturn()).when(mockServiceUserAccounts).findAllByAccountIdIs(anyInt());
            Mockito.doReturn(getServiceUserReturn()).when(mockServiceUserDAO).listFillMethod(anyInt());
            AccountForUpdateDto accountForUpdateDto = new AccountForUpdateDto();
            accountForUpdateDto.setId(1);
            accountForUpdateDto.setAccountName("AccountName");
            accountForUpdateDto.setEan(new ArrayList<>());
            accountForUpdateDto.setServiceUsers(new ArrayList<>());
            accountForUpdateDto.setAccountTypeId(1);
            accountForUpdateDto.setParentId(1);
            accountForUpdateDto.setPrimaryContactId(1);
            AccountDto result = accountService.updateAccount(accountForUpdateDto);
            Assert.assertEquals(6, result.getServiceUsersShort().size());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testListAccount() {
        try {
            AccountDAO mockAccountDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountDAO.class);
            AccountEanDAO mockAccountEans = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountEanDAO.class);
            ServiceUserAccountsDAO mockServiceUserAccounts = SpringBeanMockUtil.mockFieldOnBean(accountService, ServiceUserAccountsDAO.class);
            ServiceUserDAO mockServiceUserDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, ServiceUserDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountList()).when(mockAccountDAO).listAccounts();
            Mockito.doReturn(getAccountEanReturn()).when(mockAccountEans).findAllByAccountId(anyInt());
            Mockito.doReturn(getServiceUserAccountsReturn()).when(mockServiceUserAccounts).findAllByAccountIdIs(anyInt());
            Mockito.doReturn(getServiceUserReturn()).when(mockServiceUserDAO).listFillMethod(anyInt());
            Assert.assertEquals(7, accountService.listAccounts().size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testFindingAccount() {
        try {
            AccountDAO mockAccountDAO = SpringBeanMockUtil.mockFieldOnBean(accountService, AccountDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountReturn()).when(mockAccountDAO).findAccount(anyInt());
            Assert.assertEquals("CityTest", accountService.findAccount(1).getCity());
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
