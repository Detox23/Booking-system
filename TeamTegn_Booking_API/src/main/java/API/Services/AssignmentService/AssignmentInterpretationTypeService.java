package API.Services.AssignmentService;

import API.Models.Database_Entities.AssignmentInterpretationTypeEntity;
import API.Repository.Assignment.AssignmentInterpretationTypeDAO;
import Shared.ForCreation.AssignmentInterpretationTypeForCreationDto;
import Shared.ForCreation.AssignmentInterpretationTypeForUpdateDto;
import Shared.ToReturn.AssignmentInterpretationTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssignmentInterpretationTypeService implements IAssignmentInterpretationTypeService {


    private ModelMapper modelMapper;

    private AssignmentInterpretationTypeDAO assignmentInterpretationTypeDAO;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Autowired
    public void setAssignmentInterpretationTypeDAO(AssignmentInterpretationTypeDAO assignmentInterpretationTypeDAO) {
        this.assignmentInterpretationTypeDAO = assignmentInterpretationTypeDAO;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AssignmentInterpretationTypeDto addAssignmentInterpretationType(AssignmentInterpretationTypeForCreationDto assignmentEntity) {
        return assignmentInterpretationTypeDAO.addAssignmentInterpretationType(modelMapper.map(assignmentEntity, AssignmentInterpretationTypeEntity.class));
    }

    @Override
    public AssignmentInterpretationTypeDto findAssignmentInterpretationType(int id) {
        return assignmentInterpretationTypeDAO.findAssignmentInterpretationType(id);
    }

    @Override
    public List<AssignmentInterpretationTypeDto> listAssignmentInterpretationTypes(boolean showDeleted) {
        return assignmentInterpretationTypeDAO.listAssignmentInterpretationTypes(showDeleted);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteAssignmentInterpretationType(int id) {
        return assignmentInterpretationTypeDAO.deleteAssignmentInterpretationType(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AssignmentInterpretationTypeDto updateAssignmentInterpretationType(AssignmentInterpretationTypeForUpdateDto assignment) {
        return assignmentInterpretationTypeDAO.updateAssignmentInterpretationType(modelMapper.map(assignment, AssignmentInterpretationTypeEntity.class));
    }
}
