package API.Repository.Account;

import API.Models.Database_Entities.AccountEanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountEanDAO extends JpaRepository<AccountEanEntity, Integer> {
    List<AccountEanEntity> findAllByAccountId(int id);

    void deleteAllByAccountIdIs(int id);

    List<AccountEanEntity> findAllByAccountIdAndEanNumber(int id, String eanNumber);

    AccountEanEntity findDistinctByAccountIdAndEanNumber(int id, String eanNumber);}
