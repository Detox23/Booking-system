package API.Repository.EveningWorkPrioritisation;

import API.Models.Database_Entities.EveningWorkPrioritisationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EveningWorkPrioritisationDAO extends JpaRepository<EveningWorkPrioritisationEntity, Integer> {
    EveningWorkPrioritisationEntity getByIdIs(int id);
}
