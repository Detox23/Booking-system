package API.Services;

import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ToReturn.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDepartmentService {
    List<DepartmentDto> seeAllDepartments();
    DepartmentDto findDepartment(String name);
    boolean deleteDepartment(String name);
    DepartmentDto addDepartment(DepartmentForCreationDto department);
    DepartmentDto updateDepartment(DepartmentForCreationDto department);
}
