package API.Services;


import Shared.ForCreation.AccountEanForCreationDto;
import Shared.ToReturn.AccountDto;
import Shared.ForCreation.AccountForCreationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccountService {
    AccountDto addAccount(AccountForCreationDto account);
    boolean deleteAccount(int id);
    AccountDto findAccount(int id);
    boolean update(AccountForCreationDto account, int id);
    boolean deleteAccountComment(int accountID, int commentID);
    boolean deleteEAN(int accountID, String eanNumber);
    boolean addEAN(AccountEanForCreationDto accountEan);
    List<AccountDto> list();
}
