package API.Repository.Account;

import API.Models.Database_Entities.AccountCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountCommentDAO extends JpaRepository<AccountCommentEntity, Integer>, AccountCommentDAOCustom {
    Optional<AccountCommentEntity> findByAccountIdIsAndIdIs(int accountID, int id);

    Optional<AccountCommentEntity> findByIdIs(int id);

    List<AccountCommentEntity> findAllByAccountIdIs(int id);
}
