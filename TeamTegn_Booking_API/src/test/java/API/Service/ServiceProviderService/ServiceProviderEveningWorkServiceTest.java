package API.Service.ServiceProviderService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderEveningWorkEntity;
import API.Repository.ServiceProvider.ServiceProvider_EveningWorkDAO;
import API.Services.ServiceProviderService.ServiceProviderEveningWorkService;
import Shared.ForCreation.ServiceProviderEveningWorkForUpdateDto;
import Shared.ToReturn.ServiceProviderEveningWorkDto;
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
public class ServiceProviderEveningWorkServiceTest {

    @Autowired
    private ServiceProviderEveningWorkService serviceProviderEveningWorkService;

    private ServiceProviderEveningWorkDto getServiceProviderCompetency() {
        ServiceProviderEveningWorkDto serviceProviderEveningWorkDto = new ServiceProviderEveningWorkDto();
        serviceProviderEveningWorkDto.setWeekDay("Saturday");
        return serviceProviderEveningWorkDto;
    }

    private List<ServiceProviderEveningWorkDto> getServiceProviderCompetenciesList(){
        List<ServiceProviderEveningWorkDto> resultList = new ArrayList<>();
        resultList.add(new ServiceProviderEveningWorkDto());
        resultList.add(new ServiceProviderEveningWorkDto());
        resultList.add(new ServiceProviderEveningWorkDto());
        resultList.add(new ServiceProviderEveningWorkDto());
        resultList.add(new ServiceProviderEveningWorkDto());
        return resultList;
    }

    @Test
    public void testAddOrUpdateServiceProviderEveningWork(){
        ServiceProvider_EveningWorkDAO mockServiceProviderEveningWork = SpringBeanMockUtil.mockFieldOnBean(serviceProviderEveningWorkService, ServiceProvider_EveningWorkDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderCompetency()).when(mockServiceProviderEveningWork).addOrUpdateServiceProviderEveningWork(any(ServiceProviderEveningWorkEntity.class));
        Assert.assertEquals("Saturday", serviceProviderEveningWorkService.addOrUpdateServiceProviderEveningWork(new ServiceProviderEveningWorkForUpdateDto()).getWeekDay());
    }

    @Test
    public void testListServiceProviderEveningWorks(){
        ServiceProvider_EveningWorkDAO mockServiceProviderEveningWork = SpringBeanMockUtil.mockFieldOnBean(serviceProviderEveningWorkService, ServiceProvider_EveningWorkDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderCompetenciesList()).when(mockServiceProviderEveningWork).listServiceProviderEveningWork(anyInt());
        Assert.assertEquals(5, serviceProviderEveningWorkService.listServiceProviderEveningWork(2).size());
    }

    @Test
    public void testFindServiceProviderEveningWork(){
        ServiceProvider_EveningWorkDAO mockServiceProviderEveningWork = SpringBeanMockUtil.mockFieldOnBean(serviceProviderEveningWorkService, ServiceProvider_EveningWorkDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderCompetency()).when(mockServiceProviderEveningWork).getServiceProviderEveningWorkForSpecificDay(anyString(), anyInt());
        Assert.assertEquals("Saturday", serviceProviderEveningWorkService.getServiceProviderEveningWorkForSpecificDay("Saturday", 2).getWeekDay());
    }

}
