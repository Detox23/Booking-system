package API.Repository.Assignment;

import Objects.Factory.Database_Entities.AssignmentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Repository
public interface AssignmentDAO extends PagingAndSortingRepository<AssignmentEntity, Integer>
{
    AssignmentEntity updateAssignment(AssignmentEntity accountEntity);

}