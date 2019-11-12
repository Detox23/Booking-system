package API.Repository.Assignment;

import Objects.Factory.Database_Entities.AssignmentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IAssignmentDAO  extends PagingAndSortingRepository<AssignmentEntity, Integer>
{


}