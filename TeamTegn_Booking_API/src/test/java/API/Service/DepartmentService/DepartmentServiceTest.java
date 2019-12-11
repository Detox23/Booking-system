package API.Service.DepartmentService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.DepartmentEntity;
import API.Repository.Department.DepartmentDAO;
import API.Services.DepartmentService.DepartmentService;
import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ForCreation.DepartmentForUpdateDto;
import Shared.ToReturn.DepartmentDto;
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
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    private DepartmentDto getDepartmentDto() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("Test department name");
        return departmentDto;
    }

    private List<DepartmentDto> getDepartmentDtoList(){
        List<DepartmentDto> resultList = new ArrayList<>();
        resultList.add(new DepartmentDto());
        resultList.add(new DepartmentDto());
        resultList.add(new DepartmentDto());
        resultList.add(new DepartmentDto());
        resultList.add(new DepartmentDto());
        return resultList;
    }

    @Test
    public void testAddingDepartment(){
        DepartmentDAO mockDepartment = SpringBeanMockUtil.mockFieldOnBean(departmentService, DepartmentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getDepartmentDto()).when(mockDepartment).addOneDepartment(any(DepartmentEntity.class));
        Assert.assertEquals("Test department name", departmentService.addDepartment(new DepartmentForCreationDto()).getDepartmentName());
    }

    @Test
    public void testDeletingDepartment(){
        DepartmentDAO mockDepartment = SpringBeanMockUtil.mockFieldOnBean(departmentService, DepartmentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockDepartment).deleteOneDepartment(anyInt());
        Assert.assertEquals(true, departmentService.deleteDepartment(2));
    }

    @Test
    public void testUpdatingDepartment(){
        DepartmentDAO mockDepartment = SpringBeanMockUtil.mockFieldOnBean(departmentService, DepartmentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getDepartmentDto()).when(mockDepartment).updateOneDepartment(any(DepartmentEntity.class));
        Assert.assertEquals("Test department name", departmentService.updateDepartment(new DepartmentForUpdateDto()).getDepartmentName());
    }

    @Test
    public void testFindingDepartment(){
        DepartmentDAO mockDepartment = SpringBeanMockUtil.mockFieldOnBean(departmentService, DepartmentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getDepartmentDto()).when(mockDepartment).findOneDepartment(anyString());
        Assert.assertEquals("Test department name", departmentService.findDepartment("Name").getDepartmentName());
    }

    @Test
    public void testListingAllDepartment(){
        DepartmentDAO mockDepartment = SpringBeanMockUtil.mockFieldOnBean(departmentService, DepartmentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getDepartmentDtoList()).when(mockDepartment).listAllDepartments(anyBoolean());
        Assert.assertEquals(5, departmentService.seeAllDepartments(true).size());
    }
}
