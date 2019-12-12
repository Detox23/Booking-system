package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.AssignmentStukYearCodeEntity;
import Shared.ToReturn.AssignmentStukYearCodeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentSTUKYearCodeDAOImpl implements AssignmentSTUKYearCodeDAOCustom {

    private AssignmentSTUKYearCodeDAO assignmentSTUKYearCodeDAO;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAssignmentSTUKYearCodeDAO(AssignmentSTUKYearCodeDAO assignmentSTUKYearCodeDAO) {
        this.assignmentSTUKYearCodeDAO = assignmentSTUKYearCodeDAO;
    }


    @Override
    public AssignmentStukYearCodeDto addAssigmentStukYearCode(AssignmentStukYearCodeEntity stukYearCode) {
        try {
            checkIfExistsByStukYearCodeName(stukYearCode);
            AssignmentStukYearCodeEntity saved = assignmentSTUKYearCodeDAO.save(stukYearCode);
            return modelMapper.map(saved, AssignmentStukYearCodeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentStukYearCodeDto updateAssignmentStukYearCode(AssignmentStukYearCodeEntity stukYearCode) {
        try {
            AssignmentStukYearCodeEntity found = findIfExistsAndReturn(stukYearCode.getId());
            patcherHandler.fillNotNullFields(found, stukYearCode);
            checkIfExistsByStukYearCodeName(found);
            AssignmentStukYearCodeEntity updated = assignmentSTUKYearCodeDAO.save(found);
            return modelMapper.map(updated, AssignmentStukYearCodeDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was a problem with an update. [PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAssignmentStukYearCode(int id) {
        try {
            AssignmentStukYearCodeEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            AssignmentStukYearCodeEntity deletedResult = assignmentSTUKYearCodeDAO.save(found);
            return deletedResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AssignmentStukYearCodeDto> listAssignmentStukYearCodes(boolean showDeleted) {
        if (showDeleted) {
            try {
                List<AssignmentStukYearCodeEntity> foundCodes = assignmentSTUKYearCodeDAO.findAll();
                return modelMapper.map(foundCodes, new TypeToken<List<AssignmentStukYearCodeDto>>() {
                }.getType());
            } catch (Exception e) {
                throw e;
            }
        } else {
            try {
                List<AssignmentStukYearCodeEntity> foundCodes = assignmentSTUKYearCodeDAO.findAllByDeletedIsFalse();
                return modelMapper.map(foundCodes, new TypeToken<List<AssignmentStukYearCodeDto>>() {
                }.getType());
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public AssignmentStukYearCodeDto findAssignmentStukYearCode(int id) {
        try {
            AssignmentStukYearCodeEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AssignmentStukYearCodeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    private void checkIfExistsByStukYearCodeName(AssignmentStukYearCodeEntity assignmentStukYearCode) {
        if (assignmentStukYearCode.getId() == 0) {
            if (assignmentSTUKYearCodeDAO.countAllByStukYearCodeNameIs(assignmentStukYearCode.getStukYearCodeName()) > 0) {
                throw new DuplicateException(String.format("The stuk year code with name: %s already exists", assignmentStukYearCode.getStukYearCodeName()));
            }
        } else {
            if (assignmentSTUKYearCodeDAO.countAllByStukYearCodeNameIsAndIdIsNot(assignmentStukYearCode.getStukYearCodeName(), assignmentStukYearCode.getId()) > 0) {
                throw new DuplicateException(String.format("The stuk year code with name: %s already exists", assignmentStukYearCode.getStukYearCodeName()));
            }
        }
    }

    private AssignmentStukYearCodeEntity findIfExistsAndReturn(int id) {
        Optional<AssignmentStukYearCodeEntity> found = assignmentSTUKYearCodeDAO.findByIdIsAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("There was no result found for STUK year code with id %d", id));
        }
        return found.get();
    }
}
