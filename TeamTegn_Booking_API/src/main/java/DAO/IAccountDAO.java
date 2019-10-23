package DAO;
import Objects.Factory.Account;
import java.util.List;

public interface IAccountDAO {
    boolean addAccount(Account account);
    boolean deleteAccount(String Id);
    Account findAccountByName(String name);
    Account findAccountById(String Id);
    List<Account> list();
    void save(Account a);

}
