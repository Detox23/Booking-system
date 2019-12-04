package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.AssignmentInterpretationTypeEntity;
import Shared.ToReturn.AssignmentInterpretationTypeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentInterpretationTypeDAOImpl implements AssignmentInterpretationTypeDAOCustom {

    private AssignmentInterpretationTypeDAO assignmentInterpretationTypeDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    @Autowired
    public void setAssignmentInterpretationTypeDAO(AssignmentInterpretationTypeDAO assignmentInterpretationTypeDAO) {
        this.assignmentInterpretationTypeDAO = assignmentInterpretationTypeDAO;
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
    public AssignmentInterpretationTypeDto addAssignmentInterpretationType(AssignmentInterpretationTypeEntity assignmentInterpretationTypeService) {
        try {
            int count = assignmentInterpretationTypeDAO.countAllByInterpretationTypeNameIs(assignmentInterpretationTypeService.getInterpretationTypeName());
            if (count > 0) {
                throw new DuplicateException(String.format("The status with name: %s already exists", assignmentInterpretationTypeService.getInterpretationTypeName()));
            }
            AssignmentInterpretationTypeEntity saved = assignmentInterpretationTypeDAO.save(assignmentInterpretationTypeService);
            return modelMapper.map(saved, AssignmentInterpretationTypeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAssignmentInterpretationType(int id) {
        try {
            AssignmentInterpretationTypeEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            AssignmentInterpretationTypeEntity deletionResult = assignmentInterpretationTypeDAO.save(found);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AssignmentInterpretationTypeDto> listAssignmentInterpretationTypes() {
        try {
            return modelMapper.map(assignmentInterpretationTypeDAO.findAllByDeletedIsFalse(), new TypeToken<List<AssignmentInterpretationTypeDto>>() {
            }.getType());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentInterpretationTypeDto findAssignmentInterpretationType(int id) {
        try {
            AssignmentInterpretationTypeEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AssignmentInterpretationTypeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentInterpretationTypeDto updateAssignmentInterpretationType(AssignmentInterpretationTypeEntity assignmentInterpretationTypeService) {
        try {
            AssignmentInterpretationTypeEntity found = findIfExistsAndReturn(assignmentInterpretationTypeService.getId());
            patcherHandler.fillNotNullFields(found, assignmentInterpretationTypeService);
            AssignmentInterpretationTypeEntity result = assignmentInterpretationTypeDAO.save(found);
            return modelMapper.map(result, AssignmentInterpretationTypeDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an interpretation type. [PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }

    private AssignmentInterpretationTypeEntity findIfExistsAndReturn(int id) {
        Optional<AssignmentInterpretationTypeEntity> found = assignmentInterpretationTypeDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Interpretation type with id: %d was not found.", id));
        }
        return found.get();
    }
}
