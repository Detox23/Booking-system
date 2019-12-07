package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.AssignmentStatusEntity;
import Shared.ToReturn.AssignmentStatusDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentStatusDAOImpl implements AssignmentStatusDAOCustom {

    private AssignmentStatusDAO assignmentStatusDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    @Autowired
    public void setAssignmentStatusDAO(AssignmentStatusDAO assignmentStatusDAO) {
        this.assignmentStatusDAO = assignmentStatusDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AssignmentStatusDto> listAssignmentStatuses(boolean showDeleted) {
        if(showDeleted){
            try {
                return modelMapper.map(assignmentStatusDAO.findAll(), new TypeToken<List<AssignmentStatusDto>>() {
                }.getType());
            } catch (Exception e) {
                throw e;
            }
        }else{
            try {
                return modelMapper.map(assignmentStatusDAO.findAllByDeletedIsFalse(), new TypeToken<List<AssignmentStatusDto>>() {
                }.getType());
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public AssignmentStatusDto findAssignmentStatus(int id) {
        try {
            AssignmentStatusEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AssignmentStatusDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentStatusDto addAssignmentStatus(AssignmentStatusEntity assignmentStatusEntity) {
        try {
            checkIfExistsByStatusName(assignmentStatusEntity);
            AssignmentStatusEntity saved = assignmentStatusDAO.save(assignmentStatusEntity);
            return modelMapper.map(saved, AssignmentStatusDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentStatusDto updateAssignmentStatus(AssignmentStatusEntity assignmentStatusEntity) {
        try {
            AssignmentStatusEntity found = findIfExistsAndReturn(assignmentStatusEntity.getId());
            patcherHandler.fillNotNullFields(found, assignmentStatusEntity);
            checkIfExistsByStatusName(found);
            AssignmentStatusEntity result = assignmentStatusDAO.save(found);
            return modelMapper.map(result, AssignmentStatusDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAssignmentStatus(int id) {
        try {
            AssignmentStatusEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            AssignmentStatusEntity deletionResult = assignmentStatusDAO.save(found);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    private AssignmentStatusEntity findIfExistsAndReturn(int id) {
        Optional<AssignmentStatusEntity> found = assignmentStatusDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Assignment status with id: %d was not found.", id));
        }
        return found.get();
    }

    private void checkIfExistsByStatusName(AssignmentStatusEntity assignmentStatus){
        if(assignmentStatus.getId()==0){
            if(assignmentStatusDAO.countAllByAssignmentStatusName(assignmentStatus.getAssignmentStatusName())> 0){
                throw new DuplicateException(String.format("The status with name: %s already exists", assignmentStatus.getAssignmentStatusName()));
            }
        }else{
            if(assignmentStatusDAO.countAllByAssignmentStatusNameAndIdIsNot(assignmentStatus.getAssignmentStatusName(), assignmentStatus.getId())> 0){
                throw new DuplicateException(String.format("The status with name: %s already exists", assignmentStatus.getAssignmentStatusName()));
            }
        }
    }

}
