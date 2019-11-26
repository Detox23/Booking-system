package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AssignmentEntity;
import API.Exceptions.NotEnoughDataException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.beans.IntrospectionException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class AssignmentDAOImpl implements AssignmentDAOCustom {


    @Autowired
    private AssignmentDAO assignmentDAO;
  
    @Autowired
    private PatcherHandler patcherHandler;

    @Transactional
    @Override
    public AssignmentEntity updateOne(@NotNull AssignmentEntity assignmentEntity) {

        try {
            AssignmentEntity found = assignmentDAO.findFirstByIdAndDeletedIsFalse(assignmentEntity.getId());
            if(found != null)
            {
                patcherHandler.fillNotNullFields(found, assignmentEntity);
                AssignmentEntity result = assignmentDAO.save(found);
                return result;
            }
            return null;
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Assignment was not found while an attempt of making update.");
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AssignmentEntity> listAll() {
        return assignmentDAO.findAllByDeletedFalse();
    }
    @Override
    public Page<AssignmentEntity> listAll(Pageable pageable) {
        return assignmentDAO.findAll(pageable);
    }

    @Override
    public AssignmentEntity addOne(AssignmentEntity assignmentEntity) {
        try{
            return  assignmentDAO.save(assignmentEntity);
        }catch (NotEnoughDataException notEnoughDataException) {
            throw new NotEnoughDataException("You provided to little information");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new NotEnoughDataException("You provided to little information");
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException(noSuchElementException.getMessage());
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public boolean deleteOne(int id) {
        try {
            Optional<AssignmentEntity> found = assignmentDAO.findById(id);
            if (!found.isPresent()) {
                throw new NotFoundException("The assigment status type was not found.");
            }


            AssignmentEntity toDelete = found.get();
            toDelete.setDeleted(true);
            AssignmentEntity deletionResult = assignmentDAO.save(toDelete);
            if (deletionResult.isDeleted()) {
                return true;
            } else {
                return false;
            }
        } catch (NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }

    @Override
    public AssignmentEntity getOne(int id) {
        return assignmentDAO.findFirstByIdAndDeletedIsFalse(id);
    }


}