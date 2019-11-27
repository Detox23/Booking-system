package API.Repository.SystemUser;

import API.Database_Entities.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemUserDAO extends JpaRepository<SystemUserEntity, Integer>, SystemUserDAOCustom {
}
