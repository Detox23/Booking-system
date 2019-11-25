package API.Services.AssignmentService;

import API.Repository.Assignment.AssignmentStatusTypeDAO;
import Shared.ForCreation.AssignmentStatusTypeForCreationDto;
import Shared.ForCreation.AssignmentStatusTypeForUpdateDto;
import Shared.ToReturn.AssignmentStatusTypeDto;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentStatusTypeService implements IAssignmentStatusTypeService{


    private ModelMapper mapper;

    private AssignmentStatusTypeDAO repository;

    @Autowired
    public void setRepository(AssignmentStatusTypeDAO repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AssignmentStatusTypeDto add(AssignmentStatusTypeForCreationDto assignment) {
        AssignmentStatusTypeEntity assignmentStatusTypeEntity = mapper.map(assignment, AssignmentStatusTypeEntity.class);
        return  mapper.map(repository.addOne(assignmentStatusTypeEntity), AssignmentStatusTypeDto.class);

    }

    @Override
    public AssignmentStatusTypeDto get(int id) {
        return  mapper.map(repository.getOne(id), AssignmentStatusTypeDto.class);

    }

    @Override
    public List<AssignmentStatusTypeDto> list() {
        List<AssignmentStatusTypeEntity> elements = Lists.newArrayList(repository.listAll());

        return mapper.map(elements, new TypeToken<List<AssignmentStatusTypeDto>>() {
        }.getType());
    }

    @Override
    public boolean delete(int id) {
        return repository.deleteOne(id);
    }

    @Override
    public AssignmentStatusTypeDto update(int id, AssignmentStatusTypeForUpdateDto assignment) {
        AssignmentStatusTypeEntity assignmentStatusTypeEntity = mapper.map(assignment, AssignmentStatusTypeEntity.class);
        assignmentStatusTypeEntity.setId(id);
        return  mapper.map(repository.updateOne(assignmentStatusTypeEntity), AssignmentStatusTypeDto.class);
    }
}