package API.Repository.Log;

import API.Models.Database_Entities.EmailLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailLogDAO extends JpaRepository<EmailLogEntity, Integer> {
}
