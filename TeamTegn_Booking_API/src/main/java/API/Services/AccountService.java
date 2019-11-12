package API.Services;

import API.Exceptions.*;
import Shared.ToReturn.AccountEanDto;
import org.springframework.beans.factory.annotation.Autowired;
import Objects.Factory.Database_Entities.AccountEanEntity;
import Objects.Factory.Database_Entities.AccountEntity;
import Shared.ForCreation.AccountEanForCreationDto;
import Shared.ForCreation.AccountForCreationDto;
import org.springframework.stereotype.Service;
import API.Repository.Account.IAccountTypeDAO;
import API.Repository.Account.IAccountEanDAO;
import API.Repository.Mappers.AccountMapper;
import API.Repository.Account.IAccountDAO;
import org.modelmapper.ModelMapper;
import java.util.stream.IntStream;
import Shared.ToReturn.AccountDto;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private IAccountTypeDAO accountTypeDAO;

    @Autowired
    private IAccountEanDAO accountEanDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AccountDto addAccount(AccountForCreationDto account) throws NoAccountIDAfterSavingException, MappingAccountDatabseToDtoException, AccountNotFoundWhileAddingEANNumberException {
        AccountDto addedAccount = accountDAO.addAccount(modelMapper.map(account, AccountEntity.class), account.getEan(), account.getAccountTypeId());
        return fillAccountWithListOfEans(addedAccount);
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteAccount(int id) {
        return accountDAO.deleteAccount(id);
    }

    @Override
    public AccountDto findAccount(int id) {
        AccountDto accountToReturn = accountDAO.findAccountByID(id);
        List<AccountEanDto> foundEANNumberForAccounts = accountEanDAO.findAccountEANNumber(accountToReturn.getId());
        List<String> listEan = new ArrayList<>();
        accountToReturn.setEan(listEan);
        if (foundEANNumberForAccounts != null) {
            IntStream.range(0, foundEANNumberForAccounts.size()).parallel().forEach(index ->
                    accountToReturn.getEan().add(foundEANNumberForAccounts.get(index).getEanNumber()));
        }
        return accountToReturn;
    }

    @Override
    public List<AccountDto> list() {
        List<AccountDto> foundAccounts = accountDAO.list();
        List<AccountDto> finalListToReturn = new ArrayList<>();
        IntStream.range(0, foundAccounts.size()).parallel().forEach(index -> {
            List<AccountEanDto> foundEANNumberForAccounts = accountEanDAO.findAccountEANNumber(foundAccounts.get(index).getId());
            AccountDto accountToBeAdded = modelMapper.map(foundAccounts.get(index), AccountDto.class);
            List<String> listEanToBeAdded = new ArrayList<>();
            accountToBeAdded.setEan(listEanToBeAdded);
            if (foundEANNumberForAccounts != null) {
                IntStream.range(0, foundEANNumberForAccounts.size()).parallel().forEach(indexSecond ->
                        accountToBeAdded.getEan().add(foundEANNumberForAccounts.get(indexSecond).getEanNumber()));
            }
            finalListToReturn.add(accountToBeAdded);
        });
        return finalListToReturn;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AccountDto update(AccountDto account) throws AccountNotExistsUpdateException, UpdateErrorException {
        AccountDto updatedAccount = accountDAO.updateAccount(AccountMapper.mapAccountDtoToAccountEntity(account, accountTypeDAO.findById(account.getAccountTypeID()).get()));
        fillAccountWithListOfEans(updatedAccount);
        if (updatedAccount != null){
            return updatedAccount;
        }else{
            return null;
        }
    }

    //TODO
    @Override
    public boolean deleteAccountComment(int accountID, int commentID) {
        return false;
    }

    @Override
    public boolean deleteEAN(int accountID, String eanNumber) {
        return accountEanDAO.deleteEanNumber(accountID, eanNumber);
    }

    @Override
    public boolean addEAN(AccountEanForCreationDto accountEan) throws AccountNotFoundWhileAddingEANNumberException, AddingTheSameEANNumberToSameAccountException {
        AccountEanEntity accountEanEntity = modelMapper.map(accountEan, AccountEanEntity.class);
        return accountEanDAO.addEanNumber(accountEanEntity);
    }

    private AccountDto fillAccountWithListOfEans(AccountDto account){
        List<AccountEanDto> listOfAccountEans = accountEanDAO.findAccountEANNumber(account.getId());
        account.setEan(new ArrayList<>());
        for (AccountEanDto a : listOfAccountEans) {
            account.getEan().add(a.getEanNumber());
        }
        return account;
    }
}
