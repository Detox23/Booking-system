package API.Repository.Assignment;

import API.Database_Entities.AssignmentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentDAO extends PagingAndSortingRepository<AssignmentEntity, Integer>
{
    AssignmentEntity updateAssignment(AssignmentEntity accountEntity);

}