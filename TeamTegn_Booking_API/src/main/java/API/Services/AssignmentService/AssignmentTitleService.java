package API.Services.AssignmentService;

import API.Models.Database_Entities.AssignmentTitleEntity;
import API.Repository.Assignment.AssignmentTitleDAO;
import Shared.ForCreation.AssignmentTitleForCreationDto;
import Shared.ForCreation.AssignmentTitleForUpdateDto;
import Shared.ToReturn.AssignmentTitleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentTitleService implements IAssignmentTitleService {

    private ModelMapper modelMapper;

    private AssignmentTitleDAO assignmentTitleDAO;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAssignmentTitleDAO(AssignmentTitleDAO assignmentTitleDAO) {
        this.assignmentTitleDAO = assignmentTitleDAO;
    }

    @Override
    public AssignmentTitleDto addAssignmentTitle(AssignmentTitleForCreationDto assignmentTitle) {
        return assignmentTitleDAO.addAssignmentTitle(modelMapper.map(assignmentTitle, AssignmentTitleEntity.class));
    }

    @Override
    public AssignmentTitleDto updateAssignmentTitle(AssignmentTitleForUpdateDto assignmentTitle) {
        return assignmentTitleDAO.updateAssignmentTitle(modelMapper.map(assignmentTitle, AssignmentTitleEntity.class));
    }

    @Override
    public boolean deleteAssignmentTitle(int id) {
        return assignmentTitleDAO.deleteAssignmentTitle(id);
    }

    @Override
    public AssignmentTitleDto findAssignmentTitle(int id) {
        return assignmentTitleDAO.findAssignmentTitle(id);
    }

    @Override
    public List<AssignmentTitleDto> listAssignmentTitles() {
        return assignmentTitleDAO.listAssignmentTitles();
    }
}
