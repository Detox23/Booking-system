package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.AssignmentStatusTypeEntity;
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
    public List<AssignmentStatusTypeDto> listAssignmentStatusTypes(boolean showDeleted) {
        if (showDeleted) {
            try {
                return modelMapper.map(assignmentStatusTypeDAO.findAll(Sort.by(Sort.Direction.ASC, "DisplayOrder")), new TypeToken<List<AssignmentStatusTypeDto>>() {
                }.getType());
            } catch (Exception e) {
                throw e;
            }
        } else {
            try {
                return modelMapper.map(assignmentStatusTypeDAO.findAllByDeletedIsFalse(Sort.by(Sort.Direction.ASC, "DisplayOrder")), new TypeToken<List<AssignmentStatusTypeDto>>() {
                }.getType());
            } catch (Exception e) {
                throw e;
            }
        }
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
            checkIfExistsByStatusName(assignmentStatusTypeEntity);
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
            checkIfExistsByStatusName(found);
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

    private void checkIfExistsByStatusName(AssignmentStatusTypeEntity assignmentStatusType) {
        if (assignmentStatusType.getId() == 0) {
            if (assignmentStatusTypeDAO.countAllByAssignmentStatusTypeNameIs(assignmentStatusType.getAssignmentStatusTypeName()) > 0) {
                throw new DuplicateException(String.format("The status type with name: %s already exists", assignmentStatusType.getAssignmentStatusTypeName()));
            }
        } else {
            if (assignmentStatusTypeDAO.countAllByAssignmentStatusTypeNameIsAndIdIsNot(assignmentStatusType.getAssignmentStatusTypeName(), assignmentStatusType.getId()) > 0) {
                throw new DuplicateException(String.format("The status type with name: %s already exists", assignmentStatusType.getAssignmentStatusTypeName()));
            }
        }
    }
}
