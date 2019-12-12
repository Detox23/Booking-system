package API.Service.AssignmentService;


import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentTitleEntity;
import API.Repository.Assignment.AssignmentTitleDAO;
import API.Services.AssignmentService.AssignmentTitleService;
import Shared.ForCreation.AssignmentTitleForCreationDto;
import Shared.ForCreation.AssignmentTitleForUpdateDto;
import Shared.ToReturn.AssignmentTitleDto;
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
public class AssignmentTitleServiceTest {

    @Autowired
    private AssignmentTitleService assignmentTitleService;

    private AssignmentTitleDto getAssignmentTitleDto() {
        AssignmentTitleDto assignmentTitleDto = new AssignmentTitleDto();
        assignmentTitleDto.setTitle("Test name");
        return assignmentTitleDto;
    }

    private List<AssignmentTitleDto> getAssignmentTitleDtoList(){
        List<AssignmentTitleDto> resultList = new ArrayList<>();
        resultList.add(new AssignmentTitleDto());
        resultList.add(new AssignmentTitleDto());
        resultList.add(new AssignmentTitleDto());
        resultList.add(new AssignmentTitleDto());
        resultList.add(new AssignmentTitleDto());
        return resultList;
    }

    @Test
    public void testAddingAssignmentTitle(){
        AssignmentTitleDAO mockAssignmentTitle = SpringBeanMockUtil.mockFieldOnBean(assignmentTitleService, AssignmentTitleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentTitleDto()).when(mockAssignmentTitle).addAssignmentTitle(any(AssignmentTitleEntity.class));
        Assert.assertEquals("Test name", assignmentTitleService.addAssignmentTitle(new AssignmentTitleForCreationDto()).getTitle());
    }

    @Test
    public void testDeletingAssignmentTitle(){
        AssignmentTitleDAO mockAssignmentTitle = SpringBeanMockUtil.mockFieldOnBean(assignmentTitleService, AssignmentTitleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockAssignmentTitle).deleteAssignmentTitle(anyInt());
        Assert.assertEquals(true, assignmentTitleService.deleteAssignmentTitle(2));
    }

    @Test
    public void testUpdatingAssignmentTitle(){
        AssignmentTitleDAO mockAssignmentTitle = SpringBeanMockUtil.mockFieldOnBean(assignmentTitleService, AssignmentTitleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentTitleDto()).when(mockAssignmentTitle).updateAssignmentTitle(any(AssignmentTitleEntity.class));
        Assert.assertEquals("Test name", assignmentTitleService.updateAssignmentTitle(new AssignmentTitleForUpdateDto()).getTitle());
    }

    @Test
    public void testFindingAssignmentTitle(){
        AssignmentTitleDAO mockAssignmentTitle = SpringBeanMockUtil.mockFieldOnBean(assignmentTitleService, AssignmentTitleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentTitleDto()).when(mockAssignmentTitle).findAssignmentTitle(anyInt());
        Assert.assertEquals("Test name", assignmentTitleService.findAssignmentTitle(1).getTitle());
    }

    @Test
    public void testListingAllAssignmentTitle(){
        AssignmentTitleDAO mockAssignmentTitle = SpringBeanMockUtil.mockFieldOnBean(assignmentTitleService, AssignmentTitleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentTitleDtoList()).when(mockAssignmentTitle).listAssignmentTitles(anyBoolean());
        Assert.assertEquals(5, assignmentTitleService.listAssignmentTitles(true).size());
    }
}
