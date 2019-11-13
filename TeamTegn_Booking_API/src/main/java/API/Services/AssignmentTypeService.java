package API.Services;

import API.Database_Entities.AssignmentTypeEntity;
import API.Repository.Assignment.AssignmentTypeDAOImpl;
import Shared.ForCreation.AssignmentTypeForCreationDto;
import Shared.ForCreation.AssignmentTypeForUpdateDto;
import Shared.ToReturn.AssignmentTypeDto;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AssignmentTypeService implements IAssignmentTypeService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AssignmentTypeDAOImpl repository;
    @Override
    public AssignmentTypeDto add(AssignmentTypeForCreationDto assignmentEntity) {
       AssignmentTypeEntity a = mapper.map(assignmentEntity, AssignmentTypeEntity.class);
        return   mapper.map(repository.add(a), AssignmentTypeDto.class);
    }

    @Override
    public AssignmentTypeDto get(int id) {
        return   mapper.map(repository.findByID(id), AssignmentTypeDto.class);
    }

    @Override
    public List<AssignmentTypeDto> getAll() {

        List<AssignmentTypeEntity> elements = (List<AssignmentTypeEntity>) Lists.newArrayList( repository.list() );

         return mapper.map(elements,   new TypeToken<List<AssignmentTypeDto>>(){}.getType());
    }

    @Override
    public boolean delete(int id) {
        return repository.deleteById(id);
    }

    @Override
    public AssignmentTypeDto update(int id, AssignmentTypeForUpdateDto assignmentEntity) {
        AssignmentTypeEntity a = mapper.map(assignmentEntity, AssignmentTypeEntity.class);

        return   mapper.map(repository.update(a), AssignmentTypeDto.class);
    }
}
