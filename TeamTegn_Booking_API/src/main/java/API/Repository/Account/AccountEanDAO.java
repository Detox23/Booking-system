package API.Repository.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountEanDAO extends JpaRepository<AccountEanEntity, Integer>, AccountEanCustom {
    List<AccountEanEntity> findAllByAccountId(int id);

    List<AccountEanEntity> findAllByAccountIdAndEanNumber(int id, String eanNumber);

    AccountEanEntity findDistinctByAccountIdAndEanNumber(int id, String eanNumber);

}
