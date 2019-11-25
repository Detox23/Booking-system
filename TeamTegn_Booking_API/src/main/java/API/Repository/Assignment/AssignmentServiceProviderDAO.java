package API.Repository.Assignment;

import API.Database_Entities.AssignmentServiceProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AssignmentServiceProviderDAO extends JpaRepository<AssignmentServiceProviderEntity, Integer> {

    List<AssignmentServiceProviderEntity> findAllByAssignmentByAssignmentId(int assignmentId);
}
