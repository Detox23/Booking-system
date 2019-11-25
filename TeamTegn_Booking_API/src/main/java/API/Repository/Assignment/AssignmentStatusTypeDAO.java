package API.Repository.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AssignmentStatusTypeDAO extends JpaRepository<AssignmentStatusTypeEntity, Integer>, AssignmentStatusTypeDAOCustom {
}
