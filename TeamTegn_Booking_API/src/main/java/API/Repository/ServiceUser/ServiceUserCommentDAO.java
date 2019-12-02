package API.Repository.ServiceUser;

import API.Database_Entities.ServiceUserCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceUserCommentDAO extends JpaRepository<ServiceUserCommentEntity, Integer>, ServiceUserCommentDAOCustom {
    Iterable<ServiceUserCommentEntity> findAllByServiceUserId(int serviceUserId);
    Optional<ServiceUserCommentEntity> findByServiceUserIdIsAndIdIs(int serviceUserId, int id);


}
