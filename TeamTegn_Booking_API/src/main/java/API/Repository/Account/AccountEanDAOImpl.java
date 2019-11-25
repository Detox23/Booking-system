package API.Repository.Account;

import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import Shared.ToReturn.AccountEanDto;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.util.List;

public class AccountEanDAOImpl implements AccountEanCustom {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountEanDAO accountEanNumberCrudDAO;

    @Override
    public boolean deleteOneEanNumber(int accountID, String EANNumber) {
        try {
            AccountEanEntity found = accountEanNumberCrudDAO.findDistinctByAccountIdAndEanNumber(accountID, EANNumber);
            if (found == null) {
                throw new NotFoundException("No account found associated with provided ID and EAN number.");
            }
            accountEanNumberCrudDAO.delete(found);
            AccountEanEntity assure = accountEanNumberCrudDAO.findDistinctByAccountIdAndEanNumber(accountID, EANNumber);
            if (assure != null) {
                return false;
            } else {
                return true;
            }
        } catch (NotFoundException noSuchElementException) {
            throw noSuchElementException;
        } catch (Exception e) {
            throw e;
        }
    }

    //Checked
    @Override
    public List<AccountEanDto> findListOfAccountEANNumbers(int accountID) {
        try {
            List<AccountEanEntity> numbers = accountEanNumberCrudDAO.findAllByAccountId(accountID);
            Type listType = new TypeToken<List<AccountEanDto>>() {
            }.getType();
            return modelMapper.map(numbers, listType);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean addOneEanNumber(@NotNull AccountEanEntity accountEanEntity) {
        try {
            accountDAO.getOneAccount(accountEanEntity.getAccountId());
            List<AccountEanEntity> found = accountEanNumberCrudDAO.findAllByAccountIdAndEanNumber(accountEanEntity.getAccountId(), accountEanEntity.getEanNumber());
            if (found.size() < 1) {
                AccountEanEntity result = accountEanNumberCrudDAO.saveAndFlush(accountEanEntity);
                if (result != null) {
                    return true;
                } else {
                    throw new UnknownAddingException("There was a problem with adding EAN number for the account.");
                }
            } else {
                throw new DuplicateException("The EAN number for account already exists.");
            }
        } catch (NotFoundException ex) {
            throw new NotFoundException("Account to which you wanted to add EAN was not found.");
        } catch (Exception e) {
            throw e;
        }
    }
}
