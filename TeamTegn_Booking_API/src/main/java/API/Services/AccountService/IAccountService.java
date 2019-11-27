package API.Services.AccountService;


import Shared.ForCreation.AccountEanForCreationDto;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.AccountForUpdateDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.AccountEanDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAccountService {
    AccountDto addAccount(AccountForCreationDto account);

    boolean deleteAccount(int id);

    AccountDto findAccount(int id);

    AccountDto update(AccountForUpdateDto account);

    boolean deleteAccountComment(int accountID, int commentID);

    boolean deleteEAN(int accountID, String eanNumber);

    boolean addEAN(AccountEanForCreationDto accountEan);

    List<AccountEanDto> findListOfEANNumbersForAccount(int accountID);

    List<AccountDto> list();
}
