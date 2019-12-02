package API.Repository.Account;

import API.Database_Entities.AccountEntity;
import Shared.ToReturn.AccountDto;

import java.util.List;

public interface AccountDAOCustom {
    List<AccountDto> listAccounts();

    AccountDto addAccount(AccountEntity account, List<String> eans,  List<Integer> accountServiceUser);

    boolean deleteAccount(int Id);

    AccountDto findAccount(int id);

    AccountDto updateAccount(AccountEntity account, List<String> eans,  List<Integer> accountServiceUser);
}
