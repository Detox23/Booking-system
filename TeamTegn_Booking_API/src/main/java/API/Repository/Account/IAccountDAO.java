package API.Repository.Account;

import Objects.Factory.Database_Entities.AccountEanEntity;
import Objects.Factory.Database_Entities.AccountEntity;
import Objects.Factory.Database_Entities.AccountTypeEntity;
import Shared.ToReturn.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IAccountDAO extends JpaRepository<AccountEntity, Integer>{
    AccountTypeEntity findAccountType(int id);
    AccountDto addAccount(AccountEntity account, List<String> eans);
    boolean deleteAccount(int Id);
    List<AccountEanEntity> findAccountEANNumber(int accountID);
    List<AccountEntity> list();
    AccountEntity findAccountByID(int id);
    AccountEntity updateAccount(AccountEntity accountEntity);
   }
