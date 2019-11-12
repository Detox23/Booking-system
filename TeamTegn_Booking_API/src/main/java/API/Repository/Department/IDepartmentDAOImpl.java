package API.Repository.Department;


import API.Database_Entities.DepartmentEntity;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class IDepartmentDAOImpl implements IDepartmentDAOCustom {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IDepartmentDAO departmentDAO;

    public List<DepartmentDto> list(){
        Type listType = new TypeToken<List<DepartmentDto>>() {}.getType();
        return modelMapper.map(departmentDAO.findAll(), listType);
    }

    @Override
    public DepartmentDto findDepartment(String name) {
        DepartmentEntity found = departmentDAO.findByDepartmentName(name);
        return modelMapper.map(found, DepartmentDto.class);
    }

}
