package API.Repository.Department;

import Shared.ToReturn.DepartmentDto;

import java.util.List;


public interface DepartmentDAOCustom {
    List<DepartmentDto> listAllDepartments();

    DepartmentDto findOneDepartment(String name);

    DepartmentDto addOneDepartment(DepartmentEntity departmentEntity);

    DepartmentDto updateOneDepartment(DepartmentEntity departmentEntity);

    boolean deleteOneDepartment(String name);


}
