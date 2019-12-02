package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentStatusTypeEntity;
import Shared.ToReturn.AssignmentStatusTypeDto;

import java.util.List;

public interface AssignmentStatusTypeDAOCustom {
    List<AssignmentStatusTypeDto> listAssignmentStatusTypes();

    AssignmentStatusTypeDto findAssignmentStatusType(int id);

    AssignmentStatusTypeDto addAssignmentStatusType(AssignmentStatusTypeEntity assignmentStatusTypeEntity);

    AssignmentStatusTypeDto updateAssignmentStatusType(AssignmentStatusTypeEntity assignmentStatusTypeEntity);

    boolean deleteAssignmentStatusType(int id);
}
