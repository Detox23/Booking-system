package DAO;
import Objects.Factory.Account;
import java.util.List;

public interface IAccountDAO {
    boolean addAccount(Account account);
    boolean deleteAccount(int Id);
    boolean update(Account account);
    Account findAccountByID(int id);
    List<Account> list();

}
