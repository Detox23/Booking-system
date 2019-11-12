package API.Repository.Department;


import Shared.ToReturn.AccountDto;
import Shared.ToReturn.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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

}
