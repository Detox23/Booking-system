package API.Repository.Account;

import API.Models.Database_Entities.AccountTypeEntity;
import Shared.ToReturn.AccountTypeDto;

import java.util.List;

public interface AccountTypeCustom {
    AccountTypeDto addAccountType(AccountTypeEntity accountType);
    AccountTypeDto updateAccountType(AccountTypeEntity accountType);
    boolean deleteAccountType(int id);
    List<AccountTypeDto> listAccountTypes(boolean showDeleted);
    AccountTypeDto findAccountType(int id);
}
