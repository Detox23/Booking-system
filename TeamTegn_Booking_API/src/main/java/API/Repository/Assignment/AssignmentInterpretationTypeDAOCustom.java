package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentInterpretationTypeEntity;
import Shared.ToReturn.AssignmentInterpretationTypeDto;

import java.util.List;

public interface AssignmentInterpretationTypeDAOCustom {

    AssignmentInterpretationTypeDto addAssignmentInterpretationType(AssignmentInterpretationTypeEntity assignmentInterpretationTypeService);

    boolean deleteAssignmentInterpretationType(int id);

    List<AssignmentInterpretationTypeDto> listAssignmentInterpretationTypes();

    AssignmentInterpretationTypeDto findAssignmentInterpretationType(int id);

    AssignmentInterpretationTypeDto updateAssignmentInterpretationType(AssignmentInterpretationTypeEntity assignmentInterpretationTypeService);
}
