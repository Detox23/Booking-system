package API.Repository.Assignment;

import API.Database_Entities.AssignmentStatusEntity;

import java.util.List;

public interface AssignmentStatusDAOCustom {
    Iterable<AssignmentStatusEntity> listAll();

    AssignmentStatusEntity findOne(int id);

    AssignmentStatusEntity addOnce(AssignmentStatusEntity serviceProvider);

    AssignmentStatusEntity updateOne(AssignmentStatusEntity serviceProvider);

    boolean deleteOne(int id);

}