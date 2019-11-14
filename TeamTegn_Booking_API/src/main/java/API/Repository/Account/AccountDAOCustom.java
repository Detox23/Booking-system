package API.Repository.Account;

import API.Exceptions.*;
import API.Database_Entities.AccountEntity;
import Shared.ToReturn.AccountDto;
import java.util.List;

public interface AccountDAOCustom {
    List<AccountDto> listAllAccounts();
    AccountDto addAccount(AccountEntity account, List<String> eans, int accountTypeId) throws NoAccountIDAfterSavingException, MappingAccountDatabseToDtoException;
    boolean deleteAccount(int Id);
    AccountDto getOneAccount(int id);
    AccountDto updateAccount(AccountEntity accountEntity) throws AccountNotExistsUpdateException, UpdateErrorException, UpdatePatchException;
}
