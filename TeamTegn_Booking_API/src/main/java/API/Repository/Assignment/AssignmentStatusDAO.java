package API.Repository.Assignment;

import API.Database_Entities.AssignmentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentStatusDAO extends JpaRepository<AssignmentStatusEntity, Integer>, AssignmentStatusDAOCustom {
    int countAllByAssignmentStatusName(String name);

    List<AssignmentStatusEntity> findAllByDeletedIsFalse();

    Optional<AssignmentStatusEntity> findByIdAndDeletedIsFalse(int id);

}

