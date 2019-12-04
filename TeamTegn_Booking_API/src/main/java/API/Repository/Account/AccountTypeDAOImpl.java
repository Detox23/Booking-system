package API.Repository.Account;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.AccountTypeEntity;
import Shared.ToReturn.AccountTypeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

public class AccountTypeDAOImpl implements AccountTypeCustom {

    private AccountTypeDAO accountTypeDAO;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setAccountTypeDAO(AccountTypeDAO accountTypeDAO) {
        this.accountTypeDAO = accountTypeDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public AccountTypeDto addAccountType(AccountTypeEntity accountType) {
        try {
            int count = accountTypeDAO.countAllByAccountTypeIs(accountType.getAccountType());
            if (count > 0) {
                throw new DuplicateException(String.format("The account type with name: %s already exists", accountType.getAccountType()));
            }
            AccountTypeEntity saved = accountTypeDAO.save(accountType);
            return modelMapper.map(saved, AccountTypeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AccountTypeDto updateAccountType(AccountTypeEntity accountType) {
        try {
            AccountTypeEntity found = findIfExistsAndReturn(accountType.getId());
            patcherHandler.fillNotNullFields(found, accountType);
            AccountTypeEntity result = accountTypeDAO.save(found);
            return modelMapper.map(result, AccountTypeDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account type. [PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAccountType(int id) {
        try {
            AccountTypeEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            AccountTypeEntity deletionResult = accountTypeDAO.save(found);
            return deletionResult.getDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AccountTypeDto> listAccountTypes() {
        try {
            return modelMapper.map(accountTypeDAO.findAllByDeletedIsFalse(), new TypeToken<List<AccountTypeDto>>() {
            }.getType());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AccountTypeDto findAccountType(int id) {
        try {
            AccountTypeEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AccountTypeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    private AccountTypeEntity findIfExistsAndReturn(int id) {
        Optional<AccountTypeEntity> found = accountTypeDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Account type with id: %d was not found.", id));
        }
        return found.get();
    }

}
