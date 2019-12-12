package API.Service.AccountService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AccountTypeEntity;
import API.Repository.Account.AccountTypeDAO;
import API.Services.AccountService.AccountTypeService;
import Shared.ForCreation.AccountTypeForCreationDto;
import Shared.ForCreation.AccountTypeForUpdateDto;
import Shared.ToReturn.AccountTypeDto;
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
public class AccountTypeServiceTest {

    @Autowired
    private AccountTypeService accountTypeService;

    private AccountTypeDto getAccountType() {
        AccountTypeDto accountType = new AccountTypeDto();
        accountType.setAccountType("Type returned");
        accountType.setId(1);
        return accountType;
    }

    private List<AccountTypeDto> getAccountTypeList() {
        List<AccountTypeDto> listToReturn = new ArrayList<>();
        listToReturn.add(new AccountTypeDto());
        listToReturn.add(new AccountTypeDto());
        listToReturn.add(new AccountTypeDto());
        listToReturn.add(new AccountTypeDto());
        return listToReturn;
    }

    @Test
    public void testAddingAccountCommentShouldPass() {
        try {
            AccountTypeDAO mockAccountType = SpringBeanMockUtil.mockFieldOnBean(accountTypeService, AccountTypeDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountType()).when(mockAccountType).addAccountType(any(AccountTypeEntity.class));
            AccountTypeDto addedAccountType = accountTypeService.addAccountType(new AccountTypeForCreationDto());
            Assert.assertEquals("Type returned", addedAccountType.getAccountType());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testFindingAccountCommentShouldFind() {
        try {
            AccountTypeDAO mockAccountType = SpringBeanMockUtil.mockFieldOnBean(accountTypeService, AccountTypeDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountType()).when(mockAccountType).findAccountType(anyInt());
            AccountTypeDto foundAccountType = accountTypeService.findAccountType(1);
            Assert.assertEquals("Type returned", foundAccountType.getAccountType());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testUpdatingAccountCommentNameShouldMatch() {
        try {
            AccountTypeDAO mockAccountType = SpringBeanMockUtil.mockFieldOnBean(accountTypeService, AccountTypeDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountType()).when(mockAccountType).updateAccountType(any(AccountTypeEntity.class));
            AccountTypeDto updatedAccountType = accountTypeService.updateAccountType(new AccountTypeForUpdateDto());
            Assert.assertEquals("Type returned", updatedAccountType.getAccountType());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testDeletingAccountCommentShouldBeTrue() {
        try {
            AccountTypeDAO mockAccountType = SpringBeanMockUtil.mockFieldOnBean(accountTypeService, AccountTypeDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(true).when(mockAccountType).deleteAccountType(anyInt());
            boolean result = accountTypeService.deleteAccountType(1);
            Assert.assertTrue(result);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testListingAccountCommentsSizeShouldBeFour() {
        try {
            AccountTypeDAO mockAccountType = SpringBeanMockUtil.mockFieldOnBean(accountTypeService, AccountTypeDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountTypeList()).when(mockAccountType).listAccountTypes(anyBoolean());
            List<AccountTypeDto> returnList = accountTypeService.listAccountTypes(true);
            Assert.assertEquals(4, returnList.size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

}
