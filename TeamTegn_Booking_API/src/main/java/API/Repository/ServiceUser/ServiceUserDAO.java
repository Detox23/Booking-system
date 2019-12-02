package API.Repository.ServiceUser;

import API.Models.Database_Entities.ServiceUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ServiceUserDAO extends JpaRepository<ServiceUserEntity, Integer>, QueryByExampleExecutor<ServiceUserEntity>, ServiceUserDAOCustom {
    ServiceUserEntity findFirstByIdAndDeletedIsFalse(int id);

    Page<ServiceUserEntity> findAllByDeletedFalse(Pageable pageable);

    Optional<ServiceUserEntity> findByIdIsAndDeletedIsFalse(int id);

    int countAllByFirstNameIsAndMiddleNameIsAndLastNameIs(String firstName, String middleName, String lastName);
}
