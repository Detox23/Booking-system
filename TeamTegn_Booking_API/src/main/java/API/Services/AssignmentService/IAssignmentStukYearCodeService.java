package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentStukYearCodeForCreationDto;
import Shared.ForCreation.AssignmentStukYearCodeForUpdateDto;
import Shared.ToReturn.AssignmentStukYearCodeDto;

import java.util.List;

public interface IAssignmentStukYearCodeService {
    AssignmentStukYearCodeDto addAssigmentStukYearCode(AssignmentStukYearCodeForCreationDto stukYearCode);

    AssignmentStukYearCodeDto updateAssignmentStukYearCode(AssignmentStukYearCodeForUpdateDto stukYearCode);

    boolean deleteAssignmentStukYearCode(int id);

    List<AssignmentStukYearCodeDto> listAssignmentStukYearCodes(boolean showDeleted);

    AssignmentStukYearCodeDto findAssignmentStukYearCode(int id);
}
