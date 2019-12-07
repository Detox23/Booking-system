package API.Repository.Assignment;

import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentStatusEntity;
import Shared.ToReturn.AssignmentStatusDto;
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
public class AssignmentStatusRepositoryTest {

    @Autowired
    private AssignmentStatusDAO assignmentStatusDAO;

    private AssignmentStatusEntity assignmentStatusOne;

    private void setUp(){
        AssignmentStatusEntity assignmentStatusEntity = new AssignmentStatusEntity();
        assignmentStatusEntity.setAssignmentStatusName("AssignmentStatusTestName");
        assignmentStatusEntity.setDeleted(false);
        assignmentStatusOne = assignmentStatusDAO.save(assignmentStatusEntity);
    }

    private void tearDown(){
        assignmentStatusDAO.deleteAllInBatch();
        assignmentStatusDAO.flush();
    }

    @Test
    public void testAddingAssignmentStatusWithSameNameShouldThrowException(){
        setUp();
        try{
            AssignmentStatusEntity assignmentStatusEntity = new AssignmentStatusEntity();
            assignmentStatusEntity.setAssignmentStatusName("AssignmentStatusTestName");
            assignmentStatusEntity.setDeleted(false);
            assignmentStatusDAO.addAssignmentStatus(assignmentStatusEntity);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentStatusShouldPass(){
        setUp();
        try{
            AssignmentStatusEntity assignmentStatusEntity = new AssignmentStatusEntity();
            assignmentStatusEntity.setAssignmentStatusName("AssignmentStatusTestNameTwo");
            assignmentStatusEntity.setDeleted(false);
            AssignmentStatusDto added = assignmentStatusDAO.addAssignmentStatus(assignmentStatusEntity);
            Assert.assertEquals("AssignmentStatusTestNameTwo", assignmentStatusDAO.findById(added.getId()).get().getAssignmentStatusName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testFindingAssignmentInterpretation(){
        setUp();
        try{
            Assert.assertEquals("AssignmentStatusTestName", assignmentStatusDAO.findAssignmentStatus(assignmentStatusOne.getId()).getAssignmentStatusName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentInterpretationWithIdThatDoesNotExist(){
        setUp();
        try{
            assignmentStatusDAO.findAssignmentStatus(-1);
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingAssignmentInterpretationShouldThrowSearchingException(){
        setUp();
        try{
            assignmentStatusDAO.deleteAssignmentStatus(assignmentStatusOne.getId());
            assignmentStatusDAO.findAssignmentStatus(assignmentStatusOne.getId());
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(assignmentStatusDAO.findById(assignmentStatusOne.getId()).get().isDeleted());
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentInterpretationWithoutDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentStatusDAO.deleteAssignmentStatus(assignmentStatusOne.getId());
            Assert.assertEquals(1, assignmentStatusDAO.listAssignmentStatuses(true).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentInterpretationSizeShouldBeOne(){
        setUp();
        try{
            Assert.assertEquals(1, assignmentStatusDAO.listAssignmentStatuses(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testListingAssignmentInterpretationWithoutDeletedListSizeShouldBeOne(){
        setUp();
        try{
            Assert.assertEquals(1, assignmentStatusDAO.listAssignmentStatuses(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void updatingAssignmentInterpretationNameShouldBeChanged(){
        setUp();
        try{
            assignmentStatusOne.setAssignmentStatusName("Updated Name");
            assignmentStatusDAO.updateAssignmentStatus(assignmentStatusOne);
            Assert.assertEquals("Updated Name", assignmentStatusDAO.findAssignmentStatus(assignmentStatusOne.getId()).getAssignmentStatusName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void updatingAssignmentInterpretationForNameThatAlreadyExist(){
        setUp();
        try{
            AssignmentStatusEntity assignmentStatusEntity = new AssignmentStatusEntity();
            assignmentStatusEntity.setAssignmentStatusName("StatusNameTest231");
            assignmentStatusEntity.setDeleted(false);
            AssignmentStatusEntity added = assignmentStatusDAO.save(assignmentStatusEntity);
            added.setAssignmentStatusName("AssignmentStatusTestName");
            assignmentStatusDAO.updateAssignmentStatus(added);
            Assert.fail();
        }catch (DuplicateException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
