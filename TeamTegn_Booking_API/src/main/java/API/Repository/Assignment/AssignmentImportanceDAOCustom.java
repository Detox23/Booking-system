package API.Repository.Assignment;

import API.Database_Entities.AssignmentImportanceEntity;
import Shared.ToReturn.AssignmentImportanceDto;
import java.util.List;

public interface AssignmentImportanceDAOCustom {
    AssignmentImportanceDto addAssignmentImportance(AssignmentImportanceEntity assignmentImportance);

    boolean deleteAssignmentImportance(int id);

    List<AssignmentImportanceDto> listAssignmentImportance();

    AssignmentImportanceDto findAssignmentImportance(int id);

    AssignmentImportanceDto updateAssignmentImportance(AssignmentImportanceEntity assignmentImportance);
}
