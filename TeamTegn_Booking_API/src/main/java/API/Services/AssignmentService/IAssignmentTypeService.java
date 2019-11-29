package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentTypeForCreationDto;
import Shared.ForCreation.AssignmentTypeForUpdateDto;
import Shared.ToReturn.AssignmentTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAssignmentTypeService {
    AssignmentTypeDto addAssignmentType(AssignmentTypeForCreationDto assignmentEntity);

    AssignmentTypeDto findAssignmentType(int id);

    List<AssignmentTypeDto> listAssignmentType();

    boolean deleteAssignmentType(int id);

    AssignmentTypeDto updateAssignmentType(AssignmentTypeForUpdateDto assignmentEntity);

}
