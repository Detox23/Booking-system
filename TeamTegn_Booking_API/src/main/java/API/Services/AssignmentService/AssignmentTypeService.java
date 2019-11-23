package API.Services.AssignmentService;

import API.Database_Entities.AssignmentTypeEntity;
import API.Repository.Assignment.AssignmentTypeDAO;
import API.Repository.Assignment.AssignmentTypeDAOImpl;
import Shared.ForCreation.AssignmentTypeForCreationDto;
import Shared.ForCreation.AssignmentTypeForUpdateDto;
import Shared.ToReturn.AssignmentTypeDto;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentTypeService implements IAssignmentTypeService {


    private ModelMapper mapper;

    private AssignmentTypeDAO repository;

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setRepository(AssignmentTypeDAO repository) {
        this.repository = repository;
    }

    @Override
    public AssignmentTypeDto add(AssignmentTypeForCreationDto assignmentEntity) {
        AssignmentTypeEntity a = mapper.map(assignmentEntity, AssignmentTypeEntity.class);
        return mapper.map(repository.add(a), AssignmentTypeDto.class);
    }

    @Override
    public AssignmentTypeDto get(int id) {
        return mapper.map(repository.findByID(id), AssignmentTypeDto.class);
    }

    @Override
    public List<AssignmentTypeDto> getAll() {

        List<AssignmentTypeEntity> elements = Lists.newArrayList(repository.list());
        return mapper.map(elements, new TypeToken<List<AssignmentTypeDto>>() {
        }.getType());
    }

    @Override
    public boolean delete(int id) {
        return repository.deleteById(id);
    }

    @Override
    public AssignmentTypeDto update(int id, AssignmentTypeForUpdateDto assignmentEntity) {
        AssignmentTypeEntity a = mapper.map(assignmentEntity, AssignmentTypeEntity.class);

        return mapper.map(repository.update(a), AssignmentTypeDto.class);
    }
}
