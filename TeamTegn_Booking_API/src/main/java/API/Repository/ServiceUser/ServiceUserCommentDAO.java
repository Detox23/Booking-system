package API.Repository.ServiceUser;

import API.Models.Database_Entities.ServiceUserCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceUserCommentDAO extends JpaRepository<ServiceUserCommentEntity, Integer>, ServiceUserCommentDAOCustom {
    List<ServiceUserCommentEntity> findAllByServiceUserIdIs(int serviceUserId);
}
