package API.Repository.Account;

import API.Exceptions.MappingAccountDatabseToDtoException;
import API.Exceptions.AccountNotExistsUpdateException;
import API.Database_Entities.AccountEntity;
import API.Exceptions.NoAccountIDAfterSavingException;
import API.Exceptions.UpdateErrorException;
import Shared.ToReturn.AccountDto;
import java.util.List;

public interface IAccountDAOCustom {
    AccountDto addAccount(AccountEntity account, List<String> eans, int accountTypeId) throws NoAccountIDAfterSavingException, MappingAccountDatabseToDtoException;
    boolean deleteAccount(int Id);
    List<AccountDto> list();
    AccountDto findAccountByID(int id);
    AccountDto updateAccount(AccountEntity accountEntity) throws AccountNotExistsUpdateException, UpdateErrorException;
}
