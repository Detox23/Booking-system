package API.Services.SystemUserService;

import API.Models.Database_Entities.SystemUserEntity;
import API.Repository.Department.DepartmentDAO;
import API.Repository.SystemUser.RoleDAO;
import API.Repository.SystemUser.SystemUserDAO;
import API.Repository.SystemUser.SystemUser_DepartmentDAO;
import Shared.ForCreation.SystemUserForCreationDto;
import Shared.ForCreation.SystemUserForUpdateDto;
import Shared.ToReturn.DepartmentDto;
import Shared.ToReturn.SystemUserDepartmentDto;
import Shared.ToReturn.SystemUserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SystemUserService implements ISystemUserService, UserDetailsService {

    private SystemUserDAO systemUserDAO;

    private ModelMapper modelMapper;

    private DepartmentDAO departmentDAO;

    private SystemUser_DepartmentDAO systemUserDepartmentDAO;

    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Autowired
    public void setSystemUserDepartmentDAO(SystemUser_DepartmentDAO systemUserDepartmentDAO) {
        this.systemUserDepartmentDAO = systemUserDepartmentDAO;
    }

    @Autowired
    public void setSystemUserDAO(SystemUserDAO systemUserDAO) {
        this.systemUserDAO = systemUserDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public SystemUserDto addSystemUser(SystemUserForCreationDto systemUser) {
        SystemUserDto created = systemUserDAO.addSystemUser(modelMapper.map(systemUser, SystemUserEntity.class), systemUser.getDepartments());
        fillWithListOfDepartments(created);
        addRole(created);
        return created;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public SystemUserDto updateSystemUser(SystemUserForUpdateDto systemUser) {
        SystemUserDto updated = systemUserDAO.updateSystemUser(modelMapper.map(systemUser, SystemUserEntity.class), systemUser.getDepartments());
        fillWithListOfDepartments(updated);
        addRole(updated);
        return updated;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteSystemUser(int id) {
        return systemUserDAO.deleteSystemUser(id);
    }

    @Override
    public List<SystemUserDto> listSystemUsers(boolean showDeleted) {
        List<SystemUserDto> foundUsers = systemUserDAO.listSystemUsers(showDeleted);
        for(SystemUserDto user: foundUsers){
            fillWithListOfDepartments(user);
            addRole(user);
        }
        return foundUsers;
    }

    @Override
    public SystemUserDto findSystemUser(int id) {
        SystemUserDto found = systemUserDAO.findSystemUser(id);
        fillWithListOfDepartments(found);
        addRole(found);
        return found;
    }

    @Override
    public SystemUserDto findSystemUserByUsername(String userName){
        SystemUserDto found = systemUserDAO.findSystemUser(userName);
        fillWithListOfDepartments(found);
        addRole(found);
        return found;
    }


    private void fillWithListOfDepartments(SystemUserDto systemUser){
        systemUser.setDepartments(new ArrayList<>());
        List<SystemUserDepartmentDto> list = modelMapper.map(systemUserDepartmentDAO.findBySystemUserIdIs(systemUser.getId()),new TypeToken<List<SystemUserDepartmentDto>>() {}.getType());
        for(SystemUserDepartmentDto department: list){
            DepartmentDto foundDepartment = departmentDAO.findDepartmentByID(department.getDepartmentId());
            systemUser.getDepartments().add(foundDepartment);
        }
    }

    private void addRole(SystemUserDto systemUser){
        systemUser.setRole(roleDAO.getByIdIs(systemUser.getRoleId()).getRoleName());

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SystemUserDto user = findSystemUserByUsername(s);
        org.springframework.security.core.userdetails.User toReturn = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthority(user));
        return toReturn;
    }

    private Set<SimpleGrantedAuthority> getAuthority(SystemUserDto user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }
}
