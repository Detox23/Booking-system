package API.Repository.Assignment;
import Objects.Factory.Database_Entities.AssignmentEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
@Repository
public class AssignmentDAO
{
    @Autowired
    IAssignmentDAO assignmentDAO;
    @Transactional
    public  void check()  {
        AssignmentEntity a  = new AssignmentEntity();
        AssignmentEntity va  = new AssignmentEntity();
        AssignmentEntity av  = new AssignmentEntity();
        a.setDeleted(false);
        a.setId(101010);
        a.setServiceUserId(1);
        a.setTotalTime(11);
        a.setStartTime(new Timestamp(10));
        a.setEndTime(new Timestamp(10));
        a.setAssignedStatus(true);

        assignmentDAO.save(a)  ;
        assignmentDAO.save(va)  ;
        assignmentDAO.save(av)  ;

    }
}