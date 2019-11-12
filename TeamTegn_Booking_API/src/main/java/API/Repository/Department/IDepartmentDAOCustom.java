package API.Repository.Department;

import Shared.ToReturn.DepartmentDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IDepartmentDAOCustom {
    List<DepartmentDto> list();
    DepartmentDto findDepartment(String name);
}
