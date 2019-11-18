package API.Repository.Department;

import API.Database_Entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentDAO extends DepartmentDAOCustom, JpaRepository<DepartmentEntity, Integer> {
    DepartmentEntity findByDepartmentName(String name);
}