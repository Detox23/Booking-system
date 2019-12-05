package API.Repository.AbsenceType;

import API.Exceptions.DuplicateException;
import API.Exceptions.NotEnoughDataException;
import API.Exceptions.NotFoundException;
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



    private void setUp(){
        AbsenceTypeEntity absenceType = new AbsenceTypeEntity();
        absenceType.setAbsenceTypeName("AbsenceTypeTest");
        added = absenceTypeDAO.save(absenceType);
    }


    private void tearDown(){
        absenceTypeDAO.deleteAll();
        absenceTypeDAO.flush();
    }

    @Test
    public void testOfAddingNameShouldBeAsExpected(){
        setUp();
        AbsenceTypeEntity absenceTypeTest = new AbsenceTypeEntity();
        absenceTypeTest.setAbsenceTypeName("TestName");
        AbsenceTypeDto added = absenceTypeDAO.addAbsenceType(absenceTypeTest);
        Assert.assertEquals("TestName", absenceTypeDAO.findById(added.getId()).get().getAbsenceTypeName());
        tearDown();
    }

    @Test
    public void testOfSearchingNameShouldEquals(){
        setUp();
        AbsenceTypeDto found = absenceTypeDAO.findAbsenceType(added.getId());
        Assert.assertEquals("AbsenceTypeTest", found.getAbsenceTypeName());
        tearDown();
    }

    @Test
    public void testFindingAllSizeShouldBeOne(){
        setUp();
        Assert.assertEquals(1, absenceTypeDAO.listAbsenceTypes(false).size());
        tearDown();
    }

    @Test
    public void testFindingAllSizeShouldBeZero(){
        setUp();
        absenceTypeDAO.deleteAbsenceType(added.getId());
        Assert.assertEquals(0, absenceTypeDAO.listAbsenceTypes(false).size());
        tearDown();
    }

    @Test
    public void testFindingAllWithDeletedSizeShouldBeOne(){
        setUp();
        absenceTypeDAO.deleteAbsenceType(added.getId());
        Assert.assertEquals(1, absenceTypeDAO.listAbsenceTypes(true).size());
        tearDown();
    }


    @Test
    public void testUpdatingAbsenceTypeNameShouldBeChanged(){
        setUp();
        AbsenceTypeEntity absenceTypeEntity = new AbsenceTypeEntity();
        absenceTypeEntity.setId(added.getId());
        absenceTypeEntity.setAbsenceTypeName("UpdatedAbsenceTypeID");
        absenceTypeDAO.updateAbsenceType(absenceTypeEntity);
        Assert.assertEquals("UpdatedAbsenceTypeID", absenceTypeDAO.findById(added.getId()).get().getAbsenceTypeName());
        tearDown();
    }

    @Test
    public void testDeleteAbsenceTypeIsDeletedShouldBeOne(){
        setUp();
        absenceTypeDAO.deleteAbsenceType(added.getId());
        Assert.assertEquals(true, absenceTypeDAO.findById(added.getId()).get().isDeleted());
        tearDown();
    }

    @Test
    public void addingAbsenceTypeWithNameThatAlreadyExistsShouldThrowException(){
        setUp();
        try {
            AbsenceTypeEntity absenceType = new AbsenceTypeEntity();
            absenceType.setAbsenceTypeName("AbsenceTypeTest");
            absenceTypeDAO.addAbsenceType(absenceType);
            Assert.fail();
        }catch(DuplicateException duplicateException){
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
        }catch(NotFoundException notFound){
            Assert.assertTrue(true);
        }finally {
            tearDown();
        }
    }

    @Test
    public void notProvidingAbsenceTypeNameShouldThrowException(){
        setUp();
        try{
            AbsenceTypeEntity absenceTypeEntity = new AbsenceTypeEntity();
            absenceTypeDAO.addAbsenceType(absenceTypeEntity);
            Assert.fail();
        }catch (NotEnoughDataException notEnoughDataException){
            Assert.assertTrue(true);
        }
    }

}
