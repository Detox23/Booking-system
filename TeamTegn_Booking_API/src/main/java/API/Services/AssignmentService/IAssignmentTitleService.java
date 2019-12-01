package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentTitleForCreationDto;
import Shared.ForCreation.AssignmentTitleForUpdateDto;
import Shared.ToReturn.AssignmentTitleDto;

import java.util.List;

public interface IAssignmentTitleService {
    AssignmentTitleDto addAssignmentTitle(AssignmentTitleForCreationDto assignmentTitle);

    AssignmentTitleDto updateAssignmentTitle(AssignmentTitleForUpdateDto assignmentTitle);

    boolean deleteAssignmentTitle(int id);

    AssignmentTitleDto findAssignmentTitle(int id);

    List<AssignmentTitleDto> listAssignmentTitles();
}
