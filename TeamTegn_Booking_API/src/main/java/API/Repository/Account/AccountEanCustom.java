package API.Repository.Account;

import API.Database_Entities.AccountEanEntity;
import Shared.ToReturn.AccountEanDto;

import java.util.List;

public interface AccountEanCustom {
    boolean addOneEanNumber(AccountEanEntity accountEanEntity);

    boolean deleteOneEanNumber(int accountID, String EANNumber);

    List<AccountEanDto> findListOfAccountEANNumbers(int accountID);
}