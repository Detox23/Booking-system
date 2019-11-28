package API.Repository.Assignment;

import API.Database_Entities.AssignmentStukYearCodeEntity;
import Shared.ToReturn.AssignmentStukYearCodeDto;

import java.util.List;

public interface AssignmentSTUKYearCodeDAOCustom {
    AssignmentStukYearCodeDto addAssigmentStukYearCode(AssignmentStukYearCodeEntity stukYearCode);
    AssignmentStukYearCodeDto updateAssignmentStukYearCode(AssignmentStukYearCodeEntity stukYearCode);
    boolean deleteAssignmentStukYearCode(int id);
    List<AssignmentStukYearCodeDto> listAssignmentStukYearCodes();
    AssignmentStukYearCodeDto findAssignmentStukYearCode(int id);
}
