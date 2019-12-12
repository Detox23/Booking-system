package API.Service.ServiceUserService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceUserCommentEntity;
import API.Repository.ServiceUser.ServiceUserCommentDAO;
import API.Services.ServiceUserService.ServiceUserCommentService;
import Shared.ForCreation.ServiceUserCommentForCreationDto;
import Shared.ForCreation.ServiceUserCommentForUpdateDto;
import Shared.ToReturn.ServiceUserCommentDto;
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
public class ServiceUserCommentServiceTest {

    @Autowired
    private ServiceUserCommentService serviceUserCommentService;

    private ServiceUserCommentDto getServiceUserComment(){
        ServiceUserCommentDto serviceUserCommentDto= new ServiceUserCommentDto();
        serviceUserCommentDto.setCommentText("Test comment text");
        return serviceUserCommentDto;
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
    public void testAddingServiceUserCommentNameShouldMatch(){
        ServiceUserCommentDAO mockServiceUserComment = SpringBeanMockUtil.mockFieldOnBean(serviceUserCommentService, ServiceUserCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserComment()).when(mockServiceUserComment).addServiceUserComment(any(ServiceUserCommentEntity.class));
        Assert.assertEquals("Test comment text", serviceUserCommentService.addServiceUserComment(new ServiceUserCommentForCreationDto()).getCommentText());
    }

    @Test
    public void testDeletingServiceUserCommentShouldBeTrue(){
        ServiceUserCommentDAO mockServiceUserComment = SpringBeanMockUtil.mockFieldOnBean(serviceUserCommentService, ServiceUserCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceUserComment).deleteServiceUserComment(anyInt());
        Assert.assertTrue(serviceUserCommentService.deleteServiceUserComment(2));
    }

    @Test
    public void testUpdatingServiceUserCommentNameShouldMatch(){
        ServiceUserCommentDAO mockServiceUserComment = SpringBeanMockUtil.mockFieldOnBean(serviceUserCommentService, ServiceUserCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserComment()).when(mockServiceUserComment).updateServiceUserComment(any(ServiceUserCommentEntity.class));
        Assert.assertEquals("Test comment text", serviceUserCommentService.updateServiceUserComment(new ServiceUserCommentForUpdateDto()).getCommentText());
    }

    @Test
    public void testListingServiceUserCommentSizeOfListShouldBeFive(){
        ServiceUserCommentDAO mockServiceUserComment = SpringBeanMockUtil.mockFieldOnBean(serviceUserCommentService, ServiceUserCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserCommentList()).when(mockServiceUserComment).listServiceUserComments(anyInt());
        Assert.assertEquals(5, serviceUserCommentService.listServiceUserComments(2).size());
    }

    @Test
    public void testFindingServiceUserCommentNameShouldMatch(){
        ServiceUserCommentDAO mockServiceUserComment = SpringBeanMockUtil.mockFieldOnBean(serviceUserCommentService, ServiceUserCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getServiceUserComment()).when(mockServiceUserComment).findServiceUserComment(anyInt());
        Assert.assertEquals("Test comment text", serviceUserCommentService.findServiceUserComment(2).getCommentText());
    }

}
