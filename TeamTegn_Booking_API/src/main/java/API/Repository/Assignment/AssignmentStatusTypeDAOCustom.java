package API.Repository.Assignment;

import API.Database_Entities.AssignmentStatusTypeEntity;

public interface AssignmentStatusTypeDAOCustom {
    Iterable<AssignmentStatusTypeEntity> listAll();

    AssignmentStatusTypeEntity findOne(int id);

    AssignmentStatusTypeEntity addOne(AssignmentStatusTypeEntity assignmentStatusTypeEntity);

    AssignmentStatusTypeEntity updateOne(AssignmentStatusTypeEntity assignmentStatusTypeEntity);

    boolean deleteOne(int id);
}
