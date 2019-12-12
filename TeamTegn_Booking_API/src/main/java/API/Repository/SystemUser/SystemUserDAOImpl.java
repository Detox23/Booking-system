package API.Repository.SystemUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.CityPostcodesEntity;
import API.Models.Database_Entities.SystemUserDepartmentEntity;
import API.Models.Database_Entities.SystemUserEntity;
import API.Models.Database_Entities.WiPostcodeEntity;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
import Shared.ToReturn.SystemUserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    private SystemUser_DepartmentDAO systemUserDepartmentDAO;

    private PasswordEncoder bcryptEncoder;

    private WI_PostcodeDAO wiPostcodeDAO;

    private final int update = 1;
    private final int insert = 0;

    @Autowired
    public void setWiPostcodeDAO(WI_PostcodeDAO wiPostcodeDAO) {
        this.wiPostcodeDAO = wiPostcodeDAO;
    }

    @Autowired
    public void setBcryptEncoder(PasswordEncoder bcryptEncoder) {
        this.bcryptEncoder = bcryptEncoder;
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
            checkIfExistsByName(systemUser);
            checkIfExistsByLogin(systemUser);
            checkAndFillPostcodeAndCity(systemUser);
            addStateRegion(systemUser);
            encryptPassword(systemUser);
            SystemUserEntity saved = systemUserDAO.save(systemUser);
            addDepartments(departments, saved);
            return modelMapper.map(saved, SystemUserDto.class);
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
            checkIfExistsByName(systemUser);
            checkIfExistsByLogin(systemUser);
            checkAndFillPostcodeAndCity(systemUser);
            addStateRegion(systemUser);
            SystemUserEntity found = findIfExistsAndReturn(systemUser.getId());
            patcherHandler.fillNotNullFields(found, systemUser);
            SystemUserEntity updated = systemUserDAO.save(found);
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
        try {
            SystemUserEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            SystemUserEntity deleted = systemUserDAO.save(found);
            return deleted.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SystemUserDto> listSystemUsers(boolean showDeleted) {
        if (showDeleted) {
            try {
                return modelMapper.map(systemUserDAO.findAll(), new TypeToken<List<SystemUserDto>>() {
                }.getType());
            } catch (Exception exception) {
                throw exception;
            }
        } else {
            try {
                return modelMapper.map(systemUserDAO.findAllByDeletedIsFalse(), new TypeToken<List<SystemUserDto>>() {
                }.getType());
            } catch (Exception exception) {
                throw exception;
            }
        }
    }

    @Override
    public SystemUserDto findSystemUser(int id) {
        SystemUserEntity found = findIfExistsAndReturn(id);
        return modelMapper.map(found, SystemUserDto.class);
    }

    @Override
    public SystemUserDto findSystemUser(String userName) {
        Optional<SystemUserEntity> found = systemUserDAO.findDistinctByUserNameIs(userName);
        if (!found.isPresent() || found.get().isDeleted()) {
            throw new NotFoundException("The system user was not found.");
        }
        return modelMapper.map(found.get(), SystemUserDto.class);
    }

    private SystemUserEntity findIfExistsAndReturn(int id) {
        Optional<SystemUserEntity> found = systemUserDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("System user with id: %d was not found.", id));
        }
        return found.get();
    }

    private void checkAndFillPostcodeAndCity(SystemUserEntity systemUser) {
        if (systemUser.getCity() == null) {
            Optional<CityPostcodesEntity> found = cityPostcodesDAO.findFirstByPostcodeIs(systemUser.getPostcode());
            if (found.isPresent()) {
                systemUser.setCity(found.get().getCity());
            }
        }
    }

    private void encryptPassword(SystemUserEntity systemUser) {
        String password = systemUser.getPassword();
        String hashed = bcryptEncoder.encode(password);
        systemUser.setPassword(hashed);
    }

    private void addStateRegion(SystemUserEntity systemUser) {
        if (systemUser.getStateRegion() == null) {
            Optional<WiPostcodeEntity> wiPostcode = wiPostcodeDAO.findByPostcodeIs(systemUser.getPostcode());
            if (wiPostcode.isPresent()) {
                if (wiPostcode.get().getArhus()) {
                    systemUser.setStateRegion("Aarhus");
                } else if (wiPostcode.get().getCopenhagen()) {
                    systemUser.setStateRegion("Copenhagen");
                } else if (wiPostcode.get().getFredericia()) {
                    systemUser.setStateRegion("Fredericia");
                }
            }
        }
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

    private void checkIfExistsByName(SystemUserEntity systemUser) {
        if (systemUser.getId() == 0) {
            if (systemUserDAO.countAllByFirstNameIsAndMiddleNameIsAndLastNameIs(systemUser.getFirstName(), systemUser.getMiddleName(), systemUser.getLastName()) > 0) {
                throw new DuplicateException(String.format(
                        "There is already a system user with name: %s %s %s.",
                        systemUser.getFirstName(),
                        systemUser.getMiddleName(),
                        systemUser.getLastName()
                ));
            }
        } else {
            if (systemUserDAO.countAllByFirstNameIsAndMiddleNameIsAndLastNameIsAndIdIsNot(systemUser.getFirstName(), systemUser.getMiddleName(), systemUser.getLastName(), systemUser.getId()) > 0) {
                throw new DuplicateException(String.format(
                        "There is already a system user with name: %s %s %s.",
                        systemUser.getFirstName(),
                        systemUser.getMiddleName(),
                        systemUser.getLastName()
                ));
            }
        }
    }

    private void checkIfExistsByLogin(SystemUserEntity systemUser) {
        if (systemUser.getId() == 0) {
            if (systemUserDAO.countAllByUserNameIs(systemUser.getUserName()) > 0) {
                throw new DuplicateException(String.format("There is already a system user with the username: %s", systemUser.getUserName()));
            }
        } else {
            if (systemUserDAO.countAllByUserNameIsAndIdIsNot(systemUser.getUserName(), systemUser.getId()) > 0) {
                throw new DuplicateException(String.format("There is already a system user with the username: %s", systemUser.getUserName()));
            }
        }
    }
}
