package API.Repository.Account;

import Objects.Factory.Database_Entities.AccountEanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountEanDAO extends JpaRepository<AccountEanEntity, Integer>, JpaSpecificationExecutor<AccountEanEntity>
{
    boolean addeanNumber(AccountEanEntity accountEanEntity);
    boolean deleteeanNumber(int accountID, String EANNumber);

}
