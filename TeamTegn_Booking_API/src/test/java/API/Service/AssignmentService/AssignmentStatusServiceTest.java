package API.Service.AssignmentService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentStatusEntity;
import API.Repository.Assignment.AssignmentStatusDAO;
import API.Services.AssignmentService.AssignmentStatusService;
import Shared.ForCreation.AssignmentStatusForCreationDto;
import Shared.ForCreation.AssignmentStatusForUpdateDto;
import Shared.ToReturn.AssignmentStatusDto;
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
public class AssignmentStatusServiceTest {

    @Autowired
    private AssignmentStatusService assignmentStatusService;

    private AssignmentStatusDto getAssignmentStatusDto() {
        AssignmentStatusDto assignmentStatusDto = new AssignmentStatusDto();
        assignmentStatusDto.setAssignmentStatusName("Test name");
        return assignmentStatusDto;
    }

    private List<AssignmentStatusDto> getAssignmentStatusDtoList(){
        List<AssignmentStatusDto> resultList = new ArrayList<>();
        resultList.add(new AssignmentStatusDto());
        resultList.add(new AssignmentStatusDto());
        resultList.add(new AssignmentStatusDto());
        resultList.add(new AssignmentStatusDto());
        resultList.add(new AssignmentStatusDto());
        return resultList;
    }

    @Test
    public void testAddingAssignmentStatus(){
        AssignmentStatusDAO mockAssignmentStatus = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusService, AssignmentStatusDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStatusDto()).when(mockAssignmentStatus).addAssignmentStatus(any(AssignmentStatusEntity.class));
        Assert.assertEquals("Test name", assignmentStatusService.addAssignmentStatus(new AssignmentStatusForCreationDto()).getAssignmentStatusName());
    }

    @Test
    public void testDeletingAssignmentStatus(){
        AssignmentStatusDAO mockAssignmentStatus = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusService, AssignmentStatusDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockAssignmentStatus).deleteAssignmentStatus(anyInt());
        Assert.assertEquals(true, assignmentStatusService.deleteAssignmentStatus(2));
    }

    @Test
    public void testUpdatingAssignmentStatus(){
        AssignmentStatusDAO mockAssignmentStatus = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusService, AssignmentStatusDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStatusDto()).when(mockAssignmentStatus).updateAssignmentStatus(any(AssignmentStatusEntity.class));
        Assert.assertEquals("Test name", assignmentStatusService.updateAssignmentStatus(new AssignmentStatusForUpdateDto()).getAssignmentStatusName());
    }

    @Test
    public void testFindingAssignmentStatus(){
        AssignmentStatusDAO mockAssignmentStatus = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusService, AssignmentStatusDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStatusDto()).when(mockAssignmentStatus).findAssignmentStatus(anyInt());
        Assert.assertEquals("Test name", assignmentStatusService.findAssignmentStatus(1).getAssignmentStatusName());
    }

    @Test
    public void testListingAllAssignmentStatuses(){
        AssignmentStatusDAO mockAssignmentStatus = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusService, AssignmentStatusDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStatusDtoList()).when(mockAssignmentStatus).listAssignmentStatuses(anyBoolean());
        Assert.assertEquals(5, assignmentStatusService.listAssignmentStatuses(true).size());
    }
}
