package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AssignmentEntity;
import API.Database_Entities.AssignmentTypeEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.NoSuchElementException;

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
        assignmentTypeDAO.deleteById(id);
        return assignmentTypeDAO.findById(id).get() == null;
    }

    @Override
    public Iterable<AssignmentTypeEntity> list() {
        return assignmentTypeDAO.findAll();
    }

    @Override
    public AssignmentTypeEntity findByID(int id) {
        return assignmentTypeDAO.findFirstByIdAndAndDeletedIsFalse(id);
    }

    @Override
    public AssignmentTypeEntity update(AssignmentTypeEntity a) {

        try {
            AssignmentTypeEntity found = assignmentTypeDAO.findFirstByIdAndAndDeletedIsFalse(a.getId());
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
