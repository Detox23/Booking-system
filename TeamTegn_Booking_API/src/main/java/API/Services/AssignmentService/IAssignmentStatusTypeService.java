package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentStatusForCreationDto;
import Shared.ForCreation.AssignmentStatusForUpdateDto;
import Shared.ForCreation.AssignmentStatusTypeForCreationDto;
import Shared.ForCreation.AssignmentStatusTypeForUpdateDto;
import Shared.ToReturn.AssignmentStatusTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;



public interface IAssignmentStatusTypeService 
{
    AssignmentStatusTypeDto add(AssignmentStatusTypeForCreationDto assignmentEntity);
    AssignmentStatusTypeDto get(int id);
    List<AssignmentStatusTypeDto> list();
    boolean delete(int id);
    AssignmentStatusTypeDto update(int id, AssignmentStatusTypeForUpdateDto assignmentEntity);


}
