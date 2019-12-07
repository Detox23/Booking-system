package API.Repository.ServiceProvider;

import API.Models.Database_Entities.TransportTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportTypeDAO extends JpaRepository<TransportTypeEntity, Integer> {
}
