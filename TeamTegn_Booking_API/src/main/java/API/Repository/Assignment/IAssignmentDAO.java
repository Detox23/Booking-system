package API.Repository;

import Objects.Factory.Database_Entities.AssignmentEntity;

import java.util.List;

public interface IAssignmentDAO
{
    AssignmentEntity add(AssignmentEntity assignmentEntity);
    AssignmentEntity get(int id);
    List<AssignmentEntity> getAll();
    boolean delete(int id);

}
