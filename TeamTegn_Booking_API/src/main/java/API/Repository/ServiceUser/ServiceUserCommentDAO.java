package API.Repository.ServiceUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.ServiceProviderCommentEntity;
import API.Database_Entities.ServiceUserCommentEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.ServiceUserCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.IntrospectionException;
import java.util.Optional;
@Repository

public interface ServiceUserCommentDAO extends JpaRepository<ServiceUserCommentEntity, Integer>, ServiceUserCommentDAOCustom {
    Iterable<ServiceUserCommentEntity> findAllByServiceUserId(int serviceUserId);
    Optional<ServiceUserCommentEntity> findByServiceUserIdIsAndIdIs(int serviceUserId, int id);


}
