package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.AssignmentTypeEntity;
import Shared.ToReturn.AssignmentTypeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentTypeDAOImpl implements AssignmentTypeDAOCustom {


    private AssignmentTypeDAO assignmentTypeDAO;
    private PatcherHandler patcherHandler;
    private ModelMapper modelMapper;

    @Autowired
    public void setAssignmentTypeDAO(AssignmentTypeDAO assignmentTypeDAO) {
        this.assignmentTypeDAO = assignmentTypeDAO;
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
    public AssignmentTypeDto addAssignmentType(AssignmentTypeEntity assignmentType) {
        try {
            checkIfAssignmentTypeExists(assignmentType);
            AssignmentTypeEntity saved = assignmentTypeDAO.save(assignmentType);
            return modelMapper.map(saved, AssignmentTypeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAssignmentType(int id) {
        try {
            AssignmentTypeEntity toDelete = findIfExistsAndReturn(id);
            toDelete.setDeleted(true);
            AssignmentTypeEntity deletionResult = assignmentTypeDAO.save(toDelete);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AssignmentTypeDto> listAssignmentTypes(boolean showDeleted) {
        if (showDeleted) {
            try {
                return modelMapper.map(assignmentTypeDAO.findAll(), new TypeToken<List<AssignmentTypeDto>>() {
                }.getType());
            } catch (Exception e) {
                throw e;
            }
        } else {
            try {
                return modelMapper.map(assignmentTypeDAO.findAllByDeletedIsFalse(), new TypeToken<List<AssignmentTypeDto>>() {
                }.getType());
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public AssignmentTypeDto findAssignmentType(int id) {
        try {
            AssignmentTypeEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AssignmentTypeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentTypeDto updateAssignmentType(AssignmentTypeEntity assignmentType) {
        try {
            AssignmentTypeEntity found = findIfExistsAndReturn(assignmentType.getId());
            patcherHandler.fillNotNullFields(found, assignmentType);
            checkIfAssignmentTypeExists(found);
            AssignmentTypeEntity result = assignmentTypeDAO.save(found);
            return modelMapper.map(result, AssignmentTypeDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    private void checkIfAssignmentTypeExists(AssignmentTypeEntity assignmentType) {
        if (assignmentType.getId() == 0) {
            if (assignmentTypeDAO.countAllByAssignmentTypeNameIs(assignmentType.getAssignmentTypeName()) > 0) {
                throw new DuplicateException(String.format("The type: %s already exists", assignmentType.getAssignmentTypeName()));
            }
        } else {
            if (assignmentTypeDAO.countAllByAssignmentTypeNameIsAndIdIsNot(assignmentType.getAssignmentTypeName(), assignmentType.getId()) > 0) {
                throw new DuplicateException(String.format("The type: %s already exists", assignmentType.getAssignmentTypeName()));
            }
        }
    }

    private AssignmentTypeEntity findIfExistsAndReturn(int id) {
        Optional<AssignmentTypeEntity> found = assignmentTypeDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Assignment type with id: %d was not found.", id));
        }
        return found.get();
    }
}
