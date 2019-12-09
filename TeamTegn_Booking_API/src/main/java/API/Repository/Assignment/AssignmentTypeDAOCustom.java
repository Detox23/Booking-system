package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentTypeEntity;
import Shared.ToReturn.AssignmentTypeDto;

import java.util.List;

public interface AssignmentTypeDAOCustom {
    AssignmentTypeDto addAssignmentType(AssignmentTypeEntity assignmentType);

    boolean deleteAssignmentType(int id);

    List<AssignmentTypeDto> listAssignmentTypes(boolean showDeleted);

    AssignmentTypeDto findAssignmentType(int id);

    AssignmentTypeDto updateAssignmentType(AssignmentTypeEntity assignmentType);
}
