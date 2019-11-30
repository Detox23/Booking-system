package API.Repository.SystemUser;

import API.Configurations.Encryption.EncryptionHandler;
import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.CityPostcodesEntity;
import API.Database_Entities.SystemUserDepartmentEntity;
import API.Database_Entities.SystemUserEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Exceptions.UpdatePatchException;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import Shared.ToReturn.SystemUserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        String hashed = encryptionHandler.encrypt(password);
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
