package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentInterpretationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentInterpretationTypeDAO extends JpaRepository<AssignmentInterpretationTypeEntity, Integer>, AssignmentInterpretationTypeDAOCustom {

    List<AssignmentInterpretationTypeEntity> findAllByDeletedIsFalse();

    Optional<AssignmentInterpretationTypeEntity> findByIdAndDeletedIsFalse(int id);

    int countAllByInterpretationTypeNameIs(String interpretationType);
}
