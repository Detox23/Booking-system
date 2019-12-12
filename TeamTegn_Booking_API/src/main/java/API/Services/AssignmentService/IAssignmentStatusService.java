package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentStatusForCreationDto;
import Shared.ForCreation.AssignmentStatusForUpdateDto;
import Shared.ToReturn.AssignmentStatusDto;

import java.util.List;


public interface IAssignmentStatusService {
    AssignmentStatusDto addAssignmentStatus(AssignmentStatusForCreationDto assignmentEntity);

    AssignmentStatusDto findAssignmentStatus(int id);

    List<AssignmentStatusDto> listAssignmentStatuses(boolean showDeleted);

    boolean deleteAssignmentStatus(int id);

    AssignmentStatusDto updateAssignmentStatus(AssignmentStatusForUpdateDto assignmentEntity);

}