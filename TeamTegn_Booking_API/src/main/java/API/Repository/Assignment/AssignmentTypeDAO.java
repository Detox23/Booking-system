package API.Repository.Assignment;

import API.Database_Entities.AssignmentStatusTypeEntity;
import API.Database_Entities.AssignmentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssignmentTypeDAO extends JpaRepository<AssignmentTypeEntity, Integer>, AssignmentTypeDAOCustom {

    List<AssignmentTypeEntity> findAllByDeletedIsFalse();

    int countAllByAssignmentTypeNameIs(String name);

    Optional<AssignmentTypeEntity> findByIdAndDeletedIsFalse(int id);
}
