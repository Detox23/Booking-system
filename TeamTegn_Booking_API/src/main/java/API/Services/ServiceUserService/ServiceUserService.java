package API.Services.ServiceUserService;

import API.Database_Entities.ServiceUserEntity;
import API.Repository.ServiceUser.ServiceUserDAOImpl;
import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import Shared.ToReturn.ServiceUserDto;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServiceUserService implements IServiceUserService {

    @Autowired
    private ServiceUserDAOImpl repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ServiceUserDto add(ServiceUserForCreationDto userForCreationDto) {
        ServiceUserEntity entity = mapper.map(userForCreationDto, ServiceUserEntity.class);
        ServiceUserEntity result = repository.add(entity);
        return mapper.map(result, ServiceUserDto.class);
    }

    @Override
    public ServiceUserDto get(int id) {
        return mapper.map(repository.findByID(id), ServiceUserDto.class);

    }

    @Override
    public List<ServiceUserDto> getAll() {
        List<ServiceUserEntity> elements = (List<ServiceUserEntity>) Lists.newArrayList( repository.list() );
        return mapper.map(elements,   new TypeToken<List<ServiceUserDto>>(){}.getType());

    }

    @Override
    public boolean delete(int id) {
        return repository.deleteById(id);
    }

    @Override
    public ServiceUserDto update(int id, ServiceUserForUpdateDto forUpdateDto) {
        ServiceUserEntity entity = mapper.map(forUpdateDto, ServiceUserEntity.class);
        ServiceUserEntity result = repository.update(entity);
        return mapper.map(result, ServiceUserDto.class);
    }
}
