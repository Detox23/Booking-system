package API.Services;

import Shared.ForCreation.AssignmentTypeForCreationDto;
import Shared.ForCreation.AssignmentTypeForUpdateDto;
import Shared.ToReturn.AssignmentTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAssignmentTypeService {
    AssignmentTypeDto add(AssignmentTypeForCreationDto assignmentEntity);
    AssignmentTypeDto get(int id);
    List<AssignmentTypeDto> getAll();
    boolean delete(int id);
    AssignmentTypeDto update(int id, AssignmentTypeForUpdateDto assignmentEntity);

}
