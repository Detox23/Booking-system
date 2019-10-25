package API.Services;

import API.DAO.IAccountDAO;
import Objects.Factory.Database_Entities.AccountEntity;
import Shared.AccountDto;
import Shared.AccountForCreationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private ModelMapper modelMapper;

    public boolean addAccount(AccountForCreationDto account)
    {
        AccountEntity dbAccount = modelMapper.map(account, AccountEntity.class);
        dbAccount.setDeleted(false);
        dbAccount.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return accountDAO.addAccount(dbAccount);
    }

    public boolean deleteAccount(int id)
    {
        return accountDAO.deleteAccount(id);
    }

    public AccountDto findAccount(int id)
    {
        AccountEntity account = accountDAO.findAccountByID(id);
        return modelMapper.map(account, AccountDto.class);
    }

    public List<AccountDto> list()
    {
        List<AccountEntity> accounts = accountDAO.list();
        List<AccountDto> listDtos = modelMapper.map(accounts, new TypeToken<List<AccountDto>>(){}.getType());
        return listDtos;
    }

    //Tylko na tearaz
    public AccountDto update(AccountForCreationDto account)
    {
        return null;
    }
}
