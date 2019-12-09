package API.Repository.Assignment;

import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
import API.Repository.Department.DepartmentDAO;
import API.Repository.ServiceProvider.ServiceProviderDAO;
import API.Repository.ServiceProvider.ServiceProviderPreferredNotificationDAO;
import API.Repository.ServiceProvider.ServiceProviderSourceDAO;
import API.Repository.ServiceProvider.TransportTypeDAO;
import API.Repository.ServiceUser.ServiceUserDAO;
import API.Repository.ServiceUser.ServiceUserStatusDAO;
import Shared.ToReturn.AssignmentDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
public class AssignmentRepositoryTests {

    @Autowired
    private AssignmentDAO assignmentDAO;

    @Autowired
    private AssignmentTypeDAO assignmentTypeDAO;

    @Autowired
    private AssignmentImportanceDAO assignmentImportanceDAO;

    @Autowired
    private AssignmentTitleDAO assignmentTitleDAO;

    @Autowired
    private AssignmentInterpretationTypeDAO assignmentInterpretationTypeDAO;

    @Autowired
    private ServiceUserStatusDAO serviceUserStatusDAO;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private ServiceUserDAO serviceUserDAO;

    @Autowired
    private AssignmentStatusDAO assignmentStatusDAO;

    @Autowired
    private VocalLanguagesDAO vocalLanguagesDAO;

    @Autowired
    private ServiceProviderSourceDAO serviceProviderSourceDAO;

    @Autowired
    private TransportTypeDAO transportTypeDAO;

    @Autowired
    private ServiceProviderPreferredNotificationDAO serviceProviderPreferredNotificationDAO;

    @Autowired
    private ServiceProviderDAO serviceProviderDAO;

    @Autowired
    private Assignment_ServiceProviderDAO assignmentServiceProviderDAO;

    @Autowired
    private AssignmentStatusTypeDAO assignmentStatusTypeDAO;

    @Autowired
    private Assignment_AssignmentStatusTypeDAO assignmentAssignmentStatusTypeDAO;

    @Autowired
    private AssignmentSTUKYearCodeDAO assignmentSTUKYearCodeDAO;

    @Autowired
    private Assignment_STUKYearCodeDAO assignment_stukYearCodeDAO;

    @Autowired
    private WI_PostcodeDAO wiPostcodeDAO;
    private AssignmentTypeEntity assignmentTypeOne;
    private AssignmentImportanceEntity assignmentImportanceOne;
    private AssignmentTitleEntity assignmentTitleOne;
    private AssignmentInterpretationTypeEntity assignmentInterpretationTypeOne;
    private ServiceUserStatusEntity serviceUserStatusOne;
    private DepartmentEntity departmentOne;
    private ServiceUserEntity serviceUserOne;
    private AssignmentStatusEntity assignmentStatusOne;
    private VocalLanguagesEntity vocalLanguagesOne;
    private AssignmentEntity assignmentOne;
    private ServiceProviderSourceEntity serviceProviderSourceOne;
    private TransportTypeEntity transportTypeOne;
    private ServiceProviderPreferredNotificationEntity serviceProviderPreferredNotificationOne;
    private ServiceProviderEntity serviceProviderOne;
    private ServiceProviderEntity serviceProviderTwo;
    private AssignmentStatusTypeEntity assignmentStatusTypeOne;
    private AssignmentStatusTypeEntity assignmentStatusTypeTwo;
    private AssignmentStukYearCodeEntity stukYearCodeOne;
    private AssignmentStukYearCodeEntity stukYearCodeTwo;


    private void setUp() {
        //For assignment
        AssignmentTypeEntity assignmentTypeEntity = new AssignmentTypeEntity();
        assignmentTypeEntity.setAssignmentTypeName("TestAssignmentType");
        assignmentTypeEntity.setDeleted(false);
        assignmentTypeOne = assignmentTypeDAO.save(assignmentTypeEntity);

        AssignmentImportanceEntity assignmentImportanceEntity = new AssignmentImportanceEntity();
        assignmentImportanceEntity.setImportanceName("TestImportance");
        assignmentImportanceEntity.setDeleted(false);
        assignmentImportanceOne = assignmentImportanceDAO.save(assignmentImportanceEntity);

        AssignmentTitleEntity assignmentTitleEntity = new AssignmentTitleEntity();
        assignmentTitleEntity.setTitle("TestTitle");
        assignmentTitleEntity.setDeleted(false);
        assignmentTitleOne = assignmentTitleDAO.save(assignmentTitleEntity);

        AssignmentInterpretationTypeEntity assignmentInterpretationTypeEntity = new AssignmentInterpretationTypeEntity();
        assignmentInterpretationTypeEntity.setInterpretationTypeName("TestInterpretationType");
        assignmentInterpretationTypeEntity.setDeleted(false);
        assignmentInterpretationTypeOne = assignmentInterpretationTypeDAO.save(assignmentInterpretationTypeEntity);

        ServiceUserStatusEntity serviceUserStatusEntity = new ServiceUserStatusEntity();
        serviceUserStatusEntity.setStatus("TestServiceUserStatus");
        serviceUserStatusEntity.setDeleted(false);
        serviceUserStatusOne = serviceUserStatusDAO.save(serviceUserStatusEntity);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setCity("TestCity");
        departmentEntity.setPostcode("2313");
        departmentOne = departmentDAO.save(departmentEntity);

        ServiceUserEntity serviceUserEntity = new ServiceUserEntity();
        serviceUserEntity.setFirstName("TestFirstName");
        serviceUserEntity.setDeleted(false);
        serviceUserEntity.setDepartmentId(departmentOne.getId());
        serviceUserEntity.setStatusId(serviceUserStatusOne.getId());
        serviceUserOne = serviceUserDAO.save(serviceUserEntity);

        AssignmentStatusEntity assignmentStatusEntity = new AssignmentStatusEntity();
        assignmentStatusEntity.setAssignmentStatusName("TestAssignmentStatus");
        assignmentStatusEntity.setDeleted(false);
        assignmentStatusOne = assignmentStatusDAO.save(assignmentStatusEntity);

        VocalLanguagesEntity vocalLanguagesEntity = new VocalLanguagesEntity();
        vocalLanguagesEntity.setLanguageName("TestLanguage");
        vocalLanguagesOne = vocalLanguagesDAO.save(vocalLanguagesEntity);

        AssignmentEntity assignmentEntity = new AssignmentEntity();
        assignmentEntity.setAssignmentTypeId(assignmentTypeOne.getId());
        assignmentEntity.setImportanceId(assignmentImportanceOne.getId());
        assignmentEntity.setAssignmentTitle(assignmentTitleOne.getId());
        assignmentEntity.setInterpretationTypeId(assignmentInterpretationTypeOne.getId());
        assignmentEntity.setServiceUserId(serviceUserOne.getId());
        assignmentEntity.setVocalLanguageId(vocalLanguagesOne.getId());
        assignmentEntity.setAssignmentDate(new Date(Calendar.getInstance().getTime().getTime()));
        assignmentEntity.setStartTime(new Timestamp(System.currentTimeMillis()));
        assignmentEntity.setEndTime(new Timestamp(System.currentTimeMillis() + 3600000));
        assignmentEntity.setDeleted(false);
        assignmentEntity.setAssignedStatus(false);
        assignmentOne = assignmentDAO.save(assignmentEntity);

        //For serviceProvider
        ServiceProviderSourceEntity serviceProviderSourceEntity = new ServiceProviderSourceEntity();
        serviceProviderSourceEntity.setProviderSource("TestServiceProviderSource");
        serviceProviderSourceEntity.setDeleted(false);
        serviceProviderSourceOne = serviceProviderSourceDAO.save(serviceProviderSourceEntity);

        TransportTypeEntity transportTypeEntity = new TransportTypeEntity();
        transportTypeEntity.setTransport("TestTransport");
        transportTypeEntity.setDeleted(false);
        transportTypeOne = transportTypeDAO.save(transportTypeEntity);

        ServiceProviderPreferredNotificationEntity providerPreferredNotificationEntity = new ServiceProviderPreferredNotificationEntity();
        providerPreferredNotificationEntity.setNotificationType("TestNotification");
        providerPreferredNotificationEntity.setDeleted(false);
        serviceProviderPreferredNotificationOne = serviceProviderPreferredNotificationDAO.save(providerPreferredNotificationEntity);

        ServiceProviderEntity serviceProviderEntity = new ServiceProviderEntity();
        serviceProviderEntity.setDeleted(false);
        serviceProviderEntity.setDepartmentId(departmentOne.getId());
        serviceProviderEntity.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
        serviceProviderEntity.setTransportId(transportTypeOne.getId());
        serviceProviderEntity.setSource(serviceProviderSourceOne.getId());
        serviceProviderEntity.setStatus(false);
        serviceProviderEntity.setFirstName("ServiceProviderTestNameOne");
        serviceProviderOne = serviceProviderDAO.save(serviceProviderEntity);

        ServiceProviderEntity serviceProviderEntity2 = new ServiceProviderEntity();
        serviceProviderEntity2.setDeleted(false);
        serviceProviderEntity2.setDepartmentId(departmentOne.getId());
        serviceProviderEntity2.setPreferredNotificationId(serviceProviderPreferredNotificationOne.getId());
        serviceProviderEntity2.setTransportId(transportTypeOne.getId());
        serviceProviderEntity2.setSource(serviceProviderSourceOne.getId());
        serviceProviderEntity2.setStatus(false);
        serviceProviderEntity2.setFirstName("ServiceProviderTestNameTwo");
        serviceProviderTwo = serviceProviderDAO.save(serviceProviderEntity);

        //AssignmentStatusTypes
        AssignmentStatusTypeEntity assignmentStatusTypeEntity = new AssignmentStatusTypeEntity();
        assignmentStatusTypeEntity.setAssignmentStatusTypeName("AssignmentStatusType1");
        assignmentStatusTypeEntity.setDisplayOrder(1);
        assignmentStatusTypeEntity.setDeleted(false);
        assignmentStatusTypeOne = assignmentStatusTypeDAO.save(assignmentStatusTypeEntity);

        AssignmentStatusTypeEntity assignmentStatusTypeEntity2 = new AssignmentStatusTypeEntity();
        assignmentStatusTypeEntity2.setAssignmentStatusTypeName("AssignmentStatusType2");
        assignmentStatusTypeEntity2.setDisplayOrder(2);
        assignmentStatusTypeEntity2.setDeleted(false);
        assignmentStatusTypeTwo = assignmentStatusTypeDAO.save(assignmentStatusTypeEntity2);

        //AssignmentSTUKYearCode
        AssignmentStukYearCodeEntity assignmentStukYearCodeEntity = new AssignmentStukYearCodeEntity();
        assignmentStukYearCodeEntity.setStukYearCodeName("TestSTUKYearCode1");
        assignmentStukYearCodeEntity.setDeleted(false);
        stukYearCodeOne = assignmentSTUKYearCodeDAO.save(assignmentStukYearCodeEntity);

        AssignmentStukYearCodeEntity assignmentStukYearCodeEntity2 = new AssignmentStukYearCodeEntity();
        assignmentStukYearCodeEntity2.setStukYearCodeName("TestSTUKYearCode2");
        assignmentStukYearCodeEntity2.setDeleted(false);
        stukYearCodeTwo = assignmentSTUKYearCodeDAO.save(assignmentStukYearCodeEntity2);

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


    }

    private void tearDown() {
        assignment_stukYearCodeDAO.deleteAllInBatch();
        assignment_stukYearCodeDAO.flush();
        assignmentServiceProviderDAO.deleteAllInBatch();
        assignmentServiceProviderDAO.flush();
        assignmentAssignmentStatusTypeDAO.deleteAllInBatch();
        assignmentAssignmentStatusTypeDAO.flush();
        assignmentDAO.deleteAllInBatch();
        assignmentDAO.flush();
        vocalLanguagesDAO.deleteAllInBatch();
        vocalLanguagesDAO.flush();
        assignmentStatusDAO.deleteAllInBatch();
        assignmentStatusDAO.flush();
        serviceUserDAO.deleteAllInBatch();
        serviceUserDAO.flush();
        serviceUserStatusDAO.deleteAllInBatch();
        serviceUserStatusDAO.flush();
        serviceProviderDAO.deleteAllInBatch();
        serviceProviderDAO.flush();
        assignmentSTUKYearCodeDAO.deleteAllInBatch();
        assignmentSTUKYearCodeDAO.flush();
        departmentDAO.deleteAllInBatch();
        departmentDAO.flush();
        assignmentStatusTypeDAO.deleteAllInBatch();
        assignmentStatusTypeDAO.flush();
        serviceProviderSourceDAO.deleteAllInBatch();
        serviceProviderSourceDAO.flush();
        transportTypeDAO.deleteAllInBatch();
        transportTypeDAO.flush();
        serviceProviderPreferredNotificationDAO.deleteAllInBatch();
        serviceProviderPreferredNotificationDAO.flush();
        assignmentInterpretationTypeDAO.deleteAllInBatch();
        assignmentInterpretationTypeDAO.flush();
        assignmentTitleDAO.deleteAllInBatch();
        assignmentTitleDAO.flush();
        assignmentImportanceDAO.deleteAllInBatch();
        assignmentImportanceDAO.flush();
        assignmentTypeDAO.deleteAllInBatch();
        assignmentTypeDAO.flush();
        wiPostcodeDAO.deleteAllInBatch();
        wiPostcodeDAO.flush();
    }


    @Test
    public void testFindingAssignmentByNotExistingIDShouldThrowException() {
        setUp();
        try {
            assignmentDAO.findAssignment(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentShouldPass() {
        setUp();
        try {
            AssignmentEntity assignmentToAdd = new AssignmentEntity();
            assignmentToAdd.setAssignmentTypeId(assignmentTypeOne.getId());
            assignmentToAdd.setImportanceId(assignmentImportanceOne.getId());
            assignmentToAdd.setAssignmentTitle(assignmentTitleOne.getId());
            assignmentToAdd.setInterpretationTypeId(assignmentInterpretationTypeOne.getId());
            assignmentToAdd.setServiceUserId(serviceUserOne.getId());
            assignmentToAdd.setVocalLanguageId(vocalLanguagesOne.getId());
            assignmentToAdd.setStartTime(new Timestamp(System.currentTimeMillis()));
            assignmentToAdd.setEndTime(new Timestamp(System.currentTimeMillis() + 3600000));
            assignmentToAdd.setDeleted(false);
            assignmentToAdd.setAssignedStatus(false);
            AssignmentDto saved = assignmentDAO.addAssignment(assignmentToAdd, null, null, null);
            Assert.assertNotNull(assignmentDAO.findAssignment(saved.getId()));
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentCalculatesTotalHoursCorrectlyShouldBeFive() {
        setUp();
        try {
            AssignmentEntity assignmentToAdd = new AssignmentEntity();
            assignmentToAdd.setAssignmentTypeId(assignmentTypeOne.getId());
            assignmentToAdd.setImportanceId(assignmentImportanceOne.getId());
            assignmentToAdd.setAssignmentTitle(assignmentTitleOne.getId());
            assignmentToAdd.setInterpretationTypeId(assignmentInterpretationTypeOne.getId());
            assignmentToAdd.setServiceUserId(serviceUserOne.getId());
            assignmentToAdd.setVocalLanguageId(vocalLanguagesOne.getId());
            assignmentToAdd.setStartTime(new Timestamp(System.currentTimeMillis()));
            assignmentToAdd.setEndTime(new Timestamp(System.currentTimeMillis() + (3600000 * 5)));
            assignmentToAdd.setDeleted(false);
            assignmentToAdd.setAssignedStatus(false);
            AssignmentDto saved = assignmentDAO.addAssignment(assignmentToAdd, null, null, null);
            Assert.assertEquals(5, assignmentDAO.findAssignment(saved.getId()).getTotalTime());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentWithServiceProvidersAddsThemListSizeShouldBeTwo() {
        setUp();
        try {
            AssignmentEntity assignmentToAdd = new AssignmentEntity();
            assignmentToAdd.setAssignmentTypeId(assignmentTypeOne.getId());
            assignmentToAdd.setImportanceId(assignmentImportanceOne.getId());
            assignmentToAdd.setAssignmentTitle(assignmentTitleOne.getId());
            assignmentToAdd.setInterpretationTypeId(assignmentInterpretationTypeOne.getId());
            assignmentToAdd.setServiceUserId(serviceUserOne.getId());
            assignmentToAdd.setVocalLanguageId(vocalLanguagesOne.getId());
            assignmentToAdd.setStartTime(new Timestamp(System.currentTimeMillis()));
            assignmentToAdd.setEndTime(new Timestamp(System.currentTimeMillis() + (3600000 * 5)));
            assignmentToAdd.setDeleted(false);
            assignmentToAdd.setAssignedStatus(false);
            List<Integer> serviceProviders = new ArrayList<>();
            serviceProviders.add(serviceProviderOne.getId());
            serviceProviders.add(serviceProviderTwo.getId());
            AssignmentDto saved = assignmentDAO.addAssignment(assignmentToAdd, serviceProviders, null, null);
            Assert.assertEquals(2, assignmentServiceProviderDAO.findAllByAssignmentIdIs(saved.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentWithStatusTypesAddsThemListSizeShouldBeTwo() {
        setUp();
        try {
            AssignmentEntity assignmentToAdd = new AssignmentEntity();
            assignmentToAdd.setAssignmentTypeId(assignmentTypeOne.getId());
            assignmentToAdd.setImportanceId(assignmentImportanceOne.getId());
            assignmentToAdd.setAssignmentTitle(assignmentTitleOne.getId());
            assignmentToAdd.setInterpretationTypeId(assignmentInterpretationTypeOne.getId());
            assignmentToAdd.setServiceUserId(serviceUserOne.getId());
            assignmentToAdd.setVocalLanguageId(vocalLanguagesOne.getId());
            assignmentToAdd.setStartTime(new Timestamp(System.currentTimeMillis()));
            assignmentToAdd.setEndTime(new Timestamp(System.currentTimeMillis() + (3600000 * 5)));
            assignmentToAdd.setDeleted(false);
            assignmentToAdd.setAssignedStatus(false);
            List<Integer> statusTypes = new ArrayList<>();
            statusTypes.add(assignmentStatusTypeOne.getId());
            statusTypes.add(assignmentStatusTypeOne.getId());
            AssignmentDto saved = assignmentDAO.addAssignment(assignmentToAdd, null, statusTypes, null);
            Assert.assertEquals(2, assignmentAssignmentStatusTypeDAO.findAllByAssignmentIdIs(saved.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentWith1StukYearCodesAddsThemListSizeShouldBeTwo() {
        setUp();
        try {
            AssignmentEntity assignmentToAdd = new AssignmentEntity();
            assignmentToAdd.setAssignmentTypeId(assignmentTypeOne.getId());
            assignmentToAdd.setImportanceId(assignmentImportanceOne.getId());
            assignmentToAdd.setAssignmentTitle(assignmentTitleOne.getId());
            assignmentToAdd.setInterpretationTypeId(assignmentInterpretationTypeOne.getId());
            assignmentToAdd.setServiceUserId(serviceUserOne.getId());
            assignmentToAdd.setVocalLanguageId(vocalLanguagesOne.getId());
            assignmentToAdd.setStartTime(new Timestamp(System.currentTimeMillis()));
            assignmentToAdd.setEndTime(new Timestamp(System.currentTimeMillis() + (3600000 * 5)));
            assignmentToAdd.setDeleted(false);
            assignmentToAdd.setAssignedStatus(false);
            List<Integer> stukYears = new ArrayList<>();
            stukYears.add(stukYearCodeOne.getId());
            stukYears.add(stukYearCodeTwo.getId());
            AssignmentDto saved = assignmentDAO.addAssignment(assignmentToAdd, null, null, stukYears);
            Assert.assertEquals(2, assignment_stukYearCodeDAO.findAllByAssignmentIdIs(saved.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentByDateShouldReturnOneAssignment() {
        setUp();
        try {
            AssignmentEntity assignmentToAdd = new AssignmentEntity();
            assignmentToAdd.setAssignmentTypeId(assignmentTypeOne.getId());
            assignmentToAdd.setImportanceId(assignmentImportanceOne.getId());
            assignmentToAdd.setAssignmentTitle(assignmentTitleOne.getId());
            assignmentToAdd.setInterpretationTypeId(assignmentInterpretationTypeOne.getId());
            assignmentToAdd.setServiceUserId(serviceUserOne.getId());
            assignmentToAdd.setVocalLanguageId(vocalLanguagesOne.getId());
            assignmentToAdd.setAssignmentDate(new Date(Calendar.getInstance().getTime().getTime() + 172800000));
            assignmentToAdd.setStartTime(new Timestamp(System.currentTimeMillis()));
            assignmentToAdd.setEndTime(new Timestamp(System.currentTimeMillis() + 3600000));
            assignmentToAdd.setDeleted(false);
            assignmentToAdd.setAssignedStatus(false);
            assignmentDAO.addAssignment(assignmentToAdd, null, null, null);
            Assert.assertEquals(1, assignmentDAO.listAllAssignments(new Date(Calendar.getInstance().getTime().getTime())).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentPage() {
        setUp();
        try {
            Assert.assertEquals(1, assignmentDAO.listAssignmentsPage(PageRequest.of(0, 10)).getNumberOfElements());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }


    @Test
    public void testUpdateAssignmentCityShouldBeDifferent() {
        setUp();
        try {
            assignmentOne.setDestinationCity("TestUpdatedCity");
            assignmentDAO.updateAssignment(assignmentOne, null, null, null);
            AssignmentEntity found = assignmentDAO.findById(assignmentOne.getId()).get();
            Assert.assertEquals("TestUpdatedCity", found.getDestinationCity());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdateWithListOfServiceProvidersShouldAddThem() {
        setUp();
        try {
            List<Integer> serviceProviders = new ArrayList<>();
            serviceProviders.add(serviceProviderOne.getId());
            serviceProviders.add(serviceProviderTwo.getId());
            assignmentDAO.updateAssignment(assignmentOne, serviceProviders, null, null);
            Assert.assertEquals(2, assignmentServiceProviderDAO.findAllByAssignmentIdIs(assignmentOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdateWithListOfStatusTypesShouldAddThem() {
        setUp();
        try {
            List<Integer> statusTypes = new ArrayList<>();
            statusTypes.add(assignmentStatusTypeOne.getId());
            statusTypes.add(assignmentStatusTypeOne.getId());
            assignmentDAO.updateAssignment(assignmentOne, null, statusTypes, null);
            Assert.assertEquals(2, assignmentAssignmentStatusTypeDAO.findAllByAssignmentIdIs(assignmentOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testUpdateWithListOfStukYearCodesShouldAddThem() {
        setUp();
        try {
            List<Integer> stukYears = new ArrayList<>();
            stukYears.add(stukYearCodeOne.getId());
            stukYears.add(stukYearCodeTwo.getId());
            assignmentDAO.updateAssignment(assignmentOne, null, null, stukYears);
            Assert.assertEquals(2, assignment_stukYearCodeDAO.findAllByAssignmentIdIs(assignmentOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFillStateRegionWhileProvidingPostCodeStateRegionShouldEquals() {
        setUp();
        try {
            assignmentOne.setDestinationPostCode("8000");
            assignmentDAO.updateAssignment(assignmentOne, null, null, null);
            AssignmentEntity found = assignmentDAO.findById(assignmentOne.getId()).get();
            Assert.assertEquals("Aarhus", found.getDestinationStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFillStateRegionWhileProvidingPostCodeStateRegionShouldEqualsSecond() {
        setUp();
        try {
            assignmentOne.setDestinationPostCode("6430");
            assignmentDAO.updateAssignment(assignmentOne, null, null, null);
            AssignmentEntity found = assignmentDAO.findById(assignmentOne.getId()).get();
            Assert.assertEquals("Fredericia", found.getDestinationStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFillStateRegionWhileProvidingPostCodeStateRegionShouldEqualsThird() {
        setUp();
        try {
            assignmentOne.setDestinationPostCode("4773");
            assignmentDAO.updateAssignment(assignmentOne, null, null, null);
            AssignmentEntity found = assignmentDAO.findById(assignmentOne.getId()).get();
            Assert.assertEquals("Copenhagen", found.getDestinationStateRegion());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testDeleteAssignmentSearchingForItShouldThrowException() {
        setUp();
        try {
            assignmentDAO.deleteAssignment(assignmentOne.getId());
            assignmentDAO.findAssignment(assignmentOne.getId());
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }
}