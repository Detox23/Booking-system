package API.Service.AssignmentService;


import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentImportanceEntity;
import API.Repository.Assignment.AssignmentImportanceDAO;
import API.Services.AssignmentService.AssignmentImportanceService;
import Shared.ForCreation.AssignmentImportanceForCreationDto;
import Shared.ForCreation.AssignmentImportanceForUpdateDto;
import Shared.ToReturn.AssignmentImportanceDto;
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
public class AssignmentImportanceServiceTest {
    @Autowired
    private AssignmentImportanceService assignmentImportanceService;

    private AssignmentImportanceDto getAssignmentImportance(){
        AssignmentImportanceDto assignmentImportance = new AssignmentImportanceDto();
        assignmentImportance.setImportanceName("Test importance");
        return assignmentImportance;
    }

    private List<AssignmentImportanceDto> getAssignmentImportanceList(){
        List<AssignmentImportanceDto> returnList = new ArrayList<>();
        returnList.add(new AssignmentImportanceDto());
        returnList.add(new AssignmentImportanceDto());
        returnList.add(new AssignmentImportanceDto());
        returnList.add(new AssignmentImportanceDto());
        returnList.add(new AssignmentImportanceDto());
        return returnList;
    }

    @Test
    public void testAddingAssignmentComment(){
        AssignmentImportanceDAO mockAssignmentImportance = SpringBeanMockUtil.mockFieldOnBean(assignmentImportanceService, AssignmentImportanceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentImportance()).when(mockAssignmentImportance).addAssignmentImportance(any(AssignmentImportanceEntity.class));
        Assert.assertEquals("Test importance", assignmentImportanceService.addAssignmentImportance(new AssignmentImportanceForCreationDto()).getImportanceName());
    }

    @Test
    public void testFindingAssignmentComment(){
        AssignmentImportanceDAO mockAssignmentImportance = SpringBeanMockUtil.mockFieldOnBean(assignmentImportanceService, AssignmentImportanceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentImportance()).when(mockAssignmentImportance).findAssignmentImportance(anyInt());
        Assert.assertEquals("Test importance", assignmentImportanceService.findAssignmentImportance(2).getImportanceName());
    }

    @Test
    public void testUpdatingAssignmentComment(){
        AssignmentImportanceDAO mockAssignmentImportance = SpringBeanMockUtil.mockFieldOnBean(assignmentImportanceService, AssignmentImportanceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentImportance()).when(mockAssignmentImportance).updateAssignmentImportance(any(AssignmentImportanceEntity.class));
        Assert.assertEquals("Test importance", assignmentImportanceService.updateAssignmentImportance(new AssignmentImportanceForUpdateDto()).getImportanceName());
    }

    @Test
    public void testListingAssignmentComment(){
        AssignmentImportanceDAO mockAssignmentImportance = SpringBeanMockUtil.mockFieldOnBean(assignmentImportanceService, AssignmentImportanceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentImportanceList()).when(mockAssignmentImportance).listAssignmentImportance(anyBoolean());
        Assert.assertEquals(5, assignmentImportanceService.listAssignmentImportance(true).size());
    }

    @Test
    public void testDeletingAssignmentComment(){
        AssignmentImportanceDAO mockAssignmentImportance = SpringBeanMockUtil.mockFieldOnBean(assignmentImportanceService, AssignmentImportanceDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockAssignmentImportance).deleteAssignmentImportance(anyInt());
        Assert.assertTrue(assignmentImportanceService.deleteAssignmentImportance(2));
    }
}
