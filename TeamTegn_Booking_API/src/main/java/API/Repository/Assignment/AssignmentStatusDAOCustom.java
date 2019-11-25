package API.Repository.Assignment;

public interface AssignmentStatusDAOCustom {
    Iterable<AssignmentStatusEntity> listAll();

    AssignmentStatusEntity findOne(int id);

    AssignmentStatusEntity addOnce(AssignmentStatusEntity serviceProvider);

    AssignmentStatusEntity updateOne(AssignmentStatusEntity serviceProvider);

    boolean deleteOne(int id);

}
