package API.Services.DepartmentService;

import API.Exceptions.AccountNotExistsUpdateException;
import API.Exceptions.UpdateErrorException;
import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ToReturn.DepartmentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDepartmentService {
    List<DepartmentDto> seeAllDepartments();
    DepartmentDto findDepartment(String name);
    boolean deleteDepartment(String name) throws AccountNotExistsUpdateException;
    DepartmentDto addDepartment(DepartmentForCreationDto department);
    DepartmentDto updateDepartment(DepartmentDto department);
}
