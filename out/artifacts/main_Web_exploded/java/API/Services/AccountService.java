package API.Services;
import API.Repository.Account.IAccountDAO;
import API.Repository.Account.IAccountEanDAO;
import API.Repository.Mappers.AccountMapper;
import DAO.HibernateUtil;
import Objects.Factory.Database_Entities.AccountEanEntity;
import Objects.Factory.Database_Entities.AccountEntity;
import Objects.Factory.Database_Entities.AccountTypeEntity;
import Shared.ForCreation.AccountEanForCreationDto;
import Shared.ToReturn.AccountDto;
import Shared.ForCreation.AccountForCreationDto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
        AccountDto mappedAccount =AccountMapper.mapAccountEntityToAccountDto(accountEntity, new ArrayList<>());
        if(foundEANNumberForAccounts != null){
            IntStream.range(0, foundEANNumberForAccounts.size()).parallel().forEach(index ->
                    mappedAccount.getEan().add(foundEANNumberForAccounts.get(index).getEanNumber()));
        }
        return mappedAccount;
    }

    public List<AccountDto> list()
    {
        List<AccountDto> finalListToReturn = new ArrayList<>();
        for (AccountEntity foundAccount : accountDAO.list()) {
            List<AccountEanEntity> foundEANNumberForAccounts = accountDAO.findAccountEANNumber((int) foundAccount.getId());
            AccountDto mappedAccount = AccountMapper.mapAccountEntityToAccountDto(foundAccount, new ArrayList<>());
            if (foundEANNumberForAccounts != null) {
                IntStream.range(0, foundEANNumberForAccounts.size()).parallel().forEach(indexSecond ->
                        mappedAccount.getEan().add(foundEANNumberForAccounts.get(indexSecond).getEanNumber()));
            }
            finalListToReturn.add(mappedAccount);
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
        return accountEanDAO.deleteEanNumber(accountID, eanNumber);
    }

    @Override
    public boolean addEAN(AccountEanForCreationDto accountEan) {
        AccountEanEntity accountEanEntity = modelMapper.map(accountEan, AccountEanEntity.class);
        return accountEanDAO.addEanNumber(accountEanEntity);
    }
}
