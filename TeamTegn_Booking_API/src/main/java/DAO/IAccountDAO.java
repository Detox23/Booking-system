package DAO;
import Objects.Factory.Account_Entity;
import java.util.List;

public interface IAccountDAO {
    void save(Account_Entity a);
    List<Account_Entity> list();
}
