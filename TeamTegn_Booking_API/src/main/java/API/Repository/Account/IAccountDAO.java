package API.Repository.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import API.Database_Entities.AccountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountDAO extends JpaRepository<AccountEntity, Integer>, IAccountDAOCustom{

}
