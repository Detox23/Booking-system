package API.Repository.Assignment;

import API.Models.Database_Entities.VocalLanguagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocalLanguagesDAO extends JpaRepository<VocalLanguagesEntity, Integer> {
}
