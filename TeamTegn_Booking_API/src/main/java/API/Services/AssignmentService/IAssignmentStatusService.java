package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentStatusForCreationDto;
import Shared.ForCreation.AssignmentStatusForUpdateDto;
import Shared.ToReturn.AssignmentStatusDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAssignmentStatusService {
    AssignmentStatusDto add(AssignmentStatusForCreationDto assignmentEntity);
    AssignmentStatusDto get(int id);
    List<AssignmentStatusDto> list();
    boolean delete(int id);
    AssignmentStatusDto update(int id, AssignmentStatusForUpdateDto assignmentEntity);

}