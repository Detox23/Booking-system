package API.Services.AssignmentService;

import API.Database_Entities.AssignmentImportanceEntity;
import API.Repository.Assignment.AssignmentImportanceDAO;
import Shared.ForCreation.AssignmentImportanceForCreationDto;
import Shared.ForCreation.AssignmentImportanceForUpdateDto;
import Shared.ToReturn.AssignmentImportanceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentImportanceService implements IAssignmentImportanceService {

    private AssignmentImportanceDAO assignmentImportanceDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAssignmentImportanceDAO(AssignmentImportanceDAO assignmentImportanceDAO) {
        this.assignmentImportanceDAO = assignmentImportanceDAO;
    }

    @Override
    public AssignmentImportanceDto addAssignmentImportance(AssignmentImportanceForCreationDto assignmentImportance) {
        return assignmentImportanceDAO.addAssignmentImportance(modelMapper.map(assignmentImportance, AssignmentImportanceEntity.class));
    }

    @Override
    public boolean deleteAssignmentImportance(int id) {
        return assignmentImportanceDAO.deleteAssignmentImportance(id);
    }

    @Override
    public List<AssignmentImportanceDto> listAssignmentImportance() {
        return assignmentImportanceDAO.listAssignmentImportance();
    }

    @Override
    public AssignmentImportanceDto findAssignmentImportance(int id) {
        return assignmentImportanceDAO.findAssignmentImportance(id);
    }

    @Override
    public AssignmentImportanceDto updateAssignmentImportance(AssignmentImportanceForUpdateDto assignmentImportance) {
        return assignmentImportanceDAO.updateAssignmentImportance(modelMapper.map(assignmentImportance, AssignmentImportanceEntity.class));
    }
}
