package API.Repository.Assignment;

import API.Database_Entities.AssignmentEntity;
import API.Database_Entities.AssignmentStatusEntity;
import Shared.ForCreation.AssignmentStatusForCreationDto;
import Shared.ForCreation.AssignmentStatusForUpdateDto;
import Shared.ToReturn.AssignmentStatusDto;

import java.util.List;

public interface AssignmentStatusDAOCustom {
    List<AssignmentStatusDto> listAssignmentStatuses();

    AssignmentStatusDto findAssignmentStatus(int id);

    AssignmentStatusDto addAssignmentStatus(AssignmentStatusEntity serviceProvider);

    AssignmentStatusDto updateAssignmentStatus(AssignmentStatusEntity serviceProvider);

    boolean deleteAssignmentStatus(int id);

}
