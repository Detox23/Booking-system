package API.Repository.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentTypeDAO extends JpaRepository<AssignmentTypeEntity, Integer>, AssignmentTypeDAOCustom {


}
