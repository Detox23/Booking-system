package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.AssignmentCommentEntity;
import Shared.ToReturn.AssignmentCommentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentCommentDAOImpl implements AssignmentCommentDAOCustom {

    private AssignmentCommentDAO assignmentCommentDAO;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;

    @Autowired
    public void setAssignmentCommentDAO(AssignmentCommentDAO assignmentCommentDAO) {
        this.assignmentCommentDAO = assignmentCommentDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Override
    public List<AssignmentCommentDto> listAssignmentComments(int assignmentID) {
        try {
            Type listType = new TypeToken<List<AssignmentCommentDto>>() {
            }.getType();
            List<AssignmentCommentEntity> listOfComments = assignmentCommentDAO.findAllByAssignmentId(assignmentID);
            return modelMapper.map(listOfComments, listType);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentCommentDto findAssignmentComment(int commentId) {
        try {
            AssignmentCommentEntity found = findIfExistsAndReturn(commentId);
            return modelMapper.map(found, AssignmentCommentDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentCommentDto addAssignmentComment(AssignmentCommentEntity assignmentComment) {
        try {
            AssignmentCommentEntity addedComment = assignmentCommentDAO.save(assignmentComment);
            return modelMapper.map(addedComment, AssignmentCommentDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentCommentDto updateAssignmentComment(AssignmentCommentEntity assignmentComment) {
        try {
            AssignmentCommentEntity found = findIfExistsAndReturn(assignmentComment.getId());
            patcherHandler.fillNotNullFields(found, assignmentComment);
            AssignmentCommentEntity updated = assignmentCommentDAO.save(found);
            return modelMapper.map(updated, AssignmentCommentDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was a problem with updating an account comment. [PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAssignmentComment(int commentId) {
        try {
            AssignmentCommentEntity found = findIfExistsAndReturn(commentId);
            assignmentCommentDAO.delete(found);
            return assignmentCommentDAO.findById(commentId).isPresent();
        } catch (Exception e) {
            throw e;
        }
    }

    private AssignmentCommentEntity findIfExistsAndReturn(int id) {
        Optional<AssignmentCommentEntity> found = assignmentCommentDAO.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Assignment comment with id: %d was not found.", id));
        }
        return found.get();
    }
}
