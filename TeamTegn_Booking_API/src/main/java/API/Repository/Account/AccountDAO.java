package API.Repository.Account;

import API.Models.Database_Entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDAO extends JpaRepository<AccountEntity, Integer>, AccountDAOCustom {
    int countAllByAccountNameAndCvrNumber(String accountName, String cvrNumber);

    int countAllByAccountNameAndCvrNumberAndIdIsNot(String accountName, String cvrNumber, int id);

    List<AccountEntity> findAllByDeletedIsFalse();

    Optional<AccountEntity> findByIdIsAndDeletedIsFalse(int id);

    AccountEntity findAccountEntityByAccountName(String accountName);

}
