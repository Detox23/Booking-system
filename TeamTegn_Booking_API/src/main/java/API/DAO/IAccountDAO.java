package API.DAO;

import Objects.Factory.Database_Entities.AccountEanEntity;
import Objects.Factory.Database_Entities.AccountEntity;
import Objects.Factory.Database_Entities.AccountTypeEntity;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

public interface IAccountDAO {
    boolean addAccount(AccountEntity account, List<String> eans);
    AccountTypeEntity findAccountType(int id);
    boolean deleteAccount(int Id);
    boolean update(AccountEntity account);
    AccountEntity findAccountByID(int id);
    List<AccountEntity> list();

}
