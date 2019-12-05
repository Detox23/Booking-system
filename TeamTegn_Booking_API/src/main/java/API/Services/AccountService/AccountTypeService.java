package API.Services.AccountService;

import API.Models.Database_Entities.AccountTypeEntity;
import API.Repository.Account.AccountTypeDAO;
import Shared.ForCreation.AccountTypeForCreationDto;
import Shared.ForCreation.AccountTypeForUpdateDto;
import Shared.ToReturn.AccountTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountTypeService implements IAccountTypeService{

    private AccountTypeDAO accountTypeDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setAccountTypeDAO(AccountTypeDAO accountTypeDAO) {
        this.accountTypeDAO = accountTypeDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AccountTypeDto addAccountType(AccountTypeForCreationDto account) {
        return accountTypeDAO.addAccountType(modelMapper.map(account, AccountTypeEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteAccountType(int id) {
        return accountTypeDAO.deleteAccountType(id);
    }

    @Override
    public AccountTypeDto findAccountType(int id) {
        return accountTypeDAO.findAccountType(id);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AccountTypeDto updateAccountType(AccountTypeForUpdateDto account) {
        return accountTypeDAO.updateAccountType(modelMapper.map(account, AccountTypeEntity.class));
    }

    @Override
    public List<AccountTypeDto> listAccountTypes() {
        return accountTypeDAO.listAccountTypes();
    }
}
