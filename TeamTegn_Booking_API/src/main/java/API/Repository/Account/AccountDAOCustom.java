package API.Repository.Account;

import API.Database_Entities.AccountEntity;
import Shared.ToReturn.AccountDto;

import java.util.List;

public interface AccountDAOCustom {
    List<AccountDto> listAllAccounts();

    AccountDto addOneAccount(AccountEntity account, List<String> eans, int accountTypeId);

    boolean deleteOneAccount(int Id);

    AccountDto getOneAccount(int id);

    AccountDto updateOneAccount(AccountEntity accountEntity);
}
