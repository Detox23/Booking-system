package API.Service.ServiceProviderService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderAbsenceEntity;
import API.Repository.ServiceProvider.ServiceProviderAbsenceDAO;
import API.Services.ServiceProviderService.ServiceProviderAbsenceService;
import Shared.ForCreation.ServiceProviderAbsenceForCreationDto;
import Shared.ToReturn.ServiceProviderAbsenceDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class  ServiceProviderAbsenceServiceTest {

    @Autowired
    private ServiceProviderAbsenceService serviceProviderAbsenceService;

    private ServiceProviderAbsenceDto getServiceProviderAbsenceDto() {
        ServiceProviderAbsenceDto serviceProviderAbsenceDto = new ServiceProviderAbsenceDto();
        serviceProviderAbsenceDto.setAbsenceReason("Test absence reason");
        return serviceProviderAbsenceDto;
    }

    private List<ServiceProviderAbsenceDto> getServiceProviderAbsenceDtoList(){
        List<ServiceProviderAbsenceDto> resultList = new ArrayList<>();
        resultList.add(new ServiceProviderAbsenceDto());
        resultList.add(new ServiceProviderAbsenceDto());
        resultList.add(new ServiceProviderAbsenceDto());
        resultList.add(new ServiceProviderAbsenceDto());
        resultList.add(new ServiceProviderAbsenceDto());
        return resultList;
    }

    @Test
    public void testFindServiceProviderAbsencesForServiceProvider(){
        ServiceProviderAbsenceDAO mockServiceProviderAbsence = SpringBeanMockUtil.mockFieldOnBean(serviceProviderAbsenceService, ServiceProviderAbsenceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDtoList()).when(mockServiceProviderAbsence).findServiceProviderAbsencesForServiceProvider(anyInt());
        Assert.assertEquals(5, serviceProviderAbsenceService.findServiceProviderAbsencesForServiceProvider(2).size());
    }

    @Test
    public void testFindServiceProviderAbsencesForServiceProviderInPeriod(){
        ServiceProviderAbsenceDAO mockServiceProviderAbsence = SpringBeanMockUtil.mockFieldOnBean(serviceProviderAbsenceService, ServiceProviderAbsenceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDtoList()).when(mockServiceProviderAbsence).findServiceProviderAbsencesForServiceProviderInPeriod(any(), any(), anyInt());
        Assert.assertEquals(5, serviceProviderAbsenceService.findServiceProviderAbsencesForServiceProviderInPeriod(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), 1).size());
    }

    @Test
    public void testFindServiceProviderAbsencesInPeriod(){
        ServiceProviderAbsenceDAO mockServiceProviderAbsence = SpringBeanMockUtil.mockFieldOnBean(serviceProviderAbsenceService, ServiceProviderAbsenceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDtoList()).when(mockServiceProviderAbsence).findServiceProviderAbsencesInPeriod(any(), any());
        Assert.assertEquals(5, serviceProviderAbsenceService.findServiceProviderAbsencesInPeriod(new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())).size());
    }

    @Test
    public void testListAllServiceProviderAbsences(){
        ServiceProviderAbsenceDAO mockServiceProviderAbsence = SpringBeanMockUtil.mockFieldOnBean(serviceProviderAbsenceService, ServiceProviderAbsenceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDtoList()).when(mockServiceProviderAbsence).listAllServiceProviderAbsences();
        Assert.assertEquals(5, serviceProviderAbsenceService.listAllServiceProviderAbsences().size());
    }

    @Test
    public void testDeleteServiceProviderAbsence(){
        ServiceProviderAbsenceDAO mockServiceProviderAbsence = SpringBeanMockUtil.mockFieldOnBean(serviceProviderAbsenceService, ServiceProviderAbsenceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceProviderAbsence).deleteServiceProviderAbsence(anyInt());
        Assert.assertTrue(serviceProviderAbsenceService.deleteServiceProviderAbsence(2));
    }

    @Test
    public void testAddServiceProviderAbsence(){
        ServiceProviderAbsenceDAO mockServiceProviderAbsence = SpringBeanMockUtil.mockFieldOnBean(serviceProviderAbsenceService, ServiceProviderAbsenceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDto()).when(mockServiceProviderAbsence).addServiceProviderAbsence(any(ServiceProviderAbsenceEntity.class));
        Assert.assertEquals("Test absence reason", serviceProviderAbsenceService.addServiceProviderAbsence(new ServiceProviderAbsenceForCreationDto()).getAbsenceReason());
    }

    @Test
    public void testFindServiceProviderAbsencesInTime(){
        ServiceProviderAbsenceDAO mockServiceProviderAbsence = SpringBeanMockUtil.mockFieldOnBean(serviceProviderAbsenceService, ServiceProviderAbsenceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDtoList()).when(mockServiceProviderAbsence).findServiceProviderAbsencesInTime(any(), any());
        Assert.assertEquals(5, serviceProviderAbsenceService.findServiceProviderAbsencesInTime(new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis())).size());
    }

    @Test
    public void testFindServiceProviderAbsencesForServiceProviderInTime(){
        ServiceProviderAbsenceDAO mockServiceProviderAbsence = SpringBeanMockUtil.mockFieldOnBean(serviceProviderAbsenceService, ServiceProviderAbsenceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDtoList()).when(mockServiceProviderAbsence).findServiceProviderAbsencesForServiceProviderInTime(any(), any(), anyInt());
        Assert.assertEquals(5, serviceProviderAbsenceService.findServiceProviderAbsencesForServiceProviderInTime(new Time(System.currentTimeMillis()), new Time(System.currentTimeMillis()), 1).size());
    }

}
