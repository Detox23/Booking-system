package API.Repository.Assignment;

import API.Database_Entities.AssignmentStatusTypeEntity;
import Shared.ToReturn.AssignmentStatusTypeDto;

public interface AssignmentStatusTypeDAOCustom {
    Iterable<AssignmentStatusTypeEntity> listAll();

    AssignmentStatusTypeDto findOne(int id);

    AssignmentStatusTypeEntity addOne(AssignmentStatusTypeEntity assignmentStatusTypeEntity);

    AssignmentStatusTypeEntity updateOne(AssignmentStatusTypeEntity assignmentStatusTypeEntity);

    boolean deleteOne(int id);
}
