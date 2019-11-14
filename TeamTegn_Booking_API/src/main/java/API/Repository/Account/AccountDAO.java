package API.Repository.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import API.Database_Entities.AccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<AccountEntity, Integer>, AccountDAOCustom {


}
