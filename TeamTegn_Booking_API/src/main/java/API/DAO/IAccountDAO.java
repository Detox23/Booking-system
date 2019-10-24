package API.DAO;
import Objects.Factory.Database_Entities.Account;
import java.util.List;

public interface IAccountDAO {
    boolean addAccount(Account account);
    boolean deleteAccount(int Id);
    boolean update(Account account);
    Account findAccountByID(int id);
    List<Account> list();

}
