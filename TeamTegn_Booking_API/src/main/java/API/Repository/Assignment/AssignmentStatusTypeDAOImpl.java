package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component

public class AssignmentStatusTypeDAOImpl implements AssignmentStatusTypeDAOCustom {

    private AssignmentStatusTypeDAO assignmentStatusTypeDAO;

    private PatcherHandler patcherHandler;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setAssignmentStatusTypeDAO(AssignmentStatusTypeDAO assignmentStatusTypeDAO) {
        this.assignmentStatusTypeDAO = assignmentStatusTypeDAO;
    }

    @Override
    public Iterable<AssignmentStatusTypeEntity> listAll() {
        return assignmentStatusTypeDAO.findAll(Sort.by(Sort.Direction.ASC, "DisplayOrder"));
    }

    @Override
    public AssignmentStatusTypeEntity findOne(int id) {
        return assignmentStatusTypeDAO.findById(id).get();
    }

    @Override
    public AssignmentStatusTypeEntity addOne(AssignmentStatusTypeEntity assignmentStatusTypeEntity) {
        return assignmentStatusTypeDAO.save(assignmentStatusTypeEntity);
    }

    @Override
    public AssignmentStatusTypeEntity updateOne(AssignmentStatusTypeEntity assignmentStatusTypeEntity) {
        return null;
    }

    @Override
    public boolean deleteOne(int id) {
        Optional<AssignmentStatusTypeEntity> found = assignmentStatusTypeDAO.findById(id);
        if (found.isPresent()) {
            found.get().setIsDeleted(true);
            assignmentStatusTypeDAO.save(found.get());
            return true;
        }

        return false;
    }
}
