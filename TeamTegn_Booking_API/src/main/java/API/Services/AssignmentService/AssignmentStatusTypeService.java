package API.Services.AssignmentService;

import API.Models.Database_Entities.AssignmentStatusTypeEntity;
import API.Repository.Assignment.AssignmentStatusTypeDAO;
import Shared.ForCreation.AssignmentStatusTypeForCreationDto;
import Shared.ForCreation.AssignmentStatusTypeForUpdateDto;
import Shared.ToReturn.AssignmentStatusTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssignmentStatusTypeService implements IAssignmentStatusTypeService {


    private ModelMapper modelMapper;

    private AssignmentStatusTypeDAO assignmentStatusTypeDAO;

    @Autowired
    public void setAssignmentStatusTypeDAO(AssignmentStatusTypeDAO assignmentStatusTypeDAO) {
        this.assignmentStatusTypeDAO = assignmentStatusTypeDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AssignmentStatusTypeDto addAssignmentStatusType(AssignmentStatusTypeForCreationDto assignment) {
        return assignmentStatusTypeDAO.addAssignmentStatusType(modelMapper.map(assignment, AssignmentStatusTypeEntity.class));
    }

    @Override
    public AssignmentStatusTypeDto findAssignmentStatusType(int id) {
        return modelMapper.map(assignmentStatusTypeDAO.findAssignmentStatusType(id), AssignmentStatusTypeDto.class);

    }

    @Override
    public List<AssignmentStatusTypeDto> listAssignmentStatusTypes(boolean showDeleted) {
        return assignmentStatusTypeDAO.listAssignmentStatusTypes(showDeleted);

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteAssignmentStatusType(int id) {
        return assignmentStatusTypeDAO.deleteAssignmentStatusType(id);
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AssignmentStatusTypeDto updateAssignmentStatusType(AssignmentStatusTypeForUpdateDto assignment) {
        return assignmentStatusTypeDAO.updateAssignmentStatusType(modelMapper.map(assignment, AssignmentStatusTypeEntity.class));
    }
}