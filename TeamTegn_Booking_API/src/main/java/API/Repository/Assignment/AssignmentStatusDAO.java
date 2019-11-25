package API.Repository.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AssignmentStatusDAO extends JpaRepository<AssignmentStatusEntity, Integer>, AssignmentStatusDAOCustom {

}

