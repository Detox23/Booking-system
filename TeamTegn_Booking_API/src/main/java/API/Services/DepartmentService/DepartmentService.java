package API.Services.DepartmentService;

import API.Models.Database_Entities.DepartmentEntity;
import API.Repository.Department.DepartmentDAO;
import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ForCreation.DepartmentForUpdateDto;
import Shared.ToReturn.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

    private ModelMapper modelMapper;

    private DepartmentDAO departmentDAO;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }


    @Override
    public List<DepartmentDto> seeAllDepartments(boolean showDeleted) {
        return departmentDAO.listAllDepartments(showDeleted);
    }

    @Override
    public DepartmentDto findDepartment(String name) {
        return departmentDAO.findOneDepartment(name);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteDepartment(int id) {
        return departmentDAO.deleteOneDepartment(id);
    }


    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DepartmentDto addDepartment(DepartmentForCreationDto department) {
        return departmentDAO.addOneDepartment(modelMapper.map(department, DepartmentEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public DepartmentDto updateDepartment(DepartmentForUpdateDto department) {
        return departmentDAO.updateOneDepartment(modelMapper.map(department, DepartmentEntity.class));
    }
}
