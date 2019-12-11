package API.Services.AccountService;

import API.Exceptions.NotFoundException;
import API.Models.Database_Entities.AccountEanEntity;
import API.Models.Database_Entities.AccountEntity;
import API.Models.Database_Entities.ServiceUserAccountEntity;
import API.Repository.Account.AccountDAO;
import API.Repository.Account.AccountEanDAO;
import API.Repository.ServiceUser.ServiceUserAccountsDAO;
import API.Repository.ServiceUser.ServiceUserDAO;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.AccountForUpdateDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.AccountEanDto;
import Shared.ToReturn.ServiceUserAccountDto;
import Shared.ToReturn.ServiceUserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AccountService implements IAccountService {
    private AccountDAO accountDAO;
    private AccountEanDAO accountEanDAO;
    private ModelMapper modelMapper;
    private ServiceUserAccountsDAO serviceUserAccountsDAO;
    private ServiceUserDAO serviceUserDAO;

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Autowired
    public void setAccountEanDAO(AccountEanDAO accountEanDAO) {
        this.accountEanDAO = accountEanDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceUserAccountsDAO(ServiceUserAccountsDAO serviceUserAccountsDAO) {
        this.serviceUserAccountsDAO = serviceUserAccountsDAO;
    }

    @Autowired
    public void setServiceUserDAO(ServiceUserDAO serviceUserDAO) {
        this.serviceUserDAO = serviceUserDAO;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AccountDto addAccount(AccountForCreationDto account) {
        Map<Integer, ServiceUserDto> helperServiceUserMap = new HashMap<>();
        try {
            List<Integer> serviceUsersIds = account.getServiceUsersIds();
            List<String> eans = account.getEan();
            AccountEntity accountEntityToAdd = modelMapper.map(account, AccountEntity.class);
            AccountDto addedAccount = accountDAO.addAccount(accountEntityToAdd, eans, serviceUsersIds);
            fillAccountWithListOfEans(addedAccount);
            fillAccountWithServiceUsers(addedAccount, helperServiceUserMap);
            return addedAccount;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteAccount(int id) {
        return accountDAO.deleteAccount(id);
    }


    @Override
    public AccountDto findAccount(int id) {
        Map<Integer, ServiceUserDto> helperAccountMap = new HashMap<>();
        try {
            AccountDto found = accountDAO.findAccount(id);
            fillAccountWithListOfEans(found);
            fillAccountWithServiceUsers(found, helperAccountMap);
            return found;
        } catch (Exception e) {
            throw new NotFoundException("Service provider not found");
        }
    }

    @Override
    public List<AccountDto> listAccounts() {
        Map<Integer, ServiceUserDto> helperCompetencyMap = new HashMap<>();
        try {
            List<AccountDto> found = accountDAO.listAccounts();
            for (AccountDto account : found) {
                fillAccountWithListOfEans(account);
                fillAccountWithServiceUsers(account, helperCompetencyMap);
            }
            return found;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AccountDto updateAccount(AccountForUpdateDto account) {
        try {
            Map<Integer, ServiceUserDto> helperServiceUserMap = new HashMap<>();
            List<Integer> serviceUsersIds = account.getServiceUsers();
            List<String> eans = account.getEan();
            AccountEntity accountEntityToUpdate = modelMapper.map(account, AccountEntity.class);
            AccountDto updated = accountDAO.updateAccount(accountEntityToUpdate, eans, serviceUsersIds);
            fillAccountWithListOfEans(updated);
            fillAccountWithServiceUsers(updated, helperServiceUserMap);
            return updated;
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException("Account type is not found. Update cancelled.");
        }
    }


    private void fillAccountWithListOfEans(AccountDto account) {
        account.setEan(new ArrayList<>());
        List<AccountEanEntity> foundList = accountEanDAO.findAllByAccountId(account.getId());
        List<AccountEanDto> listOfEanNumbers = modelMapper.map(foundList, new TypeToken<List<AccountEanDto>>() {
        }.getType());
        for (AccountEanDto eanNumber : listOfEanNumbers) {
            account.getEan().add(eanNumber.getEanNumber());
        }
    }

    private void fillAccountWithServiceUsers(AccountDto account, Map<Integer, ServiceUserDto> helperServiceUserMap) {
        account.setServiceUsers(new ArrayList<>());
        List<ServiceUserAccountEntity> foundList = serviceUserAccountsDAO.findAllByAccountIdIs(account.getId());
        List<ServiceUserAccountDto> listOfServiceUserAccounts = modelMapper.map(foundList, new TypeToken<List<ServiceUserAccountDto>>() {
        }.getType());
        for (ServiceUserAccountDto serviceUser : listOfServiceUserAccounts) {
            if (helperServiceUserMap.get(serviceUser.getServiceUserId()) == null) {
                ServiceUserDto found = serviceUserDAO.findServiceUser(serviceUser.getServiceUserId());
                helperServiceUserMap.put(found.getId(), found);
                account.getServiceUsers().add(found);
            } else {
                account.getServiceUsers().add(helperServiceUserMap.get(serviceUser.getServiceUserId()));
            }
        }
    }
}
