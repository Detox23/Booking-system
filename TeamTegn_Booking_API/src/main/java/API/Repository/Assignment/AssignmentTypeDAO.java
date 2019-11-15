package API.Repository.Assignment;

import API.Database_Entities.AssignmentTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentTypeDAO extends JpaRepository<AssignmentTypeEntity, Integer> {


}
