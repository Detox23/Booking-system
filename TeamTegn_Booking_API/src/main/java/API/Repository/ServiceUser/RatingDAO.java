package API.Repository.ServiceUser;

import API.Models.Database_Entities.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingDAO extends JpaRepository<RatingEntity, Integer> {
}
