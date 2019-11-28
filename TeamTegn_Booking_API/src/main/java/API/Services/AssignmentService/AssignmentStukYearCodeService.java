package API.Services.AssignmentService;

import API.Database_Entities.AssignmentStukYearCodeEntity;
import API.Repository.Assignment.AssignmentSTUKYearCodeDAO;
import Shared.ForCreation.AssignmentStukYearCodeForCreationDto;
import Shared.ForCreation.AssignmentStukYearCodeForUpdateDto;
import Shared.ToReturn.AssignmentStukYearCodeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentStukYearCodeService implements IAssignmentStukYearCodeService {

    private AssignmentSTUKYearCodeDAO assignmentSTUKYearCodeDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAssignmentSTUKYearCodeDAO(AssignmentSTUKYearCodeDAO assignmentSTUKYearCodeDAO) {
        this.assignmentSTUKYearCodeDAO = assignmentSTUKYearCodeDAO;
    }

    @Override
    public AssignmentStukYearCodeDto addAssigmentStukYearCode(AssignmentStukYearCodeForCreationDto stukYearCode) {
        return assignmentSTUKYearCodeDAO.addAssigmentStukYearCode(modelMapper.map(stukYearCode, AssignmentStukYearCodeEntity.class));
    }

    @Override
    public AssignmentStukYearCodeDto updateAssignmentStukYearCode(AssignmentStukYearCodeForUpdateDto stukYearCode) {
        return assignmentSTUKYearCodeDAO.updateAssignmentStukYearCode(modelMapper.map(stukYearCode, AssignmentStukYearCodeEntity.class));
    }

    @Override
    public boolean deleteAssignmentStukYearCode(int id) {
        return assignmentSTUKYearCodeDAO.deleteAssignmentStukYearCode(id);
    }

    @Override
    public List<AssignmentStukYearCodeDto> listAssignmentStukYearCodes() {
        return assignmentSTUKYearCodeDAO.listAssignmentStukYearCodes();
    }

    @Override
    public AssignmentStukYearCodeDto findAssignmentStukYearCode(int id) {
        return assignmentSTUKYearCodeDAO.findAssignmentStukYearCode(id);
    }
}
