package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentImportanceForCreationDto;
import Shared.ForCreation.AssignmentImportanceForUpdateDto;
import Shared.ToReturn.AssignmentImportanceDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssignmentImportanceService implements IAssignmentImportanceService {
    @Override
    public AssignmentImportanceDto addAssignmentImportance(AssignmentImportanceForCreationDto assignmentImportance) {
        return null;
    }

    @Override
    public boolean deleteAssignmentImportance(int id) {
        return false;
    }

    @Override
    public List<AssignmentImportanceDto> listAssignmentImportance() {
        return null;
    }

    @Override
    public AssignmentImportanceDto findAssignmentImportance(int id) {
        return null;
    }

    @Override
    public AssignmentImportanceDto updateAssignmentImportance(AssignmentImportanceForUpdateDto assignmentImportance) {
        return null;
    }
}
