package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.AssignmentImportanceEntity;
import Shared.ToReturn.AssignmentImportanceDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentImportanceDAOImpl implements AssignmentImportanceDAOCustom {

    private AssignmentImportanceDAO assignmentImportanceDAO;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setAssignmentImportanceDAO(AssignmentImportanceDAO assignmentImportanceDAO) {
        this.assignmentImportanceDAO = assignmentImportanceDAO;
    }

    @Override
    public AssignmentImportanceDto addAssignmentImportance(AssignmentImportanceEntity assignmentImportance) {
        try {
            checkIfExistsByImportanceName(assignmentImportance);
            AssignmentImportanceEntity saved = assignmentImportanceDAO.save(assignmentImportance);
            return modelMapper.map(saved, AssignmentImportanceDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAssignmentImportance(int id) {
        try {
            AssignmentImportanceEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            AssignmentImportanceEntity deletionResult = assignmentImportanceDAO.save(found);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AssignmentImportanceDto> listAssignmentImportance(boolean showDeleted) {
        if (showDeleted) {
            try {
                return modelMapper.map(assignmentImportanceDAO.findAll(), new TypeToken<List<AssignmentImportanceDto>>() {
                }.getType());
            } catch (Exception e) {
                throw e;
            }
        } else {
            try {
                return modelMapper.map(assignmentImportanceDAO.findAllByDeletedIsFalse(), new TypeToken<List<AssignmentImportanceDto>>() {
                }.getType());
            } catch (Exception e) {
                throw e;
            }
        }

    }

    @Override
    public AssignmentImportanceDto findAssignmentImportance(int id) {
        try {
            AssignmentImportanceEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AssignmentImportanceDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentImportanceDto updateAssignmentImportance(AssignmentImportanceEntity assignmentImportance) {
        try {
            AssignmentImportanceEntity found = findIfExistsAndReturn(assignmentImportance.getId());
            patcherHandler.fillNotNullFields(found, assignmentImportance);
            checkIfExistsByImportanceName(found);
            AssignmentImportanceEntity result = assignmentImportanceDAO.save(found);
            return modelMapper.map(result, AssignmentImportanceDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an importance [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    private AssignmentImportanceEntity findIfExistsAndReturn(int id) {
        Optional<AssignmentImportanceEntity> found = assignmentImportanceDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Importance with id: %d was not found.", id));
        }
        return found.get();
    }

    private void checkIfExistsByImportanceName(AssignmentImportanceEntity assignmentImportance) {
        if (assignmentImportance.getId() == 0) {
            if (assignmentImportanceDAO.countAllByImportanceNameIs(assignmentImportance.getImportanceName()) > 0) {
                throw new DuplicateException(String.format("The account type with name: %s already exists", assignmentImportance.getImportanceName()));
            }
        } else {
            if (assignmentImportanceDAO.countAllByImportanceNameIsAndIdIsNot(assignmentImportance.getImportanceName(), assignmentImportance.getId()) > 0) {
                throw new DuplicateException(String.format("The account type with name: %s already exists", assignmentImportance.getImportanceName()));
            }
        }
    }
}
