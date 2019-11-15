package API.Services.DepartmentService;

import API.Database_Entities.DepartmentEntity;
import API.Repository.Department.DepartmentDAO;
import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ToReturn.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Override
    public List<DepartmentDto> seeAllDepartments() {
        return departmentDAO.listAllDepartments();
    }

    @Override
    public DepartmentDto findDepartment(String name) {
        return departmentDAO.findOneDepartment(name);
    }

    @Override
    @Transactional
    public boolean deleteDepartment(String name) {
        return departmentDAO.deleteOneDepartment(name);
    }


    @Override
    @Transactional
    public DepartmentDto addDepartment(DepartmentForCreationDto department) {
        DepartmentDto addedDepartment = departmentDAO.addOneDepartment(modelMapper.map(department, DepartmentEntity.class));
        if (addedDepartment != null) {
            return addedDepartment;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(DepartmentDto department) {
        return departmentDAO.updateOneDepartment(modelMapper.map(department, DepartmentEntity.class));
    }
}
