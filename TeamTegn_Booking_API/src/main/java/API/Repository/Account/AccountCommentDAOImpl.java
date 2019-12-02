package API.Repository.Account;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AccountCommentEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.AccountCommentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class AccountCommentDAOImpl implements AccountCommentDAOCustom {

    private AccountCommentDAO accountCommentDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    @Autowired
    public void setAccountCommentDAO(AccountCommentDAO accountCommentDAO) {
        this.accountCommentDAO = accountCommentDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountCommentDto addAccountComment(AccountCommentEntity accountComment) {
        return modelMapper.map(accountCommentDAO.save(accountComment), AccountCommentDto.class);
    }

    @Override
    public AccountCommentDto updateAccountComment(AccountCommentEntity accountComment) {
        try {
            AccountCommentEntity found = findIfExistsAndReturn(accountComment.getId());
            patcherHandler.fillNotNullFields(found, accountComment);
            AccountCommentEntity updated = accountCommentDAO.save(found);
            return modelMapper.map(updated, AccountCommentDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was a problem with updating a comment.[PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAccountComment(int accountID, int id) {
        try {
            Optional<AccountCommentEntity> found = accountCommentDAO.findByAccountIdIsAndIdIs(accountID, id);
            if(!found.isPresent()){
                throw new NotFoundException("There was no comment found.");
            }
            accountCommentDAO.deleteById(found.get().getId());
            Optional<AccountCommentEntity> assure = accountCommentDAO.findById(id);
            return assure.isPresent();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AccountCommentDto findAccountComment(int id) {
        try {
            AccountCommentEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AccountCommentDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AccountCommentDto> listAccountComments(int accountID) {
        try {
            Type listType = new TypeToken<List<AccountCommentDto>>() {
            }.getType();
            List<AccountCommentEntity> listOfComments = accountCommentDAO.findAllByAccountIdIs(accountID);
            return modelMapper.map(listOfComments, listType);
        } catch (Exception e) {
            throw e;
        }
    }

    private AccountCommentEntity findIfExistsAndReturn(int id) {
        Optional<AccountCommentEntity> found = accountCommentDAO.findByIdIs(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Account comment with id: %d was not found.", id));
        }
        return found.get();
    }
}
