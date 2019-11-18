package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AssignmentEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.AllArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.beans.IntrospectionException;
import java.util.NoSuchElementException;

@Repository
public class AssignmentDAOImpl {


    @Autowired
    private AssignmentDAO jpaRepository;

    @Autowired
    private PatcherHandler patcherHandler;

    @Transactional
    public AssignmentEntity updateAssignment(@NotNull AssignmentEntity assignmentEntity) {

        try {
            AssignmentEntity found = jpaRepository.findFirstByIdAndAndDeletedIsFalse(assignmentEntity.getId());
            if(found != null)
            {
                patcherHandler.fillNotNullFields(found, assignmentEntity);
                AssignmentEntity result = jpaRepository.save(found);
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

}