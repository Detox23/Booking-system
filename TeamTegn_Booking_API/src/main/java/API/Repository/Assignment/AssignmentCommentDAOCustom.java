package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentCommentEntity;
import Shared.ToReturn.AssignmentCommentDto;

import java.util.List;

public interface AssignmentCommentDAOCustom {
    List<AssignmentCommentDto> listAssignmentComments(int assignmentId);

    AssignmentCommentDto findAssignmentComment(int commentId);

    AssignmentCommentDto addAssignmentComment(AssignmentCommentEntity assignmentComment);

    AssignmentCommentDto updateAssignmentComment(AssignmentCommentEntity assignmentComment);

    boolean deleteAssignmentComment(int commentId);
}
