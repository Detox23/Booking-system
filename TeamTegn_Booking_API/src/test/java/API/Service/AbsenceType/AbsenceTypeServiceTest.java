package API.Service.AbsenceType;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.AbsenceTypeEntity;
import API.Repository.AbsenceType.AbsenceTypeDAO;
import API.Services.AbsenceTypeService.AbsenceTypeService;
import Shared.ForCreation.AbsenceTypeForCreationDto;
import Shared.ForCreation.AbsenceTypeForUpdateDto;
import Shared.ToReturn.AbsenceTypeDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class AbsenceTypeServiceTest {

    @Autowired
    private AbsenceTypeService absenceTypeService;

    private AbsenceTypeDto getAbsenceTypeDto(){
        AbsenceTypeDto absenceType = new AbsenceTypeDto();
        absenceType.setAbsenceTypeName("AbsenceNameTest");
        absenceType.setId(1);
        return absenceType;
    }

    private List<AbsenceTypeDto> getAbsenceTypeDtoList(){
        List<AbsenceTypeDto> absenceTypeDtoList = new ArrayList<AbsenceTypeDto>();
        absenceTypeDtoList.add(new AbsenceTypeDto());
        absenceTypeDtoList.add(new AbsenceTypeDto());
        return absenceTypeDtoList;
    }

    @Test
    public void testAddingAbsenceType(){
        try{
            AbsenceTypeDAO mockAbsenceTypeDAO = SpringBeanMockUtil.mockFieldOnBean(absenceTypeService, AbsenceTypeDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAbsenceTypeDto()).when(mockAbsenceTypeDAO).addAbsenceType(any(AbsenceTypeEntity.class));
            AbsenceTypeForCreationDto absenceTypeForCreationDto = new AbsenceTypeForCreationDto();
            absenceTypeForCreationDto.setAbsenceTypeName("AbsenceNameTest");
            AbsenceTypeDto addedResult = absenceTypeService.addAbsenceType(absenceTypeForCreationDto);
        }catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testFindingAbsenceType(){
        try{
            AbsenceTypeDAO mockAbsenceTypeDAO = SpringBeanMockUtil.mockFieldOnBean(absenceTypeService, AbsenceTypeDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAbsenceTypeDto()).when(mockAbsenceTypeDAO).findAbsenceType(anyInt());
            Assert.assertEquals("AbsenceNameTest", absenceTypeService.findAbsenceType(1).getAbsenceTypeName());
        }catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testUpdatingAbsenceType(){
        try{
            AbsenceTypeDAO mockAbsenceTypeDAO = SpringBeanMockUtil.mockFieldOnBean(absenceTypeService, AbsenceTypeDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAbsenceTypeDto()).when(mockAbsenceTypeDAO).updateAbsenceType(any(AbsenceTypeEntity.class));
            AbsenceTypeForUpdateDto absenceType = new AbsenceTypeForUpdateDto();
            absenceType.setId(1);
            absenceType.setAbsenceTypeName("AbsenceType");
            Assert.assertEquals("AbsenceNameTest", absenceTypeService.updateAbsenceType(absenceType).getAbsenceTypeName());
        }catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testListAbsenceTypes(){
        try{
            AbsenceTypeDAO mockAbsenceTypeDAO = SpringBeanMockUtil.mockFieldOnBean(absenceTypeService, AbsenceTypeDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(getAbsenceTypeDtoList()).when(mockAbsenceTypeDAO).listAbsenceTypes(anyBoolean());
            Assert.assertEquals(2, absenceTypeService.listAbsenceTypes(true).size());
        }catch (Exception e){
            Assert.fail();
        }
    }

    @Test
    public void testDeleteAbsenceType(){
        try{
            AbsenceTypeDAO mockAbsenceTypeDAO = SpringBeanMockUtil.mockFieldOnBean(absenceTypeService, AbsenceTypeDAO.class);
            MockitoAnnotations.initMocks(this);
            Mockito.doReturn(true).when(mockAbsenceTypeDAO).deleteAbsenceType(anyInt());
            Assert.assertTrue(absenceTypeService.deleteAbsenceType(1));
        }catch (Exception e){
            Assert.fail();
        }
    }
}
