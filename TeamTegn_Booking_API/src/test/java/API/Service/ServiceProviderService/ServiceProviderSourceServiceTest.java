package API.Service.ServiceProviderService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderSourceEntity;
import API.Repository.ServiceProvider.ServiceProviderSourceDAO;
import API.Services.ServiceProviderService.ServiceProviderSourceService;
import Shared.ForCreation.ServiceProviderSourceForCreationDto;
import Shared.ForCreation.ServiceProviderSourceForUpdateDto;
import Shared.ToReturn.ServiceProviderSourceDto;
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
public class ServiceProviderSourceServiceTest {

    @Autowired
    private ServiceProviderSourceService serviceProviderSourceService;

    private ServiceProviderSourceDto getServiceProviderSource(){
        ServiceProviderSourceDto serviceProviderSource = new ServiceProviderSourceDto();
        serviceProviderSource.setProviderSource("Test provider");
        return serviceProviderSource;
    }

    private List<ServiceProviderSourceDto> getServiceProviderSourceList(){
        List<ServiceProviderSourceDto> returnList = new ArrayList<>();
        returnList.add(new ServiceProviderSourceDto());
        returnList.add(new ServiceProviderSourceDto());
        returnList.add(new ServiceProviderSourceDto());
        returnList.add(new ServiceProviderSourceDto());
        returnList.add(new ServiceProviderSourceDto());
        return returnList;
    }

    @Test
    public void testAddingServiceProviderSourceNameShouldEquals(){
        ServiceProviderSourceDAO mockServiceProviderSource = SpringBeanMockUtil.mockFieldOnBean(serviceProviderSourceService, ServiceProviderSourceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderSource()).when(mockServiceProviderSource).addServiceProviderSource(any(ServiceProviderSourceEntity.class));
        Assert.assertEquals("Test provider", serviceProviderSourceService.addServiceProviderSource(new ServiceProviderSourceForCreationDto()).getProviderSource());
    }

    @Test
    public void testUpdatingServiceProviderSourceNameShouldEquals(){
        ServiceProviderSourceDAO mockServiceProviderSource = SpringBeanMockUtil.mockFieldOnBean(serviceProviderSourceService, ServiceProviderSourceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderSource()).when(mockServiceProviderSource).updateServiceProviderSource(any(ServiceProviderSourceEntity.class));
        Assert.assertEquals("Test provider", serviceProviderSourceService.updateServiceProviderSource(new ServiceProviderSourceForUpdateDto()).getProviderSource());
    }

    @Test
    public void testListingServiceProviderSourceSizeShouldBeFive(){
        ServiceProviderSourceDAO mockServiceProviderSource = SpringBeanMockUtil.mockFieldOnBean(serviceProviderSourceService, ServiceProviderSourceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderSourceList()).when(mockServiceProviderSource).listServiceProviderSources(anyBoolean());
        Assert.assertEquals(5, serviceProviderSourceService.listServiceProviderSources(true).size());
    }

    @Test
    public void testDeletingServiceProviderSourceShouldBeTrue(){
        ServiceProviderSourceDAO mockServiceProviderSource = SpringBeanMockUtil.mockFieldOnBean(serviceProviderSourceService, ServiceProviderSourceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceProviderSource).deleteServiceProviderSource(anyInt());
        Assert.assertTrue(serviceProviderSourceService.deleteServiceProviderSource(1));
    }

    @Test
    public void testFindingServiceProviderSourceNameShouldEquals(){
        ServiceProviderSourceDAO mockServiceProviderSource = SpringBeanMockUtil.mockFieldOnBean(serviceProviderSourceService, ServiceProviderSourceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderSource()).when(mockServiceProviderSource).findServiceProviderSource(anyInt());
        Assert.assertEquals("Test provider", serviceProviderSourceService.findServiceProviderSource(3).getProviderSource());
    }
}
