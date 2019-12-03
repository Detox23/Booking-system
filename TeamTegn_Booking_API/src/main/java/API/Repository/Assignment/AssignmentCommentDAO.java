package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentCommentDAO extends JpaRepository<AssignmentCommentEntity, Integer>, AssignmentCommentDAOCustom {
    List<AssignmentCommentEntity> findAllByAssignmentId(int assignmentID);

    Optional<AssignmentCommentEntity> findByAssignmentIdIsAndIdIs(int assignmentID, int id);
}
