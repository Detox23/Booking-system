package API.Repository.Account;

import Objects.Factory.Database_Entities.AccountEanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountEanDAO extends JpaRepository<AccountEanEntity, Integer>, JpaSpecificationExecutor<AccountEanEntity>
{
    boolean addEanNumber(AccountEanEntity accountEanEntity);
    boolean deleteEanNumber(int accountID, String EANNumber);

}
