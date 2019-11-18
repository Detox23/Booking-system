package API.Repository.Account;

import API.Database_Entities.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeDAO extends JpaRepository<AccountTypeEntity, Integer>, AccountTypeCustom {

}