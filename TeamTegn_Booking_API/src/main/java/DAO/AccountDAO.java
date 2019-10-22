package DAO;
import Objects.Factory.Account_Entity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import DAO.HibernateUtil;

public class AccountDAO implements IAccountDAO {
    //SessionFactory is a factory for Session object xDD. We can create one SessionFactory implementation per
    //database in any application. It is thread safe and used by all threads of an application.
    //Initial state of the SessionFactory is immutable, it includes all of the metadata about Object/Relational Mapping

    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Account_Entity account) {
        //The lifecycle of a Session is bounded by the beginning and end of a logical transaction.
        //We should open a new session for each request in multi-threaded environment.
        Session session = this.sessionFactory.openSession();
        //A session is associated with a Session and is usually instantiated by beginTransaction() call.
        //A single session might span multiple transactions since the notion of a session (a conversation between
        //the application and the data store.
        Transaction tx = session.beginTransaction();
        //The persist method is intended for adding a new entity instance to the persistence context.
        //It is useally called while adding a record to the database.
        //While called, the person object has transitioned from transient to persistent state.
        //Others: save(returns ID after saving), merge, update, saveOrUpdate
        //Most commonly used: persist and merge
        session.persist(account);
        //Flush the associated Session and end the unit of work (unless we are in FlushMode.MANUAL).
        tx.commit();
        //Closing the session.
        session.close();
    }

    @Override
    public List<Account_Entity> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account_Entity> account_list = session.createQuery("from Account").list();
        account_list.forEach(acc -> System.out.println(acc.getCity()));
        session.close();
        return account_list;
    }
}
