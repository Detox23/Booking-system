package API.Repository.ServiceProvider;


import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
import API.Repository.Department.DepartmentDAO;
import Shared.ToReturn.ServiceProviderDto;
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
public class ServiceProviderRepositoryTest {
    @Autowired
    private ServiceProviderDAO serviceProviderDAO;

    @Autowired
    private TransportTypeDAO transportTypeDAO;

    @Autowired
    private ServiceProviderPreferredNotificationDAO serviceProviderPreferredNotificationDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private ServiceProviderSourceDAO serviceProviderSourceDAO;

    @Autowired
    private ServiceProviderCompetencyDAO serviceProviderCompetencyDAO;

    @Autowired
    private ServiceProviderTypeDAO serviceProviderTypeDAO;

    @Autowired
    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

    @Autowired
    private ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO;

    @Autowired
    private WI_PostcodeDAO wiPostcodeDAO;

    @Autowired
    private CityPostcodesDAO cityPostcodesDAO;

    private TransportTypeEntity transportTypeOne;
    private ServiceProviderPreferredNotificationEntity serviceProviderPreferredNotificationOne;
    private DepartmentEntity departmentOne;
    private ServiceProviderEntity serviceProviderOne;
    private ServiceProviderSourceEntity serviceProviderSourceOne;
    private ServiceProviderCompetencyEntity serviceProviderCompetencyOne;
    private ServiceProviderCompetencyEntity serviceProviderCompetencyTwo;
    private ServiceProviderTypeEntity serviceProviderTypeOne;
    private ServiceProviderTypeEntity serviceProviderTypeTwo;

    private void setUp() {
        ServiceProviderSourceEntity serviceProviderSourceEntity = new ServiceProviderSourceEntity();
        serviceProviderSourceEntity.setProviderSource("TestServiceProviderSource");
        serviceProviderSourceEntity.setDeleted(false);
        serviceProviderSourceOne = serviceProviderSourceDAO.save(serviceProviderSourceEntity);

        TransportTypeEntity transportTypeEntity = new TransportTypeEntity();
        transportTypeEntity.setTransport("TestTransport");
        transportTypeEntity.setDeleted(false);
        transportTypeOne = transportTypeDAO.save(transportTypeEntity);

        ServiceProviderPreferredNotificationEntity providerPreferredNotificationEntity = new ServiceProviderPreferredNotificationEntity();
        providerPreferredNotificationEntity.setNotificationType("TestNotificationType");
        providerPreferredNotificationEntity.setDeleted(false);
        serviceProviderPreferredNotificationOne = serviceProviderPreferredNotificationDAO.save(providerPreferredNotificationEntity);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName("TestDepartmentNameOne");
        departmentEntity.setPostcode("9200");
        departmentOne = departmentDAO.save(departmentEntity);

        ServiceProviderCompetencyEntity serviceProviderCompetencyEntity = new ServiceProviderCompetencyEntity();
        serviceProviderCompetencyEntity.setCompetency("TestCompetency");
        serviceProviderCompetencyEntity.setDeleted(false);
        serviceProviderCompetencyOne = serviceProviderCompetencyDAO.save(serviceProviderCompetencyEntity);

        ServiceProviderCompetencyEntity serviceProviderCompetencyEntity2 = new ServiceProviderCompetencyEntity();
        serviceProviderCompetencyEntity2.setCompetency("TestCompetency2");
        serviceProviderCompetencyEntity2.setDeleted(false);
        serviceProviderCompetencyTwo = serviceProviderCompetencyDAO.save(serviceProviderCompetencyEntity2);


        ServiceProviderTypeEntity serviceProviderTypeEntity = new ServiceProviderTypeEntity();
        serviceProviderTypeEntity.setProviderType("TestProviderType");
        serviceProviderTypeEntity.setDeleted(false);
        serviceProviderTypeOne = serviceProviderTypeDAO.save(serviceProviderTypeEntity);

        ServiceProviderTypeEntity serviceProviderTypeEntity2 = new ServiceProviderTypeEntity();
        serviceProviderTypeEntity2.setProviderType("TestProviderType");
        serviceProviderTypeEntity2.setDeleted(false);
        serviceProviderTypeTwo = serviceProviderTypeDAO.save(serviceProviderTypeEntity2);

        ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
        serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
        serviceProviderEntity.setFirstName("TestFirstName");
        serviceProviderEntity.setLastName("TestLastName");
        serviceProviderEntity.setDepartmentId(departmentOne.getId());
        serviceProviderEntity.setStatus(false);
        serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
        serviceProviderEntity.setTransportId(transportTypeOne.getId());
        serviceProviderOne = serviceProviderDAO.save(serviceProviderEntity);

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

    private void tearDown(){
        serviceProviderServiceProviderCompetencyDAO.deleteAllInBatch();
        serviceProviderServiceProviderCompetencyDAO.flush();
        serviceProviderServiceProviderTypeDAO.deleteAllInBatch();
        serviceProviderServiceProviderTypeDAO.flush();
        serviceProviderDAO.deleteAllInBatch();
        serviceProviderDAO.flush();
        serviceProviderTypeDAO.deleteAllInBatch();
        serviceProviderTypeDAO.flush();
        serviceProviderCompetencyDAO.deleteAllInBatch();
        serviceProviderCompetencyDAO.flush();
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
        serviceProviderPreferredNotificationDAO.deleteAllInBatch();
        serviceProviderPreferredNotificationDAO.flush();
        transportTypeDAO.deleteAllInBatch();
        transportTypeDAO.flush();
        serviceProviderSourceDAO.deleteAllInBatch();
        serviceProviderSourceDAO.flush();
        cityPostcodesDAO.deleteAllInBatch();
        cityPostcodesDAO.flush();
        wiPostcodeDAO.deleteAllInBatch();
        wiPostcodeDAO.flush();
    }


    @Test
    public void testAddingServiceProviderShouldPass(){
        setUp();
        try{
            ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
            serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
            serviceProviderEntity.setFirstName("TestFirstNameTwo");
            serviceProviderEntity.setLastName("TestFirstNameSecond");
            serviceProviderEntity.setCpr("cprTest");
            serviceProviderEntity.setDepartmentId(departmentOne.getId());
            serviceProviderEntity.setStatus(false);
            serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
            serviceProviderEntity.setTransportId(transportTypeOne.getId());
            ServiceProviderDto added= serviceProviderDAO.addServiceProvider(serviceProviderEntity, null, null);
            Assert.assertEquals("TestFirstNameSecond", serviceProviderDAO.findById(added.getId()).get().getLastName());
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceProviderFillsStateRegion(){
        setUp();
        try{
            ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
            serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
            serviceProviderEntity.setFirstName("TestFirstNameTwo");
            serviceProviderEntity.setLastName("TestFirstNameSecond");
            serviceProviderEntity.setCpr("cprTest");
            serviceProviderEntity.setPostcode("4773");
            serviceProviderEntity.setDepartmentId(departmentOne.getId());
            serviceProviderEntity.setStatus(false);
            serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
            serviceProviderEntity.setTransportId(transportTypeOne.getId());
            ServiceProviderDto added= serviceProviderDAO.addServiceProvider(serviceProviderEntity, null, null);
            Assert.assertEquals("Copenhagen", serviceProviderDAO.findById(added.getId()).get().getStateRegion());
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceProviderFillsStateRegionTwo(){
        setUp();
        try{
            ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
            serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
            serviceProviderEntity.setFirstName("TestFirstNameTwo");
            serviceProviderEntity.setLastName("TestFirstNameSecond");
            serviceProviderEntity.setCpr("cprTest");
            serviceProviderEntity.setPostcode("6430");
            serviceProviderEntity.setDepartmentId(departmentOne.getId());
            serviceProviderEntity.setStatus(false);
            serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
            serviceProviderEntity.setTransportId(transportTypeOne.getId());
            ServiceProviderDto added= serviceProviderDAO.addServiceProvider(serviceProviderEntity, null, null);
            Assert.assertEquals("Fredericia", serviceProviderDAO.findById(added.getId()).get().getStateRegion());
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceProviderFillsStateRegionThree(){
        setUp();
        try{
            ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
            serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
            serviceProviderEntity.setFirstName("TestFirstNameTwo");
            serviceProviderEntity.setLastName("TestFirstNameSecond");
            serviceProviderEntity.setCpr("cprTest");
            serviceProviderEntity.setPostcode("8000");
            serviceProviderEntity.setDepartmentId(departmentOne.getId());
            serviceProviderEntity.setStatus(false);
            serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
            serviceProviderEntity.setTransportId(transportTypeOne.getId());
            ServiceProviderDto added= serviceProviderDAO.addServiceProvider(serviceProviderEntity, null, null);
            Assert.assertEquals("Aarhus", serviceProviderDAO.findById(added.getId()).get().getStateRegion());
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testAddingServiceProviderFillsCity(){
        setUp();
        try{
            ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
            serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
            serviceProviderEntity.setFirstName("TestFirstNameTwo");
            serviceProviderEntity.setLastName("TestFirstNameSecond");
            serviceProviderEntity.setCpr("cprTest");
            serviceProviderEntity.setPostcode("2123");
            serviceProviderEntity.setDepartmentId(departmentOne.getId());
            serviceProviderEntity.setStatus(false);
            serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
            serviceProviderEntity.setTransportId(transportTypeOne.getId());
            ServiceProviderDto added= serviceProviderDAO.addServiceProvider(serviceProviderEntity, null, null);
            Assert.assertEquals("TestCityFill", serviceProviderDAO.findById(added.getId()).get().getCity());
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceProviderWithSameName() {
        setUp();
        try {
            ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
            serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
            serviceProviderEntity.setFirstName("TestFirstName");
            serviceProviderEntity.setLastName("TestLastName");
            serviceProviderEntity.setCpr("cprTest");
            serviceProviderEntity.setDepartmentId(departmentOne.getId());
            serviceProviderEntity.setStatus(false);
            serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
            serviceProviderEntity.setTransportId(transportTypeOne.getId());
            serviceProviderDAO.addServiceProvider(serviceProviderEntity, null, null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceProviderWithCompetencies(){
        setUp();
        try {
            ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
            serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
            serviceProviderEntity.setFirstName("TestFirstNameFirst");
            serviceProviderEntity.setLastName("TestLastNameSecond");
            serviceProviderEntity.setCpr("cprTest");
            serviceProviderEntity.setDepartmentId(departmentOne.getId());
            serviceProviderEntity.setStatus(false);
            serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
            serviceProviderEntity.setTransportId(transportTypeOne.getId());
            List<Integer> competencies = new ArrayList<>();
            competencies.add(serviceProviderCompetencyOne.getId());
            competencies.add(serviceProviderCompetencyTwo.getId());
            ServiceProviderDto added = serviceProviderDAO.addServiceProvider(serviceProviderEntity, competencies, null);
            Assert.assertEquals(2, serviceProviderServiceProviderCompetencyDAO.findAllByServiceProviderId(added.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingServiceProviderWithTypes(){
        setUp();
        try {
            ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
            serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
            serviceProviderEntity.setFirstName("TestFirstNameFirst");
            serviceProviderEntity.setLastName("TestLastNameSecond");
            serviceProviderEntity.setCpr("cprTest");
            serviceProviderEntity.setDepartmentId(departmentOne.getId());
            serviceProviderEntity.setStatus(false);
            serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
            serviceProviderEntity.setTransportId(transportTypeOne.getId());
            List<Integer> types = new ArrayList<>();
            types.add(serviceProviderTypeOne.getId());
            types.add(serviceProviderTypeTwo.getId());
            ServiceProviderDto added = serviceProviderDAO.addServiceProvider(serviceProviderEntity, null, types);
            Assert.assertEquals(2, serviceProviderServiceProviderTypeDAO.findAllByServiceProviderId(added.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeleteServiceProvider(){
        setUp();
        try {
           serviceProviderDAO.deleteServiceProvider(serviceProviderOne.getId());
           Assert.assertTrue(serviceProviderDAO.findById(serviceProviderOne.getId()).get().isDeleted());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListAllSizeShouldBeOne(){
        setUp();
        try {
            Assert.assertEquals(1, serviceProviderDAO.listAllServiceProvider(false).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListAllWithDeletedSizeShouldBeOne(){
        setUp();
        try {
            serviceProviderDAO.deleteServiceProvider(serviceProviderOne.getId());
            Assert.assertEquals(1, serviceProviderDAO.listAllServiceProvider(true).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testListAllWithDeletedSizeShouldBeZero(){
        setUp();
        try {
            serviceProviderDAO.deleteServiceProvider(serviceProviderOne.getId());
            Assert.assertEquals(0, serviceProviderDAO.listAllServiceProvider(false).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingServiceProviderNameShouldMatch(){
        setUp();
        try {
            Assert.assertEquals("TestFirstName", serviceProviderDAO.findServiceProvider(serviceProviderOne.getId()).getFirstName());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingServiceProviderWithNotExistingIdThrowsException(){
        setUp();
        try {
            serviceProviderDAO.findServiceProvider(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingServiceProviderByIdThatDoesNotExist(){
        setUp();
        try {
            serviceProviderDAO.deleteServiceProvider(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingServiceProviderNameShouldChange(){
        setUp();
        try{
            serviceProviderOne.setFirstName("TestUpdatedFirstName");
            serviceProviderOne.setCpr("21323123");
            serviceProviderDAO.updateServiceProvider(serviceProviderOne, null, null);
            Assert.assertEquals("TestUpdatedFirstName", serviceProviderDAO.findById(serviceProviderOne.getId()).get().getFirstName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingServiceProviderDoesNotAddOne(){
        setUp();
        try{
            serviceProviderOne.setFirstName("TestUpdatedFirstName");
            serviceProviderOne.setCpr("21323123");
            serviceProviderDAO.updateServiceProvider(serviceProviderOne, null, null);
            Assert.assertEquals(1, serviceProviderDAO.findAll().size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

//    @Test
//    public void testUpdatingServiceProviderChangeNumberOfTypeList(){
//        setUp();
//        try{
//            serviceProviderOne.setFirstName("TestUpdatedFirstName");
//            serviceProviderOne.setCpr("21323123");
//            List<Integer> types = new ArrayList<>();
//            types.add(serviceProviderTypeOne.getId());
//            types.add(serviceProviderTypeTwo.getId());
//            serviceProviderDAO.updateServiceProvider(serviceProviderOne, null, types);
//            List<Integer> types2 = new ArrayList<>();
//            types2.add(serviceProviderTypeOne.getId());
//            serviceProviderDAO.updateServiceProvider(serviceProviderOne, null, types2);
//            Assert.assertEquals(1, serviceProviderServiceProviderTypeDAO.findAllByServiceProviderId(serviceProviderOne.getId()).size());
//        }catch (Exception e){
//            e.printStackTrace();
//            Assert.fail();
//        }finally {
//            tearDown();
//        }
//    }

//    @Test
//    public void testUpdatingServiceProviderChangeCompetenciesList(){
//        setUp();
//        try{
//            serviceProviderOne.setFirstName("TestUpdatedFirstName");
//            serviceProviderOne.setCpr("21323123");
//            List<Integer> competencies = new ArrayList<>();
//            competencies.add(serviceProviderCompetencyOne.getId());
//            competencies.add(serviceProviderCompetencyTwo.getId());
//
//            serviceProviderDAO.updateServiceProvider(serviceProviderOne, competencies, null);
//            List<Integer> competencies2 = new ArrayList<>();
//            competencies2.add(serviceProviderCompetencyOne.getId());
//            serviceProviderDAO.updateServiceProvider(serviceProviderOne, competencies2, null);
//            Assert.assertEquals(1, serviceProviderServiceProviderCompetencyDAO.findAllByServiceProviderId(serviceProviderOne.getId()).size());
//        }catch (Exception e){
//            e.printStackTrace();
//            Assert.fail();
//        }finally {
//            tearDown();
//        }
//    }

    @Test
    public void testUpdatingServiceProviderChangeStateRegion(){
        setUp();
        try{
            serviceProviderOne.setFirstName("TestUpdatedFirstName");
            serviceProviderOne.setCpr("21323123");
            serviceProviderOne.setPostcode("6430");
            serviceProviderDAO.updateServiceProvider(serviceProviderOne, null, null);
            serviceProviderOne.setStateRegion(null);
            serviceProviderOne.setPostcode("8000");
            serviceProviderDAO.updateServiceProvider(serviceProviderOne, null, null);
            Assert.assertEquals("Aarhus", serviceProviderDAO.findById(serviceProviderOne.getId()).get().getStateRegion());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testUpdatingServiceProviderNameThatAlreadyExistsShouldThrowException(){
        setUp();
        try{
            ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
            serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
            serviceProviderEntity.setFirstName("TestFirstNameFirst");
            serviceProviderEntity.setLastName("TestLastNameSecond");
            serviceProviderEntity.setCpr("cprTest");
            serviceProviderEntity.setDepartmentId(departmentOne.getId());
            serviceProviderEntity.setStatus(false);
            serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
            serviceProviderEntity.setTransportId(transportTypeOne.getId());
            ServiceProviderDto added = serviceProviderDAO.addServiceProvider(serviceProviderEntity, null, null);
            ServiceProviderEntity toUpdate = serviceProviderDAO.findById(added.getId()).get();
            toUpdate.setFirstName("TestFirstName");
            toUpdate.setLastName("TestLastName");
            serviceProviderDAO.updateServiceProvider(toUpdate, null, null);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
