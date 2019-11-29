package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentForCreationDto;
import Shared.ForCreation.AssignmentForUpdateDto;
import Shared.ToReturn.AssignmentDto;
import Shared.ToReturn.AssignmentViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;


public interface IAssignmentService {
    AssignmentDto addAssignment(AssignmentForCreationDto assignmentEntity);

    AssignmentDto findAssignment(int id);

    Page<AssignmentDto> listAssignmentsPage(Pageable pageable);

    List<AssignmentViewDto> listAssignmentsDate(Date date);

    boolean deleteAssignment(int id);

    AssignmentDto updateAssignment(AssignmentForUpdateDto assignment);

}