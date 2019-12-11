package API.Service.AssignmentService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentInterpretationTypeEntity;
import API.Repository.Assignment.AssignmentInterpretationTypeDAO;
import API.Services.AssignmentService.AssignmentInterpretationTypeService;
import Shared.ForCreation.AssignmentInterpretationTypeForCreationDto;
import Shared.ForCreation.AssignmentInterpretationTypeForUpdateDto;
import Shared.ToReturn.AssignmentInterpretationTypeDto;
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
public class AssignmentInterpretationTypeServiceTest {
    @Autowired
    private AssignmentInterpretationTypeService assignmentInterpretationTypeService;

    private AssignmentInterpretationTypeDto getAssignmentInterpretationType(){
        AssignmentInterpretationTypeDto assignmentInterpretationType = new AssignmentInterpretationTypeDto();
        assignmentInterpretationType.setInterpretationTypeName("Test interpretation");
        return assignmentInterpretationType;
    }

    private List<AssignmentInterpretationTypeDto> getAssignmentInterpretationTypeList(){
        List<AssignmentInterpretationTypeDto> returnList = new ArrayList<>();
        returnList.add(new AssignmentInterpretationTypeDto());
        returnList.add(new AssignmentInterpretationTypeDto());
        returnList.add(new AssignmentInterpretationTypeDto());
        returnList.add(new AssignmentInterpretationTypeDto());
        returnList.add(new AssignmentInterpretationTypeDto());
        return returnList;
    }

    @Test
    public void testAddingAssignmentComment(){
        AssignmentInterpretationTypeDAO mockAssignmentInterpretationType = SpringBeanMockUtil.mockFieldOnBean(assignmentInterpretationTypeService, AssignmentInterpretationTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentInterpretationType()).when(mockAssignmentInterpretationType).addAssignmentInterpretationType(any(AssignmentInterpretationTypeEntity.class));
        Assert.assertEquals("Test interpretation", assignmentInterpretationTypeService.addAssignmentInterpretationType(new AssignmentInterpretationTypeForCreationDto()).getInterpretationTypeName());
    }

    @Test
    public void testFindingAssignmentComment(){
        AssignmentInterpretationTypeDAO mockAssignmentInterpretationType = SpringBeanMockUtil.mockFieldOnBean(assignmentInterpretationTypeService, AssignmentInterpretationTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentInterpretationType()).when(mockAssignmentInterpretationType).findAssignmentInterpretationType(anyInt());
        Assert.assertEquals("Test interpretation", assignmentInterpretationTypeService.findAssignmentInterpretationType(2).getInterpretationTypeName());
    }

    @Test
    public void testUpdatingAssignmentComment(){
        AssignmentInterpretationTypeDAO mockAssignmentInterpretationType = SpringBeanMockUtil.mockFieldOnBean(assignmentInterpretationTypeService, AssignmentInterpretationTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentInterpretationType()).when(mockAssignmentInterpretationType).updateAssignmentInterpretationType(any(AssignmentInterpretationTypeEntity.class));
        Assert.assertEquals("Test interpretation", assignmentInterpretationTypeService.updateAssignmentInterpretationType(new AssignmentInterpretationTypeForUpdateDto()).getInterpretationTypeName());
    }

    @Test
    public void testListingAssignmentComment(){
        AssignmentInterpretationTypeDAO mockAssignmentInterpretationType = SpringBeanMockUtil.mockFieldOnBean(assignmentInterpretationTypeService, AssignmentInterpretationTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentInterpretationTypeList()).when(mockAssignmentInterpretationType).listAssignmentInterpretationTypes(anyBoolean());
        Assert.assertEquals(5, assignmentInterpretationTypeService.listAssignmentInterpretationTypes(true).size());
    }

    @Test
    public void testDeletingAssignmentComment(){
        AssignmentInterpretationTypeDAO mockAssignmentInterpretationType = SpringBeanMockUtil.mockFieldOnBean(assignmentInterpretationTypeService, AssignmentInterpretationTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockAssignmentInterpretationType).deleteAssignmentInterpretationType(anyInt());
        Assert.assertTrue(assignmentInterpretationTypeService.deleteAssignmentInterpretationType(2));
    }
}
