package API.Repository.ServiceUser;

import API.Database_Entities.ServiceUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceUserDAO extends JpaRepository<ServiceUserEntity, Integer>, QueryByExampleExecutor<ServiceUserEntity>, ServiceUserDAOCustom {
    ServiceUserEntity findFirstByIdAndDeletedIsFalse(int id);
   Iterable<ServiceUserEntity> findAllByDeletedFalse();
}
