package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentForCreationDto;
import Shared.ForCreation.AssignmentForUpdateDto;
import Shared.ToReturn.AssignmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IAssignmentService {
    AssignmentDto add(AssignmentForCreationDto assignmentEntity);

    AssignmentDto get(int id);

    Page<AssignmentDto> getAll(Pageable pageable);

    boolean delete(int id);

    AssignmentDto update(int id, AssignmentForUpdateDto assignmentEntity);

}