package API.DAO;

import Objects.Factory.Database_Entities.AccountEanEntity;

import java.util.List;

public interface IAccountEanNumberDAO {
    List<AccountEanEntity> findAccountEANNumber(int accountID);
}
