package API.Repository.Department;

import API.Database_Entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDepartmentDAO extends JpaRepository<DepartmentEntity, Integer>, IDepartmentDAOCustom {
}
