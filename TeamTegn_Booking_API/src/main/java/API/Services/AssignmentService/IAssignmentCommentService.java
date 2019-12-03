package API.Services.AssignmentService;

import Shared.ForCreation.AssignmentCommentForCreationDto;
import Shared.ForCreation.AssignmentCommentForUpdateDto;
import Shared.ToReturn.AssignmentCommentDto;

import java.util.List;

public interface IAssignmentCommentService {
    AssignmentCommentDto addAssignmentComment(AssignmentCommentForCreationDto assignmentComment);

    AssignmentCommentDto updateAssignmentComment(AssignmentCommentForUpdateDto assignmentComment);

    boolean deleteAssignmentComment(int commentId);

    AssignmentCommentDto findAssignmentComment(int Id);

    List<AssignmentCommentDto> listAssignmentComments(int commentId);
}
