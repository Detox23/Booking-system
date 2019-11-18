package API.Repository.Account;

import API.Database_Entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<AccountEntity, Integer>, AccountDAOCustom {
    int countAllByAccountNameAndCvrNumber(String accountName, String cvrNumber);

}
