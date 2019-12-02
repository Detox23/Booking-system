package API.Repository.Account;

import API.Models.Database_Entities.AccountCommentEntity;
import Shared.ToReturn.AccountCommentDto;

import java.util.List;

public interface AccountCommentDAOCustom {
    AccountCommentDto addAccountComment(AccountCommentEntity accountComment);

    AccountCommentDto updateAccountComment(AccountCommentEntity accountComment);

    boolean deleteAccountComment(int account, int id);

    AccountCommentDto findAccountComment(int id);

    List<AccountCommentDto> listAccountComments(int accountID);
}
