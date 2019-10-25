package API.DAO;
import DAO.HibernateUtil;
import Objects.Factory.Database_Entities.AssignmentEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AssignmentDAO implements IAssignmentDAO
{
@Autowired
private ModelMapper modelMapper;

    @Override
    public AssignmentEntity add(AssignmentEntity assignmentEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(assignmentEntity);
        tx.commit();
        session.close();
        //how to check if Is ADDED??
        return assignmentEntity;
    }

    @Override
    public AssignmentEntity get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AssignmentEntity.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.add(Restrictions.eq("isDeleted", false));
        return  (AssignmentEntity) criteria.uniqueResult();
    }

    @Override
    public List<AssignmentEntity> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AssignmentEntity.class);
        criteria.add(Restrictions.eq("IsDeleted", false));
        List<AssignmentEntity> personList = criteria.list();
        session.close();
        return personList;
    }

    @Override
    public boolean delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(AssignmentEntity.class);
        criteria.add(Restrictions.eq("id", id));
        AssignmentEntity assignmentEntity=  (AssignmentEntity) criteria.uniqueResult();
        assignmentEntity.setDeleted(true);
        Transaction tx = session.beginTransaction();
        session.save(assignmentEntity);
        tx.commit();
        session.close();
        return true;
    }
}
