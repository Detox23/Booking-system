package API.Service.ServiceProviderService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderTypeEntity;
import API.Repository.ServiceProvider.ServiceProviderTypeDAO;
import API.Services.ServiceProviderService.ServiceProviderTypeService;
import Shared.ForCreation.ServiceProviderTypeForCreationDto;
import Shared.ForCreation.ServiceProviderTypeForUpdateDto;
import Shared.ToReturn.ServiceProviderTypeDto;
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
public class ServiceProviderTypeServiceTest {

    @Autowired
    private ServiceProviderTypeService serviceProviderTypeService;

    private ServiceProviderTypeDto getServiceProviderType(){
        ServiceProviderTypeDto serviceProviderType = new ServiceProviderTypeDto();
        serviceProviderType.setProviderType("Test type");
        return serviceProviderType;
    }

    private List<ServiceProviderTypeDto> getServiceProviderTypeList(){
        List<ServiceProviderTypeDto> returnList = new ArrayList<>();
        returnList.add(new ServiceProviderTypeDto());
        returnList.add(new ServiceProviderTypeDto());
        returnList.add(new ServiceProviderTypeDto());
        returnList.add(new ServiceProviderTypeDto());
        returnList.add(new ServiceProviderTypeDto());
        return returnList;
    }

    @Test
    public void testAddingServiceProviderTypeNameShouldEquals(){
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderTypeService, ServiceProviderTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderType()).when(mockServiceProviderType).addServiceProviderType(any(ServiceProviderTypeEntity.class));
        Assert.assertEquals("Test type", serviceProviderTypeService.addServiceProviderType(new ServiceProviderTypeForCreationDto()).getProviderType());

    }

    @Test
    public void testUpdatingServiceProviderTypeNameShouldEquals(){
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderTypeService, ServiceProviderTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderType()).when(mockServiceProviderType).updateServiceProviderType(any(ServiceProviderTypeEntity.class));
        Assert.assertEquals("Test type", serviceProviderTypeService.updateServiceProviderType(new ServiceProviderTypeForUpdateDto()).getProviderType());

    }

    @Test
    public void testFindingServiceProviderTypeNameShouldEquals(){
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderTypeService, ServiceProviderTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderType()).when(mockServiceProviderType).findServiceProviderType(anyInt());
        Assert.assertEquals("Test type", serviceProviderTypeService.findServiceProviderType(1).getProviderType());

    }

    @Test
    public void testListingServiceProviderTypeNameSizeShouldBeFive(){
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderTypeService, ServiceProviderTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderTypeList()).when(mockServiceProviderType).listServiceProviderTypes(anyBoolean());
        Assert.assertEquals(5, serviceProviderTypeService.listServiceProviderTypes(true).size());

    }

    @Test
    public void testDeletingServiceProviderTypeShouldBeTrue(){
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderTypeService, ServiceProviderTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceProviderType).deleteServiceProviderType(anyInt());
        Assert.assertTrue(serviceProviderTypeService.deleteServiceProviderType(1));
    }
}
