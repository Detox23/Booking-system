package API.Repository.Assignment;

import API.Database_Entities.AssignmentEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

@Repository
public class AssignmentDAOImpl {


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public AssignmentEntity updateAssignment(@NotNull AssignmentEntity accountEntity) {
        AssignmentEntity found = entityManager.find(AssignmentEntity.class, accountEntity.getId());
        return entityManager.merge(found);
    }
}