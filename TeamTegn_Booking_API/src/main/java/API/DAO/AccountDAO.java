package API.DAO;
import Objects.Factory.Database_Entities.AccountEanEntity;
import Objects.Factory.Database_Entities.AccountEntity;
import Objects.Factory.Database_Entities.AccountTypeEntity;
import Shared.ForCreation.AccountEanForCreationDto;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import DAO.HibernateUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

//Manipulating and communication of Account object with database
@Repository
public class AccountDAO implements IAccountDAO, IAccountEanNumberDAO {

    //SessionFactory is a factory for Session object. We can create one SessionFactory implementation per
    //database in any application. It is thread safe and used by all threads of an application.
    //Initial state of the SessionFactory is immutable, it includes all of the metadata about Object/Relational Mapping


    @Autowired
    private ModelMapper modelMapper;

    //Returns all accounts form the database.
    @Override
    public List<AccountEntity> list() {
        //Retrieves and opens session from the factory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Creates a criteria object that allows to build up a query object programmatically.
        Criteria criteria = session.createCriteria(AccountEntity.class);
        criteria.add(Restrictions.eq("deleted", true));
        //Assigns the query's result to a list of accounts
        List<AccountEntity> account_list = criteria.list();
        //Closes session
        session.close();
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
        return account;
    }

    public AccountTypeEntity findAccountType(int id){
        //Retrieves and opens session from the factory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Creates a criteria object that allows to build up a query object programmatically.
        Criteria criteria = session.createCriteria(AccountTypeEntity.class);
        //Adds searching property
        criteria.add(Restrictions.eq("id", id));
        //Gets a found account and assign it to the Account object
        AccountTypeEntity account = (AccountTypeEntity) criteria.uniqueResult();
        //Closes session
        session.close();
        return account;
    }

    //Adds account to a database
    @Override
    public boolean addAccount(AccountEntity account, List<String> eans){
        //Retrieves and opens session from the factory.
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Retrieves and begins transaction.
        Transaction tx = session.beginTransaction();
        boolean aResult = false;
        try {

            session.save(account);

            int result_id = account.getId();
            if (result_id != 0) {
                if (eans.size() > 0) {
                    for (int index = 0; index < eans.size(); index++) {
                        AccountEanForCreationDto accountEanForCreationDto = new AccountEanForCreationDto();
                        accountEanForCreationDto.setAccountId(String.valueOf(result_id));
                        accountEanForCreationDto.setEanNumber(eans.get(index));
                        AccountEanEntity accountEanEntity = modelMapper.map(accountEanForCreationDto, AccountEanEntity.class);
                        accountEanEntity.setId(0);
                        session.save(accountEanEntity);
                    }
                }
            }
            aResult = true;
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            return aResult;
        }

    }

    //Deletes account from a database
    @Override
    public boolean deleteAccount(int Id){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            AccountEntity account = findAccountByID(Id);
            Transaction tx = session.beginTransaction();
            account.setDeleted(true);
            session.update(account);
            tx.commit();
            session.close();
            return tx.getStatus() == TransactionStatus.COMMITTED;
        }catch (org.hibernate.ObjectNotFoundException notFoundException){
            throw notFoundException;
        }
    }

    //Updates account object
    @Override
    public boolean update(AccountEntity account) {
        return false;
    }

    @Override
    public List<AccountEanEntity> findAccountEANNumber(int accountID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Creates a criteria object that allows to build up a query object programmatically.
        Criteria criteria = session.createCriteria(AccountEanEntity.class);
        //Adds searching property
        criteria.add(Restrictions.eq("accountId", accountID));
        //Gets a found account and assign it to the Account object
        List<AccountEanEntity> accounts_list = (List<AccountEanEntity>) criteria.list();
        //Closes session
        session.close();
        return accounts_list;
    }
}
