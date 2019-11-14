package API.Repository.Account;

import API.Database_Entities.AccountEanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEanDAO extends JpaRepository<AccountEanEntity, Integer>, AccountEanCustom
{


}
