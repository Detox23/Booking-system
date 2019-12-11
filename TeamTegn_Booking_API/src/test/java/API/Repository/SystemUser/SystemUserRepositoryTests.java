package API.Repository.SystemUser;

import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
import API.Repository.Department.DepartmentDAO;
import Shared.ToReturn.SystemUserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class SystemUserRepositoryTests {

    @Autowired
    private SystemUserDAO systemUserDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private SystemUser_DepartmentDAO systemUserDepartmentDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private WI_PostcodeDAO wiPostcodeDAO;

    @Autowired
    private CityPostcodesDAO cityPostcodesDAO;

    private DepartmentEntity departmentOne;
    private DepartmentEntity departmentTwo;
    private SystemUserEntity systemUserOne;
    private RoleEntity roleOne;

    private void setUp() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName("TestDepartmentNameOne");
        departmentEntity.setPostcode("9200");
        departmentOne = departmentDAO.save(departmentEntity);

        DepartmentEntity departmentEntity2 = new DepartmentEntity();
        departmentEntity2.setDepartmentName("TestDepartmentNameTwo");
        departmentEntity2.setPostcode("8800");
        departmentTwo = departmentDAO.save(departmentEntity2);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("Test");
        roleEntity.setDeleted(false);
        roleEntity.setRoleDescription("Test description");
        roleOne = roleDAO.save(roleEntity);


        SystemUserEntity systemUserEntity = new SystemUserEntity();
        systemUserEntity.setFirstName("FirstNameTest");
        systemUserEntity.setLastName("LastNameTest");
        systemUserEntity.setUserName("UserNameTest");
        systemUserEntity.setPassword("PasswordTest");
        systemUserEntity.setRoleId(roleOne.getId());
        systemUserOne = systemUserDAO.save(systemUserEntity);

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
        systemUserDepartmentDAO.deleteAllInBatch();
        systemUserDepartmentDAO.flush();
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
        systemUserDAO.deleteAllByIdIsGreaterThan(1);
        systemUserDAO.flush();
        roleDAO.deleteAllInBatch();
        roleDAO.flush();
        wiPostcodeDAO.deleteAllInBatch();
        wiPostcodeDAO.flush();
        cityPostcodesDAO.deleteAllInBatch();
        cityPostcodesDAO.flush();
    }

    @Test
    public void testAddingSystemUserNameShouldMatch() {
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest2");
            systemUserEntity.setPassword("PasswordTest2");
            SystemUserDto added = systemUserDAO.addSystemUser(systemUserEntity, null);
            Assert.assertEquals("FirstNameTest2", added.getFirstName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingSystemUserNameWithSameNameShouldThrowException() {
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest");
            systemUserEntity.setLastName("LastNameTest");
            systemUserEntity.setUserName("UserNameTest1");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserDAO.addSystemUser(systemUserEntity, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingSystemUserNameWithUserNameShouldThrowException() {
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserDAO.addSystemUser(systemUserEntity, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingSystemUserShouldFillStateRegionFirst(){
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest2");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserEntity.setPostcode("4773");
            SystemUserDto added = systemUserDAO.addSystemUser(systemUserEntity, null);
            Assert.assertEquals("Copenhagen", systemUserDAO.findById(added.getId()).get().getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingSystemUserShouldFillStateRegionSecond(){
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest2");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserEntity.setPostcode("8000");
            SystemUserDto added = systemUserDAO.addSystemUser(systemUserEntity, null);
            Assert.assertEquals("Aarhus", systemUserDAO.findById(added.getId()).get().getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingSystemUserShouldFillStateRegionThird(){
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest2");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserEntity.setPostcode("6430");
            SystemUserDto added = systemUserDAO.addSystemUser(systemUserEntity, null);
            Assert.assertEquals("Fredericia", systemUserDAO.findById(added.getId()).get().getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingSystemUserShouldFillsCity(){
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest2");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserEntity.setPostcode("2123");
            SystemUserDto added = systemUserDAO.addSystemUser(systemUserEntity, null);
            Assert.assertEquals("TestCityFill", systemUserDAO.findById(added.getId()).get().getCity());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingSystemUserShouldNotBeFound(){
        setUp();
        try {
            systemUserDAO.deleteSystemUser(systemUserOne.getId());
            systemUserDAO.findSystemUser(systemUserOne.getId());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingSystemUserRepositoryShouldChangeName(){
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest2");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserEntity.setPostcode("2123");
            SystemUserDto added = systemUserDAO.addSystemUser(systemUserEntity, null);
            SystemUserEntity toUpdate = systemUserDAO.findById(added.getId()).get();
            toUpdate.setFirstName("FirstNameTest2Updated");
            systemUserDAO.updateSystemUser(toUpdate, null);
            Assert.assertEquals("FirstNameTest2Updated", systemUserDAO.findById(added.getId()).get().getFirstName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testUpdatingSystemUserRepositoryForExistingNameShouldThrowException(){
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest2");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserEntity.setPostcode("2123");
            SystemUserDto added = systemUserDAO.addSystemUser(systemUserEntity, null);
            SystemUserEntity toUpdate = systemUserDAO.findById(added.getId()).get();
            toUpdate.setFirstName("FirstNameTest");
            toUpdate.setLastName("LastNameTest");
            systemUserDAO.updateSystemUser(toUpdate, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingSystemUserRepositoryForExistingUserNameShouldThrowException(){
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest2");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserEntity.setPostcode("2123");
            SystemUserDto added = systemUserDAO.addSystemUser(systemUserEntity, null);
            SystemUserEntity toUpdate = systemUserDAO.findById(added.getId()).get();
            toUpdate.setUserName("UserNameTest");
            systemUserDAO.updateSystemUser(toUpdate, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }


    @Test
    public void testUpdatingSystemUserRepositoryShouldChangeStateRegion(){
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest2");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserEntity.setPostcode("4773");
            SystemUserDto added = systemUserDAO.addSystemUser(systemUserEntity, null);
            SystemUserEntity toUpdate = systemUserDAO.findById(added.getId()).get();
            toUpdate.setPostcode("8000");
            toUpdate.setStateRegion(null);
            systemUserDAO.updateSystemUser(toUpdate, null);
            Assert.assertEquals("Aarhus", systemUserDAO.findById(added.getId()).get().getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testDeleteSystemUserUsingNotExistingId(){
        setUp();
        try {
            systemUserDAO.deleteSystemUser(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListSystemUsersNotShowingDeletedSizeShouldBeOne(){
        setUp();
        try {
            Assert.assertEquals(2, systemUserDAO.listSystemUsers(false).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListSystemUsersShowingDeletedSizeShouldBeOne(){
        setUp();
        try {
            systemUserDAO.deleteSystemUser(systemUserOne.getId());
            Assert.assertEquals(2, systemUserDAO.listSystemUsers(true).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListSystemUsersNotShowingDeletedShowingDeletedSizeShouldBeZero(){
        setUp();
        try {
            systemUserDAO.deleteSystemUser(systemUserOne.getId());
            Assert.assertEquals(1, systemUserDAO.listSystemUsers(false).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testFindSystemUserByUsernameFirstNameShouldMatch(){
        setUp();
        try {
            Assert.assertEquals("FirstNameTest", systemUserDAO.findSystemUser("UserNameTest").getFirstName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testFindSystemUserThatWasDeletedShouldThrowException(){
        setUp();
        try {
            systemUserDAO.deleteSystemUser(systemUserOne.getId());
            systemUserDAO.findSystemUser("UserNameTest");
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }


    @Test
    public void testAddSystemUserWithDepartmentDepartmentShouldBeAddedSizeShouldBeOne(){
        setUp();
        try {
            SystemUserEntity systemUserEntity = new SystemUserEntity();
            systemUserEntity.setFirstName("FirstNameTest2");
            systemUserEntity.setLastName("LastNameTest2");
            systemUserEntity.setUserName("UserNameTest2");
            systemUserEntity.setPassword("PasswordTest2");
            systemUserEntity.setPostcode("2123");
            List<Integer> departments = new ArrayList<Integer>();
            departments.add(departmentOne.getId());
            SystemUserDto added = systemUserDAO.addSystemUser(systemUserEntity, departments);
            Assert.assertEquals(1, systemUserDepartmentDAO.findBySystemUserIdIs(added.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testUpdateSystemUserWithDepartmentDepartmentShouldBeAddedSizeShouldBeTwo(){
        setUp();
        try {
            List<Integer> departments = new ArrayList<Integer>();
            departments.add(departmentOne.getId());
            departments.add(departmentTwo.getId());
            systemUserDAO.updateSystemUser(systemUserOne, departments);
            Assert.assertEquals(2, systemUserDepartmentDAO.findBySystemUserIdIs(systemUserOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }



}
