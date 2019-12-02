package API.Repository.ServiceUser;


import API.Models.Database_Entities.ServiceUserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceUserAccountsDAO extends JpaRepository<ServiceUserAccountEntity, Integer> {
    void deleteAllByAccountIdIs(int id);
    void deleteAllByServiceUserIdIs(int id);
    List<ServiceUserAccountEntity> findAllByServiceUserIdIs(int id);
    List<ServiceUserAccountEntity> findAllByAccountIdIs(int id);
}
