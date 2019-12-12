package API.Services.ServiceUserService;

import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import Shared.ToReturn.ServiceUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IServiceUserService {
    ServiceUserDto addServiceUser(ServiceUserForCreationDto assignmentEntity);

    ServiceUserDto findServiceUser(int id);

    Page<ServiceUserDto> listServiceUsers(Pageable pageable);

    boolean deleteServiceUser(int id);

    ServiceUserDto updateServiceUser(ServiceUserForUpdateDto assignmentEntity);

}
