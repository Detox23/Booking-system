package API.Repository.Assignment;

import API.Database_Entities.Assignment_StukYearCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Assignment_STUKYearCodeDAO extends JpaRepository<Assignment_StukYearCodeEntity, Integer> {

    List<Assignment_StukYearCodeEntity> findAllByAssignmentIdIs(int assignmentID);

    void deleteAllByAssignmentIdIs(int assignmentID);
}
