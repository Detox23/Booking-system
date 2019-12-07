package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentImportanceForCreationDto;
import Shared.ForCreation.AssignmentImportanceForUpdateDto;
import Shared.ToReturn.AssignmentImportanceDto;

import java.util.List;

public interface IAssignmentImportanceService {
    AssignmentImportanceDto addAssignmentImportance(AssignmentImportanceForCreationDto assignmentImportance);

    boolean deleteAssignmentImportance(int id);

    List<AssignmentImportanceDto> listAssignmentImportance(boolean showDeleted);

    AssignmentImportanceDto findAssignmentImportance(int id);

    AssignmentImportanceDto updateAssignmentImportance(AssignmentImportanceForUpdateDto assignmentImportance);
}
