package API.Services;

import Shared.ToReturn.AssignmentDto;
import Shared.ForCreation.AssignmentForCreationDto;

import java.util.List;

public interface IAssignmentService
{

    AssignmentDto add(AssignmentForCreationDto assignmentEntity);
    AssignmentDto get(int id);
    List<AssignmentDto> getAll();
    boolean delete(int id);
}
