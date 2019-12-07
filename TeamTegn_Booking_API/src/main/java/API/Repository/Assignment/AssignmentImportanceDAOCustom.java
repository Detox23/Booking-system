package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentImportanceEntity;
import Shared.ToReturn.AssignmentImportanceDto;

import java.util.List;

public interface AssignmentImportanceDAOCustom {
    AssignmentImportanceDto addAssignmentImportance(AssignmentImportanceEntity assignmentImportance);

    boolean deleteAssignmentImportance(int id);

    List<AssignmentImportanceDto> listAssignmentImportance(boolean showDeleted);

    AssignmentImportanceDto findAssignmentImportance(int id);

    AssignmentImportanceDto updateAssignmentImportance(AssignmentImportanceEntity assignmentImportance);
}
