package API.Services.ServiceUserService;

import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import Shared.ToReturn.ServiceUserDto;

import java.util.List;


public interface IServiceUserService {
    ServiceUserDto addServiceUser(ServiceUserForCreationDto assignmentEntity);
    ServiceUserDto findServiceUser(int id);
    List<ServiceUserDto> listServiceUsers();
    boolean deleteServiceUser(int id);
    ServiceUserDto updateServiceUser(int id, ServiceUserForUpdateDto assignmentEntity);

}
