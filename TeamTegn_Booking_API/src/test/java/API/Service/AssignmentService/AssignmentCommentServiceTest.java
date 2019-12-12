package API.Service.AssignmentService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentCommentEntity;
import API.Repository.Assignment.AssignmentCommentDAO;
import API.Services.AssignmentService.AssignmentCommentService;
import Shared.ForCreation.AssignmentCommentForCreationDto;
import Shared.ForCreation.AssignmentCommentForUpdateDto;
import Shared.ToReturn.AssignmentCommentDto;
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
public class AssignmentCommentServiceTest {

    @Autowired
    private AssignmentCommentService assignmentCommentService;

    private AssignmentCommentDto getAssignmentComment(){
        AssignmentCommentDto assignmentComment = new AssignmentCommentDto();
        assignmentComment.setCommentText("Test text");
        return assignmentComment;
    }

    private List<AssignmentCommentDto> getAssignmentCommentList(){
        List<AssignmentCommentDto> returnList = new ArrayList<>();
        returnList.add(new AssignmentCommentDto());
        returnList.add(new AssignmentCommentDto());
        returnList.add(new AssignmentCommentDto());
        returnList.add(new AssignmentCommentDto());
        returnList.add(new AssignmentCommentDto());
        return returnList;
    }

    @Test
    public void testAddingAssignmentComment(){
        AssignmentCommentDAO mockAssignmentComment = SpringBeanMockUtil.mockFieldOnBean(assignmentCommentService, AssignmentCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentComment()).when(mockAssignmentComment).addAssignmentComment(any(AssignmentCommentEntity.class));
        Assert.assertEquals("Test text", assignmentCommentService.addAssignmentComment(new AssignmentCommentForCreationDto()).getCommentText());
    }

    @Test
    public void testFindingAssignmentComment(){
        AssignmentCommentDAO mockAssignmentComment = SpringBeanMockUtil.mockFieldOnBean(assignmentCommentService, AssignmentCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentComment()).when(mockAssignmentComment).findAssignmentComment(anyInt());
        Assert.assertEquals("Test text", assignmentCommentService.findAssignmentComment(2).getCommentText());
    }

    @Test
    public void testUpdatingAssignmentComment(){
        AssignmentCommentDAO mockAssignmentComment = SpringBeanMockUtil.mockFieldOnBean(assignmentCommentService, AssignmentCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentComment()).when(mockAssignmentComment).updateAssignmentComment(any(AssignmentCommentEntity.class));
        Assert.assertEquals("Test text", assignmentCommentService.updateAssignmentComment(new AssignmentCommentForUpdateDto()).getCommentText());
    }

    @Test
    public void testListingAssignmentComment(){
        AssignmentCommentDAO mockAssignmentComment = SpringBeanMockUtil.mockFieldOnBean(assignmentCommentService, AssignmentCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentCommentList()).when(mockAssignmentComment).listAssignmentComments(anyInt());
        Assert.assertEquals(5, assignmentCommentService.listAssignmentComments(2).size());
    }

    @Test
    public void testDeletingAssignmentComment(){
        AssignmentCommentDAO mockAssignmentComment = SpringBeanMockUtil.mockFieldOnBean(assignmentCommentService, AssignmentCommentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockAssignmentComment).deleteAssignmentComment(anyInt());
        Assert.assertTrue(assignmentCommentService.deleteAssignmentComment(2));
    }
}
