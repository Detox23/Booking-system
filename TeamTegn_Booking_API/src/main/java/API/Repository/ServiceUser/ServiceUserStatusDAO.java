package API.Repository.ServiceUser;

import API.Models.Database_Entities.ServiceUserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceUserStatusDAO extends JpaRepository<ServiceUserStatusEntity, Integer>, ServiceUserStatusDAOCustom {
    int countAllByStatusIs(String status);

    Optional<ServiceUserStatusEntity> findByIdAndDeletedIsFalse(int id);

    List<ServiceUserStatusEntity> findAllByDeletedIsFalse();
}
