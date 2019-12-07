package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentInterpretationTypeForCreationDto;
import Shared.ForCreation.AssignmentInterpretationTypeForUpdateDto;
import Shared.ToReturn.AssignmentInterpretationTypeDto;

import java.util.List;

public interface IAssignmentInterpretationTypeService {
    AssignmentInterpretationTypeDto addAssignmentInterpretationType(AssignmentInterpretationTypeForCreationDto assignmentEntity);

    AssignmentInterpretationTypeDto findAssignmentInterpretationType(int id);

    List<AssignmentInterpretationTypeDto> listAssignmentInterpretationTypes(boolean showDeleted);

    boolean deleteAssignmentInterpretationType(int id);

    AssignmentInterpretationTypeDto updateAssignmentInterpretationType(AssignmentInterpretationTypeForUpdateDto assignment);
}
