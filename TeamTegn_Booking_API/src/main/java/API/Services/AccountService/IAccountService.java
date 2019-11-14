package API.Services.AccountService;


import API.Exceptions.*;
import Shared.ForCreation.AccountEanForCreationDto;
import Shared.ToReturn.AccountDto;
import Shared.ForCreation.AccountForCreationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccountService {
    AccountDto addAccount(AccountForCreationDto account) throws NoAccountIDAfterSavingException, MappingAccountDatabseToDtoException, AccountNotFoundWhileAddingEANNumberException;
    boolean deleteAccount(int id);
    AccountDto findAccount(int id);
    AccountDto update(AccountDto account) throws AccountNotExistsUpdateException, UpdateErrorException;
    boolean deleteAccountComment(int accountID, int commentID);
    boolean deleteEAN(int accountID, String eanNumber);
    boolean addEAN(AccountEanForCreationDto accountEan) throws AccountNotFoundWhileAddingEANNumberException, AddingTheSameEANNumberToSameAccountException;
    List<AccountDto> list();
}
