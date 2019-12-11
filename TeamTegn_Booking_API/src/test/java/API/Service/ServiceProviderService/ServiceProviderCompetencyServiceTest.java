package API.Service.ServiceProviderService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderCompetencyEntity;
import API.Repository.ServiceProvider.ServiceProviderCompetencyDAO;
import API.Services.ServiceProviderService.ServiceProviderCompetencyService;
import Shared.ForCreation.ServiceProviderCompetencyForCreationDto;
import Shared.ForCreation.ServiceProviderCompetencyForUpdateDto;
import Shared.ToReturn.ServiceProviderCompetencyDto;
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
public class ServiceProviderCompetencyServiceTest {

    @Autowired
    private ServiceProviderCompetencyService serviceProviderCompetencyService;

    private ServiceProviderCompetencyDto getServiceProviderCompetency() {
        ServiceProviderCompetencyDto serviceProviderCompetencyDto = new ServiceProviderCompetencyDto();
        serviceProviderCompetencyDto.setCompetency("Test competency");
        return serviceProviderCompetencyDto;
    }

    private List<ServiceProviderCompetencyDto> getServiceProviderCompetenciesList(){
        List<ServiceProviderCompetencyDto> resultList = new ArrayList<>();
        resultList.add(new ServiceProviderCompetencyDto());
        resultList.add(new ServiceProviderCompetencyDto());
        resultList.add(new ServiceProviderCompetencyDto());
        resultList.add(new ServiceProviderCompetencyDto());
        resultList.add(new ServiceProviderCompetencyDto());
        return resultList;
    }

    @Test
    public void testAddServiceProviderCompetency(){
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderCompetencyService, ServiceProviderCompetencyDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderCompetency()).when(mockServiceProviderCompetency).addServiceProviderCompetency(any(ServiceProviderCompetencyEntity.class));
        Assert.assertEquals("Test competency", serviceProviderCompetencyService.addServiceProviderCompetency(new ServiceProviderCompetencyForCreationDto()).getCompetency());
    }

    @Test
    public void testUpdateServiceProviderCompetency(){
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderCompetencyService, ServiceProviderCompetencyDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderCompetency()).when(mockServiceProviderCompetency).updateServiceProviderCompetency(any(ServiceProviderCompetencyEntity.class));
        Assert.assertEquals("Test competency", serviceProviderCompetencyService.updateServiceProviderCompetency(new ServiceProviderCompetencyForUpdateDto()).getCompetency());

    }

    @Test
    public void testFindServiceProviderCompetency(){
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderCompetencyService, ServiceProviderCompetencyDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderCompetency()).when(mockServiceProviderCompetency).findServiceProviderCompetency(anyInt());
        Assert.assertEquals("Test competency", serviceProviderCompetencyService.findServiceProviderCompetency(2).getCompetency());

    }

    @Test
    public void testDeleteServiceProviderCompetency(){
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderCompetencyService, ServiceProviderCompetencyDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceProviderCompetency).deleteServiceProviderCompetency(anyInt());
        Assert.assertTrue(serviceProviderCompetencyService.deleteServiceProviderCompetency(2));

    }

    @Test
    public void testFindServiceProviderCompetencies(){
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderCompetencyService, ServiceProviderCompetencyDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderCompetenciesList()).when(mockServiceProviderCompetency).listServiceProviderCompetencies(anyBoolean());
        Assert.assertEquals(5, serviceProviderCompetencyService.listServiceProviderCompetencies(true).size());
    }
}
