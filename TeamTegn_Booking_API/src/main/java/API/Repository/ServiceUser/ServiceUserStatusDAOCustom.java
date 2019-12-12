package API.Repository.ServiceUser;

import API.Models.Database_Entities.ServiceUserStatusEntity;
import Shared.ToReturn.ServiceUserStatusDto;

import java.util.List;

public interface ServiceUserStatusDAOCustom {
    ServiceUserStatusDto addServiceUserStatus(ServiceUserStatusEntity serviceUserStatus);

    ServiceUserStatusDto updateServiceUserStatus(ServiceUserStatusEntity serviceUserStatus);

    boolean deleteServiceUserStatus(int id);

    ServiceUserStatusDto findServiceUserStatus(int id);

    List<ServiceUserStatusDto> listServiceUserStatuses(boolean showDeleted);
}
