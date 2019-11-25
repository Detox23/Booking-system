package API.Repository.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentDAO extends PagingAndSortingRepository<AssignmentEntity, Integer>, JpaRepository<AssignmentEntity, Integer>,AssignmentDAOCustom {

    AssignmentEntity findFirstByIdAndDeletedIsFalse(int id);

    List<AssignmentEntity> findAllByDeletedFalse();

}