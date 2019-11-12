package API.Repository.Account;

import Objects.Factory.Database_Entities.AccountEanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountEanDAO extends JpaRepository<AccountEanEntity, Integer>, IAccountEanCustom
{


}
