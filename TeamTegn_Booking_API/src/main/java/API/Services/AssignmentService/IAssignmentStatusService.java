package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentStatusForCreationDto;
import Shared.ForCreation.AssignmentStatusForUpdateDto;
import Shared.ToReturn.AssignmentStatusDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAssignmentStatusService {
    AssignmentStatusDto addAssignmentStatus(AssignmentStatusForCreationDto assignmentEntity);
    AssignmentStatusDto findAssignmentStatus(int id);
    List<AssignmentStatusDto> listAssignmentStatuses();
    boolean deleteAssignmentStatus(int id);
    AssignmentStatusDto updateAssignmentStatus(AssignmentStatusForUpdateDto assignmentEntity);

}