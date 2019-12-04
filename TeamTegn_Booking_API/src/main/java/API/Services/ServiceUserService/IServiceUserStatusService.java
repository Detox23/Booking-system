package API.Services.ServiceUserService;

import Shared.ForCreation.ServiceUserStatusForCreationDto;
import Shared.ForCreation.ServiceUserStatusForUpdateDto;
import Shared.ToReturn.ServiceUserStatusDto;

import java.util.List;

public interface IServiceUserStatusService {
    ServiceUserStatusDto addServiceUserStatus(ServiceUserStatusForCreationDto serviceUserStatus);
    ServiceUserStatusDto updateServiceUserStatus(ServiceUserStatusForUpdateDto serviceUserStatus);
    boolean deleteServiceUserStatus(int id);
    ServiceUserStatusDto findServiceUserStatus(int id);
    List<ServiceUserStatusDto> listServiceUserStatuses(boolean showDeleted);
}
