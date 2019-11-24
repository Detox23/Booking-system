package API.Repository.Assignment;

import API.Database_Entities.AssignmentStatusTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface AssignmentStatusTypeDAO extends JpaRepository<AssignmentStatusTypeEntity, Integer>, AssignmentStatusTypeDAOCustom {
}
