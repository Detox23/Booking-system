package API.Repository.Assignment;

import API.Database_Entities.AssignmentTypeEntity;
import Shared.ToReturn.AssignmentTypeDto;

import java.util.List;

public interface AssignmentTypeDAOCustom {
    AssignmentTypeDto addAssignmentType(AssignmentTypeEntity a);

    boolean deleteAssignmentType(int Id);

    List<AssignmentTypeDto> listAssignmentType();

    AssignmentTypeDto findAssignmentType(int id);

    AssignmentTypeDto updateAssignmentType(AssignmentTypeEntity a);
}
