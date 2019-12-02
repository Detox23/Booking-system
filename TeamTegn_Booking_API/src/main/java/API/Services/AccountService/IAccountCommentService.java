package API.Services.AccountService;

import Shared.ForCreation.AccountCommentDto;
import Shared.ForCreation.AccountCommentForCreationDto;
import Shared.ForCreation.AccountCommentForUpdateDto;

import java.util.List;

public interface IAccountCommentService {

    AccountCommentDto addAccountComment(AccountCommentForCreationDto accountComment);

    AccountCommentDto updateAccountComment(AccountCommentForUpdateDto accountComment);

    boolean deleteAccountComment(int accountID, int id);

    AccountCommentDto findAccountComment(int id);

    List<AccountCommentDto> listAccountComments(int accountID);
}
