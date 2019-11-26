package API.Repository.Department;

import API.Database_Entities.DepartmentEntity;
import Shared.ToReturn.DepartmentDto;

import java.util.List;


public interface DepartmentDAOCustom {
    List<DepartmentDto> listAllDepartments();

    DepartmentDto findOneDepartment(String name);

    DepartmentDto addOneDepartment(DepartmentEntity departmentEntity);

    DepartmentDto updateOneDepartment(DepartmentEntity departmentEntity);

    boolean deleteOneDepartment(int id);


}
