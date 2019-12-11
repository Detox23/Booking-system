package API.Service.AccountService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AccountCommentEntity;
import API.Repository.Account.AccountCommentDAO;
import API.Services.AccountService.AccountCommentService;
import Shared.ForCreation.AccountCommentForCreationDto;
import Shared.ForCreation.AccountCommentForUpdateDto;
import Shared.ToReturn.AccountCommentDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class AccountCommentServiceTest {

    @Autowired
    private AccountCommentService accountCommentService;

    private AccountCommentDto getAccountComment() {
        AccountCommentDto accountComment = new AccountCommentDto();
        accountComment.setId(1);
        accountComment.setAccountId(1);
        accountComment.setCommentDate(new Timestamp(System.currentTimeMillis()));
        accountComment.setCommentText("Returned comment text");
        accountComment.setUserId(1);
        return accountComment;
    }

    private List<AccountCommentDto> getAccountComments() {
        List<AccountCommentDto> listToReturn = new ArrayList<>();
        listToReturn.add(new AccountCommentDto());
        listToReturn.add(new AccountCommentDto());
        listToReturn.add(new AccountCommentDto());
        listToReturn.add(new AccountCommentDto());
        return listToReturn;
    }

    @Test
    public void testAddingAccountCommentShouldPass() {
        try {
            AccountCommentDAO mockAccountComment = SpringBeanMockUtil.mockFieldOnBean(accountCommentService, AccountCommentDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountComment()).when(mockAccountComment).addAccountComment(any(AccountCommentEntity.class));
            AccountCommentForCreationDto accountCommentForCreationDto = new AccountCommentForCreationDto();
            accountCommentForCreationDto.setAccountId(0);
            accountCommentForCreationDto.setCommentText("Returned comment text");
            AccountCommentDto addedComment = accountCommentService.addAccountComment(accountCommentForCreationDto);
            Assert.assertEquals("Returned comment text", addedComment.getCommentText());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testFindingAccountCommentShouldFind() {
        try {
            AccountCommentDAO mockAccountComment = SpringBeanMockUtil.mockFieldOnBean(accountCommentService, AccountCommentDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountComment()).when(mockAccountComment).findAccountComment(anyInt());
            AccountCommentDto foundComment = accountCommentService.findAccountComment(1);
            Assert.assertEquals("Returned comment text", foundComment.getCommentText());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testUpdatingAccountCommentNameShouldMatch() {
        try {
            AccountCommentDAO mockAccountComment = SpringBeanMockUtil.mockFieldOnBean(accountCommentService, AccountCommentDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountComment()).when(mockAccountComment).updateAccountComment(any(AccountCommentEntity.class));
            AccountCommentForUpdateDto accountCommentForUpdateDto = new AccountCommentForUpdateDto();
            accountCommentForUpdateDto.setAccountId(0);
            accountCommentForUpdateDto.setId(1);
            accountCommentForUpdateDto.setCommentText("Returned comment text");
            AccountCommentDto updatedAccount = accountCommentService.updateAccountComment(accountCommentForUpdateDto);
            Assert.assertEquals("Returned comment text", updatedAccount.getCommentText());
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testDeletingAccountCommentShouldBeTrue() {
        try {
            AccountCommentDAO mockAccountComment = SpringBeanMockUtil.mockFieldOnBean(accountCommentService, AccountCommentDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(true).when(mockAccountComment).deleteAccountComment(anyInt(), anyInt());
            boolean result = accountCommentService.deleteAccountComment(1, 1);
            Assert.assertTrue(result);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testListingAccountCommentsSizeShouldBeFour() {
        try {
            AccountCommentDAO mockAccountComment = SpringBeanMockUtil.mockFieldOnBean(accountCommentService, AccountCommentDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAccountComments()).when(mockAccountComment).listAccountComments(anyInt());
            List<AccountCommentDto> returnList = accountCommentService.listAccountComments(1);
            Assert.assertEquals(4, returnList.size());
        } catch (Exception e) {
            Assert.fail();
        }
    }

}
