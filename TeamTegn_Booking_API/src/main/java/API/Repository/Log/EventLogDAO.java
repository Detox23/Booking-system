package API.Repository.Log;

import API.Models.Database_Entities.EventLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogDAO extends JpaRepository<EventLogEntity, Integer> {
}
