package API.Service.ServiceProviderService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderCommentEntity;
import API.Repository.ServiceProvider.ServiceProviderCommentDAO;
import API.Services.ServiceProviderService.ServiceProviderCommentService;
import Shared.ForCreation.ServiceProviderCommentForCreationDto;
import Shared.ForCreation.ServiceProviderCommentForUpdateDto;
import Shared.ToReturn.ServiceProviderCommentDto;
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
public class ServiceProviderCommentServiceTest {

    @Autowired
    private ServiceProviderCommentService serviceProviderCommentService;

    private ServiceProviderCommentDto getServiceProviderAbsenceDto() {
        ServiceProviderCommentDto serviceProviderCommentDto = new ServiceProviderCommentDto();
        serviceProviderCommentDto.setCommentText("Test comment text");
        return serviceProviderCommentDto;
    }

    private List<ServiceProviderCommentDto> getServiceProviderAbsenceDtoList(){
        List<ServiceProviderCommentDto> resultList = new ArrayList<>();
        resultList.add(new ServiceProviderCommentDto());
        resultList.add(new ServiceProviderCommentDto());
        resultList.add(new ServiceProviderCommentDto());
        resultList.add(new ServiceProviderCommentDto());
        resultList.add(new ServiceProviderCommentDto());
        return resultList;
    }

    @Test
    public void testAddServiceProviderComment(){
        ServiceProviderCommentDAO mockServiceProviderComment = SpringBeanMockUtil.mockFieldOnBean(serviceProviderCommentService, ServiceProviderCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDto()).when(mockServiceProviderComment).addServiceProviderComment(any(ServiceProviderCommentEntity.class));
        Assert.assertEquals("Test comment text", serviceProviderCommentService.addServiceProviderComment(new ServiceProviderCommentForCreationDto()).getCommentText());
    }

    @Test
    public void testUpdateServiceProviderComment(){
        ServiceProviderCommentDAO mockServiceProviderComment = SpringBeanMockUtil.mockFieldOnBean(serviceProviderCommentService, ServiceProviderCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDto()).when(mockServiceProviderComment).updateServiceProviderComment(any(ServiceProviderCommentEntity.class));
        Assert.assertEquals("Test comment text", serviceProviderCommentService.updateServiceProviderComment(new ServiceProviderCommentForUpdateDto()).getCommentText());

    }

    @Test
    public void testFindServiceProviderComment(){
        ServiceProviderCommentDAO mockServiceProviderComment = SpringBeanMockUtil.mockFieldOnBean(serviceProviderCommentService, ServiceProviderCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDto()).when(mockServiceProviderComment).findServiceProviderComment(anyInt());
        Assert.assertEquals("Test comment text", serviceProviderCommentService.findServiceProviderComment(2).getCommentText());

    }

    @Test
    public void testDeleteServiceProviderComment(){
        ServiceProviderCommentDAO mockServiceProviderComment = SpringBeanMockUtil.mockFieldOnBean(serviceProviderCommentService, ServiceProviderCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceProviderComment).deleteServiceProviderComment(anyInt());
        Assert.assertTrue(serviceProviderCommentService.deleteServiceProviderComment(2));

    }

    @Test
    public void testFindServiceProviderComments(){
        ServiceProviderCommentDAO mockServiceProviderComment = SpringBeanMockUtil.mockFieldOnBean(serviceProviderCommentService, ServiceProviderCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceProviderAbsenceDtoList()).when(mockServiceProviderComment).findServiceProviderComments(anyInt());
        Assert.assertEquals(5, serviceProviderCommentService.findServiceProviderComments(2).size());
    }
}
