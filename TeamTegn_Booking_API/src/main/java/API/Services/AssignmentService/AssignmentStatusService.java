package API.Services.AssignmentService;

import API.Database_Entities.AssignmentStatusEntity;
import API.Repository.Assignment.AssignmentStatusDAO;
import Shared.ForCreation.AssignmentStatusForCreationDto;
import Shared.ForCreation.AssignmentStatusForUpdateDto;
import Shared.ToReturn.AssignmentStatusDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssignmentStatusService implements IAssignmentStatusService {

    private ModelMapper modelMapper;

    private AssignmentStatusDAO assignmentStatusDAO;

    @Autowired
    public void setAssignmentStatusDAO(AssignmentStatusDAO assignmentStatusDAO) {
        this.assignmentStatusDAO = assignmentStatusDAO;
    }

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.modelMapper = mapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AssignmentStatusDto addAssignmentStatus(AssignmentStatusForCreationDto assignment) {
        return assignmentStatusDAO.addAssignmentStatus(modelMapper.map(assignment, AssignmentStatusEntity.class));
    }

    @Override
    public AssignmentStatusDto findAssignmentStatus(int id) {
        return modelMapper.map(assignmentStatusDAO.findAssignmentStatus(id), AssignmentStatusDto.class);
    }

    @Override
    public List<AssignmentStatusDto> listAssignmentStatuses() {
        return assignmentStatusDAO.listAssignmentStatuses();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteAssignmentStatus(int id) {
        return assignmentStatusDAO.deleteAssignmentStatus(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AssignmentStatusDto updateAssignmentStatus(AssignmentStatusForUpdateDto assignment) {
        return assignmentStatusDAO.updateAssignmentStatus(modelMapper.map(assignment, AssignmentStatusEntity.class));
    }
}


