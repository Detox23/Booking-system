package API.Repository.Department;

import API.Database_Entities.DepartmentEntity;
import API.Exceptions.AccountNotExistsUpdateException;
import API.Exceptions.UpdateErrorException;
import Shared.ToReturn.DepartmentDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DepartmentDAOCustom {
    List<DepartmentDto> list();
    DepartmentDto findDepartment(String name);
    DepartmentDto addDepartment(DepartmentEntity departmentEntity);
    DepartmentDto updateDepartment(DepartmentEntity departmentEntity);
    boolean deleteDepartment(String name) throws AccountNotExistsUpdateException;

    
}
