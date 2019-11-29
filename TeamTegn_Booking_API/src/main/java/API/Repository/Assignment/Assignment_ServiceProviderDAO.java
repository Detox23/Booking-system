package API.Repository.Assignment;

import API.Database_Entities.AssignmentServiceProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Assignment_ServiceProviderDAO extends JpaRepository<AssignmentServiceProviderEntity, Integer> {

    List<AssignmentServiceProviderEntity> findAllByAssignmentIdIs(int assignmentId);

    void deleteAllByAssignmentIdIs(int assignmentID);

}
