package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AssignmentTypeEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class AssignmentTypeDAOImpl implements AssignmentTypeDAOCustom {

    @Autowired
    private AssignmentTypeDAO assignmentTypeDAO;
    @Autowired
    private PatcherHandler patcherHandler;

    @Override
    public AssignmentTypeEntity add(AssignmentTypeEntity a) {
        return assignmentTypeDAO.save(a);
    }

    @Override
    public boolean deleteById(int id) {
        try {
            Optional<AssignmentTypeEntity> found = assignmentTypeDAO.findById(id);
            if (!found.isPresent()) {
                throw new NotFoundException("The assigment type was not found.");
            }
            AssignmentTypeEntity toDelete = found.get();
            toDelete.setDeleted(true);
            AssignmentTypeEntity deletionResult = assignmentTypeDAO.save(toDelete);
            if (deletionResult.isDeleted()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }

    @Override
    public Iterable<AssignmentTypeEntity> list() {
        return assignmentTypeDAO.findAll();
    }

    @Override
    public AssignmentTypeEntity findByID(int id) {
        return assignmentTypeDAO.findById(id).get();
    }

    @Override
    public AssignmentTypeEntity update(AssignmentTypeEntity a) {

        try {
            AssignmentTypeEntity found = assignmentTypeDAO.findById(a.getId()).get();
            if(found != null)
            {
                patcherHandler.fillNotNullFields(found, a);
                AssignmentTypeEntity result = assignmentTypeDAO.save(found);
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
