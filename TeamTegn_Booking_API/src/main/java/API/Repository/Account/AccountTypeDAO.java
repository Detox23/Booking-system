package API.Repository.Account;

import API.Database_Entities.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeDAO extends JpaRepository<AccountTypeEntity, Integer>, AccountTypeCustom {

}
