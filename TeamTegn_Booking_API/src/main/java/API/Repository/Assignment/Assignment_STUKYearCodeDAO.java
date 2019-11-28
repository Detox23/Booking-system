package API.Repository.Assignment;

import API.Database_Entities.Assignment_StukYearCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Assignment_STUKYearCodeDAO extends JpaRepository<Assignment_StukYearCodeEntity, Integer> {
    void deleteAllByAssignmentIdIs(int assignmentID);
}
