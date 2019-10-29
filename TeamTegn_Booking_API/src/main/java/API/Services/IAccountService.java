package API.Services;


import Shared.ToReturn.AccountDto;
import Shared.ForCreation.AccountForCreationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAccountService {
    boolean addAccount(AccountForCreationDto account);

    boolean deleteAccount(int id);

    AccountDto findAccount(int id);

    boolean update(AccountForCreationDto account);

    boolean deleteAccountComment(int accountID, int commentID);

    List<AccountDto> list();
}
