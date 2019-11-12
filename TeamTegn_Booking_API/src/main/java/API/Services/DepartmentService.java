package API.Services;

import API.Repository.Department.IDepartmentDAO;
import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ToReturn.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentDAO departmentDAO;

    @Override
    public List<DepartmentDto> seeAllDepartments() {
        return departmentDAO.list();
    }

    @Override
    public DepartmentDto findDepartment(String name) {
        return departmentDAO.findDepartment(name);
    }

    @Override
    public boolean deleteDepartment(String name) {
        return false;
    }

    @Override
    public DepartmentDto addDepartment(DepartmentForCreationDto department) {
        return null;
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentForCreationDto department) {
        return null;
    }
}
