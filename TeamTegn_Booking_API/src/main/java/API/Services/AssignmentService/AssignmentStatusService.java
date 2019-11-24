package API.Services.AssignmentService;

import API.Database_Entities.AssignmentStatusEntity;
import API.Repository.Assignment.AssignmentStatusDAO;
import API.Repository.Assignment.AssignmentStatusDAOImpl;
import Shared.ForCreation.AssignmentStatusForCreationDto;
import Shared.ForCreation.AssignmentStatusForUpdateDto;
import Shared.ToReturn.AssignmentStatusDto;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentStatusService implements IAssignmentStatusService {

    private ModelMapper mapper;

    private AssignmentStatusDAO repository;

    @Autowired
    public void setRepository(AssignmentStatusDAO repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AssignmentStatusDto add(AssignmentStatusForCreationDto assignment) {
        AssignmentStatusEntity assignmentStatusEntity = mapper.map(assignment, AssignmentStatusEntity.class);
        return mapper.map(repository.addOnce(assignmentStatusEntity), AssignmentStatusDto.class);
    }

    @Override
    public AssignmentStatusDto get(int id) {
        return mapper.map(repository.findOne(id), AssignmentStatusDto.class);

    }

    @Override
    public List<AssignmentStatusDto> list() {
        List<AssignmentStatusEntity> elements = Lists.newArrayList(repository.listAll());

        return mapper.map(elements, new TypeToken<List<AssignmentStatusDto>>() {
        }.getType());
    }

    @Override
    public boolean delete(int id) {
        return repository.deleteOne(id);
    }

    @Override
    public AssignmentStatusDto update(int id, AssignmentStatusForUpdateDto assignment) {
        AssignmentStatusEntity assignmentStatusEntity = mapper.map(assignment, AssignmentStatusEntity.class);
        return mapper.map(repository.updateOne(assignmentStatusEntity), AssignmentStatusDto.class);
    }
}


