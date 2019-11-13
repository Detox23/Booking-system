package API.Repository.Assignment;

import API.Database_Entities.AssignmentEntity;
import API.Database_Entities.AssignmentTypeEntity;

import java.util.List;

public interface AssignmentTypeDAOCustom {
    AssignmentTypeEntity add(AssignmentTypeEntity a);
    boolean deleteById(int Id);
    Iterable<AssignmentTypeEntity> list();
    AssignmentTypeEntity findByID(int id);
    AssignmentTypeEntity update(AssignmentTypeEntity a);
}
