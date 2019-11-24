package API.Repository.Account;

import API.Database_Entities.DepartmentEntity;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.MainApplicationClass;
import API.Repository.Department.DepartmentDAO;
import Shared.ToReturn.DepartmentDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class DepartmentRepositoryTest {


    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private ModelMapper modelMapper;


    private String addedName;

    private void setUp() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName("TestDepartmentName");
        departmentEntity.setCity("TestDepartmentCity");
        departmentEntity.setCountry("TestDepartmentCountry");
        addedName = departmentDAO.save(departmentEntity).getDepartmentName();

        DepartmentEntity departmentEntity1 = new DepartmentEntity();
        departmentEntity1.setDepartmentName("TestDepartmentName1");
        departmentEntity1.setCity("TestDepartmentCity");
        departmentEntity1.setCountry("TestDepartmnetCountry");
        departmentDAO.save(departmentEntity1);

    }


    private void setDown() {
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
    }

    @Test
    public void testAddDepartmentRepositoryShouldReturnNotNullObject() {
        try {
            setUp();
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentAdding");
            departmentEntity.setCity("testDepartmentAddingCity");
            departmentEntity.setCountry("testDepartmentAddingCountry");
            Assert.assertNotNull(departmentDAO.addOneDepartment(departmentEntity));

        } catch (Exception e) {
            Assert.fail();
        } finally {
            setDown();
        }
    }

    @Test
    public void testAddDepartmentRepositoryShouldThrowDuplicateException() {
        try {
            setUp();
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentName");
            departmentDAO.addOneDepartment(departmentEntity);
            Assert.fail();
        } catch (DuplicateException duplicate) {
            Assert.assertEquals("Department with the name already exists.", duplicate.getMessage());
        } finally {
            setDown();
        }
    }

    @Test
    public void testFindDepartmentShouldReturnSetUpObject() {
        try {
            setUp();
            DepartmentDto found = departmentDAO.findOneDepartment(addedName);
            Assert.assertEquals("TestDepartmentName", found.getDepartmentName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            setDown();
        }
    }

    @Test
    public void testFindAllDepartmentsShouldReturnListOfSizeTwo() {
        try {
            setUp();
            Assert.assertEquals(2, departmentDAO.listAllDepartments().size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            setDown();
        }
    }

    @Test
    public void testFindDepartmentInvalidName() {
        try {
            setUp();
            departmentDAO.findOneDepartment("NotExistingDepartment");
            Assert.fail();
        } catch (NotFoundException notFoundExeption) {
            Assert.assertEquals("Department with the provided name was not found.", notFoundExeption.getMessage());
        } finally {
            setDown();
        }
    }

    @Test
    public void testDeleteDepartmentNewListShouldHaveOneItem() {
        try {
            setUp();
            departmentDAO.deleteOneDepartment("TestDepartmentName");
            Assert.assertEquals(1, departmentDAO.listAllDepartments().size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            setDown();
        }
    }

}
