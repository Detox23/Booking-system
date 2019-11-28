package API.Repository.Assignment;

import API.Database_Entities.AssignmentStukYearCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentSTUKYearCodeDAO extends JpaRepository<AssignmentStukYearCodeEntity, Integer>, AssignmentSTUKYearCodeDAOCustom {
    List<AssignmentStukYearCodeEntity> findAllByDeletedIsFalse();
    Optional<AssignmentStukYearCodeEntity> findByStukYearCodeNameIsAndDeletedIsFalse(String name);

}