package API.Repository.Account;
import Shared.ToReturn.AccountTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import Objects.Factory.Database_Entities.AccountTypeEntity;

public class IAccountTypeDAOImpl implements IAccountTypeCustom {

    @Autowired
    private IAccountTypeDAO accountTypeDAO;

    @Autowired
    private ModelMapper modelMapper;

    public AccountTypeDto findAccountType(int id){
        return modelMapper.map(accountTypeDAO.findById(id).get(), AccountTypeDto.class);
    }
}
