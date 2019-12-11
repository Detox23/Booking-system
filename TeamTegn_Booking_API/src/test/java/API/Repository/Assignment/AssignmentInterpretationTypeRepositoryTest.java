package API.Repository.Assignment;

import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentInterpretationTypeEntity;
import Shared.ToReturn.AssignmentInterpretationTypeDto;
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
public class AssignmentInterpretationTypeRepositoryTest {

    @Autowired
    private AssignmentInterpretationTypeDAO assignmentInterpretationTypeDAO;

    private AssignmentInterpretationTypeEntity assignmentInterpretationTypeOne;

    private void setUp(){
        AssignmentInterpretationTypeEntity assignmentInterpretationTypeEntity = new AssignmentInterpretationTypeEntity();
        assignmentInterpretationTypeEntity.setInterpretationTypeName("InterpretationTypeTest");
        assignmentInterpretationTypeEntity.setDeleted(false);
        assignmentInterpretationTypeOne = assignmentInterpretationTypeDAO.save(assignmentInterpretationTypeEntity);
    }

    private void tearDown(){
        assignmentInterpretationTypeDAO.deleteAllInBatch();
        assignmentInterpretationTypeDAO.flush();
    }

    @Test
    public void testAddingAssignmentInterpretationWithSameNameShouldThrowException(){
        setUp();
        try{
            AssignmentInterpretationTypeEntity assignmentInterpretationTypeEntity = new AssignmentInterpretationTypeEntity();
            assignmentInterpretationTypeEntity.setInterpretationTypeName("InterpretationTypeTest");
            assignmentInterpretationTypeEntity.setDeleted(false);
            assignmentInterpretationTypeDAO.addAssignmentInterpretationType(assignmentInterpretationTypeEntity);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentInterpretationShouldPass(){
        setUp();
        try{
            AssignmentInterpretationTypeEntity assignmentInterpretationTypeEntity = new AssignmentInterpretationTypeEntity();
            assignmentInterpretationTypeEntity.setInterpretationTypeName("InterpretationTypeTestTwo");
            assignmentInterpretationTypeEntity.setDeleted(false);
            AssignmentInterpretationTypeDto added = assignmentInterpretationTypeDAO.addAssignmentInterpretationType(assignmentInterpretationTypeEntity);
            Assert.assertEquals("InterpretationTypeTestTwo", assignmentInterpretationTypeDAO.findById(added.getId()).get().getInterpretationTypeName());
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
            Assert.assertEquals("InterpretationTypeTest", assignmentInterpretationTypeDAO.findAssignmentInterpretationType(assignmentInterpretationTypeOne.getId()).getInterpretationTypeName());
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
            assignmentInterpretationTypeDAO.findAssignmentInterpretationType(-1);
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
            assignmentInterpretationTypeDAO.deleteAssignmentInterpretationType(assignmentInterpretationTypeOne.getId());
            assignmentInterpretationTypeDAO.findAssignmentInterpretationType(assignmentInterpretationTypeOne.getId());
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(assignmentInterpretationTypeDAO.findById(assignmentInterpretationTypeOne.getId()).get().isDeleted());
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentInterpretationWithoutDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentInterpretationTypeDAO.deleteAssignmentInterpretationType(assignmentInterpretationTypeOne.getId());
            Assert.assertEquals(1, assignmentInterpretationTypeDAO.listAssignmentInterpretationTypes(true).size());
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
            Assert.assertEquals(1, assignmentInterpretationTypeDAO.listAssignmentInterpretationTypes(false).size());
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
            Assert.assertEquals(1, assignmentInterpretationTypeDAO.listAssignmentInterpretationTypes(false).size());
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
            assignmentInterpretationTypeOne.setInterpretationTypeName("Updated Name");
            assignmentInterpretationTypeDAO.updateAssignmentInterpretationType(assignmentInterpretationTypeOne);
            Assert.assertEquals("Updated Name", assignmentInterpretationTypeDAO.findAssignmentInterpretationType(assignmentInterpretationTypeOne.getId()).getInterpretationTypeName());
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
            AssignmentInterpretationTypeEntity assignmentInterpretationTypeEntity = new AssignmentInterpretationTypeEntity();
            assignmentInterpretationTypeEntity.setInterpretationTypeName("InterpretationTypeTest231");
            assignmentInterpretationTypeEntity.setDeleted(false);
            AssignmentInterpretationTypeEntity added = assignmentInterpretationTypeDAO.save(assignmentInterpretationTypeEntity);
            added.setInterpretationTypeName("InterpretationTypeTest");
            assignmentInterpretationTypeDAO.updateAssignmentInterpretationType(added);
            Assert.fail();
        }catch (DuplicateException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
