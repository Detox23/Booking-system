package API.Repository.SystemUser;

import API.Models.Database_Entities.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SystemUserDAO extends JpaRepository<SystemUserEntity, Integer>, SystemUserDAOCustom {
    List<SystemUserEntity> findAllByDeletedIsFalse();

    int countAllByFirstNameIsAndMiddleNameIsAndLastNameIs(String firstName, String middleName, String lastName);

    int countAllByFirstNameIsAndMiddleNameIsAndLastNameIsAndIdIsNot(String firstName, String middleName, String lastName, int id);

    int countAllByUserNameIs(String userName);

    int countAllByUserNameIsAndIdIsNot(String useRName, int id);

    Optional<SystemUserEntity> findByIdAndDeletedIsFalse(int id);

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    Optional<SystemUserEntity> findDistinctByUserNameIs(String userName);

    //For test purposes
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    Optional<SystemUserEntity> deleteAllByIdIsGreaterThan(int id);
}
