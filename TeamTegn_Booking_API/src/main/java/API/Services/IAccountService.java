package API.Services;

import Shared.AccountDto;
import Shared.AccountForCreationDto;

import java.util.List;

public interface IAccountService
{
    boolean addAccount(AccountForCreationDto account);
    boolean deleteAccount(int id);
    AccountDto findAccount(int id);
    List<AccountDto> list();
}
