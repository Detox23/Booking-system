package API.Services.SystemUserService;

import API.Configurations.Encryption.EncryptionHandler;
import API.Database_Entities.SystemUserEntity;
import API.Repository.Department.DepartmentDAO;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemUserService implements ISystemUserService {

    private SystemUserDAO systemUserDAO;

    private EncryptionHandler encryptionHandler;

    private ModelMapper modelMapper;

    private DepartmentDAO departmentDAO;

    private SystemUser_DepartmentDAO systemUserDepartmentDAO;

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Autowired
    public void setSystemUserDepartmentDAO(SystemUser_DepartmentDAO systemUserDepartmentDAO) {
        this.systemUserDepartmentDAO = systemUserDepartmentDAO;
    }

    @Autowired
    public void setEncryptionHandler(EncryptionHandler encryptionHandler) {
        this.encryptionHandler = encryptionHandler;
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
        decryptPassword(created);
        fillWithListOfDepartments(created);
        return created;
    }




    @Override
    @Transactional(rollbackFor = Throwable.class)
    public SystemUserDto updateSystemUser(SystemUserForUpdateDto systemUser) {
        SystemUserDto updated = systemUserDAO.updateSystemUser(modelMapper.map(systemUser, SystemUserEntity.class), systemUser.getDepartments());
        decryptPassword(updated);
        fillWithListOfDepartments(updated);
        return updated;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteSystemUser(int id) {
        return systemUserDAO.deleteSystemUser(id);
    }

    @Override
    public List<SystemUserDto> listSystemUsers() {
        List<SystemUserDto> foundUsers = systemUserDAO.listSystemUsers();
        List<SystemUserDto> returnList = new ArrayList<>();
        for(SystemUserDto user: foundUsers){
            decryptPassword(user);
            fillWithListOfDepartments(user);
            returnList.add(user);
        }
        return returnList;
    }

    @Override
    public SystemUserDto findSystemUser(int id) {
        SystemUserDto found = systemUserDAO.findSystemUser(id);
        decryptPassword(found);
        fillWithListOfDepartments(found);
        return found;
    }


    private void decryptPassword(SystemUserDto systemUser){
        try {
            String passwordDecrypted = encryptionHandler.decrypt(systemUser.getPassword());
            systemUser.setPassword(passwordDecrypted);
        }catch(IllegalArgumentException illegalArgumentException){
            systemUser.setPassword(systemUser.getPassword());
        }
    }

    private void fillWithListOfDepartments(SystemUserDto systemUser){
        systemUser.setDepartments(new ArrayList<>());
        List<SystemUserDepartmentDto> list = modelMapper.map(systemUserDepartmentDAO.findBySystemUserIdIs(systemUser.getId()),new TypeToken<List<SystemUserDepartmentDto>>() {}.getType());
        for(SystemUserDepartmentDto department: list){
            DepartmentDto foundDepartment = departmentDAO.findDepartmentByID(department.getDepartmentId());
            systemUser.getDepartments().add(foundDepartment);
        }
    }
}
