package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentStatusTypeForCreationDto;
import Shared.ForCreation.AssignmentStatusTypeForUpdateDto;
import Shared.ToReturn.AssignmentStatusTypeDto;

import java.util.List;


public interface IAssignmentStatusTypeService {
    AssignmentStatusTypeDto addAssignmentStatusType(AssignmentStatusTypeForCreationDto assignmentEntity);

    AssignmentStatusTypeDto findAssignmentStatusType(int id);

    List<AssignmentStatusTypeDto> listAssignmentStatusTypes(boolean showDeleted);

    boolean deleteAssignmentStatusType(int id);

    AssignmentStatusTypeDto updateAssignmentStatusType(AssignmentStatusTypeForUpdateDto assignmentEntity);


}
