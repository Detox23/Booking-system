package API.Services;

import API.DAO.IAssignmentDAO;
import Objects.Factory.Database_Entities.AssignmentEntity;
import Shared.ToReturn.AssignmentDto;
import Shared.ForCreation.AssignmentForCreationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService implements IAssignmentService {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IAssignmentDAO assignmentDAO;

    @Override
    public AssignmentDto add(AssignmentForCreationDto assignmentEntity) {
        AssignmentEntity dbEntity = mapper.map(assignmentEntity, AssignmentEntity.class);
        AssignmentEntity assignment = assignmentDAO.add(dbEntity);
        return  mapper.map(assignment, AssignmentDto.class);
    }

    @Override
    public AssignmentDto get(int id) {
        AssignmentEntity assignment = assignmentDAO.get(id);
        return  mapper.map(assignment, AssignmentDto.class);
    }

    @Override
    public List<AssignmentDto> getAll() {
        List<AssignmentEntity> list = assignmentDAO.getAll();
        List<AssignmentDto> listDtos = mapper.map(list, new TypeToken<List<AssignmentDto>>(){}.getType());
        return listDtos;
    }

    @Override
    public boolean delete(int id) {
       return assignmentDAO.delete(id);
    }
}
