package API.Repository.ServiceUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AssignmentTitleEntity;
import API.Database_Entities.ServiceUserAccountEntity;
import API.Database_Entities.ServiceUserEntity;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.ServiceUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class ServiceUserDAOImpl implements ServiceUserDAOCustom {

    private ServiceUserAccountsDAO serviceUserAccountsDAO;

    private ServiceUserDAO serviceUserDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;


    @Autowired
    public void setServiceUserAccountsDAO(ServiceUserAccountsDAO serviceUserAccountsDAO) {
        this.serviceUserAccountsDAO = serviceUserAccountsDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setServiceUserDAO(ServiceUserDAO serviceUserDAO) {
        this.serviceUserDAO = serviceUserDAO;
    }


    @Override
    public ServiceUserDto addServiceUser(ServiceUserEntity serviceUser, List<Integer> accounts) {
        try {
            int count = serviceUserDAO.countAllByFirstNameIsAndMiddleNameIsAndLastNameIs(serviceUser.getFirstName(), serviceUser.getLastName(), serviceUser.getLastName());
            if (count > 0) {
                throw new DuplicateException(String.format("The is already service user with name: %s %s %s.", serviceUser.getFirstName(),
                        serviceUser.getMiddleName(), serviceUser.getLastName()));
            }
            ServiceUserEntity saved = serviceUserDAO.save(serviceUser);
            addServiceProvidersAccounts(accounts, saved.getId());
            return modelMapper.map(saved, ServiceUserDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteServiceUser(int id) {
        try {
            ServiceUserEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            ServiceUserEntity deletionResult = serviceUserDAO.save(found);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Page<ServiceUserDto> listServiceUsers(Pageable pageable) {
        try {
            Page<ServiceUserDto> pageToReturn = serviceUserDAO.findAllByDeletedFalse(pageable).map(x -> modelMapper.map(x, ServiceUserDto.class));
            return pageToReturn;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public ServiceUserDto findServiceUser(int id) {
        try {
            ServiceUserEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, ServiceUserDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceUserDto updateServiceUser(ServiceUserEntity serviceUser, List<Integer> accounts) {
        try {
            ServiceUserEntity found = findIfExistsAndReturn(serviceUser.getId());
            patcherHandler.fillNotNullFields(found, serviceUser);
            addServiceProvidersAccounts(accounts, serviceUser.getId());
            ServiceUserEntity result = serviceUserDAO.save(found);
            return modelMapper.map(result, ServiceUserDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an assignment title. [PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }


    private ServiceUserEntity findIfExistsAndReturn(int id) {
        Optional<ServiceUserEntity> found = serviceUserDAO.findByIdIsAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Service user with id: %d was not found.", id));
        }
        return found.get();
    }

    private void addServiceProvidersAccounts(List<Integer> accounts, int id){
        try{
            if(accounts != null){
                serviceUserAccountsDAO.deleteAllByServiceUserIdIs(id);
                for(Integer account: accounts){
                    ServiceUserAccountEntity serviceUserAccount = new ServiceUserAccountEntity();
                    serviceUserAccount.setAccountId(account);
                    serviceUserAccount.setServiceUserId(id);
                    ServiceUserAccountEntity saved = serviceUserAccountsDAO.save(serviceUserAccount);
                    if(saved == null){
                        throw new UnknownAddingException(String.format("There was a problem with adding service user."));
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }
    }
}

