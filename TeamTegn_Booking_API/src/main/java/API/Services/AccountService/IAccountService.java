package API.Services.AccountService;


import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.AccountForUpdateDto;
import Shared.ToReturn.AccountDto;

import java.util.List;


public interface IAccountService {
    AccountDto addAccount(AccountForCreationDto account);

    boolean deleteAccount(int id);

    AccountDto findAccount(int id);

    AccountDto updateAccount(AccountForUpdateDto account);

    List<AccountDto> listAccounts();
}
