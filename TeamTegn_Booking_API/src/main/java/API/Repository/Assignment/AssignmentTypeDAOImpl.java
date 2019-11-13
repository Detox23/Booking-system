package API.Repository.Assignment;

import API.Database_Entities.AccountTypeEntity;
import API.Database_Entities.AssignmentTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public class AssignmentTypeDAOImpl implements AssignmentTypeDAOCustom {

    @Autowired
    private AssignmentTypeDAO jpaRepo;

    @Override
    public AssignmentTypeEntity add(AssignmentTypeEntity a) {
        return jpaRepo.save(a);
    }

    @Override
    public boolean deleteById(int id) {
        jpaRepo.deleteById(id);
        return jpaRepo.findById(id).get() == null;
    }

    @Override
    public Iterable<AssignmentTypeEntity> list() {
        return jpaRepo.findAll();
    }

    @Override
    public AssignmentTypeEntity findByID(int id) {
        return  jpaRepo.findById(id).get();
    }

    @Override
    public AssignmentTypeEntity update(AssignmentTypeEntity a) {
       return jpaRepo.save(a);
    }
}
