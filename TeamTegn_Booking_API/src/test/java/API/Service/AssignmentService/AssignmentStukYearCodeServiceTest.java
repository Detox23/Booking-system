package API.Service.AssignmentService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentStukYearCodeEntity;
import API.Repository.Assignment.AssignmentSTUKYearCodeDAO;
import API.Services.AssignmentService.AssignmentStukYearCodeService;
import Shared.ForCreation.AssignmentStukYearCodeForCreationDto;
import Shared.ForCreation.AssignmentStukYearCodeForUpdateDto;
import Shared.ToReturn.AssignmentStukYearCodeDto;
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
public class AssignmentStukYearCodeServiceTest {

    @Autowired
    private AssignmentStukYearCodeService assignmentStukYearCodeService;

    private AssignmentStukYearCodeDto getAssignmentStukYearCodeDto() {
        AssignmentStukYearCodeDto assignmentStukYearCodeDto = new AssignmentStukYearCodeDto();
        assignmentStukYearCodeDto.setStukYearCodeName("Test name");
        return assignmentStukYearCodeDto;
    }

    private List<AssignmentStukYearCodeDto> getAssignmentStukYearCodeDtoList(){
        List<AssignmentStukYearCodeDto> resultList = new ArrayList<>();
        resultList.add(new AssignmentStukYearCodeDto());
        resultList.add(new AssignmentStukYearCodeDto());
        resultList.add(new AssignmentStukYearCodeDto());
        resultList.add(new AssignmentStukYearCodeDto());
        resultList.add(new AssignmentStukYearCodeDto());
        return resultList;
    }
    @Test
    public void testAddingAssignmentStukYearCode(){
        AssignmentSTUKYearCodeDAO mockAssignmentStukYearCode = SpringBeanMockUtil.mockFieldOnBean(assignmentStukYearCodeService, AssignmentSTUKYearCodeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStukYearCodeDto()).when(mockAssignmentStukYearCode).addAssigmentStukYearCode(any(AssignmentStukYearCodeEntity.class));
        Assert.assertEquals("Test name", assignmentStukYearCodeService.addAssigmentStukYearCode(new AssignmentStukYearCodeForCreationDto()).getStukYearCodeName());
    }

    @Test
    public void testDeletingAssignmentStukYearCode(){
        AssignmentSTUKYearCodeDAO mockAssignmentStukYearCode = SpringBeanMockUtil.mockFieldOnBean(assignmentStukYearCodeService, AssignmentSTUKYearCodeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockAssignmentStukYearCode).deleteAssignmentStukYearCode(anyInt());
        Assert.assertEquals(true, assignmentStukYearCodeService.deleteAssignmentStukYearCode(2));
    }

    @Test
    public void testUpdatingAssignmentStukYearCode(){
        AssignmentSTUKYearCodeDAO mockAssignmentStukYearCode = SpringBeanMockUtil.mockFieldOnBean(assignmentStukYearCodeService, AssignmentSTUKYearCodeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStukYearCodeDto()).when(mockAssignmentStukYearCode).updateAssignmentStukYearCode(any(AssignmentStukYearCodeEntity.class));
        Assert.assertEquals("Test name", assignmentStukYearCodeService.updateAssignmentStukYearCode(new AssignmentStukYearCodeForUpdateDto()).getStukYearCodeName());
    }

    @Test
    public void testFindingAssignmentStukYearCode(){
        AssignmentSTUKYearCodeDAO mockAssignmentStukYearCode = SpringBeanMockUtil.mockFieldOnBean(assignmentStukYearCodeService, AssignmentSTUKYearCodeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStukYearCodeDto()).when(mockAssignmentStukYearCode).findAssignmentStukYearCode(anyInt());
        Assert.assertEquals("Test name", assignmentStukYearCodeService.findAssignmentStukYearCode(1).getStukYearCodeName());
    }

    @Test
    public void testListingAllAssignmentStukYearCodes(){
        AssignmentSTUKYearCodeDAO mockAssignmentStukYearCode = SpringBeanMockUtil.mockFieldOnBean(assignmentStukYearCodeService, AssignmentSTUKYearCodeDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentStukYearCodeDtoList()).when(mockAssignmentStukYearCode).listAssignmentStukYearCodes(anyBoolean());
        Assert.assertEquals(5, assignmentStukYearCodeService.listAssignmentStukYearCodes(true).size());
    }


}
