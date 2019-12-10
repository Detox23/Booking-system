package API.Repository.ServiceUser;

import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.Account.AccountDAO;
import API.Repository.Account.AccountTypeDAO;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
import API.Repository.Department.DepartmentDAO;
import Shared.ToReturn.ServiceUserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class ServiceUserRepositoryTest {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountTypeDAO accountTypeDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private ServiceUserDAO serviceUserDAO;

    @Autowired
    private ServiceUserStatusDAO serviceUserStatusDAO;

    @Autowired
    private ServiceUserAccountsDAO serviceUserAccountsDAO;

    @Autowired
    private WI_PostcodeDAO wiPostcodeDAO;

    @Autowired
    private CityPostcodesDAO cityPostcodesDAO;

    private AccountTypeEntity accountTypeOne;
    private AccountEntity accountOne;
    private AccountEntity accountTwo;
    private DepartmentEntity departmentOne;
    private ServiceUserEntity serviceUserOne;
    private ServiceUserStatusEntity serviceUserStatusOne;

    private void setUp() {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName("TestDepartmentNameOne");
        departmentEntity.setPostcode("9200");
        departmentOne = departmentDAO.save(departmentEntity);

        ServiceUserStatusEntity serviceUserStatusEntity = new ServiceUserStatusEntity();
        serviceUserStatusEntity.setStatus("TestStatus");
        serviceUserStatusEntity.setDeleted(false);
        serviceUserStatusOne = serviceUserStatusDAO.save(serviceUserStatusEntity);

        AccountTypeEntity accountTypeEntity1 = new AccountTypeEntity();
        accountTypeEntity1.setAccountType("TestAccountType");
        accountTypeEntity1.setDeleted(false);
        accountTypeOne = accountTypeDAO.save(accountTypeEntity1);

        ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
        serviceUserEntity.setFirstName("TestFirstName");
        serviceUserEntity.setLastName("TestLastName");
        serviceUserEntity.setCpr("2308784512");
        serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
        serviceUserEntity.setDepartmentId(departmentOne.getId());
        serviceUserEntity.setDeleted(false);
        serviceUserOne = serviceUserDAO.save(serviceUserEntity);

        AccountEntity accountEntity1 = new AccountEntity();
        accountEntity1.setAccountName("TestAccount");
        accountEntity1.setCity("TestCity");
        accountEntity1.setParentId(1);
        accountEntity1.setAccountTypeId(accountTypeOne.getId());
        accountEntity1.setDepartmentId(departmentOne.getId());
        accountOne = accountDAO.save(accountEntity1);

        AccountEntity accountEntity2 = new AccountEntity();
        accountEntity2.setAccountName("TestAccountSecond");
        accountEntity2.setCity("TestCitySecond");
        accountEntity2.setParentId(1);
        accountEntity2.setAccountTypeId(accountTypeOne.getId());
        accountEntity2.setDepartmentId(departmentOne.getId());
        accountTwo = accountDAO.save(accountEntity2);

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
        serviceUserAccountsDAO.deleteAllInBatch();
        serviceUserAccountsDAO.flush();
        serviceUserDAO.deleteAllInBatch();
        serviceUserDAO.flush();
        serviceUserStatusDAO.deleteAllInBatch();
        serviceUserStatusDAO.flush();
        accountDAO.deleteAllInBatch();
        accountDAO.flush();
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
        accountTypeDAO.deleteAllInBatch();
        accountTypeDAO.flush();
        cityPostcodesDAO.deleteAllInBatch();
        cityPostcodesDAO.flush();
        wiPostcodeDAO.deleteAllInBatch();
        wiPostcodeDAO.flush();
    }




    @Test
    public void testAddingServiceUser() {
        setUp();
        try {
            ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
            serviceUserEntity.setFirstName("TestFirstNameTwo");
            serviceUserEntity.setLastName("TestLastNameTwo");
            serviceUserEntity.setCpr("2356894512");
            serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
            serviceUserEntity.setDepartmentId(departmentOne.getId());
            serviceUserEntity.setDeleted(false);
            ServiceUserDto added = serviceUserDAO.addServiceUser(serviceUserEntity, null);
            Assert.assertEquals("TestFirstNameTwo", serviceUserDAO.findById(added.getId()).get().getFirstName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testAddingServiceUserAddWithAccountListAddsThemToATable() {
        setUp();
        try {
            ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
            serviceUserEntity.setFirstName("TestFirstNameTwo");
            serviceUserEntity.setLastName("TestLastNameTwo");
            serviceUserEntity.setCpr("1265789845");
            serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
            serviceUserEntity.setDepartmentId(departmentOne.getId());
            serviceUserEntity.setDeleted(false);
            List<Integer> accounts = new ArrayList<>();
            accounts.add(accountOne.getId());
            accounts.add(accountTwo.getId());
            ServiceUserDto added = serviceUserDAO.addServiceUser(serviceUserEntity, accounts);
            Assert.assertEquals(2, serviceUserAccountsDAO.findAllByServiceUserIdIs(added.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceUserSetPostcodeFillsStateRegionOne(){
        setUp();
        try {
            ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
            serviceUserEntity.setFirstName("TestFirstNameTwo");
            serviceUserEntity.setLastName("TestLastNameTwo");
            serviceUserEntity.setCpr("1265789845");
            serviceUserEntity.setPostcode("8000");
            serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
            serviceUserEntity.setDepartmentId(departmentOne.getId());
            serviceUserEntity.setDeleted(false);;
            ServiceUserDto added = serviceUserDAO.addServiceUser(serviceUserEntity, null);
            Assert.assertEquals("Aarhus", added.getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceUserSetPostcodeFillsStateRegionTwo(){
        setUp();
        try {
            ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
            serviceUserEntity.setFirstName("TestFirstNameTwo");
            serviceUserEntity.setLastName("TestLastNameTwo");
            serviceUserEntity.setCpr("1265789845");
            serviceUserEntity.setPostcode("6430");
            serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
            serviceUserEntity.setDepartmentId(departmentOne.getId());
            serviceUserEntity.setDeleted(false);;
            ServiceUserDto added = serviceUserDAO.addServiceUser(serviceUserEntity, null);
            Assert.assertEquals("Fredericia", added.getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceUserSetPostcodeFillsStateRegionThree(){
        setUp();
        try {
            ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
            serviceUserEntity.setFirstName("TestFirstNameTwo");
            serviceUserEntity.setLastName("TestLastNameTwo");
            serviceUserEntity.setCpr("1265789845");
            serviceUserEntity.setPostcode("4773");
            serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
            serviceUserEntity.setDepartmentId(departmentOne.getId());
            serviceUserEntity.setDeleted(false);;
            ServiceUserDto added = serviceUserDAO.addServiceUser(serviceUserEntity, null);
            Assert.assertEquals("Copenhagen", added.getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceUserWithExistingNameThrowsException(){
        setUp();
        try {
            ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
            serviceUserEntity.setFirstName("TestFirstName");
            serviceUserEntity.setLastName("TestLastName");
            serviceUserEntity.setCpr("1265789845");
            serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
            serviceUserEntity.setDepartmentId(departmentOne.getId());
            serviceUserEntity.setDeleted(false);;
            ServiceUserDto added = serviceUserDAO.addServiceUser(serviceUserEntity, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingServiceUserNameMatches(){
        setUp();
        try {
            Assert.assertEquals("2308784512", serviceUserDAO.findServiceUser(serviceUserOne.getId()).getCpr());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingServiceUserWithNotExistingId(){
        setUp();
        try {
            Assert.assertEquals("2308784512", serviceUserDAO.findServiceUser(-5).getCpr());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeleteServiceUserListIsEmpty(){
        setUp();
        try {
            serviceUserDAO.deleteServiceUser(serviceUserOne.getId());
            Assert.assertEquals(0, serviceUserDAO.listServiceUsers(PageRequest.of(0,10)).getNumberOfElements());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeleteServiceUserWithNotExistingId(){
        setUp();
        try {
            serviceUserDAO.deleteServiceUser(-5);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdateServiceUserNameShouldBeChanged(){
        setUp();
        try {
            ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
            serviceUserEntity.setFirstName("TestFirstNameTwo");
            serviceUserEntity.setLastName("TestLastNameTwo");
            serviceUserEntity.setCpr("1265789845");
            serviceUserEntity.setPostcode("8000");
            serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
            serviceUserEntity.setDepartmentId(departmentOne.getId());
            serviceUserEntity.setDeleted(false);;
            ServiceUserDto added = serviceUserDAO.addServiceUser(serviceUserEntity, null);
            ServiceUserEntity toUpdate = serviceUserDAO.findById(added.getId()).get();
            toUpdate.setFirstName("TestFirstNameUpdated");
            toUpdate.setStateRegion(null);
            toUpdate.setPostcode("4773");
            ServiceUserDto updated = serviceUserDAO.updateServiceUser(toUpdate, null);
            Assert.assertEquals("Copenhagen", updated.getStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdateServiceUserForNameThatAlreadyExists(){
        setUp();
        try {
            ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
            serviceUserEntity.setFirstName("TestFirstNameTwo");
            serviceUserEntity.setLastName("TestLastNameTwo");
            serviceUserEntity.setCpr("1265789845");
            serviceUserEntity.setPostcode("8000");
            serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
            serviceUserEntity.setDepartmentId(departmentOne.getId());
            serviceUserEntity.setDeleted(false);;
            ServiceUserDto added = serviceUserDAO.addServiceUser(serviceUserEntity, null);
            ServiceUserEntity toUpdate = serviceUserDAO.findById(added.getId()).get();
            toUpdate.setFirstName("TestFirstName");
            toUpdate.setLastName("TestLastName");
            serviceUserDAO.updateServiceUser(toUpdate, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddServiceUserForNameCityShouldBeFilled(){
        setUp();
        try {
            ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
            serviceUserEntity.setFirstName("TestFirstNameTwo");
            serviceUserEntity.setLastName("TestLastNameTwo");
            serviceUserEntity.setCpr("1265789845");
            serviceUserEntity.setPostcode("2123");
            serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
            serviceUserEntity.setDepartmentId(departmentOne.getId());
            serviceUserEntity.setDeleted(false);;
            ServiceUserDto added = serviceUserDAO.addServiceUser(serviceUserEntity, null);
            Assert.assertEquals("TestCityFill", added.getCity());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

}
