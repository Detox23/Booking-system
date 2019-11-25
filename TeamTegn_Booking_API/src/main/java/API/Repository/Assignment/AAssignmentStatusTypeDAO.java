package API.Repository.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AAssignmentStatusTypeDAO extends JpaRepository<AssignmentAssignmentStatusTypeEntity, Integer> {
    List<Integer> findAllByAssignmentId(int assignmentId);
    void deleteAllByAssignmentId(int assignmentId);


}
