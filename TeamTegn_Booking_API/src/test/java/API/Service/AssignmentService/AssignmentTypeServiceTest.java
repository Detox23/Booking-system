package API.Service.AssignmentService;


import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentTypeEntity;
import API.Repository.Assignment.AssignmentTypeDAO;
import API.Services.AssignmentService.AssignmentTypeService;
import Shared.ForCreation.AssignmentTypeForCreationDto;
import Shared.ForCreation.AssignmentTypeForUpdateDto;
import Shared.ToReturn.AssignmentTypeDto;
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
public class AssignmentTypeServiceTest {

    @Autowired
    private AssignmentTypeService assignmentTypeService;

    private AssignmentTypeDto getAssignmentTypeDto() {
        AssignmentTypeDto assignmentTypeDto = new AssignmentTypeDto();
        assignmentTypeDto.setAssignmentTypeName("Test name");
        return assignmentTypeDto;
    }

    private List<AssignmentTypeDto> getAssignmentTypeDtoList(){
        List<AssignmentTypeDto> resultList = new ArrayList<>();
        resultList.add(new AssignmentTypeDto());
        resultList.add(new AssignmentTypeDto());
        resultList.add(new AssignmentTypeDto());
        resultList.add(new AssignmentTypeDto());
        resultList.add(new AssignmentTypeDto());
        return resultList;
    }

    @Test
    public void testAddingAssignmentType(){
        AssignmentTypeDAO mockAssignmentType = SpringBeanMockUtil.mockFieldOnBean(assignmentTypeService, AssignmentTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentTypeDto()).when(mockAssignmentType).addAssignmentType(any(AssignmentTypeEntity.class));
        Assert.assertEquals("Test name", assignmentTypeService.addAssignmentType(new AssignmentTypeForCreationDto()).getAssignmentTypeName());
    }

    @Test
    public void testDeletingAssignmentType(){
        AssignmentTypeDAO mockAssignmentType = SpringBeanMockUtil.mockFieldOnBean(assignmentTypeService, AssignmentTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockAssignmentType).deleteAssignmentType(anyInt());
        Assert.assertEquals(true, assignmentTypeService.deleteAssignmentType(2));
    }

    @Test
    public void testUpdatingAssignmentType(){
        AssignmentTypeDAO mockAssignmentType = SpringBeanMockUtil.mockFieldOnBean(assignmentTypeService, AssignmentTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentTypeDto()).when(mockAssignmentType).updateAssignmentType(any(AssignmentTypeEntity.class));
        Assert.assertEquals("Test name", assignmentTypeService.updateAssignmentType(new AssignmentTypeForUpdateDto()).getAssignmentTypeName());
    }

    @Test
    public void testFindingAssignmentType(){
        AssignmentTypeDAO mockAssignmentType = SpringBeanMockUtil.mockFieldOnBean(assignmentTypeService, AssignmentTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentTypeDto()).when(mockAssignmentType).findAssignmentType(anyInt());
        Assert.assertEquals("Test name", assignmentTypeService.findAssignmentType(1).getAssignmentTypeName());
    }

    @Test
    public void testListingAllAssignmentType(){
        AssignmentTypeDAO mockAssignmentType = SpringBeanMockUtil.mockFieldOnBean(assignmentTypeService, AssignmentTypeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentTypeDtoList()).when(mockAssignmentType).listAssignmentTypes(anyBoolean());
        Assert.assertEquals(5, assignmentTypeService.listAssignmentTypes(true).size());
    }
}
