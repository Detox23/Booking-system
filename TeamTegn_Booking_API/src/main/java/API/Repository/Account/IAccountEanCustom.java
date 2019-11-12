package API.Repository.Account;

import API.Exceptions.AccountNotFoundWhileAddingEANNumberException;
import API.Exceptions.AddingTheSameEANNumberToSameAccountException;
import Objects.Factory.Database_Entities.AccountEanEntity;
import Shared.ToReturn.AccountEanDto;

import java.util.List;

public interface IAccountEanCustom {
    boolean addEanNumber(AccountEanEntity accountEanEntity) throws AccountNotFoundWhileAddingEANNumberException, AddingTheSameEANNumberToSameAccountException;
    boolean deleteEanNumber(int accountID, String EANNumber);
    List<AccountEanDto> findAccountEANNumber(int accountID);
}
