package API.Repository.Assignment;

import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentTitleEntity;
import Shared.ToReturn.AssignmentTitleDto;
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
public class AssignmentTitleRepositoryTest {

    @Autowired
    private AssignmentTitleDAO assignmentTitleDAO;

    private AssignmentTitleEntity assignmentTitleOne;
    private AssignmentTitleEntity assignmentTitleTwo;

    private void setUp(){
        AssignmentTitleEntity assignmentTitleEntity = new AssignmentTitleEntity();
        assignmentTitleEntity.setTitle("TestTitleOne");
        assignmentTitleEntity.setDeleted(false);
        assignmentTitleOne = assignmentTitleDAO.save(assignmentTitleEntity);

        AssignmentTitleEntity assignmentTitleEntity1 = new AssignmentTitleEntity();
        assignmentTitleEntity1.setTitle("TestTitleTwo");
        assignmentTitleEntity1.setDeleted(false);
        assignmentTitleTwo = assignmentTitleDAO.save(assignmentTitleEntity1);
    }

    private void tearDown(){
        assignmentTitleDAO.deleteAllInBatch();
        assignmentTitleDAO.flush();
    }

    @Test
    public void testAddingAssignmentTitleWithSameNameShouldThrowException(){
        setUp();
        try{
            AssignmentTitleEntity assignmentTitleEntity = new AssignmentTitleEntity();
            assignmentTitleEntity.setTitle("TestTitleOne");
            assignmentTitleEntity.setDeleted(false);
            assignmentTitleDAO.addAssignmentTitle(assignmentTitleEntity);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentTitleShouldPass(){
        setUp();
        try{
            AssignmentTitleEntity assignmentTitleEntity = new AssignmentTitleEntity();
            assignmentTitleEntity.setTitle("TestTitleThree");
            assignmentTitleEntity.setDeleted(false);
            AssignmentTitleDto added = assignmentTitleDAO.addAssignmentTitle(assignmentTitleEntity);
            Assert.assertEquals("TestTitleThree", assignmentTitleDAO.findById(added.getId()).get().getTitle());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentTitle(){
        setUp();
        try{
            Assert.assertEquals("TestTitleOne", assignmentTitleDAO.findAssignmentTitle(assignmentTitleOne.getId()).getTitle());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentTitleWithIdThatDoesNotExist(){
        setUp();
        try{
            assignmentTitleDAO.findAssignmentTitle(-1);
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingAssignmentTitleShouldThrowSearchingException(){
        setUp();
        try{
            assignmentTitleDAO.deleteAssignmentTitle(assignmentTitleOne.getId());
            assignmentTitleDAO.findAssignmentTitle(assignmentTitleOne.getId());
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(assignmentTitleDAO.findById(assignmentTitleOne.getId()).get().getDeleted());
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentTitleWithoutDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentTitleDAO.deleteAssignmentTitle(assignmentTitleOne.getId());
            Assert.assertEquals(1, assignmentTitleDAO.listAssignmentTitles(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentTitleWithDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentTitleDAO.deleteAssignmentTitle(assignmentTitleOne.getId());
            Assert.assertEquals(2, assignmentTitleDAO.listAssignmentTitles(true).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentTitleSizeShouldBeOne(){
        setUp();
        try{
            int size = assignmentTitleDAO.listAssignmentTitles(false).size();
            Assert.assertEquals(2,size);
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testListingAssignmentTitleWithoutDeletedListSizeShouldBeOne(){
        setUp();
        try{
            Assert.assertEquals(2, assignmentTitleDAO.listAssignmentTitles(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void updatingAssignmentTitleNameShouldBeChanged(){
        setUp();
        try{
            assignmentTitleOne.setTitle("Updated Name");
            assignmentTitleDAO.updateAssignmentTitle(assignmentTitleOne);
            Assert.assertEquals("Updated Name", assignmentTitleDAO.findAssignmentTitle(assignmentTitleOne.getId()).getTitle());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void updatingAssignmentTitleForNameThatAlreadyExist(){
        setUp();
        try{
            AssignmentTitleEntity assignmentTitleEntity = new AssignmentTitleEntity();
            assignmentTitleEntity.setTitle("AssignmentTitleOne123");
            assignmentTitleEntity.setDeleted(false);
            AssignmentTitleEntity added = assignmentTitleDAO.save(assignmentTitleEntity);
            added.setTitle("TestTitleOne");
            assignmentTitleDAO.updateAssignmentTitle(added);
            Assert.fail();
        }catch (DuplicateException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
