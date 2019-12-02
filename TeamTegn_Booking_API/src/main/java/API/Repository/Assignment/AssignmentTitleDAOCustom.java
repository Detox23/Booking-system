package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentTitleEntity;
import Shared.ToReturn.AssignmentTitleDto;

import java.util.List;

public interface AssignmentTitleDAOCustom {
    AssignmentTitleDto addAssignmentTitle(AssignmentTitleEntity assignmentTitle);

    AssignmentTitleDto updateAssignmentTitle(AssignmentTitleEntity assignmentTitle);

    AssignmentTitleDto findAssignmentTitle(int id);

    List<AssignmentTitleDto> listAssignmentTitles();

    boolean deleteAssignmentTitle(int id);
}
