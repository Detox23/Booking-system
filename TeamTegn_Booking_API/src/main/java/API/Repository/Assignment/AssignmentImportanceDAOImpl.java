package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AssignmentImportanceEntity;
import Shared.ToReturn.AssignmentImportanceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return null;
    }

    @Override
    public boolean deleteAssignmentImportance(int id) {
        return false;
    }

    @Override
    public List<AssignmentImportanceDto> listAssignmentImportance() {
        return null;
    }

    @Override
    public AssignmentImportanceDto findAssignmentImportance(int id) {
        return null;
    }

    @Override
    public AssignmentImportanceDto updateAssignmentImportance(AssignmentImportanceEntity assignmentImportance) {
        return null;
    }
}
