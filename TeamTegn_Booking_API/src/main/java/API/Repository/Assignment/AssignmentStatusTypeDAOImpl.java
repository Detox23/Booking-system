package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AssignmentStatusTypeEntity;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.AssignmentStatusDto;
import Shared.ToReturn.AssignmentStatusTypeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentStatusTypeDAOImpl implements AssignmentStatusTypeDAOCustom {

    private AssignmentStatusTypeDAO assignmentStatusTypeDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setAssignmentStatusTypeDAO(AssignmentStatusTypeDAO assignmentStatusTypeDAO) {
        this.assignmentStatusTypeDAO = assignmentStatusTypeDAO;
    }

    @Override
    public List<AssignmentStatusTypeDto> listAssignmentStatusTypes() {
        return modelMapper.map(assignmentStatusTypeDAO.findAllByDeletedIsFalse(Sort.by(Sort.Direction.ASC, "DisplayOrder")), new TypeToken<List<AssignmentStatusDto>>() {
        }.getType());
    }

    @Override
    public AssignmentStatusTypeDto findAssignmentStatusType(int id) {
        try {
            AssignmentStatusTypeEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AssignmentStatusTypeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentStatusTypeDto addAssignmentStatusType(AssignmentStatusTypeEntity assignmentStatusTypeEntity) {
        try {
            int count = assignmentStatusTypeDAO.countAllByAssignmentStatusTypeNameIs(assignmentStatusTypeEntity.getAssignmentStatusTypeName());
            if (count > 0) {
                throw new DuplicateException(String.format("The status with name: %s already exists", assignmentStatusTypeEntity.getAssignmentStatusTypeName()));
            }
            AssignmentStatusTypeEntity saved = assignmentStatusTypeDAO.save(assignmentStatusTypeEntity);
            return modelMapper.map(saved, AssignmentStatusTypeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentStatusTypeDto updateAssignmentStatusType(AssignmentStatusTypeEntity assignmentStatusTypeEntity) {
        try {
            AssignmentStatusTypeEntity found = findIfExistsAndReturn(assignmentStatusTypeEntity.getId());
            patcherHandler.fillNotNullFields(found, assignmentStatusTypeEntity);
            AssignmentStatusTypeEntity result = assignmentStatusTypeDAO.save(found);
            return modelMapper.map(result, AssignmentStatusTypeDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAssignmentStatusType(int id) {
        try {
            AssignmentStatusTypeEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            AssignmentStatusTypeEntity deletionResult = assignmentStatusTypeDAO.save(found);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    private AssignmentStatusTypeEntity findIfExistsAndReturn(int id) {
        Optional<AssignmentStatusTypeEntity> found = assignmentStatusTypeDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Assignment status type with id: %d was not found.", id));
        }
        return found.get();
    }
}
