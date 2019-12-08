package API.Repository.Assignment;


import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentImportanceEntity;
import Shared.ToReturn.AssignmentImportanceDto;
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
public class AssignmentImportanceRepositoryTest {

    @Autowired
    private AssignmentImportanceDAO assignmentImportanceDAO;

    private AssignmentImportanceEntity assignmentImportanceOne;

    private void setUp(){
        AssignmentImportanceEntity assignmentImportanceEntity = new AssignmentImportanceEntity();
        assignmentImportanceEntity.setImportanceName("TestImportance");
        assignmentImportanceEntity.setDeleted(false);
        assignmentImportanceOne = assignmentImportanceDAO.save(assignmentImportanceEntity);
    }

    private void tearDown(){
        assignmentImportanceDAO.deleteAllInBatch();
        assignmentImportanceDAO.flush();
    }

    @Test
    public void testAddingAssignmentImportanceWithSameNameShouldThrowException(){
        setUp();
        try{
            AssignmentImportanceEntity assignmentImportanceEntity = new AssignmentImportanceEntity();
            assignmentImportanceEntity.setImportanceName("TestImportance");
            assignmentImportanceEntity.setDeleted(false);
            assignmentImportanceDAO.addAssignmentImportance(assignmentImportanceEntity);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentImportanceShouldPass(){
        setUp();
        try{
            AssignmentImportanceEntity assignmentImportanceEntity = new AssignmentImportanceEntity();
            assignmentImportanceEntity.setImportanceName("TestImportance2");
            assignmentImportanceEntity.setDeleted(false);
            AssignmentImportanceDto added = assignmentImportanceDAO.addAssignmentImportance(assignmentImportanceEntity);
            Assert.assertEquals("TestImportance2", assignmentImportanceDAO.findById(added.getId()).get().getImportanceName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testFindingAssignmentImportance(){
        setUp();
        try{
            Assert.assertEquals("TestImportance", assignmentImportanceDAO.findAssignmentImportance(assignmentImportanceOne.getId()).getImportanceName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentImportanceWithIdThatDoesNotExist(){
        setUp();
        try{
            assignmentImportanceDAO.findAssignmentImportance(-1);
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingAssignmentImportanceShouldThrowSearchingException(){
        setUp();
        try{
            assignmentImportanceDAO.deleteAssignmentImportance(assignmentImportanceOne.getId());
            assignmentImportanceDAO.findAssignmentImportance(assignmentImportanceOne.getId());
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(assignmentImportanceDAO.findById(assignmentImportanceOne.getId()).get().isDeleted());
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentImportanceWithoutDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentImportanceDAO.deleteAssignmentImportance(assignmentImportanceOne.getId());
            Assert.assertEquals(1, assignmentImportanceDAO.listAssignmentImportance(true).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentImportanceSizeShouldBeOne(){
        setUp();
        try{
            Assert.assertEquals(1, assignmentImportanceDAO.listAssignmentImportance(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testListingAssignmentImportanceWithoutDeletedListSizeShouldBeOne(){
        setUp();
        try{
            Assert.assertEquals(1, assignmentImportanceDAO.listAssignmentImportance(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void updatingAssignmentImportanceNameShouldBeChanged(){
        setUp();
        try{
            assignmentImportanceOne.setImportanceName("Updated Name");
            assignmentImportanceDAO.updateAssignmentImportance(assignmentImportanceOne);
            Assert.assertEquals("Updated Name", assignmentImportanceDAO.findAssignmentImportance(assignmentImportanceOne.getId()).getImportanceName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void updatingAssignmentImportanceForNameThatAlreadyExist(){
        setUp();
        try{
            AssignmentImportanceEntity assignmentImportanceEntity = new AssignmentImportanceEntity();
            assignmentImportanceEntity.setImportanceName("TestImportance2");
            assignmentImportanceEntity.setDeleted(false);
            AssignmentImportanceEntity added = assignmentImportanceDAO.save(assignmentImportanceEntity);
            added.setImportanceName("TestImportance");
            assignmentImportanceDAO.updateAssignmentImportance(added);
            Assert.fail();
        }catch (DuplicateException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
