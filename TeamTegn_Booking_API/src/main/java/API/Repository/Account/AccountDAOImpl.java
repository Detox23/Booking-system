package API.Repository.Account;

import API.Configurations.Patcher.PatcherHandler;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import API.Database_Entities.AccountEanEntity;
import API.Database_Entities.AccountEntity;
import org.springframework.stereotype.Component;

import javax.persistence.PersistenceContext;

import org.jetbrains.annotations.NotNull;

import javax.persistence.EntityManager;

import Shared.ToReturn.AccountDto;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;

import API.Exceptions.*;

import java.util.List;
import java.util.NoSuchElementException;


@Component
public class AccountDAOImpl implements AccountDAOCustom {

    private PatcherHandler patcherHandler;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private AccountEanDAO accountEanNumberCrudDAO;

    @PersistenceContext
    private EntityManager entityManager;


    public List<AccountDto> list() {
        Type listType = new TypeToken<List<AccountDto>>() {
        }.getType();
        return modelMapper.map(accountDAO.findAll(), listType);
    }


    public AccountDto findAccountByID(int id) {
        return modelMapper.map(accountDAO.findById(id).get(), AccountDto.class);
    }


    public AccountDto updateAccount(@NotNull AccountEntity accountDto) throws UpdateErrorException {
        try {
            AccountEntity found = accountDAO.findById(accountDto.getId()).get();
            patcherHandler.fillNotNullFields(found, accountDto);
            AccountEntity result = accountDAO.save(found);
            if (result != null) {
                return modelMapper.map(found, AccountDto.class);
            } else {
                throw new UpdateErrorException("Update failure");
            }
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Not found.");
        } catch(IntrospectionException introspectionException){
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        }

    }


    public AccountDto addAccount(AccountEntity account, List<String> eans, int accountTypeId) throws NoAccountIDAfterSavingException {
        try {
            account.setAccountTypeByAccountTypeId(accountTypeDAO.findById(accountTypeId).get());
            AccountEntity accountEntity = accountDAO.saveAndFlush(account);
            int result_id = accountEntity.getId();
            if (result_id != 0) {
                if (eans != null) {
                    for (int index = 0; index < eans.size(); index++) {
                        AccountEanEntity accountEanEntity = new AccountEanEntity();
                        accountEanEntity.setAccountId(result_id);
                        accountEanEntity.setEanNumber(eans.get(index));
                        accountEanEntity.setId(0);
                        try {
                            accountEanNumberCrudDAO.addEanNumber(accountEanEntity);
                        } catch (AccountNotFoundWhileAddingEANNumberException | AddingTheSameEANNumberToSameAccountException e) {
                            e.printStackTrace();
                        }
                    }
                }
                AccountDto toReturn = modelMapper.map(account, AccountDto.class);
                return toReturn;
            } else {
                throw new NoAccountIDAfterSavingException("ID does not exists.");
            }
        } catch (NoAccountIDAfterSavingException noAccountIdAfterSaving) {
            throw noAccountIdAfterSaving;
        }
    }


    public boolean deleteAccount(int id) {
        try {
            AccountEntity account = accountDAO.findById(id).get();
            account.setDeleted(true);
            AccountEntity updatedAccount = accountDAO.saveAndFlush(account);
            if (updatedAccount.isDeleted() == true) {
                return true;
            }
        } catch (org.hibernate.ObjectNotFoundException notFoundException) {
            throw notFoundException;

        }
        return false;
    }

}
