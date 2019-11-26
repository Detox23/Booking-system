package API.Repository.Account;

import API.Database_Entities.AccountEanEntity;
import Shared.ToReturn.AccountEanDto;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface AccountEanCustom {
    boolean addOneEanNumber(@NotNull AccountEanEntity accountEanEntity);

    boolean deleteOneEanNumber(int accountID, String EANNumber);

    List<AccountEanDto> findListOfAccountEANNumbers(int accountID);
}
