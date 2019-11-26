package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AssignmentStatusEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component

public class AssignmentStatusDAOImpl implements AssignmentStatusDAOCustom{

    @Autowired
    private AssignmentStatusDAO assignmentStatusDAO;
    @Autowired
    private PatcherHandler patcherHandler;

    @Override
    public Iterable<AssignmentStatusEntity> listAll() {
        return assignmentStatusDAO.findAll();
    }

    @Override
    public AssignmentStatusEntity findOne(int id) {
        return assignmentStatusDAO.findById(id).get();
    }

    @Override
    public AssignmentStatusEntity addOnce(AssignmentStatusEntity assignmentStatusEntity) {
        return assignmentStatusDAO.save(assignmentStatusEntity);
    }

    @Override
    public AssignmentStatusEntity updateOne(AssignmentStatusEntity assignmentStatusEntity) {
        try {
            AssignmentStatusEntity found = assignmentStatusDAO.findById(assignmentStatusEntity.getId()).get();
            if(found != null)
            {
                patcherHandler.fillNotNullFields(found, assignmentStatusEntity);
                AssignmentStatusEntity result = assignmentStatusDAO.save(found);
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
        try {
            Optional<AssignmentStatusEntity> found = assignmentStatusDAO.findById(id);
            if (!found.isPresent()) {
                throw new NotFoundException("The assigment status type was not found.");
            }
            AssignmentStatusEntity toDelete = found.get();
            toDelete.setDeleted(true);
            AssignmentStatusEntity deletionResult = assignmentStatusDAO.save(toDelete);
            if (deletionResult.isDeleted()) {
                return true;
            } else {
                return false;
            }
        }catch (NotFoundException notFoundException){
            throw notFoundException;
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }
}
