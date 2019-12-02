package API.Repository.Account;

import API.Database_Entities.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountTypeDAO extends JpaRepository<AccountTypeEntity, Integer>, AccountTypeCustom {
    Optional<AccountTypeEntity> findByIdAndDeletedIsFalse(int id);

    int countAllByAccountTypeIs(String accountType);

    List<AccountTypeEntity> findAllByDeletedIsFalse();
}
