package API.Repository.Assignment;

public interface AssignmentStatusTypeDAOCustom {
    Iterable<AssignmentStatusTypeEntity> listAll();

    AssignmentStatusTypeEntity findOne(int id);

    AssignmentStatusTypeEntity addOne(AssignmentStatusTypeEntity assignmentStatusTypeEntity);

    AssignmentStatusTypeEntity updateOne(AssignmentStatusTypeEntity assignmentStatusTypeEntity);

    boolean deleteOne(int id);
}
