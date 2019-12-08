package API.Repository.Assignment;

import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentTypeEntity;
import Shared.ToReturn.AssignmentTypeDto;
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
public class AssignmentTypeRepositoryTest {

    @Autowired
    private AssignmentTypeDAO assignmentTypeDAO;

    private AssignmentTypeEntity assignmentTypeOne;
    private AssignmentTypeEntity assignmentTypeTwo;

    private void setUp(){
        AssignmentTypeEntity assignmentTypeEntity = new AssignmentTypeEntity();
        assignmentTypeEntity.setAssignmentTypeName("TestAssignmentTypeOne");
        assignmentTypeEntity.setDeleted(false);
        assignmentTypeOne = assignmentTypeDAO.save(assignmentTypeEntity);

        AssignmentTypeEntity assignmentTypeEntity1 = new AssignmentTypeEntity();
        assignmentTypeEntity1.setAssignmentTypeName("TestAssignmentTypeTwo");
        assignmentTypeEntity1.setDeleted(false);
        assignmentTypeTwo = assignmentTypeDAO.save(assignmentTypeEntity1);
    }

    private void tearDown(){
        assignmentTypeDAO.deleteAllInBatch();
        assignmentTypeDAO.flush();
    }

    @Test
    public void testAddingAssignmentTypeWithSameNameShouldThrowException(){
        setUp();
        try{
            AssignmentTypeEntity assignmentTypeEntity = new AssignmentTypeEntity();
            assignmentTypeEntity.setAssignmentTypeName("TestAssignmentTypeOne");
            assignmentTypeEntity.setDeleted(false);
            assignmentTypeDAO.addAssignmentType(assignmentTypeEntity);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentTypeShouldPass(){
        setUp();
        try{
            AssignmentTypeEntity assignmentTypeEntity = new AssignmentTypeEntity();
            assignmentTypeEntity.setAssignmentTypeName("TestAssignmentTypeThree");
            assignmentTypeEntity.setDeleted(false);
            AssignmentTypeDto added = assignmentTypeDAO.addAssignmentType(assignmentTypeEntity);
            Assert.assertEquals("TestAssignmentTypeThree", assignmentTypeDAO.findById(added.getId()).get().getAssignmentTypeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentType(){
        setUp();
        try{
            Assert.assertEquals("TestAssignmentTypeOne", assignmentTypeDAO.findAssignmentType(assignmentTypeOne.getId()).getAssignmentTypeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentTypeWithIdThatDoesNotExist(){
        setUp();
        try{
            assignmentTypeDAO.findAssignmentType(-1);
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingAssignmentTypeShouldThrowSearchingException(){
        setUp();
        try{
            assignmentTypeDAO.deleteAssignmentType(assignmentTypeOne.getId());
            assignmentTypeDAO.findAssignmentType(assignmentTypeOne.getId());
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(assignmentTypeDAO.findById(assignmentTypeOne.getId()).get().isDeleted());
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentTypeWithoutDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentTypeDAO.deleteAssignmentType(assignmentTypeOne.getId());
            Assert.assertEquals(1, assignmentTypeDAO.listAssignmentTypes(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentTypeWithDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentTypeDAO.deleteAssignmentType(assignmentTypeOne.getId());
            Assert.assertEquals(2, assignmentTypeDAO.listAssignmentTypes(true).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentTypeSizeShouldBeOne(){
        setUp();
        try{
            int size = assignmentTypeDAO.listAssignmentTypes(false).size();
            Assert.assertEquals(2,size);
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testListingAssignmentTypeWithoutDeletedListSizeShouldBeOne(){
        setUp();
        try{
            Assert.assertEquals(2, assignmentTypeDAO.listAssignmentTypes(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void updatingAssignmentTypeNameShouldBeChanged(){
        setUp();
        try{
            assignmentTypeOne.setAssignmentTypeName("Updated Name");
            assignmentTypeDAO.updateAssignmentType(assignmentTypeOne);
            Assert.assertEquals("Updated Name", assignmentTypeDAO.findAssignmentType(assignmentTypeOne.getId()).getAssignmentTypeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void updatingAssignmentTypeForNameThatAlreadyExist(){
        setUp();
        try{
            AssignmentTypeEntity assignmentTypeEntity = new AssignmentTypeEntity();
            assignmentTypeEntity.setAssignmentTypeName("AssignmentTypeOne123");
            assignmentTypeEntity.setDeleted(false);
            AssignmentTypeEntity added = assignmentTypeDAO.save(assignmentTypeEntity);
            added.setAssignmentTypeName("TestAssignmentTypeOne");
            assignmentTypeDAO.updateAssignmentType(added);
            Assert.fail();
        }catch (DuplicateException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
