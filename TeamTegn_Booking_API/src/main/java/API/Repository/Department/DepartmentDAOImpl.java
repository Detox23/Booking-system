package API.Repository.Department;


import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.DepartmentEntity;
import API.Database_Entities.ServiceProviderEntity;
import API.Exceptions.DeletionException;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Repository.ServiceProvider.ServiceProviderDAO;
import Shared.ToReturn.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class DepartmentDAOImpl implements DepartmentDAOCustom {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private ServiceProviderDAO serviceProviderDAO;

    private PatcherHandler patcherHandler;

    private final String DEFAULT_DEPARTMENT_NAME = "Unassigned";

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Override
    public List<DepartmentDto> listAllDepartments() {
        try {
            Type listType = new TypeToken<List<DepartmentDto>>() {
            }.getType();
            return modelMapper.map(departmentDAO.findAllByDeletedIsFalse(), listType);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DepartmentDto findOneDepartment(String name) {
        try {
            Optional<DepartmentEntity> found = departmentDAO.findByDepartmentName(name);
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("Department does not exists.");
            }
            return modelMapper.map(found.get(), DepartmentDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DepartmentDto addOneDepartment(DepartmentEntity departmentEntity) {
        try {
            Optional<DepartmentEntity> departmentCheck = departmentDAO.findByDepartmentName(departmentEntity.getDepartmentName());
            if (departmentCheck.isPresent()) {
                throw new DuplicateException("Department with the name already exists.");
            }
            departmentEntity.setDeleted(false);
            DepartmentEntity added = departmentDAO.save(departmentEntity);
            return modelMapper.map(added, DepartmentDto.class);
        } catch (DuplicateException duplicateException) {
            throw duplicateException;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DepartmentDto updateOneDepartment(DepartmentEntity departmentEntity) {
        try {
            Optional<DepartmentEntity> found = departmentDAO.findById(departmentEntity.getId());
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("The department you want to update was deleted or does not exist.");
            }
            patcherHandler.fillNotNullFields(found.get(), departmentEntity);
            DepartmentEntity updated = departmentDAO.save(found.get());
            return modelMapper.map(updated, DepartmentDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating a competency [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteOneDepartment(int id) {
        try {
            Optional<DepartmentEntity> found = departmentDAO.findById(id);
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("The department user was not found.");
            }
            DepartmentEntity toDelete = found.get();
            toDelete.setDeleted(true);
            DepartmentEntity deletionResult = departmentDAO.save(toDelete);
            if (deletionResult.isDeleted()) {
                return true;
            } else {
                return false;
            }
        } catch (NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }

}
