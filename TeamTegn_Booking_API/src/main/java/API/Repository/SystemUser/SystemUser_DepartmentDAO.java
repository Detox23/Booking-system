package API.Repository.SystemUser;

import API.Database_Entities.SystemUserDepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemUser_DepartmentDAO extends JpaRepository<SystemUserDepartmentEntity, Integer> {
    List<SystemUserDepartmentEntity> findBySystemUserIdIs(int systemUserID);
    int deleteAllBySystemUserIdIs(int systemUserID);
}
