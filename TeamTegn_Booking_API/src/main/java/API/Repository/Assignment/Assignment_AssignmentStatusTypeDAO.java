package API.Repository.Assignment;

import API.Database_Entities.AssignmentAssignmentStatusTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Assignment_AssignmentStatusTypeDAO extends JpaRepository<AssignmentAssignmentStatusTypeEntity, Integer> {
    List<Integer> findAllByAssignmentId(int assignmentId);
    void deleteAllByAssignmentId(int assignmentId);


}