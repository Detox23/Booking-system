package API.Repository.Account;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.AccountEanEntity;
import API.Models.Database_Entities.AccountEntity;
import API.Models.Database_Entities.ServiceUserAccountEntity;
import API.Repository.ServiceUser.ServiceUserAccountsDAO;
import Shared.ToReturn.AccountDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;


@Component
public class AccountDAOImpl implements AccountDAOCustom {

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    private AccountDAO accountDAO;

    private AccountTypeDAO accountTypeDAO;

    private AccountEanDAO accountEanNumberCrudDAO;

    private ServiceUserAccountsDAO serviceUserAccountsDAO;

    @Autowired
    public void setServiceUserAccountsDAO(ServiceUserAccountsDAO serviceUserAccountsDAO) {
        this.serviceUserAccountsDAO = serviceUserAccountsDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Autowired
    public void setAccountTypeDAO(AccountTypeDAO accountTypeDAO) {
        this.accountTypeDAO = accountTypeDAO;
    }

    @Autowired
    public void setAccountEanNumberCrudDAO(AccountEanDAO accountEanNumberCrudDAO) {
        this.accountEanNumberCrudDAO = accountEanNumberCrudDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Override
    public List<AccountDto> listAccounts() {
        try {
            return modelMapper.map(accountDAO.findAllByDeletedIsFalse(), new TypeToken<List<AccountDto>>() {
            }.getType());
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public AccountDto findAccount(int id) {
        try {
            AccountEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AccountDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AccountDto addAccount(AccountEntity account, List<String> eans, List<Integer> accountServiceUser) {
        try {
            checkIfExistsByNameAndCVR(account);
            AccountEntity saved = accountDAO.save(account);
            addEanNumbers(eans, saved.getId());
            addAccountsServiceUsers(accountServiceUser, saved.getId());
            return modelMapper.map(saved, AccountDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AccountDto updateAccount(AccountEntity account, List<String> eans,  List<Integer> accountServiceUser) {
        try {

            AccountEntity found = findIfExistsAndReturn(account.getId());
            patcherHandler.fillNotNullFields(found, account);
            checkIfExistsByNameAndCVR(account);
            addEanNumbers(eans, account.getId());
            addAccountsServiceUsers(accountServiceUser, account.getId());
            AccountEntity result = accountDAO.save(found);
            return modelMapper.map(result, AccountDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAccount(int id) {
        try {
            AccountEntity toDelete = findIfExistsAndReturn(id);
            toDelete.setDeleted(true);
            AccountEntity deletionResult = accountDAO.save(toDelete);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }

    }

    private AccountEntity findIfExistsAndReturn(int id) {
        Optional<AccountEntity> found = accountDAO.findByIdIsAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Account with id: %d was not found.", id));
        }
        return found.get();
    }

    private void checkIfExistsByNameAndCVR(AccountEntity account){
        if(account.getId() == 0){
            if (accountDAO.countAllByAccountNameAndCvrNumber(account.getAccountName(), account.getCvrNumber()) > 0){
                throw new DuplicateException("Account with exact name and CVR number already exists.");
            }
        }else{
            if (accountDAO.countAllByAccountNameAndCvrNumberAndIdIsNot(account.getAccountName(), account.getCvrNumber(),account.getId()) > 0) {
                throw new DuplicateException("Account with exact name and CVR number already exists.");
            }
        }
    }

    private void addEanNumbers(List<String> eans, int id){
        try{
            if(eans != null){
                accountEanNumberCrudDAO.deleteAllByAccountIdIs(id);
                for(String ean: eans){
                    AccountEanEntity accountEanEntity = new AccountEanEntity();
                    accountEanEntity.setEanNumber(ean);
                    accountEanEntity.setAccountId(id);
                    AccountEanEntity saved = accountEanNumberCrudDAO.save(accountEanEntity);
                    if(saved == null){
                        throw new UnknownAddingException(String.format("There was a problem with adding ean number."));
                    }
                }
            }
        }catch(Exception e){
            throw e;
        }
    }

    private void addAccountsServiceUsers(List<Integer> serviceUsers, int id){
        try{
            if(serviceUsers != null){
                serviceUserAccountsDAO.deleteAllByAccountIdIs(id);
                for(Integer serviceUser: serviceUsers){
                    ServiceUserAccountEntity serviceUserAccount = new ServiceUserAccountEntity();
                    serviceUserAccount.setAccountId(id);
                    serviceUserAccount.setServiceUserId(serviceUser);
                    ServiceUserAccountEntity saved = serviceUserAccountsDAO.save(serviceUserAccount);
                    if(saved == null){
                        throw new UnknownAddingException(String.format("There was a problem with adding service user."));
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }
    }

}
