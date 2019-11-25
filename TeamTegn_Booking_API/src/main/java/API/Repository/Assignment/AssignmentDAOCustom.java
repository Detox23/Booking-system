package API.Repository.Assignment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AssignmentDAOCustom {
    List<AssignmentEntity> listAll();

    AssignmentEntity addOne(AssignmentEntity assignmentEntity);

    boolean deleteOne(int id);

    AssignmentEntity getOne(int id);

    AssignmentEntity updateOne(AssignmentEntity assignmentEntity);

    Page<AssignmentEntity> listAll(Pageable pageable);
}
