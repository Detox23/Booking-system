package API.Repository.Account;

import Shared.ToReturn.AccountTypeDto;

public interface IAccountTypeCustom {
    AccountTypeDto findAccountType(int id);
}
