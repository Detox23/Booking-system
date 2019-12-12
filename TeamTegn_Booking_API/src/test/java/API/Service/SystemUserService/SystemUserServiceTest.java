package API.Service.SystemUserService;


import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.RoleEntity;
import API.Models.Database_Entities.SystemUserEntity;
import API.Repository.Department.DepartmentDAO;
import API.Repository.SystemUser.RoleDAO;
import API.Repository.SystemUser.SystemUserDAO;
import API.Repository.SystemUser.SystemUser_DepartmentDAO;
import API.Services.SystemUserService.SystemUserService;
import Shared.ForCreation.SystemUserForCreationDto;
import Shared.ForCreation.SystemUserForUpdateDto;
import Shared.ToReturn.DepartmentDto;
import Shared.ToReturn.SystemUserDepartmentDto;
import Shared.ToReturn.SystemUserDto;
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
public class SystemUserServiceTest {

    @Autowired
    private SystemUserService systemUserService;

    private DepartmentDto getDepartmentDto() {
        DepartmentDto department = new DepartmentDto();
        department.setDepartmentName("Test department name");
        return department;
    }

    private SystemUserDto getSystemUserDto() {
        SystemUserDto systemUser = new SystemUserDto();
        systemUser.setUserName("Username");
        systemUser.setFirstName("TestFirstName");
        systemUser.setRoleId(1);
        return systemUser;
    }

    private RoleEntity getRoleEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("Administrator");
        return roleEntity;
    }

    private SystemUserDepartmentDto getSystemDepartment() {
        SystemUserDepartmentDto systemUserDepartmentDto = new SystemUserDepartmentDto();
        systemUserDepartmentDto.setDepartmentId(1);
        systemUserDepartmentDto.setId(1);
        systemUserDepartmentDto.setSystemUserId(1);
        return systemUserDepartmentDto;
    }

    private List<SystemUserDepartmentDto> getDepartmentDtoList() {
        List<SystemUserDepartmentDto> returnList = new ArrayList<>();
        returnList.add(getSystemDepartment());
        returnList.add(getSystemDepartment());
        returnList.add(getSystemDepartment());
        returnList.add(getSystemDepartment());
        returnList.add(getSystemDepartment());
        return returnList;
    }

    private List<SystemUserDto> getSystemUserDtoList() {
        List<SystemUserDto> returnList = new ArrayList<>();
        returnList.add(getSystemUserDto());
        returnList.add(getSystemUserDto());
        returnList.add(getSystemUserDto());
        returnList.add(getSystemUserDto());
        returnList.add(getSystemUserDto());
        return returnList;
    }

    @Test
    public void testAddSystemRoleShouldMatch() {
        SystemUserDAO mockSystemUser = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUserDAO.class);
        SystemUser_DepartmentDAO mockSystemUserDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUser_DepartmentDAO.class);
        DepartmentDAO mockDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, DepartmentDAO.class);
        RoleDAO mockRoleDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, RoleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getSystemUserDto()).when(mockSystemUser).addSystemUser(any(SystemUserEntity.class), any());
        Mockito.doReturn(getDepartmentDtoList()).when(mockSystemUserDepartmentDAO).findBySystemUserIdIs(anyInt());
        Mockito.doReturn(getDepartmentDto()).when(mockDepartmentDAO).findDepartmentByID(anyInt());
        Mockito.doReturn(getRoleEntity()).when(mockRoleDAO).getByIdIs(anyInt());
        SystemUserForCreationDto systemUserForCreationDto = new SystemUserForCreationDto();
        systemUserForCreationDto.setFirstName("First name");
        Assert.assertEquals("Administrator", systemUserService.addSystemUser(systemUserForCreationDto).getRole());
    }


    @Test
    public void testAddSystemListSizeOfDepartmentsShouldBeFive() {
        SystemUserDAO mockSystemUser = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUserDAO.class);
        SystemUser_DepartmentDAO mockSystemUserDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUser_DepartmentDAO.class);
        DepartmentDAO mockDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, DepartmentDAO.class);
        RoleDAO mockRoleDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, RoleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getSystemUserDto()).when(mockSystemUser).addSystemUser(any(SystemUserEntity.class), any());
        Mockito.doReturn(getDepartmentDtoList()).when(mockSystemUserDepartmentDAO).findBySystemUserIdIs(anyInt());
        Mockito.doReturn(getDepartmentDto()).when(mockDepartmentDAO).findDepartmentByID(anyInt());
        Mockito.doReturn(getRoleEntity()).when(mockRoleDAO).getByIdIs(anyInt());
        SystemUserForCreationDto systemUserForCreationDto = new SystemUserForCreationDto();
        systemUserForCreationDto.setFirstName("First name");
        Assert.assertEquals(5, systemUserService.addSystemUser(systemUserForCreationDto).getDepartments().size());
    }


    @Test
    public void testUpdatingSystemRoleShouldMatch() {
        SystemUserDAO mockSystemUser = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUserDAO.class);
        SystemUser_DepartmentDAO mockSystemUserDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUser_DepartmentDAO.class);
        DepartmentDAO mockDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, DepartmentDAO.class);
        RoleDAO mockRoleDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, RoleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getSystemUserDto()).when(mockSystemUser).updateSystemUser(any(SystemUserEntity.class), any());
        Mockito.doReturn(getDepartmentDtoList()).when(mockSystemUserDepartmentDAO).findBySystemUserIdIs(anyInt());
        Mockito.doReturn(getDepartmentDto()).when(mockDepartmentDAO).findDepartmentByID(anyInt());
        Mockito.doReturn(getRoleEntity()).when(mockRoleDAO).getByIdIs(anyInt());
        SystemUserForUpdateDto systemUserForUpdateDto = new SystemUserForUpdateDto();
        systemUserForUpdateDto.setFirstName("First name");
        systemUserForUpdateDto.setRoleId(1232);
        Assert.assertEquals("Administrator", systemUserService.updateSystemUser(systemUserForUpdateDto).getRole());
    }

    @Test
    public void testUpdatingSystemListSizeOfDepartmentsShouldBeFive(){
        SystemUserDAO mockSystemUser = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUserDAO.class);
        SystemUser_DepartmentDAO mockSystemUserDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUser_DepartmentDAO.class);
        DepartmentDAO mockDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, DepartmentDAO.class);
        RoleDAO mockRoleDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, RoleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getSystemUserDto()).when(mockSystemUser).updateSystemUser(any(SystemUserEntity.class), any());
        Mockito.doReturn(getDepartmentDtoList()).when(mockSystemUserDepartmentDAO).findBySystemUserIdIs(anyInt());
        Mockito.doReturn(getDepartmentDto()).when(mockDepartmentDAO).findDepartmentByID(anyInt());
        Mockito.doReturn(getRoleEntity()).when(mockRoleDAO).getByIdIs(anyInt());
        SystemUserForUpdateDto systemUserForUpdateDto = new SystemUserForUpdateDto();
        systemUserForUpdateDto.setFirstName("First name");
        systemUserForUpdateDto.setRoleId(1232);
        Assert.assertEquals(5, systemUserService.updateSystemUser(systemUserForUpdateDto).getDepartments().size());
    }


    @Test
    public void testFindSystemUserByUsernameNameShouldMatch(){
        SystemUserDAO mockSystemUser = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUserDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getSystemUserDto()).when(mockSystemUser).findSystemUser(anyString());
        Assert.assertEquals("Username", systemUserService.findSystemUserByUsername("Username").getUserName());
    }

    @Test
    public void testFindSystemUserByIdNameShouldMatch(){
        SystemUserDAO mockSystemUser = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUserDAO.class);
        RoleDAO mockRoleDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, RoleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getSystemUserDto()).when(mockSystemUser).findSystemUser(anyInt());
        Mockito.doReturn(getRoleEntity()).when(mockRoleDAO).getByIdIs(anyInt());
        Assert.assertEquals("Username", systemUserService.findSystemUser(5).getUserName());
    }


    @Test
    public void testListingSystemUserSizeShouldBeFive(){
        SystemUserDAO mockSystemUser = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUserDAO.class);
        SystemUser_DepartmentDAO mockSystemUserDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUser_DepartmentDAO.class);
        DepartmentDAO mockDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, DepartmentDAO.class);
        RoleDAO mockRoleDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, RoleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(getSystemUserDtoList()).when(mockSystemUser).listSystemUsers(anyBoolean());
        Mockito.doReturn(getDepartmentDtoList()).when(mockSystemUserDepartmentDAO).findBySystemUserIdIs(anyInt());
        Mockito.doReturn(getDepartmentDto()).when(mockDepartmentDAO).findDepartmentByID(anyInt());
        Mockito.doReturn(getRoleEntity()).when(mockRoleDAO).getByIdIs(anyInt());
        Assert.assertEquals(5, systemUserService.listSystemUsers(true).size());
    }

    @Test
    public void testDeletingSystemUserShouldBeTrue(){
        SystemUserDAO mockSystemUser = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUserDAO.class);
        SystemUser_DepartmentDAO mockSystemUserDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, SystemUser_DepartmentDAO.class);
        DepartmentDAO mockDepartmentDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, DepartmentDAO.class);
        RoleDAO mockRoleDAO = SpringBeanMockUtil.mockFieldOnBean(systemUserService, RoleDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockSystemUser).deleteSystemUser(anyInt());
        Mockito.doReturn(getDepartmentDtoList()).when(mockSystemUserDepartmentDAO).findBySystemUserIdIs(anyInt());
        Mockito.doReturn(getDepartmentDto()).when(mockDepartmentDAO).findDepartmentByID(anyInt());
        Mockito.doReturn(getRoleEntity()).when(mockRoleDAO).getByIdIs(anyInt());
        Assert.assertTrue(systemUserService.deleteSystemUser(2));
    }



}
