package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentStatusTypeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AssignmentStatusTypeDAO extends JpaRepository<AssignmentStatusTypeEntity, Integer>, AssignmentStatusTypeDAOCustom {
    Optional<AssignmentStatusTypeEntity> findByIdAndDeletedIsFalse(int id);

    List<AssignmentStatusTypeEntity> findAllByDeletedIsFalse(Sort sort);

    int countAllByAssignmentStatusTypeNameIs(String name);
}
