package API.DAO;
import Objects.Factory.Database_Entities.AccountEntity;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import DAO.HibernateUtil;
import java.util.List;

import org.springframework.stereotype.Repository;

//Manipulating and communication of Account object with database
@Repository
public class AccountDAO implements IAccountDAO {

    //SessionFactory is a factory for Session object. We can create one SessionFactory implementation per
    //database in any application. It is thread safe and used by all threads of an application.
    //Initial state of the SessionFactory is immutable, it includes all of the metadata about Object/Relational Mapping

    //Returns all accounts form the database.
    @Override
    public List<AccountEntity> list() {
        //Retrieves and opens session from the factory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Creates a criteria object that allows to build up a query object programmatically.
        Criteria criteria = session.createCriteria(AccountEntity.class);
        //Assigns the query's result to a list of accounts
        List<AccountEntity> account_list = criteria.list();
        //Closes session
        session.close();
        account_list.forEach(acc -> System.out.println(acc.getCity()));
        return account_list;
    }

    //Returns found-by-name account
    @Override
    public AccountEntity findAccountByID(int Id) {
        //Retrieves and opens session from the factory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Creates a criteria object that allows to build up a query object programmatically.
        Criteria criteria = session.createCriteria(AccountEntity.class);
        //Adds searching property
        criteria.add(Restrictions.eq("id", Id));
        //Gets a found account and assign it to the Account object
        AccountEntity account = (AccountEntity) criteria.uniqueResult();
        //Closes session
        session.close();
        System.out.println(account.toString());
        return account;
    }

    //Adds account to a database
    @Override
    public boolean addAccount(AccountEntity account){
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
    public boolean deleteAccount(int Id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            AccountEntity account = findAccountByID(Id);
            Transaction tx = session.beginTransaction();
            session.delete(account);
            tx.commit();
            session.close();
            return true;
        }catch (org.hibernate.ObjectNotFoundException notFoundException){
            throw notFoundException;
        }
    }

    //Updates account object
    @Override
    public boolean update(AccountEntity account) {
        return false;
    }
}
