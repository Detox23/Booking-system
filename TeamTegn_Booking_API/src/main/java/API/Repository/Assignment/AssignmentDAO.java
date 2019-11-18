package API.Repository.Assignment;

import API.Database_Entities.AssignmentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentDAO extends PagingAndSortingRepository<AssignmentEntity, Integer> {

    AssignmentEntity findFirstByIdAndDeletedIsFalse(int id);

    List<AssignmentEntity> findAllByDeletedFalse();

}