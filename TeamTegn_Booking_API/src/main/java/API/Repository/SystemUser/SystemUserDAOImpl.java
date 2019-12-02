package API.Repository.SystemUser;

import API.Configurations.Encryption.EncryptionHandler;
import API.Configurations.Patcher.PatcherHandler;
import API.Models.Database_Entities.CityPostcodesEntity;
import API.Models.Database_Entities.SystemUserDepartmentEntity;
import API.Models.Database_Entities.SystemUserEntity;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Exceptions.UpdatePatchException;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import Shared.ToReturn.SystemUserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

@Component
public class SystemUserDAOImpl implements SystemUserDAOCustom {

    private SystemUserDAO systemUserDAO;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;

    private CityPostcodesDAO cityPostcodesDAO;

    private EncryptionHandler encryptionHandler;

    private SystemUser_DepartmentDAO systemUserDepartmentDAO;

    private PasswordEncoder bcryptEncoder;

    private RoleDAO roleDAO;

    @Autowired
    public void setBcryptEncoder(PasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setSystemUserDepartmentDAO(SystemUser_DepartmentDAO systemUserDepartmentDAO) {
        this.systemUserDepartmentDAO = systemUserDepartmentDAO;
    }

    @Autowired
    public void setCityPostcodesDAO(CityPostcodesDAO cityPostcodesDAO) {
        this.cityPostcodesDAO = cityPostcodesDAO;
    }

    @Autowired
    public void setEncryptionHandler(EncryptionHandler encryptionHandler) {
        this.encryptionHandler = encryptionHandler;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setSystemUserDAO(SystemUserDAO systemUserDAO) {
        this.systemUserDAO = systemUserDAO;
    }


    @Override
    public SystemUserDto addSystemUser(SystemUserEntity systemUser, List<Integer> departments) {
        try {
            if(systemUserDAO.countAllByFirstNameIsAndLastNameIsAndUserNameIs(systemUser.getFirstName(), systemUser.getLastName(), systemUser.getUserName())> 0){
                throw new DuplicateException(String.format(
                        "There is already a system user with name: %s %s, and username: %s",
                        systemUser.getFirstName(),
                        systemUser.getLastName(),
                        systemUser.getUserName()
                ));
            }
            checkAndFillPostcodeAndCity(systemUser);
            encryptPassword(systemUser);
            SystemUserEntity saved = systemUserDAO.save(systemUser);
            if (saved.getId() > 0) {
                if (departments.size() == 0 || departments == null) {
                    return modelMapper.map(saved, SystemUserDto.class);
                } else {
                    addDepartments(departments, saved);
                    return modelMapper.map(saved, SystemUserDto.class);
                }
            }
            throw new UnknownAddingException("The user was not added.");
        } catch (UnknownAddingException unknownAddingException) {
            throw unknownAddingException;
        } catch (DataIntegrityViolationException e) {
            throw new NotFoundException("One of provided information does not match with database. Check if your data is is correct.");
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public SystemUserDto updateSystemUser(SystemUserEntity systemUser, List<Integer> departments) {
        try {
            Optional<SystemUserEntity> found = systemUserDAO.findById(systemUser.getId());
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("The system user was not found.");
            }
            checkAndFillPostcodeAndCity(systemUser);
            encryptPassword(systemUser);
            patcherHandler.fillNotNullFields(found.get(), systemUser);
            SystemUserEntity updated = systemUserDAO.save(found.get());
            addDepartments(departments, updated);
            return modelMapper.map(updated, SystemUserDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating the system user. [PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteSystemUser(int id) {
        Optional<SystemUserEntity> foundToDelete = systemUserDAO.findById(id);
        if (!foundToDelete.isPresent() || foundToDelete.get().isDeleted()) {
            throw new NotFoundException("The system user was not found.");
        }
        foundToDelete.get().setDeleted(true);
        SystemUserEntity deleted = systemUserDAO.save(foundToDelete.get());
        return deleted.isDeleted();
    }

    @Override
    public List<SystemUserDto> listSystemUsers() {
        try {
            return modelMapper.map(systemUserDAO.findAllByDeletedIsFalse(), new TypeToken<List<SystemUserDto>>() {
            }.getType());
        } catch (Exception exception) {
            throw exception;
        }
    }

    @Override
    public SystemUserDto findSystemUser(int id) {
        Optional<SystemUserEntity> found = systemUserDAO.findById(id);
        if (!found.isPresent() || found.get().isDeleted()) {
            throw new NotFoundException("The system user was not found.");
        }
        return modelMapper.map(found.get(), SystemUserDto.class);
    }

    @Override
    public SystemUserDto findSystemUser(String userName){
        Optional<SystemUserEntity> found = systemUserDAO.findDistinctByUserNameIs(userName);
        if (!found.isPresent() || found.get().isDeleted()) {
            throw new NotFoundException("The system user was not found.");
        }
        return modelMapper.map(found.get(), SystemUserDto.class);
    }

    @Override
    public boolean logIn(String login, String password) {
        Optional<SystemUserEntity> systemUserEntity = systemUserDAO.findDistinctByUserNameIs(login);
        if(!systemUserEntity.isPresent()|| systemUserEntity.get().isDeleted()){
            throw new NotFoundException(String.format("The system user with login %s does not exist.", login));
        }
        String passwordFound = encryptionHandler.decrypt(systemUserEntity.get().getPassword());
        if(passwordFound.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    private void checkAndFillPostcodeAndCity(SystemUserEntity systemUser) {
        if (systemUser.getCity() == null && systemUser.getPostcode() != null) {
            Optional<CityPostcodesEntity> found = cityPostcodesDAO.findFirstByPostcodeIs(systemUser.getPostcode());
            if (found.isPresent()) {
                systemUser.setCity(found.get().getCity());
            }
        }
        if (systemUser.getPostcode() == null && systemUser.getCity() != null) {
            Optional<CityPostcodesEntity> found = cityPostcodesDAO.findFirstByCityIs(systemUser.getCity());
            if (found.isPresent()) {
                systemUser.setPostcode(found.get().getPostcode());
            }
        }
    }

    private void encryptPassword(SystemUserEntity systemUser) {
        String password = systemUser.getPassword();
        String hashed = bcryptEncoder.encode(password);
        systemUser.setPassword(hashed);
    }


    private void addDepartments(List<Integer> departments, SystemUserEntity saved) {
        if (departments != null) {
            systemUserDepartmentDAO.deleteAllBySystemUserIdIs(saved.getId());
            departments.forEach(departmentNumber -> {
                SystemUserDepartmentEntity suDepartmentEntity = new SystemUserDepartmentEntity();
                suDepartmentEntity.setDepartmentId(departmentNumber);
                suDepartmentEntity.setSystemUserId(saved.getId());
                systemUserDepartmentDAO.save(suDepartmentEntity);
            });
        }
    }



}
