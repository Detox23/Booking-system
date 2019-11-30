package API.Repository.ServiceUser;


import API.Database_Entities.ServiceUserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceUserAccountsDAO extends JpaRepository<ServiceUserAccountEntity, Integer> {

    List<ServiceUserAccountEntity> findAllByServiceUserId(int id);
    List<ServiceUserAccountEntity> findAllServiceUserIdByAccountId(int id);
}
