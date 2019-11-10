package API.Repository.Account;

import API.Exceptions.AccountNotFoundWhileAddingEANNumberException;
import API.Exceptions.AddingTheSameEANNumberToSameAccountException;
import API.Exceptions.MappingAccountDatabseToDtoException;
import API.Exceptions.NoAccountIDAfterSavingException;
import API.Repository.Mappers.AccountMapper;
import Shared.ToReturn.AccountDto;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import Objects.Factory.Database_Entities.AccountTypeEntity;
import Objects.Factory.Database_Entities.AccountEanEntity;
import Objects.Factory.Database_Entities.AccountEntity;
import Shared.ForCreation.AccountEanForCreationDto;

import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.hibernate.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;
import java.util.stream.IntStream;


@Component
public class IAccountDAOImpl {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private IAccountTypeDAO accountTypeDAO;

    @Autowired
    private IAccountEanDAO accountEanNumberCrudDAO;

    @PersistenceContext
    private EntityManager entityManager;


    public List<AccountEntity> list() {
        return (List<AccountEntity>) accountDAO.findAll();
    }


    public AccountEntity findAccountByID(int id) {
        return accountDAO.findById(id).get();
    }

    @Transactional
    public AccountEntity updateAccount(@NotNull AccountEntity accountEntity){
        AccountEntity found =  entityManager.find(AccountEntity.class, accountEntity.getId());
        return entityManager.merge(found);
    }

    public AccountTypeEntity findAccountType(int id){
        return accountTypeDAO.findById(id).get();
    }



    public AccountDto addAccount(AccountEntity account, List<String> eans) throws NoAccountIDAfterSavingException, MappingAccountDatabseToDtoException {
        EntityTransaction transaction = null;
        Session session = null;
        try {
            session = entityManager.unwrap(Session.class);
            transaction = entityManager.getTransaction();
            transaction.begin();
            account.setAccountTypeByAccountTypeId(entityManager.getReference(AccountTypeEntity.class, account.getAccountTypeByAccountTypeId().getId()));
            accountDAO.save(account);
            int result_id = account.getId();
            if (result_id != 0) {
                if (eans.size() > 0) {
                    IntStream.range(0, eans.size()).forEach(index -> {
                        AccountEanForCreationDto accountEanForCreationDto = new AccountEanForCreationDto();
                        accountEanForCreationDto.setAccountId((int) result_id);
                        accountEanForCreationDto.setEanNumber(eans.get(index));
                        AccountEanEntity accountEanEntity = AccountMapper.mapAccountEanForCreationToAccountEanEntity(accountEanForCreationDto);
                        accountEanEntity.setId(0);
                        accountEanNumberCrudDAO.addeanNumber(accountEanEntity);
                    });
                }
                AccountDto toReturn = AccountMapper.mapAccountEntityToAccountDto(account, eans);
                transaction.commit();
                return toReturn;
            }else{
                throw new NoAccountIDAfterSavingException("ID does not exists.");
            }
        }catch (NoAccountIDAfterSavingException e){
            transaction.rollback();
            throw e;
        }
        catch (NullPointerException e2){
            transaction.rollback();
            throw new MappingAccountDatabseToDtoException("Error while mapping.");
        }
    }

    @Transactional
    public boolean deleteAccount(int Id){
        try {
            Session session = entityManager.unwrap(Session.class);
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

    public List<AccountEanEntity> findAccountEANNumber(int accountID) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountEanEntity> criteria = builder.createQuery(AccountEanEntity.class);
        Root<AccountEanEntity> root = criteria.from(AccountEanEntity.class);
        criteria.where(builder.equal(root.get("accountId"), accountID));
        List<AccountEanEntity> numbers = entityManager.createQuery(criteria).getResultList();
        return numbers;
    }
}
