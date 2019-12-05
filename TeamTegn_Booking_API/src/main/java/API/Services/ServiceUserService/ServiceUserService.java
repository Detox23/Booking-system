package API.Services.ServiceUserService;

import API.Configurations.Encryption.EncryptionHandler;
import API.Exceptions.NotFoundException;
import API.Models.Database_Entities.ServiceUserAccountEntity;
import API.Models.Database_Entities.ServiceUserEntity;
import API.Repository.Account.AccountDAO;
import API.Repository.ServiceUser.ServiceUserAccountsDAO;
import API.Repository.ServiceUser.ServiceUserDAO;
import API.Repository.ServiceUser.ServiceUserPreferencesDAO;
import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import Shared.ToReturn.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ServiceUserService implements IServiceUserService {
    private ServiceUserDAO serviceUserDAO;
    private AccountDAO accountDAO;
    private ServiceUserAccountsDAO serviceUserAccountsDAO;
    private ModelMapper modelMapper;
    private EncryptionHandler encryptionHandler;
    private ServiceUserPreferencesService serviceUserPreferencesService;
    private ServiceUserCommentService serviceUserCommentService;

    @Autowired
    public void setServiceUserCommentService(ServiceUserCommentService serviceUserCommentService) {
        this.serviceUserCommentService = serviceUserCommentService;
    }

    @Autowired
    public void setServiceUserPreferencesService(ServiceUserPreferencesService serviceUserPreferencesService) {
        this.serviceUserPreferencesService = serviceUserPreferencesService;
    }

    @Autowired
    public void setEncryptionHandler(EncryptionHandler encryptionHandler) {
        this.encryptionHandler = encryptionHandler;
    }

    @Autowired
    public void setServiceUserDAO(ServiceUserDAO serviceUserDAO) {
        this.serviceUserDAO = serviceUserDAO;
    }

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Autowired
    public void setServiceUserAccountsDAO(ServiceUserAccountsDAO serviceUserAccountsDAO) {
        this.serviceUserAccountsDAO = serviceUserAccountsDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceUserDto addServiceUser(ServiceUserForCreationDto serviceUser) {
        Map<Integer, AccountDto> helperAccountMap = new HashMap<>();
        try {
            ServiceUserDto addedServiceUser = serviceUserDAO.addServiceUser(modelMapper.map(serviceUser, ServiceUserEntity.class), serviceUser.getAccounts());
            decryptCpr(addedServiceUser);
            fillServiceUserWithAccounts(addedServiceUser, helperAccountMap);
            fillServiceUserWithPreferences(addedServiceUser);
            fillServiceUserWithComments(addedServiceUser);
            return addedServiceUser;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceUserDto findServiceUser(int id) {
        try {
            Map<Integer, AccountDto> helperAccountMap = new HashMap<>();
            ServiceUserDto found = serviceUserDAO.findServiceUser(id);
            decryptCpr(found);
            fillServiceUserWithAccounts(found, helperAccountMap);
            fillServiceUserWithPreferences(found);
            fillServiceUserWithComments(found);
            return found;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Page<ServiceUserDto> listServiceUsers(Pageable pageable) {
        try {
            Map<Integer, AccountDto> helperAccountMap = new HashMap<>();
            Page<ServiceUserDto> found = serviceUserDAO.listServiceUsers(pageable);
            found.toList().forEach(serviceUser -> {
//                fillServiceUserWithAccounts(serviceUser, helperAccountMap);
//                fillServiceUserWithPreferences(serviceUser);
//                fillServiceUserWithComments(serviceUser);
                decryptCpr(serviceUser);
            });
            return found;
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceUser(int id) {
        return serviceUserDAO.deleteServiceUser(id);
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceUserDto updateServiceUser(ServiceUserForUpdateDto serviceUser) {
        try {
            Map<Integer, AccountDto> helperAccountMap = new HashMap<>();
            ServiceUserDto updated = serviceUserDAO.updateServiceUser(modelMapper.map(serviceUser, ServiceUserEntity.class), serviceUser.getAccounts());
            decryptCpr(updated);
            fillServiceUserWithAccounts(updated, helperAccountMap);
            fillServiceUserWithPreferences(updated);
            fillServiceUserWithComments(updated);
            return updated;
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException("Account type is not found. Update cancelled.");
        }
    }

    private void decryptCpr(ServiceUserDto serviceProvider) {
        String decrypted = encryptionHandler.decrypt(serviceProvider.getCpr());
        serviceProvider.setCpr(decrypted);
    }


    private void fillServiceUserWithAccounts(ServiceUserDto serviceUser, Map<Integer, AccountDto> helperAccountMap) {
        serviceUser.setAccounts(new ArrayList<>());
        List<ServiceUserAccountEntity> foundList = serviceUserAccountsDAO.findAllByServiceUserIdIs(serviceUser.getId());
        List<ServiceUserAccountDto> listOfServiceUserAccounts = modelMapper.map(foundList, new TypeToken<List<ServiceUserAccountDto>>() {
        }.getType());
        for (ServiceUserAccountDto account : listOfServiceUserAccounts) {
            if (helperAccountMap.get(account.getAccountId()) == null) {
                AccountDto found = accountDAO.findAccount(account.getAccountId());
                helperAccountMap.put(found.getId(), found);
                serviceUser.getAccounts().add(found);
            } else {
                serviceUser.getAccounts().add(helperAccountMap.get(account.getAccountId()));
            }
        }
    }

    private void fillServiceUserWithPreferences(ServiceUserDto serviceUser) {
        serviceUser.setPreferences(new ArrayList<>());
        List<ServiceUserPreferencesDto> listOfPreferences = serviceUserPreferencesService.listServiceUserPreferences(serviceUser.getId());
        for (ServiceUserPreferencesDto preferences : listOfPreferences) {
            serviceUser.getPreferences().add(preferences);
        }
    }

    private void fillServiceUserWithComments(ServiceUserDto serviceUser){
        serviceUser.setComments(new ArrayList<>());
        List<ServiceUserCommentDto> listOfComments = serviceUserCommentService.listServiceUserComments(serviceUser.getId());
        for (ServiceUserCommentDto comment: listOfComments){
            serviceUser.getComments().add(comment);
        }
    }
}

