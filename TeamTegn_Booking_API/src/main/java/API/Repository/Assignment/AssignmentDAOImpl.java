package API.Repository.Assignment;
import Objects.Factory.Database_Entities.AccountEntity;
import Objects.Factory.Database_Entities.AssignmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Repository
public class AssignmentDAOImpl
{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public AssignmentEntity updateAssignment(@NotNull AssignmentEntity accountEntity){
        AssignmentEntity found =  entityManager.find(AssignmentEntity.class, accountEntity.getId());
        return entityManager.merge(found);
    }
}