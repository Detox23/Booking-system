package API.Repository.Assignment;

import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.MainApplicationClass;
import API.Models.Database_Entities.AssignmentStatusTypeEntity;
import Shared.ToReturn.AssignmentStatusTypeDto;
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
public class AssignmentStatusTypeRepositoryTest {

    @Autowired
    private AssignmentStatusTypeDAO assignmentStatusTypeDAO;

    private AssignmentStatusTypeEntity assignmentStatusTypeOne;
    private AssignmentStatusTypeEntity assignmentStatusTypeTwo;

    private void setUp() {
        AssignmentStatusTypeEntity assignmentStatusTypeEntity = new AssignmentStatusTypeEntity();
        assignmentStatusTypeEntity.setAssignmentStatusTypeName("StatusTypeOne");
        assignmentStatusTypeEntity.setDisplayOrder(1);
        assignmentStatusTypeEntity.setDeleted(false);
        assignmentStatusTypeOne = assignmentStatusTypeDAO.save(assignmentStatusTypeEntity);

        AssignmentStatusTypeEntity assignmentStatusTypeEntity2 = new AssignmentStatusTypeEntity();
        assignmentStatusTypeEntity2.setAssignmentStatusTypeName("StatusTypeTwo");
        assignmentStatusTypeEntity2.setDisplayOrder(2);
        assignmentStatusTypeEntity2.setDeleted(false);
        assignmentStatusTypeTwo = assignmentStatusTypeDAO.save(assignmentStatusTypeEntity2);
    }

    private void tearDown() {
        assignmentStatusTypeDAO.deleteAllInBatch();
        assignmentStatusTypeDAO.flush();
    }

    @Test
    public void testAddingAssignmentStatusTypeWithSameNameShouldThrowException(){
        setUp();
        try{
            AssignmentStatusTypeEntity assignmentStatusEntity = new AssignmentStatusTypeEntity();
            assignmentStatusEntity.setAssignmentStatusTypeName("StatusTypeTwo");
            assignmentStatusEntity.setDisplayOrder(3);
            assignmentStatusEntity.setDeleted(false);
            assignmentStatusTypeDAO.addAssignmentStatusType(assignmentStatusEntity);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testAddingAssignmentStatusTypeShouldPass(){
        setUp();
        try{
            AssignmentStatusTypeEntity assignmentStatusEntity = new AssignmentStatusTypeEntity();
            assignmentStatusEntity.setAssignmentStatusTypeName("StatusTypeThree");
            assignmentStatusEntity.setDisplayOrder(3);
            assignmentStatusEntity.setDeleted(false);
            AssignmentStatusTypeDto added = assignmentStatusTypeDAO.addAssignmentStatusType(assignmentStatusEntity);
            Assert.assertEquals("StatusTypeThree", assignmentStatusTypeDAO.findById(added.getId()).get().getAssignmentStatusTypeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testFindingAssignmentStatusType(){
        setUp();
        try{
            Assert.assertEquals("StatusTypeOne", assignmentStatusTypeDAO.findAssignmentStatusType(assignmentStatusTypeOne.getId()).getAssignmentStatusTypeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAssignmentStatusTypeWithIdThatDoesNotExist(){
        setUp();
        try{
            assignmentStatusTypeDAO.findAssignmentStatusType(-1);
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeletingAssignmentStatusTypeShouldThrowSearchingException(){
        setUp();
        try{
            assignmentStatusTypeDAO.deleteAssignmentStatusType(assignmentStatusTypeOne.getId());
            assignmentStatusTypeDAO.findAssignmentStatusType(assignmentStatusTypeOne.getId());
            Assert.fail();
        }catch (NotFoundException e){
            Assert.assertTrue(assignmentStatusTypeDAO.findById(assignmentStatusTypeOne.getId()).get().isDeleted());
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentStatusTypeWithoutDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentStatusTypeDAO.deleteAssignmentStatusType(assignmentStatusTypeOne.getId());
            Assert.assertEquals(1, assignmentStatusTypeDAO.listAssignmentStatusTypes(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentStatusTypeWithDeletedListShouldBeOne(){
        setUp();
        try{
            assignmentStatusTypeDAO.deleteAssignmentStatusType(assignmentStatusTypeOne.getId());
            Assert.assertEquals(2, assignmentStatusTypeDAO.listAssignmentStatusTypes(true).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testListingAssignmentStatusTypeSizeShouldBeOne(){
        setUp();
        try{
            int size = assignmentStatusTypeDAO.listAssignmentStatusTypes(false).size();
            Assert.assertEquals(2,size);
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testListingAssignmentStatusTypeWithoutDeletedListSizeShouldBeOne(){
        setUp();
        try{
            Assert.assertEquals(2, assignmentStatusTypeDAO.listAssignmentStatusTypes(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void updatingAssignmentStatusTypeNameShouldBeChanged(){
        setUp();
        try{
            assignmentStatusTypeOne.setAssignmentStatusTypeName("Updated Name");
            assignmentStatusTypeDAO.updateAssignmentStatusType(assignmentStatusTypeOne);
            Assert.assertEquals("Updated Name", assignmentStatusTypeDAO.findAssignmentStatusType(assignmentStatusTypeOne.getId()).getAssignmentStatusTypeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void updatingAssignmentStatusTypeForNameThatAlreadyExist(){
        setUp();
        try{
            AssignmentStatusTypeEntity assignmentStatusTypeEntity = new AssignmentStatusTypeEntity();
            assignmentStatusTypeEntity.setAssignmentStatusTypeName("StatusTypeNameTest");
            assignmentStatusTypeEntity.setDeleted(false);
            assignmentStatusTypeEntity.setDisplayOrder(1);
            AssignmentStatusTypeEntity added = assignmentStatusTypeDAO.save(assignmentStatusTypeEntity);
            added.setAssignmentStatusTypeName("StatusTypeOne");
            assignmentStatusTypeDAO.updateAssignmentStatusType(added);
            Assert.fail();
        }catch (DuplicateException e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

}
