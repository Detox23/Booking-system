package API.Services;

import Shared.ForCreation.AssignmentForUpdateDto;
import Shared.ToReturn.AssignmentDto;
import Shared.ForCreation.AssignmentForCreationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAssignmentService
{

    AssignmentDto add(AssignmentForCreationDto assignmentEntity);
    AssignmentDto get(int id);
    Page<AssignmentDto> getAll(Pageable pageable);
    boolean delete(int id);
    AssignmentDto update(int id, AssignmentForUpdateDto assignmentEntity);

}