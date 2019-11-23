package API.Repository.Assignment;

import API.Database_Entities.AssignmentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AssignmentStatusDAO extends JpaRepository<AssignmentStatusEntity, Integer>, AssignmentStatusDAOCustom {

}

