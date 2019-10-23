package DAO;
import Objects.Factory.Account;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;

//Manipulating and communication of Account object with database
public class AccountDAO implements IAccountDAO {

    //SessionFactory is a factory for Session object. We can create one SessionFactory implementation per
    //database in any application. It is thread safe and used by all threads of an application.
    //Initial state of the SessionFactory is immutable, it includes all of the metadata about Object/Relational Mapping

    @Override
    public void save(Account account) {
        //The lifecycle of a Session is bounded by the beginning and end of a logical transaction.
        //We should open a new session for each request in multi-threaded environment.
        Session session = HibernateUtil.getSessionFactory().openSession();
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

    //Returns all accounts form the database.
    @Override
    public List<Account> list() {
        //Retrieves and opens session from the factory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Creates a criteria object that allows to build up a query object programmatically.
        Criteria criteria = session.createCriteria(Account.class);
        //Assigns the query's result to a list of accounts
        List<Account> account_list = criteria.list();
        //Closes session
        session.close();
        account_list.forEach(acc -> System.out.println(acc.getCity()));
        return account_list;
    }

    //Returns found-by-name account
    @Override
    public Account findAccountByName(String name) {
        //Retrieves and opens session from the factory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Creates a criteria object that allows to build up a query object programmatically.
        Criteria criteria = session.createCriteria(Account.class);
        //Adds searching property
        criteria.add(Restrictions.eq("accountName", name));
        //Gets a found account and assign it to the Account object
        Account account = (Account) criteria.uniqueResult();
        //Closes session
        session.close();
        System.out.println(account.toString());
        return account;
    }

    @Override
    public Account findAccountById(String Id) {

        //Retrieves and opens session from the factory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Creates a criteria object that allows to build up a query object programmatically.
        Criteria criteria = session.createCriteria(Account.class);
        //Adds searching property
        criteria.add(Restrictions.eq("id", Integer.parseInt(Id)));
        //Gets a found account and assign it to the Account object
        Account account = (Account) criteria.uniqueResult();
        //Closes session
        session.close();
        System.out.println(account.toString());
        return account;
    }

    //Adds account to a database
    @Override
    public boolean addAccount(Account account){
        //Retrieves and opens session from the factory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Retrieves and begins transaction.
        Transaction tx = session.beginTransaction();
        session.save(account);
        tx.commit();
        session.close();
        return true;
    }

    //Deletes account from a database
    @Override
    public boolean deleteAccount(String Id){
        try {
            Gson gson = new Gson();
            Account acc = gson.fromJson(Id, Account.class);
            //        System.out.println("ASD");
            Session session = HibernateUtil.getSessionFactory().openSession();
            Account account = session.load(Account.class, acc.getId());
            Transaction tx = session.beginTransaction();
            session.delete(account);
            tx.commit();
            session.close();
            return true;
        }catch (org.hibernate.ObjectNotFoundException notFoundException){
            throw notFoundException;
        }
    }
}
