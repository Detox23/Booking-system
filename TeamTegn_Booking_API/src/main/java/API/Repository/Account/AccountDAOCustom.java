package API.Repository.Account;

import Shared.ToReturn.AccountDto;

import java.util.List;

public interface AccountDAOCustom {
    List<AccountDto> listAllAccounts();

    AccountDto addOneAccount(AccountEntity account, List<String> eans, int accountTypeId);

    boolean deleteOneAccount(int Id);

    AccountDto getOneAccount(int id);

    AccountDto updateOneAccount(AccountEntity accountEntity);
}
