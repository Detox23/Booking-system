package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.NoSuchElementException;
@Component

public class AssignmentStatusDAOImpl implements AssignmentStatusDAOCustom{

    @Autowired
    private AssignmentStatusDAO jpaRepository;
    @Autowired
    private PatcherHandler patcherHandler;

    @Override
    public Iterable<AssignmentStatusEntity> listAll() {
        return jpaRepository.findAll();
    }

    @Override
    public AssignmentStatusEntity findOne(int id) {
        return jpaRepository.findById(id).get();
    }

    @Override
    public AssignmentStatusEntity addOnce(AssignmentStatusEntity assignmentStatusEntity) {
        return jpaRepository.save(assignmentStatusEntity);
    }

    @Override
    public AssignmentStatusEntity updateOne(AssignmentStatusEntity assignmentStatusEntity) {
        try {
            AssignmentStatusEntity found = jpaRepository.findById(assignmentStatusEntity.getId()).get();
            if(found != null)
            {
                patcherHandler.fillNotNullFields(found, assignmentStatusEntity);
                AssignmentStatusEntity result = jpaRepository.save(found);
                return result;
            }
            return null;
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Account was not found while an attempt of making update.");
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteOne(int id) {
        AssignmentStatusEntity assignmentStatusEntity = jpaRepository.findById(id).get();
        if(assignmentStatusEntity != null)
        {
            assignmentStatusEntity.setIsDeleted(true);
            jpaRepository.save(assignmentStatusEntity);
            return true;
        }
        return false;
    }
}
