package API.Repository.SystemUser;

import API.Models.Database_Entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<RoleEntity, Integer> {
    RoleEntity getByIdIs(int id);
}
