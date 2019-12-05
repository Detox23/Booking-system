package API.Repository.Log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import API.Models.Database_Entities.EmailLogEntity;

@Repository
public interface EmailLogDAO extends JpaRepository<EmailLogEntity, Integer> {
}
