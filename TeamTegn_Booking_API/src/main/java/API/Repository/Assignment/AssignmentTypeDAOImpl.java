package API.Repository.Assignment;

import API.Database_Entities.AssignmentTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignmentTypeDAOImpl implements AssignmentTypeDAOCustom {

    @Autowired
    private AssignmentTypeDAO assignmentTypeDAO;

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
        return assignmentTypeDAO.findById(id).get();
    }

    @Override
    public AssignmentTypeEntity update(AssignmentTypeEntity a) {
        return assignmentTypeDAO.save(a);
    }
}
