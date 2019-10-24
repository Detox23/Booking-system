package API.Services;

import DAO.AccountDAO;
import Objects.Factory.Account;
import Shared.AccountDto;
import Shared.AccountForCreationDto;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService  {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private ModelMapper modelMapper;

    public boolean addAccount(AccountForCreationDto account)
    {
        Account dbAccount = modelMapper.map(account, Account.class);
        dbAccount.setDeleted(false);
        return accountDAO.addAccount(dbAccount);
    }
}
