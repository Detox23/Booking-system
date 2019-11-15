package API.Repository.Account;

import API.Exceptions.NotFoundException;
import Shared.ToReturn.AccountTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

public class AccountTypeDAOImpl implements AccountTypeCustom {

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private ModelMapper modelMapper;

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
