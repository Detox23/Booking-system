package API.Services.AssignmentService;

import API.Database_Entities.AssignmentEntity;
import API.Repository.Assignment.AssignmentDAO;
import API.Repository.Assignment.AssignmentDAOImpl;
import Shared.ForCreation.AssignmentForCreationDto;
import Shared.ForCreation.AssignmentForUpdateDto;
import Shared.ToReturn.AssignmentDto;
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
    private AssignmentDAOImpl assignmentRepository;


    @Override
    public AssignmentDto add(AssignmentForCreationDto assignmentEntity) {
        AssignmentEntity dbEntity = mapper.map(assignmentEntity, AssignmentEntity.class);
        AssignmentEntity assignment = assignmentRepository.addOne(dbEntity);
        return mapper.map(assignment, AssignmentDto.class);
    }

    @Override
    public AssignmentDto get(int id) {
        AssignmentEntity assignment = assignmentRepository.getOne(id);
            return mapper.map(assignment, AssignmentDto.class);
    }

    @Override
    public Page<AssignmentDto> getAll(Pageable pageable) {
        Page<AssignmentEntity> list = assignmentRepository.listAll(pageable);
        Page<AssignmentDto> listDtos = mapper.map(list, new TypeToken<Page<AssignmentDto>>() {
        }.getType());
        return listDtos;
    }

    @Override
    public boolean delete(int id) {
       return assignmentRepository.deleteOne(id);
    }

    @Override
    public AssignmentDto update(int id, AssignmentForUpdateDto assignmentEntity) {
        AssignmentEntity as = mapper.map(assignmentEntity, AssignmentEntity.class);
        as.setId(id);
        AssignmentEntity entity = assignmentRepository.updateOne(as);
        return mapper.map(entity, AssignmentDto.class);

    }


}