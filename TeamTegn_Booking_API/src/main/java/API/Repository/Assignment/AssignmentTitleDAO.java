package API.Repository.Assignment;

import API.Database_Entities.AssignmentTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentTitleDAO extends JpaRepository<AssignmentTitleEntity, Integer>, AssignmentTitleDAOCustom {
    List<AssignmentTitleEntity> findAllByDeletedIsFalse();

    Optional<AssignmentTitleEntity> findByIdAndDeletedIsFalse(int id);

    int countAllByTitleIs(String title);
}
