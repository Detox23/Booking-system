package API.DAO;
import Objects.Factory.Database_Entities.AccountEntity;

import java.util.List;

public interface IAccountDAO {
    boolean addAccount(AccountEntity account);
    boolean deleteAccount(int Id);
    boolean update(AccountEntity account);
    AccountEntity findAccountByID(int id);
    List<AccountEntity> list();

}
