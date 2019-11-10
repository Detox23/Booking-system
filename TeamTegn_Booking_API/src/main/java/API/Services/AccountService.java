package API.Services;
import API.Repository.Account.IAccountDAO;
import API.Repository.Account.IAccountEanDAO;
import API.Repository.Mappers.AccountMapper;
import Objects.Factory.Database_Entities.AccountEanEntity;
import Objects.Factory.Database_Entities.AccountEntity;
import Objects.Factory.Database_Entities.AccountTypeEntity;
import Shared.ForCreation.AccountEanForCreationDto;
import Shared.ToReturn.AccountDto;
import Shared.ForCreation.AccountForCreationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    private PropertyMap propertyMapUpdate;

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private IAccountEanDAO accountEanDAO;

    @Autowired
    private ModelMapper modelMapper;


    public AccountDto addAccount(AccountForCreationDto account)
    {
        AccountTypeEntity accountTypeEntity = accountDAO.findAccountType(account.getAccountTypeID());
        AccountEntity accountEntity = AccountMapper.mapAccountForCreationToAccountEntity(account, accountTypeEntity);
        return accountDAO.addAccount(accountEntity, account.getEan());
    }

    public boolean deleteAccount(int id)
    {
        return accountDAO.deleteAccount(id);
    }

    public AccountDto findAccount(int id)
    {
        AccountEntity accountEntity = accountDAO.findAccountByID(id);
        List<AccountEanEntity> foundEANNumberForAccounts = accountDAO.findAccountEANNumber((int) accountEntity.getId());
        AccountDto accountToReturn = modelMapper.map(accountEntity, AccountDto.class);
        List<String> listEan = new ArrayList<>();
        accountToReturn.setEan(listEan);
        if(foundEANNumberForAccounts != null){
            for(int index = 0; index < foundEANNumberForAccounts.size(); index++){
                accountToReturn.getEan().add(foundEANNumberForAccounts.get(index).getEanNumber());
            }
        }
        return accountToReturn;
    }

    public List<AccountDto> list()
    {
        List<AccountEntity> foundAccounts = accountDAO.list();
        List<AccountDto> finalListToReturn = new ArrayList<>();
        for(int index = 0; index <foundAccounts.size(); index++){
            List<AccountEanEntity> foundEANNumberForAccounts = accountDAO.findAccountEANNumber((int) foundAccounts.get(index).getId());
            AccountDto accountToBeAdded = modelMapper.map(foundAccounts.get(index), AccountDto.class);
            List<String> listEanToBeAdded = new ArrayList<>();
            accountToBeAdded.setEan(listEanToBeAdded);
            if(foundEANNumberForAccounts != null){
                for(int indexSecond = 0; indexSecond< foundEANNumberForAccounts.size(); indexSecond++){
                    accountToBeAdded.getEan().add(foundEANNumberForAccounts.get(indexSecond).getEanNumber());
                }
            }
            finalListToReturn.add(accountToBeAdded);
        }
        return finalListToReturn;
    }

    @Override
    public boolean update(AccountForCreationDto account, int id)
    {
        AccountEntity accountEntity = accountDAO.findAccountByID(id);
        if(this.propertyMapUpdate == null){
            PropertyMap<AccountForCreationDto, AccountEntity> skipModifiedFieldsMap = new PropertyMap<AccountForCreationDto, AccountEntity>(){
                protected void configure(){
                    map(source.getDepartmentID()).setDepartmentId(accountEntity.getDepartmentId());
                    map(source.getParentID()).setParentId(accountEntity.getParentId());
                    map(source.getAccountTypeID()).setAccountTypeByAccountTypeId(accountEntity.getAccountTypeByAccountTypeId());
                    map(source.getPrimaryContactID()).setPrimaryContactId(accountEntity.getPrimaryContactId());
                }
            };
            this.propertyMapUpdate = skipModifiedFieldsMap;
            modelMapper.addMappings(skipModifiedFieldsMap);
        }

        AccountEntity dbAccount = modelMapper.map(account, AccountEntity.class);
        boolean result = false;
        if (accountDAO.updateAccount(dbAccount) != null){
                result = true;
        };
        return result;
    }

    //TODO
    @Override
    public boolean deleteAccountComment(int accountID, int commentID) {
        return false;
    }

    @Override
    public boolean deleteEAN(int accountID, String eanNumber) {
        return accountEanDAO.deleteeanNumber(accountID, eanNumber);
    }

    @Override
    public boolean addEAN(AccountEanForCreationDto accountEan) {
        AccountEanEntity accountEanEntity = modelMapper.map(accountEan, AccountEanEntity.class);
        return accountEanDAO.addeanNumber(accountEanEntity);
    }
}
