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
            int count = assignmentTypeDAO.countAllByAssignmentTypeNameIs(assignmentType.getAssignmentTypeName());
            if (count > 0) {
                throw new DuplicateException(String.format("The status with name: %s already exists", assignmentType.getAssignmentTypeName()));
            }
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
    public List<AssignmentTypeDto> listAssignmentType() {
        return modelMapper.map(assignmentTypeDAO.findAllByDeletedIsFalse(), new TypeToken<List<AssignmentTypeDto>>() {
        }.getType());
    }

    @Override
    public AssignmentTypeDto findAssignmentType(int id) {
        AssignmentTypeEntity found = findIfExistsAndReturn(id);
        return modelMapper.map(found, AssignmentTypeDto.class);
    }

    @Override
    public AssignmentTypeDto updateAssignmentType(AssignmentTypeEntity assignmentType) {
        try {
            AssignmentTypeEntity found = findIfExistsAndReturn(assignmentType.getId());
            patcherHandler.fillNotNullFields(found, assignmentType);
            AssignmentTypeEntity result = assignmentTypeDAO.save(found);
            return modelMapper.map(result, AssignmentTypeDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
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
