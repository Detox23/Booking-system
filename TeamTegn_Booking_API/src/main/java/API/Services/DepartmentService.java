package API.Services;

import API.Database_Entities.DepartmentEntity;
import API.Exceptions.AccountNotExistsUpdateException;
import API.Exceptions.UpdateErrorException;
import API.Repository.Department.IDepartmentDAO;
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
    @Transactional
    public boolean deleteDepartment(String name) throws AccountNotExistsUpdateException {
        return departmentDAO.deleteDepartment(name);
    }


    @Override
    @Transactional
    public DepartmentDto addDepartment(DepartmentForCreationDto department) {
        DepartmentDto addedDepartment = departmentDAO.addDepartment(modelMapper.map(department, DepartmentEntity.class));
        if (addedDepartment != null){
            return addedDepartment;
        }
        throw new NullPointerException("Adding department failure.");
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(DepartmentDto department) {
        return departmentDAO.updateDepartment(modelMapper.map(department, DepartmentEntity.class));
    }
}
