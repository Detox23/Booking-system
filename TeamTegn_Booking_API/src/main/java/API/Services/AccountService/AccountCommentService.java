package API.Services.AccountService;

import API.Database_Entities.AccountCommentEntity;
import API.Repository.Account.AccountCommentDAO;
import Shared.ForCreation.AccountCommentForCreationDto;
import Shared.ForCreation.AccountCommentForUpdateDto;
import Shared.ToReturn.AccountCommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountCommentService implements IAccountCommentService {

    private AccountCommentDAO accountCommentDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setAccountCommentDAO(AccountCommentDAO accountCommentDAO) {
        this.accountCommentDAO = accountCommentDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountCommentDto addAccountComment(AccountCommentForCreationDto accountComment) {
        return accountCommentDAO.addAccountComment(modelMapper.map(accountComment, AccountCommentEntity.class));
    }

    @Override
    public AccountCommentDto updateAccountComment(AccountCommentForUpdateDto accountComment) {
        return accountCommentDAO.updateAccountComment(modelMapper.map(accountComment, AccountCommentEntity.class));
    }

    @Override
    public boolean deleteAccountComment(int accountID, int id) {
        return accountCommentDAO.deleteAccountComment(accountID, id);
    }

    @Override
    public AccountCommentDto findAccountComment(int id) {
        return accountCommentDAO.findAccountComment(id);
    }

    @Override
    public List<AccountCommentDto> listAccountComments(int accountID) {
        return accountCommentDAO.listAccountComments(accountID);
    }
}
