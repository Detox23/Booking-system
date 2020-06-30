package API.Services.AccountService;

import Shared.ForCreation.AccountTypeForCreationDto;
import Shared.ForCreation.AccountTypeForUpdateDto;
import Shared.ToReturn.AccountTypeDto;
import java.util.List;

public interface IAccountTypeService {
    AccountTypeDto addAccountType(AccountTypeForCreationDto account);

    boolean deleteAccountType(int id);

    AccountTypeDto findAccountType(int id);

    AccountTypeDto updateAccountType(AccountTypeForUpdateDto account);

    List<AccountTypeDto> listAccountTypes(boolean showDeleted);
}
