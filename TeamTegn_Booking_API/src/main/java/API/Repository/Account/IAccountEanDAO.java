package API.Repository.Account;

import API.Database_Entities.AccountEanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountEanDAO extends JpaRepository<AccountEanEntity, Integer>, IAccountEanCustom
{


}
