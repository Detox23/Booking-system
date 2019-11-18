package API.Repository.Account;

import Shared.ToReturn.AccountTypeDto;

public interface AccountTypeCustom {
    AccountTypeDto findOneAccountType(int id);
}
