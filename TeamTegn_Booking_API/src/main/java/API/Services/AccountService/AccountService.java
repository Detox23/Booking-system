package API.Services.AccountService;

import API.Database_Entities.AccountEanEntity;
import API.Database_Entities.AccountEntity;
import API.Database_Entities.ServiceUserAccountEntity;
import API.Database_Entities.ServiceUserEntity;
import API.Exceptions.NotFoundException;
import API.Repository.Account.AccountDAO;
import API.Repository.Account.AccountEanDAO;
import API.Repository.Account.AccountTypeDAO;
import API.Repository.ServiceUser.ServiceUserAccountsDAO;
import API.Repository.ServiceUser.ServiceUserDAO;
import Shared.ForCreation.AccountEanForCreationDto;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.AccountForUpdateDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.AccountEanDto;
import Shared.ToReturn.ServiceUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private AccountEanDAO accountEanDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ServiceUserAccountsDAO serviceUserAccountsDAO;
    @Autowired
    private ServiceUserDAO serviceUserDAO;
    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AccountDto addAccount(AccountForCreationDto account) {

        try {
            AccountDto addedAccount = accountDAO.addOneAccount(modelMapper.map(account, AccountEntity.class), account.getEan(), account.getAccountTypeId());
           account.getServiceUsersIds().forEach((id)-> serviceUserAccountsDAO.save(new ServiceUserAccountEntity(addedAccount.getId(),id )) );
            return fillAccountWithListOfEans(addedAccount);
        }catch(Exception e){
            throw e;
        }
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteAccount(int id) {
        return accountDAO.deleteOneAccount(id);
    }


    @Override
    public AccountDto findAccount(int id) {
        AccountDto accountToReturn = accountDAO.getOneAccount(id);
        List<AccountEanDto> foundEANNumberForAccounts = accountEanDAO.findListOfAccountEANNumbers(accountToReturn.getId());
        accountToReturn.setEan(new ArrayList<>());
        if (foundEANNumberForAccounts != null) {
            IntStream.range(0, foundEANNumberForAccounts.size()).parallel().forEach(index ->
                    accountToReturn.getEan().add(foundEANNumberForAccounts.get(index).getEanNumber()));
        }

            List<ServiceUserAccountEntity> ids =serviceUserAccountsDAO.findAllServiceUserIdByAccountId(id);
            List<ServiceUserDto> suDtos = new ArrayList<ServiceUserDto>();
            if(ids != null)
            {
                ids.forEach((su)->
                {
                    Optional<ServiceUserEntity> suEntity = serviceUserDAO.findById(su.getServiceUserId());
                    if(suEntity.isPresent())
                    {
                        suDtos.add(mapper.map(suEntity.get(), ServiceUserDto.class));
                    }
                });
            accountToReturn.setServiceUsers(suDtos);
        }

        return accountToReturn;
    }
    private List<ServiceUserDto> getServiceUserAccounts(AccountDto accountDto)
    {
        List<ServiceUserAccountEntity> ids =serviceUserAccountsDAO.findAllServiceUserIdByAccountId(accountDto.getId());
        List<ServiceUserDto> suDtos = new ArrayList<ServiceUserDto>();
        if(ids != null)
        {
            ids.forEach((su)->
            {
                Optional<ServiceUserEntity> suEntity = serviceUserDAO.findById(su.getServiceUserId());
                if(suEntity.isPresent())
                {
                    suDtos.add(mapper.map(suEntity.get(), ServiceUserDto.class));
                }
            });
        }
        return  suDtos;
    }
    @Override
    public List<AccountDto> list() {
        List<AccountDto> foundAccounts = accountDAO.listAllAccounts();
        List<AccountDto> finalListToReturn = new ArrayList<>();
        IntStream.range(0, foundAccounts.size()).parallel().forEach(index -> {
            List<AccountEanDto> foundEANNumberForAccounts = accountEanDAO.findListOfAccountEANNumbers(foundAccounts.get(index).getId());
            AccountDto accountToBeAdded = modelMapper.map(foundAccounts.get(index), AccountDto.class);
            List<String> listEanToBeAdded = new ArrayList<>();
            accountToBeAdded.setEan(listEanToBeAdded);
            if (foundEANNumberForAccounts != null) {
                IntStream.range(0, foundEANNumberForAccounts.size()).parallel().forEach(indexSecond ->
                        accountToBeAdded.getEan().add(foundEANNumberForAccounts.get(indexSecond).getEanNumber()));
            }
            finalListToReturn.add(accountToBeAdded);
        });
        finalListToReturn.forEach((account) ->account.setServiceUsers(getServiceUserAccounts(account)));
        return finalListToReturn;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AccountDto update(AccountForUpdateDto account) {
        try {
            AccountEntity accountEntity = modelMapper.map(account, AccountEntity.class);
            AccountDto updatedAccount = accountDAO.updateOneAccount(accountEntity);
            if (updatedAccount != null) {
                fillAccountWithListOfEans(updatedAccount);
                return updatedAccount;
            } else {
                return null;
            }
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException("Account type is not found. Update cancelled.");
        }
    }

    //TODO
    @Override
    public boolean deleteAccountComment(int accountID, int commentID) {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteEAN(int accountID, String eanNumber) {
        return accountEanDAO.deleteOneEanNumber(accountID, eanNumber);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean addEAN(AccountEanForCreationDto accountEan) {
        AccountEanEntity accountEanEntity = modelMapper.map(accountEan, AccountEanEntity.class);
        return accountEanDAO.addOneEanNumber(accountEanEntity);
    }

    @Override
    public List<AccountEanDto> findListOfEANNumbersForAccount(int accountID) {
        return accountEanDAO.findListOfAccountEANNumbers(accountID);
    }

    private AccountDto fillAccountWithListOfEans(AccountDto account) {
        List<AccountEanDto> listOfAccountEans = accountEanDAO.findListOfAccountEANNumbers(account.getId());
        account.setEan(new ArrayList<>());
        for (AccountEanDto a : listOfAccountEans) {
            account.getEan().add(a.getEanNumber());
        }
        return account;
    }
}
