package API.Services;

import API.Repository.Assignment.AssignmentDAO;
import API.Database_Entities.AssignmentEntity;
import Shared.ForCreation.AssignmentForUpdateDto;
import Shared.ToReturn.AssignmentDto;
import Shared.ForCreation.AssignmentForCreationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentService implements IAssignmentService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AssignmentDAO assignmentDAO;


    @Override
    public AssignmentDto add(AssignmentForCreationDto assignmentEntity) {
        AssignmentEntity dbEntity = mapper.map(assignmentEntity, AssignmentEntity.class);
        AssignmentEntity assignment = assignmentDAO.save(dbEntity);
        return mapper.map(assignment, AssignmentDto.class);
    }

    @Override
    public AssignmentDto get(int id) {
        Optional<AssignmentEntity> assignment = assignmentDAO.findById(id);
        if(assignment.isPresent()) {
            return mapper.map(assignment.get(), AssignmentDto.class);
        }

        return null;
    }

    @Override
    public Page<AssignmentDto> getAll(Pageable pageable) {
        Page<AssignmentEntity> list = assignmentDAO.findAll( pageable);
        Page<AssignmentDto> listDtos = mapper.map(list, new TypeToken<Page<AssignmentDto>>(){}.getType());
        return listDtos;
    }

    @Override
    public boolean delete(int id) {
        assignmentDAO.deleteById(id);
        return assignmentDAO.findById(id).get() == null;
    }

    @Override
    public AssignmentDto update(int id, AssignmentForUpdateDto assignmentEntity) {
        AssignmentEntity as = mapper.map(assignmentEntity, AssignmentEntity.class);
        as.setId(id);
        AssignmentEntity entity = assignmentDAO.updateAssignment(as);
        return  mapper.map(entity, AssignmentDto.class);

    }


}