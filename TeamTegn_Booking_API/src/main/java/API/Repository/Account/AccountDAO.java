package API.Repository.Account;

import API.Database_Entities.AccountEntity;
import API.Database_Entities.AssignmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDAO extends JpaRepository<AccountEntity, Integer>,  AccountDAOCustom {
    int countAllByAccountNameAndCvrNumber(String accountName, String cvrNumber);
    List<AccountEntity> findAllByDeletedIsFalse();
    AccountEntity findAccountEntityByAccountName(String accountName);

}
