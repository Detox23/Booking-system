package API.Service.AssignmentService;

import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.*;
import API.Repository.Assignment.*;
import API.Repository.ServiceProvider.ServiceProviderDAO;
import API.Services.AssignmentService.AssignmentService;
import Shared.ForCreation.AssignmentForCreationDto;
import Shared.ForCreation.AssignmentForUpdateDto;
import Shared.ToReturn.AssignmentDto;
import Shared.ToReturn.AssignmentStatusTypeDto;
import Shared.ToReturn.AssignmentStukYearCodeDto;
import Shared.ToReturn.ServiceProviderDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class AssignmentServiceTest {

    @Autowired
    private AssignmentService assignmentService;

    private AssignmentDto getAssignmentDto() {
        AssignmentDto returnAssignment = new AssignmentDto();
        returnAssignment.setAssignmentTitle("Test title");
        returnAssignment.setVocalLanguageId(3);
        return returnAssignment;
    }
    private Optional<VocalLanguagesEntity> getVocalLanguageEntity(){
        return Optional.of(new VocalLanguagesEntity());
    }

    private Optional<ServiceProviderDto> getServiceProviderDto(){
        return Optional.of(new ServiceProviderDto());
    }

    private AssignmentStatusTypeDto getAssignmentStatusTypeDto(){
        return new AssignmentStatusTypeDto();
    }

    private AssignmentStukYearCodeDto getAssignmentStukYearCodeDto(){
        return new AssignmentStukYearCodeDto();
    }

    private Page<AssignmentDto> getAssignmentDtoPage() {
        List<AssignmentDto> returnList = new ArrayList<>();
        returnList.add(new AssignmentDto());
        returnList.add(new AssignmentDto());
        returnList.add(new AssignmentDto());
        returnList.add(new AssignmentDto());
        returnList.add(new AssignmentDto());
        returnList.add(new AssignmentDto());
        return new PageImpl<AssignmentDto>(returnList);
    }

    private List<AssignmentDto> getAssignmentDtoList() {
        List<AssignmentDto> returnList = new ArrayList<>();
        returnList.add(new AssignmentDto());
        returnList.add(new AssignmentDto());
        returnList.add(new AssignmentDto());
        returnList.add(new AssignmentDto());
        returnList.add(new AssignmentDto());
        returnList.add(new AssignmentDto());
        return returnList;
    }

    private List<AssignmentAssignmentStatusTypeEntity> getAssignmentStatusTypesEntities(){
        List<AssignmentAssignmentStatusTypeEntity> returnList = new ArrayList<>();
        returnList.add(new AssignmentAssignmentStatusTypeEntity());
        returnList.add(new AssignmentAssignmentStatusTypeEntity());
        returnList.add(new AssignmentAssignmentStatusTypeEntity());
        returnList.add(new AssignmentAssignmentStatusTypeEntity());
        return returnList;
    }

    private List<AssignmentServiceProviderEntity> getAssignmentServiceProviderEntities(){
        List<AssignmentServiceProviderEntity> returnList = new ArrayList<>();
        returnList.add(new AssignmentServiceProviderEntity());
        returnList.add(new AssignmentServiceProviderEntity());
        returnList.add(new AssignmentServiceProviderEntity());
        returnList.add(new AssignmentServiceProviderEntity());
        return returnList;
    }

    private List<Assignment_StukYearCodeEntity> getAssignmentStukYearCodeEntities() {
        List<Assignment_StukYearCodeEntity> returnList = new ArrayList<>();
        returnList.add(new Assignment_StukYearCodeEntity());
        returnList.add(new Assignment_StukYearCodeEntity());
        returnList.add(new Assignment_StukYearCodeEntity());
        returnList.add(new Assignment_StukYearCodeEntity());
        return returnList;
    }



    @Test
    public void testAddingAssignmentSizeOfStukYearCodesShouldBeFour(){
        AssignmentDAO mockAssignmentDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentDAO.class);
        Assignment_STUKYearCodeDAO mockAssignment_STUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_STUKYearCodeDAO.class);
        Assignment_ServiceProviderDAO mockAssignmentServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_ServiceProviderDAO.class);
        Assignment_AssignmentStatusTypeDAO mockAssignmentAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_AssignmentStatusTypeDAO.class);
        ServiceProviderDAO mockServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, ServiceProviderDAO.class);
        AssignmentStatusTypeDAO mockAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentStatusTypeDAO.class);
        AssignmentSTUKYearCodeDAO mockAssignmentSTUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentSTUKYearCodeDAO.class);
        VocalLanguagesDAO mockVocalLanguages = SpringBeanMockUtil.mockFieldOnBean(assignmentService, VocalLanguagesDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentDto()).when(mockAssignmentDAO).addAssignment(any(AssignmentEntity.class), anyList(), anyList(), anyList());
        Mockito.doReturn(getAssignmentServiceProviderEntities()).when(mockAssignmentServiceProviderDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProviderDAO).findById(anyInt());
        Mockito.doReturn(getAssignmentStatusTypesEntities()).when(mockAssignmentAssignmentStatusTypeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStatusTypeDto()).when(mockAssignmentStatusTypeDAO).findAssignmentStatusType(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeEntities()).when(mockAssignment_STUKYearCodeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeDto()).when(mockAssignmentSTUKYearCodeDAO).findAssignmentStukYearCode(anyInt());
        Mockito.doReturn(getVocalLanguageEntity()).when(mockVocalLanguages).findById(anyInt());
        AssignmentForCreationDto assignmentForCreationDto = new AssignmentForCreationDto();
        assignmentForCreationDto.setAssignmentStatusTypeIds(new ArrayList<>());
        assignmentForCreationDto.setStukYearCodes(new ArrayList<>());
        assignmentForCreationDto.setServiceProviders(new ArrayList<>());
        Assert.assertEquals(4, assignmentService.addAssignment(assignmentForCreationDto).getStukYearCodes().size());
    }

    @Test
    public void testAddingAssignmentSizeOfServiceProvidersShouldBeFour(){
        AssignmentDAO mockAssignmentDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentDAO.class);
        Assignment_STUKYearCodeDAO mockAssignment_STUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_STUKYearCodeDAO.class);
        Assignment_ServiceProviderDAO mockAssignmentServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_ServiceProviderDAO.class);
        Assignment_AssignmentStatusTypeDAO mockAssignmentAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_AssignmentStatusTypeDAO.class);
        ServiceProviderDAO mockServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, ServiceProviderDAO.class);
        AssignmentStatusTypeDAO mockAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentStatusTypeDAO.class);
        AssignmentSTUKYearCodeDAO mockAssignmentSTUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentSTUKYearCodeDAO.class);
        VocalLanguagesDAO mockVocalLanguages = SpringBeanMockUtil.mockFieldOnBean(assignmentService, VocalLanguagesDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentDto()).when(mockAssignmentDAO).addAssignment(any(AssignmentEntity.class), anyList(), anyList(), anyList());
        Mockito.doReturn(getAssignmentServiceProviderEntities()).when(mockAssignmentServiceProviderDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProviderDAO).findById(anyInt());
        Mockito.doReturn(getAssignmentStatusTypesEntities()).when(mockAssignmentAssignmentStatusTypeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStatusTypeDto()).when(mockAssignmentStatusTypeDAO).findAssignmentStatusType(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeEntities()).when(mockAssignment_STUKYearCodeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeDto()).when(mockAssignmentSTUKYearCodeDAO).findAssignmentStukYearCode(anyInt());
        Mockito.doReturn(getVocalLanguageEntity()).when(mockVocalLanguages).findById(anyInt());
        AssignmentForCreationDto assignmentForCreationDto = new AssignmentForCreationDto();
        assignmentForCreationDto.setAssignmentStatusTypeIds(new ArrayList<>());
        assignmentForCreationDto.setStukYearCodes(new ArrayList<>());
        assignmentForCreationDto.setServiceProviders(new ArrayList<>());
        Assert.assertEquals(4, assignmentService.addAssignment(assignmentForCreationDto).getServiceProviders().size());
    }

    @Test
    public void testAddingAssignmentSizeOfStatusTypesShouldBeFour(){
        AssignmentDAO mockAssignmentDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentDAO.class);
        Assignment_STUKYearCodeDAO mockAssignment_STUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_STUKYearCodeDAO.class);
        Assignment_ServiceProviderDAO mockAssignmentServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_ServiceProviderDAO.class);
        Assignment_AssignmentStatusTypeDAO mockAssignmentAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_AssignmentStatusTypeDAO.class);
        ServiceProviderDAO mockServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, ServiceProviderDAO.class);
        AssignmentStatusTypeDAO mockAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentStatusTypeDAO.class);
        AssignmentSTUKYearCodeDAO mockAssignmentSTUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentSTUKYearCodeDAO.class);
        VocalLanguagesDAO mockVocalLanguages = SpringBeanMockUtil.mockFieldOnBean(assignmentService, VocalLanguagesDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentDto()).when(mockAssignmentDAO).addAssignment(any(AssignmentEntity.class), anyList(), anyList(), anyList());
        Mockito.doReturn(getAssignmentServiceProviderEntities()).when(mockAssignmentServiceProviderDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProviderDAO).findById(anyInt());
        Mockito.doReturn(getAssignmentStatusTypesEntities()).when(mockAssignmentAssignmentStatusTypeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStatusTypeDto()).when(mockAssignmentStatusTypeDAO).findAssignmentStatusType(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeEntities()).when(mockAssignment_STUKYearCodeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeDto()).when(mockAssignmentSTUKYearCodeDAO).findAssignmentStukYearCode(anyInt());
        Mockito.doReturn(getVocalLanguageEntity()).when(mockVocalLanguages).findById(anyInt());
        AssignmentForCreationDto assignmentForCreationDto = new AssignmentForCreationDto();
        assignmentForCreationDto.setAssignmentStatusTypeIds(new ArrayList<>());
        assignmentForCreationDto.setStukYearCodes(new ArrayList<>());
        assignmentForCreationDto.setServiceProviders(new ArrayList<>());
        Assert.assertEquals(4, assignmentService.addAssignment(assignmentForCreationDto).getAssignmentStatusTypeIds().size());
    }

    @Test
    public void testDeletingAssignmentShouldBeTrue(){
        AssignmentDAO mockAssignmentDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockAssignmentDAO).deleteAssignment(anyInt());
        Assert.assertEquals(true, assignmentService.deleteAssignment(0));
    }

    @Test
    public void testFindingAssignment(){
        AssignmentDAO mockAssignmentDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentDAO.class);
        Assignment_STUKYearCodeDAO mockAssignment_STUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_STUKYearCodeDAO.class);
        Assignment_ServiceProviderDAO mockAssignmentServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_ServiceProviderDAO.class);
        Assignment_AssignmentStatusTypeDAO mockAssignmentAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_AssignmentStatusTypeDAO.class);
        ServiceProviderDAO mockServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, ServiceProviderDAO.class);
        AssignmentStatusTypeDAO mockAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentStatusTypeDAO.class);
        AssignmentSTUKYearCodeDAO mockAssignmentSTUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentSTUKYearCodeDAO.class);
        VocalLanguagesDAO mockVocalLanguages = SpringBeanMockUtil.mockFieldOnBean(assignmentService, VocalLanguagesDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentDto()).when(mockAssignmentDAO).findAssignment(anyInt());
        Mockito.doReturn(getAssignmentServiceProviderEntities()).when(mockAssignmentServiceProviderDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProviderDAO).findById(anyInt());
        Mockito.doReturn(getAssignmentStatusTypesEntities()).when(mockAssignmentAssignmentStatusTypeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStatusTypeDto()).when(mockAssignmentStatusTypeDAO).findAssignmentStatusType(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeEntities()).when(mockAssignment_STUKYearCodeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeDto()).when(mockAssignmentSTUKYearCodeDAO).findAssignmentStukYearCode(anyInt());
        Mockito.doReturn(getVocalLanguageEntity()).when(mockVocalLanguages).findById(anyInt());
        Assert.assertEquals("Test title", assignmentService.findAssignment(1).getAssignmentTitle());
    }

    @Test
    public void testUpdatingAssignmentTitleShouldMatch(){
        AssignmentDAO mockAssignmentDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentDAO.class);
        Assignment_STUKYearCodeDAO mockAssignment_STUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_STUKYearCodeDAO.class);
        Assignment_ServiceProviderDAO mockAssignmentServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_ServiceProviderDAO.class);
        Assignment_AssignmentStatusTypeDAO mockAssignmentAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_AssignmentStatusTypeDAO.class);
        ServiceProviderDAO mockServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, ServiceProviderDAO.class);
        AssignmentStatusTypeDAO mockAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentStatusTypeDAO.class);
        AssignmentSTUKYearCodeDAO mockAssignmentSTUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentSTUKYearCodeDAO.class);
        VocalLanguagesDAO mockVocalLanguages = SpringBeanMockUtil.mockFieldOnBean(assignmentService, VocalLanguagesDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentDto()).when(mockAssignmentDAO).updateAssignment(any(AssignmentEntity.class), anyList(), anyList(), anyList());
        Mockito.doReturn(getAssignmentServiceProviderEntities()).when(mockAssignmentServiceProviderDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProviderDAO).findById(anyInt());
        Mockito.doReturn(getAssignmentStatusTypesEntities()).when(mockAssignmentAssignmentStatusTypeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStatusTypeDto()).when(mockAssignmentStatusTypeDAO).findAssignmentStatusType(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeEntities()).when(mockAssignment_STUKYearCodeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeDto()).when(mockAssignmentSTUKYearCodeDAO).findAssignmentStukYearCode(anyInt());
        Mockito.doReturn(getVocalLanguageEntity()).when(mockVocalLanguages).findById(anyInt());
        AssignmentForUpdateDto assignmentForUpdateDto = new AssignmentForUpdateDto();
        assignmentForUpdateDto.setAssignmentStatusTypeIds(new ArrayList<>());
        assignmentForUpdateDto.setStukYearCodes(new ArrayList<>());
        assignmentForUpdateDto.setServiceProviders(new ArrayList<>());
        Assert.assertEquals("Test title", assignmentService.updateAssignment(assignmentForUpdateDto).getAssignmentTitle());
    }

    @Test
    public void testListingAssignments(){
        AssignmentDAO mockAssignmentDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentDAO.class);
        Assignment_STUKYearCodeDAO mockAssignment_STUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_STUKYearCodeDAO.class);
        Assignment_ServiceProviderDAO mockAssignmentServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_ServiceProviderDAO.class);
        Assignment_AssignmentStatusTypeDAO mockAssignmentAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_AssignmentStatusTypeDAO.class);
        ServiceProviderDAO mockServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, ServiceProviderDAO.class);
        AssignmentStatusTypeDAO mockAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentStatusTypeDAO.class);
        AssignmentSTUKYearCodeDAO mockAssignmentSTUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentSTUKYearCodeDAO.class);
        VocalLanguagesDAO mockVocalLanguages = SpringBeanMockUtil.mockFieldOnBean(assignmentService, VocalLanguagesDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentDtoPage()).when(mockAssignmentDAO).listAssignmentsPage(any());
        Mockito.doReturn(getAssignmentServiceProviderEntities()).when(mockAssignmentServiceProviderDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProviderDAO).findById(anyInt());
        Mockito.doReturn(getAssignmentStatusTypesEntities()).when(mockAssignmentAssignmentStatusTypeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStatusTypeDto()).when(mockAssignmentStatusTypeDAO).findAssignmentStatusType(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeEntities()).when(mockAssignment_STUKYearCodeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeDto()).when(mockAssignmentSTUKYearCodeDAO).findAssignmentStukYearCode(anyInt());
        Mockito.doReturn(getVocalLanguageEntity()).when(mockVocalLanguages).findById(anyInt());
        AssignmentForUpdateDto assignmentForUpdateDto = new AssignmentForUpdateDto();
        assignmentForUpdateDto.setAssignmentStatusTypeIds(new ArrayList<>());
        assignmentForUpdateDto.setStukYearCodes(new ArrayList<>());
        assignmentForUpdateDto.setServiceProviders(new ArrayList<>());
        Assert.assertEquals(6, assignmentService.listAssignmentsPage(PageRequest.of(0, 10)).getNumberOfElements());
    }

    @Test
    public void testListingAssignmentsByDate(){
        AssignmentDAO mockAssignmentDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentDAO.class);
        Assignment_STUKYearCodeDAO mockAssignment_STUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_STUKYearCodeDAO.class);
        Assignment_ServiceProviderDAO mockAssignmentServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_ServiceProviderDAO.class);
        Assignment_AssignmentStatusTypeDAO mockAssignmentAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, Assignment_AssignmentStatusTypeDAO.class);
        ServiceProviderDAO mockServiceProviderDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, ServiceProviderDAO.class);
        AssignmentStatusTypeDAO mockAssignmentStatusTypeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentStatusTypeDAO.class);
        AssignmentSTUKYearCodeDAO mockAssignmentSTUKYearCodeDAO = SpringBeanMockUtil.mockFieldOnBean(assignmentService, AssignmentSTUKYearCodeDAO.class);
        VocalLanguagesDAO mockVocalLanguages = SpringBeanMockUtil.mockFieldOnBean(assignmentService, VocalLanguagesDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getAssignmentDtoList()).when(mockAssignmentDAO).listAllAssignments(any());
        Mockito.doReturn(getAssignmentServiceProviderEntities()).when(mockAssignmentServiceProviderDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProviderDAO).findById(anyInt());
        Mockito.doReturn(getAssignmentStatusTypesEntities()).when(mockAssignmentAssignmentStatusTypeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStatusTypeDto()).when(mockAssignmentStatusTypeDAO).findAssignmentStatusType(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeEntities()).when(mockAssignment_STUKYearCodeDAO).findAllByAssignmentIdIs(anyInt());
        Mockito.doReturn(getAssignmentStukYearCodeDto()).when(mockAssignmentSTUKYearCodeDAO).findAssignmentStukYearCode(anyInt());
        Mockito.doReturn(getVocalLanguageEntity()).when(mockVocalLanguages).findById(anyInt());
        AssignmentForUpdateDto assignmentForUpdateDto = new AssignmentForUpdateDto();
        assignmentForUpdateDto.setAssignmentStatusTypeIds(new ArrayList<>());
        assignmentForUpdateDto.setStukYearCodes(new ArrayList<>());
        assignmentForUpdateDto.setServiceProviders(new ArrayList<>());
        Assert.assertEquals(6, assignmentService.listAssignmentsDate(new Date(System.currentTimeMillis())).size());
    }
}
