package API.Repository.Account;

import Objects.Factory.Database_Entities.AccountTypeEntity;
import org.springframework.data.repository.CrudRepository;

public interface IAccountTypeDAO extends CrudRepository<AccountTypeEntity, Integer> {

}
