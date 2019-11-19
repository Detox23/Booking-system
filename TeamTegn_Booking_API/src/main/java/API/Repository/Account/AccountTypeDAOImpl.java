package API.Repository.Account;

import API.Exceptions.NotFoundException;
import Shared.ToReturn.AccountTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public class AccountTypeDAOImpl implements AccountTypeCustom {

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
    public AccountTypeDto findOneAccountType(int id) {
        try {
            return modelMapper.map(accountTypeDAO.findById(id).get(), AccountTypeDto.class);
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException("Account type no found for ID.");
        } catch (Exception e) {
            throw e;
        }
    }

}
