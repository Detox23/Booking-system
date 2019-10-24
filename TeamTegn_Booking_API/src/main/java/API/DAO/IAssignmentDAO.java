package API.DAO;

import Objects.Factory.Database_Entities.AssignmentEntity;
import Shared.AssignmentDto;

import java.util.List;

public interface IAssignmentDAO
{
    AssignmentEntity add(AssignmentEntity assignmentEntity);
    AssignmentEntity get(int id);
    List<AssignmentEntity> getAll();
    boolean delete(int id);

}
