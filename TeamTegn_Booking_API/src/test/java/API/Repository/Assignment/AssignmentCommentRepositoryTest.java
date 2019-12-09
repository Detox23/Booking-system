package API.Repository.Assignment;

import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.Department.DepartmentDAO;
import API.Repository.ServiceProvider.ServiceProviderDAO;
import API.Repository.ServiceProvider.ServiceProviderPreferredNotificationDAO;
import API.Repository.ServiceProvider.ServiceProviderSourceDAO;
import API.Repository.ServiceProvider.TransportTypeDAO;
import API.Repository.ServiceUser.ServiceUserDAO;
import API.Repository.ServiceUser.ServiceUserStatusDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
public class AssignmentCommentRepositoryTest {

    @Autowired
    private AssignmentCommentDAO assignmentCommentDAO;

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
    private AssignmentCommentEntity assignmentCommentOne;
    private AssignmentCommentEntity assignmentCommentTwo;

    private void setUp(){
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
        departmentEntity.setPostcode("9200");
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

        AssignmentCommentEntity assignmentCommentEntity = new AssignmentCommentEntity();
        assignmentCommentEntity.setAssignmentId(assignmentOne.getId());
        assignmentCommentEntity.setCommentText("This is test comment.");
        assignmentCommentOne = assignmentCommentDAO.save(assignmentCommentEntity);

        AssignmentCommentEntity assignmentCommentEntity2 = new AssignmentCommentEntity();
        assignmentCommentEntity2.setAssignmentId(assignmentOne.getId());
        assignmentCommentEntity2.setCommentText("This is test comment2.");
        assignmentCommentTwo = assignmentCommentDAO.save(assignmentCommentEntity2);

    }

    private void tearDown(){
        assignment_stukYearCodeDAO.deleteAllInBatch();
        assignment_stukYearCodeDAO.flush();
        assignmentServiceProviderDAO.deleteAllInBatch();
        assignmentServiceProviderDAO.flush();
        assignmentAssignmentStatusTypeDAO.deleteAllInBatch();
        assignmentAssignmentStatusTypeDAO.flush();
        assignmentCommentDAO.deleteAllInBatch();
        assignmentCommentDAO.flush();
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
    }


    @Test
    public void testAddingAssignmentComment(){
        setUp();
        try {
            AssignmentCommentEntity assignmentCommentEntity = new AssignmentCommentEntity();
            assignmentCommentEntity.setAssignmentId(assignmentOne.getId());
            assignmentCommentEntity.setCommentText("This is added comment.");
            assignmentCommentDAO.addAssignmentComment(assignmentCommentEntity);
            Assert.assertEquals(3, assignmentCommentDAO.findAllByAssignmentId(assignmentOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentCommentNameShouldMatch(){
        setUp();
        try {
            Assert.assertEquals("This is test comment.", assignmentCommentDAO.findAssignmentComment(assignmentCommentOne.getId()).getCommentText());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void updateAssignmentCommentNameShouldBeChanged(){
        setUp();
        try {
            assignmentCommentOne.setCommentText("Updated comment one text.");
            assignmentCommentDAO.updateAssignmentComment(assignmentCommentOne);
            Assert.assertEquals("Updated comment one text.", assignmentCommentDAO.findAssignmentComment(assignmentCommentOne.getId()).getCommentText());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void updateShouldChangeNotAddListSizeOfListShouldBeTwo(){
        setUp();
        try {
            assignmentCommentOne.setCommentText("Updated comment one text.");
            assignmentCommentDAO.updateAssignmentComment(assignmentCommentOne);
            Assert.assertEquals(2, assignmentCommentDAO.findAllByAssignmentId(assignmentOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void deleteAssignmentCommentListSizeShouldBeOne(){
        setUp();
        try {
            assignmentCommentDAO.deleteAssignmentComment(assignmentCommentOne.getId());
            Assert.assertEquals(1, assignmentCommentDAO.findAllByAssignmentId(assignmentOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void listCommentsForAssignmentSizeShouldBeTwo(){
        setUp();
        try {
            Assert.assertEquals(2, assignmentCommentDAO.listAssignmentComments(assignmentOne.getId()).size());
        } catch (Exception e) {
            Assert.fail();
        } finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentWithNotExistingId(){
        setUp();
        try {
            assignmentCommentDAO.findAssignmentComment(-1);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        } finally {
            tearDown();
        }
    }



}
