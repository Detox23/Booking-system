package API.Repository.Department;

import API.Models.Database_Entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DepartmentDAO extends DepartmentDAOCustom, JpaRepository<DepartmentEntity, Integer> {
    Optional<DepartmentEntity> findByDepartmentName(String name);

    List<DepartmentEntity> findAllByDeletedIsFalse();

    Optional<DepartmentEntity> findByIdIsAndDeletedIsFalse(int id);

    int countAllByDepartmentNameIs(String departmentName);

    int countAllByDepartmentNameIsAndIdIsNot(String departmentName, int id);


}

