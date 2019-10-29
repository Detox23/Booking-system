package API.Services;

import API.DAO.IAccountDAO;
import API.DAO.IAccountEanNumberDAO;
import Objects.Factory.Database_Entities.AccountEanEntity;
import Objects.Factory.Database_Entities.AccountEntity;
import Objects.Factory.Database_Entities.AccountTypeEntity;
import Shared.ToReturn.AccountDto;
import Shared.ForCreation.AccountForCreationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    private PropertyMap skipMapWhileCreation;

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private IAccountEanNumberDAO accountEanDAO;

    @Autowired
    private ModelMapper modelMapper;



    public boolean addAccount(AccountForCreationDto account)
    {
        AccountTypeEntity accountTypeEntity = accountDAO.findAccountType(account.getAccountTypeID());
        if(this.skipMapWhileCreation == null){
            PropertyMap<AccountForCreationDto, AccountEntity> skipModifiedFieldsMap = new PropertyMap<AccountForCreationDto, AccountEntity>(){
                protected void configure(){
                    skip().setId(0);
                    skip().setLastModified(null);
                    skip().setAccountTypeByAccountTypeId(null);
                    skip().setLastModifiedBy(null);
                    skip().setCreatedDate(null);
                    skip().setDeleted(false);
                }
            };
            this.skipMapWhileCreation = skipModifiedFieldsMap;
            modelMapper.addMappings(skipModifiedFieldsMap);
        }
        AccountEntity accountEntity = modelMapper.map(account, AccountEntity.class);
        accountEntity.setAccountTypeByAccountTypeId(accountTypeEntity);
        accountEntity.setDeleted(false);
        accountEntity.setEan(null);
        accountEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return accountDAO.addAccount(accountEntity, account.getEan());
    }

    public boolean deleteAccount(int id)
    {
        return accountDAO.deleteAccount(id);
    }

    public AccountDto findAccount(int id)
    {
        AccountEntity accountEntity = accountDAO.findAccountByID(id);
        List<AccountEanEntity> foundEANNumberForAccounts = accountEanDAO.findAccountEANNumber(accountEntity.getId());
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
            List<AccountEanEntity> foundEANNumberForAccounts = accountEanDAO.findAccountEANNumber(foundAccounts.get(index).getId());
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

    //Tylko na teraz
    @Override
    //TODO
    public boolean update(AccountForCreationDto account)
    {
        AccountEntity dbAccount = modelMapper.map(account, AccountEntity.class);
        return false;
    }

    //TODO
    @Override
    public boolean deleteAccountComment(int accountID, int commentID) {
        return false;
    }
}
