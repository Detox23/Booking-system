package API.Repository.AbsenceType;

import API.Exceptions.NotEnoughDataException;
import API.MainApplicationClass;
import API.Models.Database_Entities.AbsenceTypeEntity;
import Shared.ToReturn.AbsenceTypeDto;
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
public class AbsenceTypeRepositoryTest {


    @Autowired
    private AbsenceTypeDAO absenceTypeDAO;

    private AbsenceTypeEntity added;
    private AbsenceTypeEntity added2;


    private void setUp(){
        AbsenceTypeEntity absenceType = new AbsenceTypeEntity();
        absenceType.setAbsenceTypeName("AbsenceTypeTest");
        added = absenceTypeDAO.save(absenceType);

        AbsenceTypeEntity absenceType2 = new AbsenceTypeEntity();
        absenceType2.setAbsenceTypeName("AbsenceTypeTest2");
        added2 = absenceTypeDAO.save(absenceType2);

    }


    private void tearDown(){
        absenceTypeDAO.deleteAll();
        absenceTypeDAO.flush();
    }

    @Test
    public void testUpdatingAbsenceTypeForNameThatExists(){
        setUp();
        try{
            added2.setAbsenceTypeName("AbsenceTypeTest");
            absenceTypeDAO.updateAbsenceType(added2);
            Assert.fail();
        }catch (Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void testOfAddingNameShouldBeAsExpected(){
        setUp();
        try{
            AbsenceTypeEntity absenceTypeTest = new AbsenceTypeEntity();
            absenceTypeTest.setAbsenceTypeName("TestName");
            AbsenceTypeDto added = absenceTypeDAO.addAbsenceType(absenceTypeTest);
            Assert.assertEquals("TestName", absenceTypeDAO.findById(added.getId()).get().getAbsenceTypeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testOfSearchingNameShouldEquals(){
        setUp();
        try{
            AbsenceTypeDto found = absenceTypeDAO.findAbsenceType(added.getId());
            Assert.assertEquals("AbsenceTypeTest", found.getAbsenceTypeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAllSizeShouldBeOne(){
        setUp();
        try{
            Assert.assertEquals(2, absenceTypeDAO.listAbsenceTypes(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAllSizeShouldBeZero(){
        setUp();
        try{
            absenceTypeDAO.deleteAbsenceType(added.getId());
            Assert.assertEquals(1, absenceTypeDAO.listAbsenceTypes(false).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testFindingAllWithDeletedSizeShouldBeTwo(){
        setUp();
        try{
            absenceTypeDAO.deleteAbsenceType(added.getId());
            Assert.assertEquals(2, absenceTypeDAO.listAbsenceTypes(true).size());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }


    @Test
    public void testUpdatingAbsenceTypeNameShouldBeChanged(){
        setUp();
        try{
            AbsenceTypeEntity absenceTypeEntity = new AbsenceTypeEntity();
            absenceTypeEntity.setId(added.getId());
            absenceTypeEntity.setAbsenceTypeName("UpdatedAbsenceTypeID");
            absenceTypeDAO.updateAbsenceType(absenceTypeEntity);
            Assert.assertEquals("UpdatedAbsenceTypeID", absenceTypeDAO.findById(added.getId()).get().getAbsenceTypeName());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void testDeleteAbsenceTypeIsDeletedShouldBeOne(){
        setUp();
        try{
            absenceTypeDAO.deleteAbsenceType(added.getId());
            Assert.assertEquals(true, absenceTypeDAO.findById(added.getId()).get().isDeleted());
        }catch (Exception e){
            Assert.fail();
        }finally {
            tearDown();
        }
    }

    @Test
    public void addingAbsenceTypeWithNameThatAlreadyExistsShouldThrowException(){
        setUp();
        try {
            AbsenceTypeEntity absenceType = new AbsenceTypeEntity();
            absenceType.setAbsenceTypeName("AbsenceTypeTest");
            absenceTypeDAO.addAbsenceType(absenceType);
            Assert.fail();
        }catch(Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void unsuccessfulSearchingShouldThrowException(){
        setUp();
        try{
            absenceTypeDAO.findAbsenceType(-1);
            Assert.fail();
        }catch(Exception e){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }
}
