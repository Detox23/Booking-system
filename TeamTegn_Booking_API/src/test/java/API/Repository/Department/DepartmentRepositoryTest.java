package API.Repository.Department;

import API.MainApplicationClass;
import API.Models.Database_Entities.CityPostcodesEntity;
import API.Models.Database_Entities.DepartmentEntity;
import API.Models.Database_Entities.WiPostcodeEntity;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
import Shared.ToReturn.DepartmentDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private WI_PostcodeDAO wiPostcodeDAO;

    @Autowired
    private CityPostcodesDAO cityPostcodesDAO;

    private DepartmentEntity departmentOne;
    private DepartmentEntity departmentTwo;

    private void setUp() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName("TestDepartmentNameOne");
        departmentEntity.setPostcode("9200");
        departmentOne = departmentDAO.save(departmentEntity);


        DepartmentEntity departmentEntity2 = new DepartmentEntity();
        departmentEntity2.setDepartmentName("TestDepartmentNameTwo");
        departmentEntity2.setPostcode("9200");
        departmentTwo = departmentDAO.save(departmentEntity2);

        WiPostcodeEntity wiPostcodeEntity = new WiPostcodeEntity();
        wiPostcodeEntity.setCity("CityTest");
        wiPostcodeEntity.setPostcode("8000");
        wiPostcodeEntity.setArhus(true);
        wiPostcodeEntity.setCopenhagen(false);
        wiPostcodeEntity.setFredericia(false);
        wiPostcodeDAO.save(wiPostcodeEntity);

        WiPostcodeEntity wiPostcodeEntity2 = new WiPostcodeEntity();
        wiPostcodeEntity2.setCity("CityTest2");
        wiPostcodeEntity2.setPostcode("6430");
        wiPostcodeEntity2.setArhus(false);
        wiPostcodeEntity2.setCopenhagen(false);
        wiPostcodeEntity2.setFredericia(true);
        wiPostcodeDAO.save(wiPostcodeEntity2);

        WiPostcodeEntity wiPostcodeEntity3 = new WiPostcodeEntity();
        wiPostcodeEntity3.setCity("CityTest3");
        wiPostcodeEntity3.setPostcode("4773");
        wiPostcodeEntity3.setArhus(false);
        wiPostcodeEntity3.setCopenhagen(true);
        wiPostcodeEntity3.setFredericia(false);
        wiPostcodeDAO.save(wiPostcodeEntity3);

        CityPostcodesEntity cityPostCode = new CityPostcodesEntity();
        cityPostCode.setPostcode("2123");
        cityPostCode.setCity("TestCityFill");
        cityPostcodesDAO.save(cityPostCode);
    }

    private void tearDown() {
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
        wiPostcodeDAO.deleteAllInBatch();
        wiPostcodeDAO.flush();
        cityPostcodesDAO.deleteAllInBatch();
        cityPostcodesDAO.flush();
    }

    @Test
    public void testAddingDepartmentWithExistingNameShouldThrowException() {
        setUp();
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentNameOne");
            departmentEntity.setPostcode("3215");
            departmentEntity.setDeleted(false);
            departmentDAO.addOneDepartment(departmentEntity);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingDepartmentShouldPass() {
        setUp();
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentNameNew");
            departmentEntity.setPostcode("3215");
            departmentEntity.setDeleted(false);
            DepartmentDto added = departmentDAO.addOneDepartment(departmentEntity);
            Assert.assertEquals("TestDepartmentNameNew", departmentDAO.findById(added.getId()).get().getDepartmentName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingDepartmentShouldFillCity() {
        setUp();
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentNameNew");
            departmentEntity.setPostcode("2123");
            departmentEntity.setDeleted(false);
            DepartmentDto added = departmentDAO.addOneDepartment(departmentEntity);
            Assert.assertEquals("TestCityFill", departmentDAO.findById(added.getId()).get().getCity());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingDepartmentShouldAddStateRegion() {
        setUp();
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentNameNew");
            departmentEntity.setPostcode("6430");
            departmentEntity.setDeleted(false);
            DepartmentDto added = departmentDAO.addOneDepartment(departmentEntity);
            Assert.assertEquals("Fredericia", departmentDAO.findById(added.getId()).get().getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingDepartmentShouldAddStateRegionTwo() {
        setUp();
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentNameNew");
            departmentEntity.setPostcode("4773");
            departmentEntity.setDeleted(false);
            DepartmentDto added = departmentDAO.addOneDepartment(departmentEntity);
            Assert.assertEquals("Copenhagen", departmentDAO.findById(added.getId()).get().getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingDepartmentShouldAddStateRegionThree() {
        setUp();
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentNameNew");
            departmentEntity.setPostcode("8000");
            departmentEntity.setDeleted(false);
            DepartmentDto added = departmentDAO.addOneDepartment(departmentEntity);
            Assert.assertEquals("Aarhus", departmentDAO.findById(added.getId()).get().getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingDepartmentShouldChangeName() {
        setUp();
        try {
            departmentOne.setDepartmentName("Updated");
            departmentDAO.updateOneDepartment(departmentOne);
            Assert.assertEquals("Updated", departmentDAO.findById(departmentOne.getId()).get().getDepartmentName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }

    }

    @Test
    public void testUpdatingDepartmentPostCodeShouldFillStateRegion() {
        setUp();
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentNameNew");
            departmentEntity.setPostcode("6430");
            departmentEntity.setDeleted(false);
            DepartmentDto added = departmentDAO.addOneDepartment(departmentEntity);
            DepartmentEntity found = departmentDAO.findById(added.getId()).get();
            found.setPostcode("4773");
            found.setStateRegion(null);
            departmentDAO.updateOneDepartment(found);
            Assert.assertEquals("Copenhagen", departmentDAO.findById(found.getId()).get().getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }

    }

    @Test
    public void testUpdatingDepartmentCityShouldBeFilled() {
        setUp();
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentNameNew");
            departmentEntity.setPostcode("6430");
            departmentEntity.setDeleted(false);
            DepartmentDto added = departmentDAO.addOneDepartment(departmentEntity);
            DepartmentEntity found = departmentDAO.findById(added.getId()).get();
            found.setPostcode("2123");
            found.setCity(null);
            departmentDAO.updateOneDepartment(found);
            Assert.assertEquals("TestCityFill", departmentDAO.findById(found.getId()).get().getCity());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeleteDepartmentShouldNotBeFound() {
        setUp();
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentNameNew");
            departmentEntity.setPostcode("6430");
            departmentEntity.setDeleted(false);
            DepartmentDto added = departmentDAO.addOneDepartment(departmentEntity);
            DepartmentEntity found = departmentDAO.findById(added.getId()).get();
            found.setPostcode("2123");
            found.setCity(null);
            departmentDAO.updateOneDepartment(found);
            Assert.assertEquals("TestCityFill", departmentDAO.findById(found.getId()).get().getCity());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingDepartmentNameShouldMatch() {
        setUp();
        try {
            Assert.assertEquals("TestDepartmentNameOne", departmentDAO.findDepartmentByID(departmentOne.getId()).getDepartmentName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingDepartmentsNameForOneThatAlreadyExistsShouldThrowException() {
        setUp();
        try {
            DepartmentEntity departmentEntity = new DepartmentEntity();
            departmentEntity.setDepartmentName("TestDepartmentNameNewThree");
            departmentEntity.setPostcode("6430");
            departmentEntity.setDeleted(false);
            DepartmentDto added = departmentDAO.addOneDepartment(departmentEntity);
            DepartmentEntity found = departmentDAO.findById(added.getId()).get();
            found.setDepartmentName("TestDepartmentNameOne");
            departmentDAO.updateOneDepartment(found);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingDepartmentWithNotExistingIdShouldThrowException() {
        setUp();
        try {
            departmentDAO.findDepartmentByID(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingDepartmentShouldNotBeFound() {
        setUp();
        try {
            departmentDAO.deleteOneDepartment(departmentOne.getId());
            departmentDAO.findDepartmentByID(departmentOne.getId());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingNotExistingDepartmentShouldThrowException() {
        setUp();
        try {
            departmentDAO.deleteOneDepartment(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingDepartmentNameShouldEquals() {
        setUp();
        try {
            departmentDAO.findDepartmentByID(departmentOne.getId());
            Assert.assertEquals("TestDepartmentNameOne", departmentDAO.findDepartmentByID(departmentOne.getId()).getDepartmentName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingDepartmentByNameTheNameShouldEquals() {
        setUp();
        try {
            departmentDAO.findByDepartmentName("TestDepartmentNameOne");
            Assert.assertEquals("TestDepartmentNameOne", departmentDAO.findDepartmentByID(departmentOne.getId()).getDepartmentName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testFindingDepartmentByNameShouldThrowException() {
        setUp();
        try {
            departmentDAO.findOneDepartment("asd");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingDepartmentSizeShouldBeOne() {
        setUp();
        try {
            departmentDAO.deleteOneDepartment(departmentOne.getId());
            Assert.assertEquals(1, departmentDAO.listAllDepartments(false).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListingDepartmentShowingDeletingSizeShouldBeTwo() {
        setUp();
        try {
            departmentDAO.deleteOneDepartment(departmentOne.getId());
            Assert.assertEquals(2, departmentDAO.listAllDepartments(true).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }
}
