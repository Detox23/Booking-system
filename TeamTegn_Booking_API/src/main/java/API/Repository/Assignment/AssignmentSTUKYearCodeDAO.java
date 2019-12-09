package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentStukYearCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentSTUKYearCodeDAO extends JpaRepository<AssignmentStukYearCodeEntity, Integer>, AssignmentSTUKYearCodeDAOCustom {
    List<AssignmentStukYearCodeEntity> findAllByDeletedIsFalse();
    int countAllByStukYearCodeNameIsAndIdIsNot(String stukYearCodeName, int id);
    int countAllByStukYearCodeNameIs(String stukYearCodeName);
    Optional<AssignmentStukYearCodeEntity> findByIdIsAndDeletedIsFalse(int id);

}
