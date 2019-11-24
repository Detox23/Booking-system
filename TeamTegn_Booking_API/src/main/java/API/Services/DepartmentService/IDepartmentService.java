package API.Services.DepartmentService;

import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ForCreation.DepartmentForUpdateDto;
import Shared.ToReturn.DepartmentDto;

import java.util.List;


public interface IDepartmentService {
    List<DepartmentDto> seeAllDepartments();

    DepartmentDto findDepartment(String name);

    boolean deleteDepartment(String name);

    DepartmentDto addDepartment(DepartmentForCreationDto department);

    DepartmentDto updateDepartment(DepartmentForUpdateDto department);
}
