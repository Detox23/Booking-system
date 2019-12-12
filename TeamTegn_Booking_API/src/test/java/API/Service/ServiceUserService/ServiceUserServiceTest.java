package API.Service.ServiceUserService;

import API.Configurations.Encryption.EncryptionHandler;
import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceUserAccountEntity;
import API.Models.Database_Entities.ServiceUserEntity;
import API.Repository.Account.AccountDAO;
import API.Repository.ServiceUser.ServiceUserAccountsDAO;
import API.Repository.ServiceUser.ServiceUserDAO;
import API.Services.ServiceUserService.ServiceUserCommentService;
import API.Services.ServiceUserService.ServiceUserPreferencesService;
import API.Services.ServiceUserService.ServiceUserService;
import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.ServiceUserCommentDto;
import Shared.ToReturn.ServiceUserDto;
import Shared.ToReturn.ServiceUserPreferencesDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class ServiceUserServiceTest {

    @Autowired
    private ServiceUserService serviceUserService;

    private ServiceUserDto getServiceUserDto(){
        ServiceUserDto serviceUser = new ServiceUserDto();
        serviceUser.setId(2);
        serviceUser.setFirstName("FirstName");
        serviceUser.setLastName("LastName");
        serviceUser.setCpr("Test Cpr");
        return serviceUser;
    }

    private Page<ServiceUserDto> getServiceUserDtoList(){
        List<ServiceUserDto> returnList = new ArrayList<>();
        returnList.add(getServiceUserDto());
        returnList.add(getServiceUserDto());
        returnList.add(getServiceUserDto());
        returnList.add(getServiceUserDto());
        returnList.add(getServiceUserDto());
        return  new PageImpl<ServiceUserDto>(returnList);
    }

    private List<ServiceUserAccountEntity> getServiceUserAccountList(){
        List<ServiceUserAccountEntity> returnList = new ArrayList<>();
        returnList.add(new ServiceUserAccountEntity());
        returnList.add(new ServiceUserAccountEntity());
        returnList.add(new ServiceUserAccountEntity());
        returnList.add(new ServiceUserAccountEntity());
        returnList.add(new ServiceUserAccountEntity());
        return returnList;
    }

    private AccountDto getAccountDto(){
        AccountDto accountToReturn = new AccountDto();
        accountToReturn.setAccountName("Test account name");
        return accountToReturn;
    }

    private List<ServiceUserPreferencesDto> getServiceUserPreferencesList(){
        List<ServiceUserPreferencesDto> returnList = new ArrayList<>();
        returnList.add(new ServiceUserPreferencesDto());
        returnList.add(new ServiceUserPreferencesDto());
        returnList.add(new ServiceUserPreferencesDto());
        returnList.add(new ServiceUserPreferencesDto());
        returnList.add(new ServiceUserPreferencesDto());
        return returnList;
    }

    private List<ServiceUserCommentDto> getServiceUserCommentList(){
        List<ServiceUserCommentDto> returnList = new ArrayList<>();
        returnList.add(new ServiceUserCommentDto());
        returnList.add(new ServiceUserCommentDto());
        returnList.add(new ServiceUserCommentDto());
        returnList.add(new ServiceUserCommentDto());
        returnList.add(new ServiceUserCommentDto());
        return returnList;
    }


    @Test
    public void testAddingServiceUserCprShouldMatch(){
        ServiceUserDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserDAO.class);
        AccountDAO mockAccount = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, AccountDAO.class);
        ServiceUserAccountsDAO mockServiceUserAccounts = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserAccountsDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, EncryptionHandler.class);
        ServiceUserPreferencesService mockServiceUserPreferencesService = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserPreferencesService.class);
        ServiceUserCommentService mockServiceUserCommentService = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserCommentService.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserDto()).when(mockServiceUser).addServiceUser(any(ServiceUserEntity.class), anyList());
        Mockito.doReturn(getAccountDto()).when(mockAccount).findAccount(anyInt());
        Mockito.doReturn(getServiceUserAccountList()).when(mockServiceUserAccounts).findAllByServiceUserIdIs(anyInt());
        Mockito.doReturn(getServiceUserPreferencesList()).when(mockServiceUserPreferencesService).listServiceUserPreferences(anyInt());
        Mockito.doReturn(getServiceUserCommentList()).when(mockServiceUserCommentService).listServiceUserComments(anyInt());
        Mockito.doReturn("Decrypted").when(mockEncryptionHandler).decrypt(anyString());
        ServiceUserForCreationDto serviceUserForCreationDto = new ServiceUserForCreationDto();
        serviceUserForCreationDto.setCpr("12312");
        serviceUserForCreationDto.setAccounts(new ArrayList<>());
        serviceUserForCreationDto.setFirstName("Test first name");
        ServiceUserDto added = serviceUserService.addServiceUser(serviceUserForCreationDto);
        Assert.assertEquals("Decrypted", added.getCpr());
    }


    @Test
    public void testFindingServiceUserCprShouldMatch(){
        ServiceUserDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserDAO.class);
        AccountDAO mockAccount = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, AccountDAO.class);
        ServiceUserAccountsDAO mockServiceUserAccounts = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserAccountsDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, EncryptionHandler.class);
        ServiceUserPreferencesService mockServiceUserPreferencesService = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserPreferencesService.class);
        ServiceUserCommentService mockServiceUserCommentService = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserCommentService.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserDto()).when(mockServiceUser).findServiceUser(anyInt());
        Mockito.doReturn(getAccountDto()).when(mockAccount).findAccount(anyInt());
        Mockito.doReturn(getServiceUserAccountList()).when(mockServiceUserAccounts).findAllByServiceUserIdIs(anyInt());
        Mockito.doReturn(getServiceUserPreferencesList()).when(mockServiceUserPreferencesService).listServiceUserPreferences(anyInt());
        Mockito.doReturn(getServiceUserCommentList()).when(mockServiceUserCommentService).listServiceUserComments(anyInt());
        Mockito.doReturn("Decrypted").when(mockEncryptionHandler).decrypt(anyString());
        ServiceUserDto added = serviceUserService.findServiceUser(2);
        Assert.assertEquals("Decrypted", added.getCpr());
    }

    @Test
    public void testUpdatingServiceUserAccountsSizeShouldBeFive(){
        ServiceUserDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserDAO.class);
        AccountDAO mockAccount = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, AccountDAO.class);
        ServiceUserAccountsDAO mockServiceUserAccounts = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserAccountsDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, EncryptionHandler.class);
        ServiceUserPreferencesService mockServiceUserPreferencesService = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserPreferencesService.class);
        ServiceUserCommentService mockServiceUserCommentService = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserCommentService.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserDto()).when(mockServiceUser).updateServiceUser(any(ServiceUserEntity.class), anyList());
        Mockito.doReturn(getAccountDto()).when(mockAccount).findAccount(anyInt());
        Mockito.doReturn(getServiceUserAccountList()).when(mockServiceUserAccounts).findAllByServiceUserIdIs(anyInt());
        Mockito.doReturn(getServiceUserPreferencesList()).when(mockServiceUserPreferencesService).listServiceUserPreferences(anyInt());
        Mockito.doReturn(getServiceUserCommentList()).when(mockServiceUserCommentService).listServiceUserComments(anyInt());
        Mockito.doReturn("Decrypted").when(mockEncryptionHandler).decrypt(anyString());
        ServiceUserForUpdateDto serviceUserForUpdateDto = new ServiceUserForUpdateDto();
        serviceUserForUpdateDto.setCpr("12312");
        serviceUserForUpdateDto.setAccounts(new ArrayList<>());
        serviceUserForUpdateDto.setFirstName("Test first name");
        ServiceUserDto added = serviceUserService.updateServiceUser(serviceUserForUpdateDto);
        Assert.assertEquals(5, added.getAccounts().size());
    }

    @Test
    public void testUpdatingServiceUserCommentsSizeShouldBeFive(){
        ServiceUserDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserDAO.class);
        AccountDAO mockAccount = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, AccountDAO.class);
        ServiceUserAccountsDAO mockServiceUserAccounts = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserAccountsDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, EncryptionHandler.class);
        ServiceUserPreferencesService mockServiceUserPreferencesService = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserPreferencesService.class);
        ServiceUserCommentService mockServiceUserCommentService = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserCommentService.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserDto()).when(mockServiceUser).updateServiceUser(any(ServiceUserEntity.class), anyList());
        Mockito.doReturn(getAccountDto()).when(mockAccount).findAccount(anyInt());
        Mockito.doReturn(getServiceUserAccountList()).when(mockServiceUserAccounts).findAllByServiceUserIdIs(anyInt());
        Mockito.doReturn(getServiceUserPreferencesList()).when(mockServiceUserPreferencesService).listServiceUserPreferences(anyInt());
        Mockito.doReturn(getServiceUserCommentList()).when(mockServiceUserCommentService).listServiceUserComments(anyInt());
        Mockito.doReturn("Decrypted").when(mockEncryptionHandler).decrypt(anyString());
        ServiceUserForUpdateDto serviceUserForUpdateDto = new ServiceUserForUpdateDto();
        serviceUserForUpdateDto.setCpr("12312");
        serviceUserForUpdateDto.setAccounts(new ArrayList<>());
        serviceUserForUpdateDto.setFirstName("Test first name");
        ServiceUserDto added = serviceUserService.updateServiceUser(serviceUserForUpdateDto);
        Assert.assertEquals(5, added.getComments().size());
    }

    @Test
    public void testListingServiceUsersSizeOfListShouldBeFive(){
        ServiceUserDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserDAO.class);
        AccountDAO mockAccount = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, AccountDAO.class);
        ServiceUserAccountsDAO mockServiceUserAccounts = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserAccountsDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, EncryptionHandler.class);
        ServiceUserPreferencesService mockServiceUserPreferencesService = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserPreferencesService.class);
        ServiceUserCommentService mockServiceUserCommentService = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserCommentService.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserDtoList()).when(mockServiceUser).listServiceUsers(any());
        Mockito.doReturn(getAccountDto()).when(mockAccount).findAccount(anyInt());
        Mockito.doReturn(getServiceUserAccountList()).when(mockServiceUserAccounts).findAllByServiceUserIdIs(anyInt());
        Mockito.doReturn(getServiceUserPreferencesList()).when(mockServiceUserPreferencesService).listServiceUserPreferences(anyInt());
        Mockito.doReturn(getServiceUserCommentList()).when(mockServiceUserCommentService).listServiceUserComments(anyInt());
        Mockito.doReturn("Decrypted").when(mockEncryptionHandler).decrypt(anyString());
        ServiceUserForUpdateDto serviceUserForUpdateDto = new ServiceUserForUpdateDto();
        serviceUserForUpdateDto.setCpr("12312");
        serviceUserForUpdateDto.setAccounts(new ArrayList<>());
        serviceUserForUpdateDto.setFirstName("Test first name");
        List<ServiceUserDto> resultList = serviceUserService.listServiceUsers(PageRequest.of(0, 10)).toList();
        Assert.assertEquals(5, resultList.size());
    }

    @Test
    public void testDeletingServiceUserShouldBeTrue(){
        ServiceUserDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserService, ServiceUserDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceUser).deleteServiceUser(anyInt());
        Assert.assertTrue(serviceUserService.deleteServiceUser(1));
    }
}
