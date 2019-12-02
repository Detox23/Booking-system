package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Models.Database_Entities.AssignmentStukYearCodeEntity;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
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
            checkForDuplicates(stukYearCode.getStukYearCodeName());
            AssignmentStukYearCodeEntity saved = assignmentSTUKYearCodeDAO.save(stukYearCode);
            return modelMapper.map(saved, AssignmentStukYearCodeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentStukYearCodeDto updateAssignmentStukYearCode(AssignmentStukYearCodeEntity stukYearCode) {
        try {
            checkForDuplicates(stukYearCode.getStukYearCodeName());
            AssignmentStukYearCodeEntity found = findIfExistsAndReturn(stukYearCode.getId());
            patcherHandler.fillNotNullFields(found, stukYearCode);
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
        AssignmentStukYearCodeEntity found = findIfExistsAndReturn(id);
        found.setDeleted(true);
        AssignmentStukYearCodeEntity deletedResult = assignmentSTUKYearCodeDAO.save(found);
        return deletedResult.isDeleted();
    }

    @Override
    public List<AssignmentStukYearCodeDto> listAssignmentStukYearCodes() {
        List<AssignmentStukYearCodeEntity> foundCodes = assignmentSTUKYearCodeDAO.findAllByDeletedIsFalse();
        return modelMapper.map(foundCodes, new TypeToken<List<AssignmentStukYearCodeDto>>() {
        }.getType());
    }

    @Override
    public AssignmentStukYearCodeDto findAssignmentStukYearCode(int id) {
        AssignmentStukYearCodeEntity found = findIfExistsAndReturn(id);
        return modelMapper.map(found, AssignmentStukYearCodeDto.class);
    }

    private void checkForDuplicates(String name) {
        Optional<AssignmentStukYearCodeEntity> found = assignmentSTUKYearCodeDAO.findByStukYearCodeNameIsAndDeletedIsFalse(name);
        if (found.isPresent()) {
            throw new DuplicateException(String.format("There is already STUK year code with the name: %s", name));
        }
    }

    private AssignmentStukYearCodeEntity findIfExistsAndReturn(int id) {
        Optional<AssignmentStukYearCodeEntity> found = assignmentSTUKYearCodeDAO.findById(id);
        if (!found.isPresent() || found.get().isDeleted()) {
            throw new NotFoundException(String.format("There was no result found for STUK year code with id %d", id));
        }
        return found.get();
    }
}
