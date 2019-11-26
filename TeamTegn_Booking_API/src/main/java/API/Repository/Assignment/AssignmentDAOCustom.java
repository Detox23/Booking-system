package API.Repository.Assignment;

import API.Database_Entities.AssignmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AssignmentDAOCustom {
    List<AssignmentEntity> listAll();

    AssignmentEntity addOne(AssignmentEntity assignmentEntity);

    boolean deleteOne(int id);

    AssignmentEntity getOne(int id);

    AssignmentEntity updateOne(@NotNull AssignmentEntity assignmentEntity);

    Page<AssignmentEntity> listAll(Pageable pageable);
}
