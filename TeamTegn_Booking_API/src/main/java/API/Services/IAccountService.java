package API.Services;


import Shared.ToReturn.AccountDto;
import Shared.ForCreation.AccountForCreationDto;

import java.util.List;

public interface IAccountService {
    boolean addAccount(AccountForCreationDto account);

    boolean deleteAccount(int id);

    AccountDto findAccount(int id);

    List<AccountDto> list();
}
