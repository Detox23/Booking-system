package API.Repository.Department;


import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.DepartmentEntity;
import API.Exceptions.DeletionException;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class DepartmentDAOImpl implements DepartmentDAOCustom {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartmentDAO departmentDAO;

    private PatcherHandler patcherHandler;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Override
    public List<DepartmentDto> listAllDepartments() {
        try {
            Type listType = new TypeToken<List<DepartmentDto>>() {
            }.getType();
            return modelMapper.map(departmentDAO.findAll(), listType);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DepartmentDto findOneDepartment(String name) {
        try {
            DepartmentEntity found = departmentDAO.findByDepartmentName(name);
            return modelMapper.map(found, DepartmentDto.class);
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new NotFoundException("Department with the provided name was not found.");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DepartmentDto addOneDepartment(DepartmentEntity departmentEntity) {
        try {
            DepartmentEntity departmentCheck = departmentDAO.findByDepartmentName(departmentEntity.getDepartmentName());
            if (departmentCheck != null) {
                throw new DuplicateException("Department with the name already exists.");
            }
            DepartmentEntity added = departmentDAO.save(departmentEntity);
            return modelMapper.map(added, DepartmentDto.class);
        }catch (DuplicateException duplicateException){
            throw duplicateException;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DepartmentDto updateOneDepartment(DepartmentEntity departmentEntity) {
        try {
            DepartmentEntity found = departmentDAO.findById(departmentEntity.getId()).get();
            patcherHandler.fillNotNullFields(found, departmentEntity);
            DepartmentEntity updated = departmentDAO.save(found);
            return modelMapper.map(updated, DepartmentDto.class);
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException("Department was not found while an attempt of making update.");
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating a competency [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteOneDepartment(String name) {
        try {
            DepartmentEntity found = departmentDAO.findByDepartmentName(name);
            departmentDAO.deleteById(found.getId());
            DepartmentEntity assure = departmentDAO.findByDepartmentName(name);
            if (assure == null) {
                return true;
            } else {
                throw new DeletionException("Department was not deleted.");
            }
        } catch (DeletionException deletionException) {
            throw deletionException;
        } catch (NullPointerException nullPointerException) {
            throw new NotFoundException("Department you wanted to delete does not exist.");
        } catch (Exception e) {
            throw e;
        }
    }

}
