package API.Repository.Account;

import API.Exceptions.AccountNotFoundWhileAddingEANNumberException;
import API.Exceptions.AddingTheSameEANNumberToSameAccountException;
import API.Database_Entities.AccountEanEntity;
import Shared.ToReturn.AccountEanDto;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

public class AccountEanDAOImpl implements AccountEanCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountEanDAO accountEanNumberCrudDAO;

    @Transactional
    public boolean deleteEanNumber(int accountID, String EANNumber) {
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

    public List<AccountEanDto> findAccountEANNumber(int accountID) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountEanEntity> criteria = builder.createQuery(AccountEanEntity.class);
        Root<AccountEanEntity> root = criteria.from(AccountEanEntity.class);
        criteria.where(builder.equal(root.get("accountId"), accountID));
        List<AccountEanEntity> numbers = entityManager.createQuery(criteria).getResultList();
        Type listType = new TypeToken<List<AccountEanDto>>() {}.getType();
        return modelMapper.map(numbers, listType);
    }

    @Transactional
    public boolean addEanNumber(@NotNull AccountEanEntity accountEanEntity) throws AccountNotFoundWhileAddingEANNumberException, AddingTheSameEANNumberToSameAccountException
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
