package API.Service.ServiceUserService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceUserStatusEntity;
import API.Repository.ServiceUser.ServiceUserStatusDAO;
import API.Services.ServiceUserService.ServiceUserStatusService;
import Shared.ForCreation.ServiceUserStatusForCreationDto;
import Shared.ForCreation.ServiceUserStatusForUpdateDto;
import Shared.ToReturn.ServiceUserStatusDto;
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

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class ServiceUserStatusServiceTest {

    @Autowired
    private ServiceUserStatusService serviceUserStatusService;

    private ServiceUserStatusDto getServiceUserStatus(){
        ServiceUserStatusDto serviceUserStatusDto = new ServiceUserStatusDto();
        serviceUserStatusDto.setStatus("Test status");
        return serviceUserStatusDto;
    }

    private List<ServiceUserStatusDto> getListServiceUserStatus(){
        List<ServiceUserStatusDto> returnList = new ArrayList<>();
        returnList.add(new ServiceUserStatusDto());
        returnList.add(new ServiceUserStatusDto());
        returnList.add(new ServiceUserStatusDto());
        returnList.add(new ServiceUserStatusDto());
        returnList.add(new ServiceUserStatusDto());
        return returnList;
    }

    @Test
    public void testAddingServiceUserStatusNameShouldMatch(){
        ServiceUserStatusDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserStatusService, ServiceUserStatusDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserStatus()).when(mockServiceUser).addServiceUserStatus(any(ServiceUserStatusEntity.class));
        Assert.assertEquals("Test status", serviceUserStatusService.addServiceUserStatus(new ServiceUserStatusForCreationDto()).getStatus());
    }

    @Test
    public void testUpdatingServiceUserStatusNameShouldMatch(){
        ServiceUserStatusDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserStatusService, ServiceUserStatusDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserStatus()).when(mockServiceUser).updateServiceUserStatus(any(ServiceUserStatusEntity.class));
        Assert.assertEquals("Test status", serviceUserStatusService.updateServiceUserStatus(new ServiceUserStatusForUpdateDto()).getStatus());
    }

    @Test
    public void testFindingServiceUserStatusNameShouldMatch(){
        ServiceUserStatusDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserStatusService, ServiceUserStatusDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserStatus()).when(mockServiceUser).findServiceUserStatus(anyInt());
        Assert.assertEquals("Test status", serviceUserStatusService.findServiceUserStatus(1).getStatus());
    }

    @Test
    public void testListingServiceUserStatusesSizeShouldBeFive(){
        ServiceUserStatusDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserStatusService, ServiceUserStatusDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getListServiceUserStatus()).when(mockServiceUser).listServiceUserStatuses(anyBoolean());
        Assert.assertEquals(5, serviceUserStatusService.listServiceUserStatuses(false).size());
    }

    @Test
    public void testDeletingServiceUserStatusShouldBeTrue(){
        ServiceUserStatusDAO mockServiceUser = SpringBeanMockUtil.mockFieldOnBean(serviceUserStatusService, ServiceUserStatusDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceUser).deleteServiceUserStatus(anyInt());
        Assert.assertTrue(serviceUserStatusService.deleteServiceUserStatus(1));
    }
}
