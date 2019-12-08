package API.Repository.Assignment;

import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentStukYearCodeEntity;
import Shared.ToReturn.AssignmentStukYearCodeDto;
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
public class AssignmentStukYearCodeRepositoryTest {

    @Autowired
    private AssignmentSTUKYearCodeDAO assignmentSTUKYearCodeDAO;

    private AssignmentStukYearCodeEntity assignmentStukYearCodeOne;
    private AssignmentStukYearCodeEntity assignmentStukYearCodeTwo;

    private void setUp(){
        AssignmentStukYearCodeEntity assignmentStukYearCodeEntity = new AssignmentStukYearCodeEntity();
        assignmentStukYearCodeEntity.setStukYearCodeName("StukYearCodeOne");
        assignmentStukYearCodeEntity.setDeleted(false);
        assignmentStukYearCodeOne = assignmentSTUKYearCodeDAO.save(assignmentStukYearCodeEntity);

        AssignmentStukYearCodeEntity assignmentStukYearCodeEntity1 = new AssignmentStukYearCodeEntity();
        assignmentStukYearCodeEntity1.setStukYearCodeName("StukYearCodeOne");
        assignmentStukYearCodeEntity1.setDeleted(false);
        assignmentStukYearCodeTwo = assignmentSTUKYearCodeDAO.save(assignmentStukYearCodeEntity1);
    }

    private void tearDown(){
        assignmentSTUKYearCodeDAO.deleteAllInBatch();
        assignmentSTUKYearCodeDAO.flush();
    }

    @Test
    public void testAddingAssignmentStukYearCodeWithSameNameShouldThrowException(){
        setUp();
        try{
            AssignmentStukYearCodeEntity assignmentStukYearCodeEntity = new AssignmentStukYearCodeEntity();
            assignmentStukYearCodeEntity.setStukYearCodeName("StukYearCodeOne");
            assignmentStukYearCodeEntity.setDeleted(false);
            assignmentSTUKYearCodeDAO.addAssigmentStukYearCode(assignmentStukYearCodeEntity);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentStukYearCodeShouldPass(){
        setUp();
        try{
            AssignmentStukYearCodeEntity assignmentStukYearCodeEntity = new AssignmentStukYearCodeEntity();
            assignmentStukYearCodeEntity.setStukYearCodeName("StukYearCodeThree");
            assignmentStukYearCodeEntity.setDeleted(false);
            AssignmentStukYearCodeDto added = assignmentSTUKYearCodeDAO.addAssigmentStukYearCode(assignmentStukYearCodeEntity);
            Assert.assertEquals("StukYearCodeThree", assignmentSTUKYearCodeDAO.findById(added.getId()).get().getStukYearCodeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentStukYearCode(){
        setUp();
        try{
            Assert.assertEquals("StukYearCodeOne", assignmentSTUKYearCodeDAO.findAssignmentStukYearCode(assignmentStukYearCodeOne.getId()).getStukYearCodeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentStukYearCodeWithIdThatDoesNotExist(){
        setUp();
        try{
            assignmentSTUKYearCodeDAO.findAssignmentStukYearCode(-1);
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingAssignmentStukYearCodeShouldThrowSearchingException(){
        setUp();
        try{
            assignmentSTUKYearCodeDAO.deleteAssignmentStukYearCode(assignmentStukYearCodeOne.getId());
            assignmentSTUKYearCodeDAO.findAssignmentStukYearCode(assignmentStukYearCodeOne.getId());
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(assignmentSTUKYearCodeDAO.findById(assignmentStukYearCodeOne.getId()).get().isDeleted());
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentStukYearCodeWithoutDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentSTUKYearCodeDAO.deleteAssignmentStukYearCode(assignmentStukYearCodeOne.getId());
            Assert.assertEquals(1, assignmentSTUKYearCodeDAO.listAssignmentStukYearCodes(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentStukYearCodeWithDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentSTUKYearCodeDAO.deleteAssignmentStukYearCode(assignmentStukYearCodeOne.getId());
            Assert.assertEquals(2, assignmentSTUKYearCodeDAO.listAssignmentStukYearCodes(true).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentStukYearCodeSizeShouldBeOne(){
        setUp();
        try{
            int size = assignmentSTUKYearCodeDAO.listAssignmentStukYearCodes(false).size();
            Assert.assertEquals(2,size);
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testListingAssignmentStukYearCodeWithoutDeletedListSizeShouldBeOne(){
        setUp();
        try{
            Assert.assertEquals(2, assignmentSTUKYearCodeDAO.listAssignmentStukYearCodes(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void updatingAssignmentStukYearCodeNameShouldBeChanged(){
        setUp();
        try{
            assignmentStukYearCodeOne.setStukYearCodeName("Updated Name");
            assignmentSTUKYearCodeDAO.updateAssignmentStukYearCode(assignmentStukYearCodeOne);
            Assert.assertEquals("Updated Name", assignmentSTUKYearCodeDAO.findAssignmentStukYearCode(assignmentStukYearCodeOne.getId()).getStukYearCodeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void updatingAssignmentStukYearCodeForNameThatAlreadyExist(){
        setUp();
        try{
            AssignmentStukYearCodeEntity assignmentStukYearCodeEntity = new AssignmentStukYearCodeEntity();
            assignmentStukYearCodeEntity.setStukYearCodeName("StukYearCodeOne123");
            assignmentStukYearCodeEntity.setDeleted(false);
            AssignmentStukYearCodeEntity added = assignmentSTUKYearCodeDAO.save(assignmentStukYearCodeEntity);
            added.setStukYearCodeName("StukYearCodeOne");
            assignmentSTUKYearCodeDAO.updateAssignmentStukYearCode(added);
            Assert.fail();
        }catch (DuplicateException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
