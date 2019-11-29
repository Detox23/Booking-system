package API.Repository.Assignment;

import API.Database_Entities.AssignmentEntity;
import Shared.ToReturn.AssignmentDto;
import Shared.ToReturn.AssignmentViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.sql.Date;
import java.util.List;

public interface AssignmentDAOCustom {
    List<AssignmentViewDto> listAllAssignments(Date date);

    AssignmentDto addAssignment(AssignmentEntity assignmentEntity, List<Integer> serviceProviders, List<Integer> assignmentStatusTypeIds);

    boolean deleteAssignment(int id);

    AssignmentDto findAssignment(int id);

    AssignmentDto updateAssignment(AssignmentEntity assignmentEntity, List<Integer> serviceProviders, List<Integer> assignmentStatusTypes);

    Page<AssignmentDto> listAssignmentsPage(Pageable pageable);
}
