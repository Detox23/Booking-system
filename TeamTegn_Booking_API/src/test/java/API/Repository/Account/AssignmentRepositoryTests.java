package API.Repository.Account;

import API.Database_Entities.AccountEntity;
import API.Database_Entities.AssignmentEntity;
import API.Exceptions.NotEnoughDataException;
import API.MainApplicationClass;
import API.Repository.Assignment.AssignmentDAO;
import API.Repository.Assignment.AssignmentDAOImpl;
import Shared.ForCreation.AssignmentForCreationDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class AssignmentRepositoryTests {
    @Autowired
    private AssignmentDAO assignmentDAO;
    @Autowired
    private AssignmentDAOImpl repositoryUnderTest;

    @Autowired
    private ModelMapper mapper;


    private int assignmentOneId;
    private int assignmentTwoId;

    private void setUp() {

        AssignmentForCreationDto one = new AssignmentForCreationDto();
        one.setAssignmentTitle("Assignment1");
        one.setServiceUserId(1);
        one.setStartTime(new Timestamp(System.currentTimeMillis()));
        one.setEndTime(new Timestamp(System.currentTimeMillis()));
        one.setTotalTime(100);
        one.setAssignedStatus(true);

        AssignmentForCreationDto two = new AssignmentForCreationDto();
        two.setAssignmentTitle("Assignment2");
        two.setServiceUserId(2);
        two.setStartTime(new Timestamp(System.currentTimeMillis()));
        two.setEndTime(new Timestamp(System.currentTimeMillis()));
        two.setTotalTime(200);
        two.setAssignedStatus(true);

        AssignmentEntity ae1 = assignmentDAO.save(mapper.map(one, AssignmentEntity.class));
        AssignmentEntity ae2 = assignmentDAO.save(mapper.map(two, AssignmentEntity.class));
        assignmentOneId = (ae1.getId());
        assignmentTwoId = (ae2.getId());

    }


    private void setDown() {
        assignmentDAO.deleteAll();
        assignmentDAO.flush();

    }

    @Test
    public void listAll_AccountsSize_ShouldBeTwo() {
        setUp();
        try {
            List<AssignmentEntity> returnList = repositoryUnderTest.listAll();
            Assert.assertEquals(2, returnList.size());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            setDown();
        }}

        @Test
    public void listAll_Page_ShouldHaveSize5() {
        setUp();
        try {
            Page<AssignmentEntity> returnList = repositoryUnderTest.listAll(PageRequest.of(0, 5, Sort.Direction.DESC, "id"));

            Assert.assertEquals(5, returnList.getPageable().getPageSize());
            Assert.assertEquals(0, returnList.getPageable().getPageNumber());
            Assert.assertEquals(assignmentTwoId, ((AssignmentEntity) returnList.toList().toArray()[0]).getId());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            setDown();
        }}

    @Test
    public void getOne_returnedObject_ShouldBeNull() {
        setUp();
        try {
            AssignmentEntity returnObject = repositoryUnderTest.getOne(1010);
            Assert.assertNull(returnObject);
        } catch (Exception error) {
            Assert.fail();
        } finally {
            setDown();
        }}

    @Test
    public void getOne_ReturnedObject_ShouldBeValid() {
        setUp();
        try {
            AssignmentEntity returnObject = repositoryUnderTest.getOne(assignmentOneId);
            Assert.assertNotNull(returnObject);
            Assert.assertEquals("Assignment1", returnObject.getAssignmentTitle());
            Assert.assertEquals(1, returnObject.getServiceUserId());
            Assert.assertEquals(100, returnObject.getTotalTime());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            setDown();
        }
    }
    @Test
    public void addOne_returnedObject_ShouldBeValid() {
        setUp();
        try {
            AssignmentEntity ae = new AssignmentEntity();
            ae.setDestinationCity("Aalborg");
            ae.setAssignmentEndDate(Date.valueOf(LocalDate.of(2019, 9,10)));
            ae.setServiceUserId(100);
            ae.setStartTime(new Timestamp(System.currentTimeMillis()));
            ae.setEndTime(new Timestamp(System.currentTimeMillis()));
            ae.setTotalTime(100);
            ae.setAssignedStatus(true);
            AssignmentEntity returnObject = repositoryUnderTest.addOne(ae);
            Assert.assertNotNull(returnObject);
            Assert.assertEquals(Date.valueOf(LocalDate.of(2019, 9,10)), returnObject.getAssignmentEndDate());
        } catch (Exception error) {
            Assert.fail();
        } finally {
            setDown();
        }
    }

    @Test
    public void addOne_ObjectIsInvalid_ShouldThrowException() {
        setUp();
        try {
            AssignmentEntity ae = new AssignmentEntity();
            repositoryUnderTest.addOne(ae);
        } catch (NotEnoughDataException error) {
            Assert.assertEquals(NotEnoughDataException.class, error.getClass());
        } finally {
            setDown();
        }}

    @Test
    public void deleteOne_Result_ShouldBeTrue() {
        setUp();
        try {
            boolean result  = repositoryUnderTest.deleteOne(assignmentOneId);
            Assert.assertEquals(true, result);
        } catch (Exception error) {
            Assert.fail();
        } finally {
            setDown();
        }}


    }

