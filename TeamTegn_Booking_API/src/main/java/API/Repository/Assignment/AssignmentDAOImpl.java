package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AccountEntity;
import API.Database_Entities.AssignmentEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.AllArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.beans.IntrospectionException;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class AssignmentDAOImpl implements AssignmentDAOCustom {


    @Autowired
    private AssignmentDAO jpaRepository;

    @Autowired
    private PatcherHandler patcherHandler;

    @Transactional
    @Override
    public AssignmentEntity updateOne(@NotNull AssignmentEntity assignmentEntity) {

        try {
            AssignmentEntity found = jpaRepository.findFirstByIdAndDeletedIsFalse(assignmentEntity.getId());
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

    @Override
    public List<AssignmentEntity> listAll() {
        return jpaRepository.findAllByDeletedFalse();
    }
    @Override
    public Page<AssignmentEntity> listAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    @Override
    public AssignmentEntity addOne(AssignmentEntity assignmentEntity) {
        return jpaRepository.save(assignmentEntity);
    }

    @Override
    public boolean deleteOne(int id) {
        AssignmentEntity assignmentEntity = jpaRepository.findFirstByIdAndDeletedIsFalse(id);
        assignmentEntity.setDeleted(true);
        return jpaRepository.save(assignmentEntity) != null;
    }

    @Override
    public AssignmentEntity getOne(int id) {
        return jpaRepository.findFirstByIdAndDeletedIsFalse(id);
    }


}