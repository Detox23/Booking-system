package API.Repository.Department;

import API.Database_Entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDepartmentDAO extends IDepartmentDAOCustom, JpaRepository<DepartmentEntity, Integer>{
    DepartmentEntity findByDepartmentName(String name);
}
