package API.Services;

import API.Database_Entities.ServiceUserEntity;
import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import Shared.ToReturn.ServiceUserDto;

import java.util.List;

public interface IServiceUserService {
    ServiceUserDto add(ServiceUserForCreationDto assignmentEntity);
    ServiceUserDto get(int id);
    List<ServiceUserDto> getAll();
    boolean delete(int id);
    ServiceUserDto update(int id, ServiceUserForUpdateDto assignmentEntity);

}
