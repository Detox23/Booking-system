package API.Services.DepartmentService;

import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ForCreation.DepartmentForUpdateDto;
import Shared.ToReturn.DepartmentDto;

import java.util.List;


public interface IDepartmentService {
    List<DepartmentDto> seeAllDepartments();

    DepartmentDto findDepartment(String name);

    boolean deleteDepartment(int id);

    DepartmentDto addDepartment(DepartmentForCreationDto department);

    DepartmentDto updateDepartment(DepartmentForUpdateDto department);
}
