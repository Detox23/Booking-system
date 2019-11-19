package API.Repository.Account;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AccountEanEntity;
import API.Database_Entities.AccountEntity;
import API.Exceptions.*;
import Shared.ToReturn.AccountDto;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;


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
            return modelMapper.map(accountDAO.findAll(), listType);
        } catch (Exception e) {
            throw e;
        }
    }


    public AccountDto getOneAccount(int id) {
        try {
            AccountEntity found = accountDAO.findById(id).get();
            return modelMapper.map(found, AccountDto.class);
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException("Account was not found.");
        } catch (Exception e) {
            throw e;
        }
    }

    public AccountDto addOneAccount(AccountEntity account, List<String> eans, int accountTypeId) {
        try {
            if (accountDAO.countAllByAccountNameAndCvrNumber(account.getAccountName(), account.getCvrNumber()) > 0) {
                throw new DuplicateException("Account with exact name and CVR number already exists.");
            }
            account.setAccountTypeByAccountTypeId(accountTypeDAO.findById(accountTypeId).get());
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
        } catch (NotEnoughDataException dataIntegrityViolationException) {
            throw new NotEnoughDataException("You provided to little information to create the account.");
        } catch (DataIntegrityViolationException dataIntegrityViolidationException) {
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
            AccountEntity found = accountDAO.findById(accountDto.getId()).get();
            if (found.isDeleted() == true) {
                throw new UpdateErrorException("You can't update deleted account.");
            }
            patcherHandler.fillNotNullFields(found, accountDto);
            AccountEntity result = accountDAO.save(found);
            return modelMapper.map(result, AccountDto.class);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Account was not found while an attempt of making update.");
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
        }

    }

    //checked
    public boolean deleteOneAccount(int id) {
        try {
            AccountEntity account = accountDAO.findById(id).get();
            account.setDeleted(true);
            AccountEntity updatedAccount = accountDAO.save(account);
            if (updatedAccount.isDeleted() == true) {
                return true;
            } else {
                throw new DeletionException("Account was not deleted.");
            }
        } catch (DeletionException deletionException) {
            throw deletionException;
        } catch (NoSuchElementException emptyResult) {
            throw new NotFoundException("Account you wanted to delete does not exist.");
        } catch (Exception e) {
            throw e;
        }
    }

}
