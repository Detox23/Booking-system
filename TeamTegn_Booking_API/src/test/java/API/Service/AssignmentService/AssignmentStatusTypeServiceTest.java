package API.Service.AssignmentService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentStatusTypeEntity;
import API.Repository.Assignment.AssignmentStatusTypeDAO;
import API.Services.AssignmentService.AssignmentStatusTypeService;
import Shared.ForCreation.AssignmentStatusTypeForCreationDto;
import Shared.ForCreation.AssignmentStatusTypeForUpdateDto;
import Shared.ToReturn.AssignmentStatusTypeDto;
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
public class AssignmentStatusTypeServiceTest {

    @Autowired
    private AssignmentStatusTypeService assignmentStatusTypeService;

    private AssignmentStatusTypeDto getAssignmentStatusTypeDto() {
        AssignmentStatusTypeDto assignmentStatusTypeDto = new AssignmentStatusTypeDto();
        assignmentStatusTypeDto.setAssignmentStatusTypeName("Test name");
        assignmentStatusTypeDto.setDisplayOrder(0);
        return assignmentStatusTypeDto;
    }

    private List<AssignmentStatusTypeDto> getAssignmentStatusTypeDtoList(){
        List<AssignmentStatusTypeDto> resultList = new ArrayList<>();
        resultList.add(new AssignmentStatusTypeDto());
        resultList.add(new AssignmentStatusTypeDto());
        resultList.add(new AssignmentStatusTypeDto());
        resultList.add(new AssignmentStatusTypeDto());
        resultList.add(new AssignmentStatusTypeDto());
        return resultList;
    }

    @Test
    public void testAddingAssignmentStatusType(){
        AssignmentStatusTypeDAO mockAssignmentStatusType = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusTypeService, AssignmentStatusTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStatusTypeDto()).when(mockAssignmentStatusType).addAssignmentStatusType(any(AssignmentStatusTypeEntity.class));
        Assert.assertEquals("Test name", assignmentStatusTypeService.addAssignmentStatusType(new AssignmentStatusTypeForCreationDto()).getAssignmentStatusTypeName());
    }

    @Test
    public void testDeletingAssignmentStatus(){
        AssignmentStatusTypeDAO mockAssignmentStatusType = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusTypeService, AssignmentStatusTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockAssignmentStatusType).deleteAssignmentStatusType(anyInt());
        Assert.assertEquals(true, assignmentStatusTypeService.deleteAssignmentStatusType(2));
    }

    @Test
    public void testUpdatingAssignmentStatus(){
        AssignmentStatusTypeDAO mockAssignmentStatusType = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusTypeService, AssignmentStatusTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStatusTypeDto()).when(mockAssignmentStatusType).updateAssignmentStatusType(any(AssignmentStatusTypeEntity.class));
        Assert.assertEquals("Test name", assignmentStatusTypeService.updateAssignmentStatusType(new AssignmentStatusTypeForUpdateDto()).getAssignmentStatusTypeName());
    }

    @Test
    public void testFindingAssignmentStatus(){
        AssignmentStatusTypeDAO mockAssignmentStatusType = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusTypeService, AssignmentStatusTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStatusTypeDto()).when(mockAssignmentStatusType).findAssignmentStatusType(anyInt());
        Assert.assertEquals("Test name", assignmentStatusTypeService.findAssignmentStatusType(1).getAssignmentStatusTypeName());
    }

    @Test
    public void testListingAllAssignmentStatuses(){
        AssignmentStatusTypeDAO mockAssignmentStatusType = SpringBeanMockUtil.mockFieldOnBean(assignmentStatusTypeService, AssignmentStatusTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStatusTypeDtoList()).when(mockAssignmentStatusType).listAssignmentStatusTypes(anyBoolean());
        Assert.assertEquals(5, assignmentStatusTypeService.listAssignmentStatusTypes(true).size());
    }
}
