package API.Repository.Account;

import API.Exceptions.AccountNotFoundWhileAddingEANNumberException;
import API.Exceptions.AddingTheSameEANNumberToSameAccountException;
import Objects.Factory.Database_Entities.AccountEanEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

public class IAccountEanDAOImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private IAccountEanDAO accountEanNumberCrudDAO;

    @Transactional
    public boolean deleteeanNumber(int accountID, String EANNumber) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountEanEntity> criteriaQuery = criteriaBuilder.createQuery(AccountEanEntity.class);
        Root<AccountEanEntity> root = criteriaQuery.from(AccountEanEntity.class);
        Predicate accountIDP = criteriaBuilder.equal(root.get("accountId"),accountID);
        Predicate eanNumberP = criteriaBuilder.equal(root.get("eanNumber"), EANNumber);
        Predicate finalpredicate = criteriaBuilder.and(accountIDP, eanNumberP);
        criteriaQuery.where(finalpredicate);
        AccountEanEntity found = entityManager.createQuery(criteriaQuery).getSingleResult();
        accountEanNumberCrudDAO.delete(found);
        return true;
    }


    @Transactional
    public boolean addeanNumber(@NotNull AccountEanEntity accountEanEntity) throws AccountNotFoundWhileAddingEANNumberException, AddingTheSameEANNumberToSameAccountException
    {
        try {
            if (accountDAO.findAccountByID(accountEanEntity.getAccountId()) != null) {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<AccountEanEntity> criteriaQuery = criteriaBuilder.createQuery(AccountEanEntity.class);
                Root<AccountEanEntity> root = criteriaQuery.from(AccountEanEntity.class);
                Predicate accountIDP = criteriaBuilder.equal(root.get("accountId"), accountEanEntity.getAccountId());
                Predicate eanNumberP = criteriaBuilder.equal(root.get("eanNumber"), accountEanEntity.getEanNumber());
                Predicate finalpredicate = criteriaBuilder.and(accountIDP, eanNumberP);
                criteriaQuery.where(finalpredicate);
                List<AccountEanEntity> found = entityManager.createQuery(criteriaQuery).getResultList();
                if (found.size() < 1) {
                    AccountEanEntity result = accountEanNumberCrudDAO.save(accountEanEntity);
                    if (result != null) {
                        return true;
                    }
                    return false;
                }else{
                    throw new AddingTheSameEANNumberToSameAccountException("");
                }
            }
        } catch (NoSuchElementException ex) {
            //"You tried to add an ean number to not existing account."
            throw new AccountNotFoundWhileAddingEANNumberException("");
        }
        return false;
    }
}
