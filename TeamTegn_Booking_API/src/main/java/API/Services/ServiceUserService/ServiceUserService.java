package API.Services.ServiceUserService;

import API.Database_Entities.ServiceUserEntity;
import API.Repository.ServiceUser.ServiceUserDAO;
import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import Shared.ToReturn.ServiceUserDto;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceUserService implements IServiceUserService {

    private ServiceUserDAO serviceUserDAO;

    private ModelMapper mapper;

    @Autowired
    public void setServiceUserDAO(ServiceUserDAO serviceUserDAO) {
        this.serviceUserDAO = serviceUserDAO;
    }

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public ServiceUserDto addServiceUser(ServiceUserForCreationDto userForCreationDto) {
        ServiceUserEntity entity = mapper.map(userForCreationDto, ServiceUserEntity.class);
        ServiceUserEntity result = serviceUserDAO.add(entity);
        return mapper.map(result, ServiceUserDto.class);
    }

    @Override
    public ServiceUserDto findServiceUser(int id) {
        return mapper.map(serviceUserDAO.findByID(id), ServiceUserDto.class);

    }

    @Override
    public List<ServiceUserDto> listServiceUsers() {
        List<ServiceUserEntity> elements = Lists.newArrayList(serviceUserDAO.list());
        return mapper.map(elements, new TypeToken<List<ServiceUserDto>>() {
        }.getType());

    }

    @Override
    public boolean deleteServiceUser(int id) {
        return serviceUserDAO.deleteById(id);
    }


    @Override
    public ServiceUserDto updateServiceUser(int id, ServiceUserForUpdateDto forUpdateDto) {
        ServiceUserEntity entity = mapper.map(forUpdateDto, ServiceUserEntity.class);
        entity.setId(id);
        ServiceUserEntity result = serviceUserDAO.update(entity);
        return mapper.map(result, ServiceUserDto.class);
    }
}
