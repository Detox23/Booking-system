package API.Repository.SystemUser;

import API.Models.Database_Entities.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemUserDAO extends JpaRepository<SystemUserEntity, Integer>, SystemUserDAOCustom {
    List<SystemUserEntity> findAllByDeletedIsFalse();

    int countAllByFirstNameIsAndLastNameIsAndUserNameIs(String firstName, String lastName, String userName);

    Optional<SystemUserEntity> findDistinctByUserNameIs(String userName);

}
