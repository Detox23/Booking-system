package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentServiceProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;


public interface Assignment_ServiceProviderDAO extends JpaRepository<AssignmentServiceProviderEntity, Integer> {

    //https://stackoverflow.com/questions/14223649/how-to-implement-auditoraware-with-spring-data-jpa-and-spring-security/17767962#17767962
    List<AssignmentServiceProviderEntity> findAllByAssignmentIdIs(int assignmentId);

    void deleteAllByAssignmentIdIs(int assignmentID);

}
