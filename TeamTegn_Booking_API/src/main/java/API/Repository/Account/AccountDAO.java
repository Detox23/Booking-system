package API.Repository.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import API.Database_Entities.AccountEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDAO extends JpaRepository<AccountEntity, Integer>, AccountDAOCustom {
    int countAllByAccountNameAndCvrNumber(String accountName, String cvrNumber);

}
