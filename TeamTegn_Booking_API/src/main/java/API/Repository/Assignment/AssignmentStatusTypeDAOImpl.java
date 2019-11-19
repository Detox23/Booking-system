package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AssignmentStatusTypeEntity;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component

public class AssignmentStatusTypeDAOImpl implements AssignmentStausTypeCustom{
    @Autowired
    private AssignmentStatusTypeDAO jpaRepository;
    @Autowired
    private PatcherHandler patcherHandler;

    @Override
    public Iterable<AssignmentStatusTypeEntity> listAll() {
        return jpaRepository.findAll(Sort.by(Sort.Direction.ASC, "DisplayOrder"));
    }

    @Override
    public AssignmentStatusTypeEntity findOne(int id) {
        return jpaRepository.findById(id).get();
    }

    @Override
    public AssignmentStatusTypeEntity addOne(AssignmentStatusTypeEntity assignmentStatusTypeEntity) {
        return jpaRepository.save(assignmentStatusTypeEntity);
    }

    @Override
    public AssignmentStatusTypeEntity updateOne(AssignmentStatusTypeEntity assignmentStatusTypeEntity) {
        return null;
    }

    @Override
    public boolean deleteOne(int id) {
        AssignmentStatusTypeEntity statusTypeEntity  = jpaRepository.findById(id).get();
        if(statusTypeEntity!= null)
        {
            statusTypeEntity.setIsDeleted(true);
            jpaRepository.save(statusTypeEntity);
            return  true;
        }

        return false;
    }
}
