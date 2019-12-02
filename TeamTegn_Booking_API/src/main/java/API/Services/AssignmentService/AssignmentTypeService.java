package API.Services.AssignmentService;

import API.Models.Database_Entities.AssignmentTypeEntity;
import API.Repository.Assignment.AssignmentTypeDAO;
import Shared.ForCreation.AssignmentTypeForCreationDto;
import Shared.ForCreation.AssignmentTypeForUpdateDto;
import Shared.ToReturn.AssignmentTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentTypeService implements IAssignmentTypeService {


    private ModelMapper modelMapper;

    private AssignmentTypeDAO assignmentTypeDAO;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAssignmentTypeDAO(AssignmentTypeDAO assignmentTypeDAO) {
        this.assignmentTypeDAO = assignmentTypeDAO;
    }

    @Override
    public AssignmentTypeDto addAssignmentType(AssignmentTypeForCreationDto assignmentType) {
        return assignmentTypeDAO.addAssignmentType(modelMapper.map(assignmentType, AssignmentTypeEntity.class));
    }

    @Override
    public AssignmentTypeDto findAssignmentType(int id) {
        return assignmentTypeDAO.findAssignmentType(id);
    }

    @Override
    public List<AssignmentTypeDto> listAssignmentType() {
        return assignmentTypeDAO.listAssignmentType();
    }

    @Override
    public boolean deleteAssignmentType(int id) {
        return assignmentTypeDAO.deleteAssignmentType(id);
    }

    @Override
    public AssignmentTypeDto updateAssignmentType(AssignmentTypeForUpdateDto assignmentType) {
        return assignmentTypeDAO.updateAssignmentType(modelMapper.map(assignmentType, AssignmentTypeEntity.class));
    }
}
