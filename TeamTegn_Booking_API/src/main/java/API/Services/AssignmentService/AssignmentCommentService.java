package API.Services.AssignmentService;

import API.Models.Database_Entities.AssignmentCommentEntity;
import API.Repository.Assignment.AssignmentCommentDAO;
import Shared.ForCreation.AssignmentCommentForCreationDto;
import Shared.ForCreation.AssignmentCommentForUpdateDto;
import Shared.ToReturn.AssignmentCommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssignmentCommentService implements IAssignmentCommentService{

    private AssignmentCommentDAO assignmentCommentDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAssignmentCommentDAO(AssignmentCommentDAO assignmentCommentDAO) {
        this.assignmentCommentDAO = assignmentCommentDAO;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AssignmentCommentDto addAssignmentComment(AssignmentCommentForCreationDto assignmentComment) {
        return assignmentCommentDAO.addAssignmentComment(modelMapper.map(assignmentComment, AssignmentCommentEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AssignmentCommentDto updateAssignmentComment(AssignmentCommentForUpdateDto assignmentComment) {
        return assignmentCommentDAO.updateAssignmentComment(modelMapper.map(assignmentComment, AssignmentCommentEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteAssignmentComment(int commentId) {
        return assignmentCommentDAO.deleteAssignmentComment(commentId);
    }

    @Override
    public AssignmentCommentDto findAssignmentComment(int id) {
        return assignmentCommentDAO.findAssignmentComment(id);
    }

    @Override
    public List<AssignmentCommentDto> listAssignmentComments(int assignmentId) {
        return assignmentCommentDAO.listAssignmentComments(assignmentId);
    }
}
