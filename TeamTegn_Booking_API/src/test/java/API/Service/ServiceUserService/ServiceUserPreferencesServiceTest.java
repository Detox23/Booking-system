package API.Service.ServiceUserService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceUserPreferencesEntity;
import API.Repository.ServiceUser.ServiceUserPreferencesDAO;
import API.Services.ServiceProviderService.ServiceProviderService;
import API.Services.ServiceUserService.ServiceUserPreferencesService;
import Shared.ForCreation.ServiceUserPreferencesForCreationDto;
import Shared.ForCreation.ServiceUserPreferencesForUpdateDto;
import Shared.ToReturn.ServiceProviderDto;
import Shared.ToReturn.ServiceUserPreferencesDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class ServiceUserPreferencesServiceTest {

    @Autowired
    private ServiceUserPreferencesService serviceUserPreferencesService;

    private ServiceUserPreferencesDto getServiceUserPreferencesDto() {
        ServiceUserPreferencesDto serviceUserPreferences = new ServiceUserPreferencesDto();
        serviceUserPreferences.setServiceUserId(1);
        serviceUserPreferences.setServiceProviderId(1);
        serviceUserPreferences.setRating(2);
        return serviceUserPreferences;
    }

    private List<ServiceUserPreferencesDto> getServiceUserPreferencesDtoList() {
        List<ServiceUserPreferencesDto> returnList = new ArrayList<>();
        returnList.add(new ServiceUserPreferencesDto());
        returnList.add(new ServiceUserPreferencesDto());
        returnList.add(new ServiceUserPreferencesDto());
        returnList.add(new ServiceUserPreferencesDto());
        returnList.add(new ServiceUserPreferencesDto());
        return returnList;
    }

    private ServiceProviderDto getServiceProviderDto() {
        ServiceProviderDto serviceProviderDto = new ServiceProviderDto();
        serviceProviderDto.setFirstName("Name");
        return serviceProviderDto;
    }

    @Test
    public void testAddServiceUserPreferencesServiceUserIdShouldMatch() {
        ServiceUserPreferencesDAO mockServiceUserPreference = SpringBeanMockUtil.mockFieldOnBean(serviceUserPreferencesService, ServiceUserPreferencesDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserPreferencesDto()).when(mockServiceUserPreference).addServiceUserPreference(anyInt(), any(ServiceUserPreferencesEntity.class));
        Assert.assertEquals(1, serviceUserPreferencesService.addServiceUserPreference(1, new ServiceUserPreferencesForCreationDto()).getServiceUserId());
    }

    @Test
    public void testDeletingServiceUserPreferencesShouldBeTrue() {
        ServiceUserPreferencesDAO mockServiceUserPreference = SpringBeanMockUtil.mockFieldOnBean(serviceUserPreferencesService, ServiceUserPreferencesDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceUserPreference).deleteServiceUserPreference(anyInt());
        Assert.assertTrue(serviceUserPreferencesService.deleteServiceUserPreference(1));
    }

    @Test
    public void testFindingServiceUserPreferencesServiceUserServiceProviderNameShouldMatch() {
        ServiceUserPreferencesDAO mockServiceUserPreference = SpringBeanMockUtil.mockFieldOnBean(serviceUserPreferencesService, ServiceUserPreferencesDAO.class);
        ServiceProviderService mockServiceUserService = SpringBeanMockUtil.mockFieldOnBean(serviceUserPreferencesService, ServiceProviderService.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderDto()).when(mockServiceUserService).findServiceProvider(anyInt());
        Mockito.doReturn(getServiceUserPreferencesDto()).when(mockServiceUserPreference).findServiceProviderAndUser(anyInt(), anyInt());
        Assert.assertEquals(1, serviceUserPreferencesService.findServiceProviderAndUser(1, 1).getServiceUserId());
    }

    @Test
    public void testUpdatingServiceUserPreferencesServiceUserIdShouldMatch(){
        ServiceUserPreferencesDAO mockServiceUserPreference = SpringBeanMockUtil.mockFieldOnBean(serviceUserPreferencesService, ServiceUserPreferencesDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserPreferencesDto()).when(mockServiceUserPreference).updateServiceUserPreference(any(ServiceUserPreferencesEntity.class));
        Assert.assertEquals(1, serviceUserPreferencesService.updateServiceUserPreference(new ServiceUserPreferencesForUpdateDto()).getServiceUserId());
    }

    @Test
    public void testListingServiceUserPreferencesSizeOfListShouldBeFive(){
        ServiceUserPreferencesDAO mockServiceUserPreference = SpringBeanMockUtil.mockFieldOnBean(serviceUserPreferencesService, ServiceUserPreferencesDAO.class);
        ServiceProviderService mockServiceUserService = SpringBeanMockUtil.mockFieldOnBean(serviceUserPreferencesService, ServiceProviderService.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderDto()).when(mockServiceUserService).findServiceProvider(anyInt());
        Mockito.doReturn(getServiceUserPreferencesDtoList()).when(mockServiceUserPreference).listServiceUserPreferences(anyInt());
        Assert.assertEquals(5, serviceUserPreferencesService.listServiceUserPreferences(1).size());
    }
}
