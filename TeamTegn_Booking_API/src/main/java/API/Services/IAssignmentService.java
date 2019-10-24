package API.Services;

import Shared.AssignmentDto;
import Shared.AssignmentForCreationDto;

import java.util.List;

public interface IAssignmentService
{

    AssignmentDto add(AssignmentForCreationDto assignmentEntity);
    AssignmentDto get(int id);
    List<AssignmentDto> getAll();
    boolean delete(int id);
}
