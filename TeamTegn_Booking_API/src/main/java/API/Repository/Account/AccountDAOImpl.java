package API.Repository.Account;

import API.Configurations.Patcher.PatcherHandler;

import API.Database_Entities.AccountEanEntity;
import API.Database_Entities.AccountEntity;
import API.Database_Entities.AccountTypeEntity;
import API.Exceptions.*;
import Shared.ToReturn.AccountDto;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Component
public class AccountDAOImpl implements AccountDAOCustom {

    private PatcherHandler patcherHandler;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private AccountTypeDAO accountTypeDAO;
    @Autowired
    private AccountEanDAO accountEanNumberCrudDAO;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Override
    public List<AccountDto> listAllAccounts() {
        try {
            Type listType = new TypeToken<List<AccountDto>>() {
            }.getType();
            return modelMapper.map(accountDAO.findAllByDeletedIsFalse(), listType);
        } catch (Exception e) {
            throw e;
        }
    }


    public AccountDto getOneAccount(int id) {
        try {
            Optional<AccountEntity> found = accountDAO.findById(id);
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("Account does not exist.");
            }
            return modelMapper.map(found.get(), AccountDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public AccountDto addOneAccount(AccountEntity account, List<String> eans, int accountTypeId) {
        try {
            if (accountDAO.countAllByAccountNameAndCvrNumber(account.getAccountName(), account.getCvrNumber()) > 0) {
                throw new DuplicateException("Account with exact name and CVR number already exists.");
            }
            Optional<AccountTypeEntity> entity = accountTypeDAO.findById(accountTypeId);
            if (!entity.isPresent()) {
                throw new NotFoundException("Account type was not found while adding account.");
            }
            account.setAccountTypeId(entity.get().getId());
            account.setDeleted(false);
            AccountEntity accountEntity = accountDAO.save(account);
            if (accountEntity.getId() > 0) {
                if (eans != null) {
                    for (int index = 0; index < eans.size(); index++) {
                        AccountEanEntity accountEanEntity = new AccountEanEntity();
                        accountEanEntity.setAccountId(accountEntity.getId());
                        accountEanEntity.setEanNumber(eans.get(index));
                        accountEanNumberCrudDAO.addOneEanNumber(accountEanEntity);
                    }
                }
                return modelMapper.map(account, AccountDto.class);
            } else {
                throw new UnknownAddingException("There was a problem with adding an account.");
            }
        } catch (NotEnoughDataException notEnoughDataException) {
            throw new NotEnoughDataException("You provided to little information to create the account.");
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new NotEnoughDataException("You provided to little information to create the account.");
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException("Account type was not found. Adding cancelled.");
        } catch (Exception e) {
            throw e;
        }
    }

    //checked
    public AccountDto updateOneAccount(@NotNull AccountEntity accountDto) {
        try {
            Optional<AccountEntity> found = accountDAO.findById(accountDto.getId());
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("The account you want to update was deleted or does not exist.");
            }
            patcherHandler.fillNotNullFields(found.get(), accountDto);
            AccountEntity result = accountDAO.save(found.get());
            AccountDto toReturn = modelMapper.map(result, AccountDto.class);
            return toReturn;
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
        }

    }

    //checked
    public boolean deleteOneAccount(int id) {
        try {
            Optional<AccountEntity> found = accountDAO.findById(id);
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("Account does not exist.");
            }
            AccountEntity toDelete = found.get();
            toDelete.setDeleted(true);
            AccountEntity deletionResult = accountDAO.save(toDelete);
            if (deletionResult.isDeleted()) {
                return true;
            } else {
                return false;
            }
        } catch (NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }

}
