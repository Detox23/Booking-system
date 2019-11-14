package API.Repository.Account;

import API.Exceptions.*;
import API.Database_Entities.AccountEntity;
import Shared.ToReturn.AccountDto;
import java.util.List;

public interface AccountDAOCustom {
    AccountDto addAccount(AccountEntity account, List<String> eans, int accountTypeId) throws NoAccountIDAfterSavingException, MappingAccountDatabseToDtoException;
    boolean deleteAccount(int Id);
    List<AccountDto> list();
    AccountDto findAccountByID(int id);
    AccountDto updateAccount(AccountEntity accountEntity) throws AccountNotExistsUpdateException, UpdateErrorException, UpdatePatchException;
}
