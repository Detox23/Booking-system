package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentImportanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentImportanceDAO extends JpaRepository<AssignmentImportanceEntity, Integer>, AssignmentImportanceDAOCustom {
    List<AssignmentImportanceEntity> findAllByDeletedIsFalse();

    Optional<AssignmentImportanceEntity> findByIdAndDeletedIsFalse(int id);

    int countAllByImportanceNameIs(String importanceName);
}
