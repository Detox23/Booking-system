package API.Services.SystemUserService;

import Shared.ForCreation.SystemUserForCreationDto;
import Shared.ForCreation.SystemUserForUpdateDto;
import Shared.ToReturn.SystemUserDto;

import java.util.List;

public interface ISystemUserService {
    SystemUserDto addSystemUser(SystemUserForCreationDto systemUser);

    SystemUserDto updateSystemUser(SystemUserForUpdateDto systemUser);

    boolean deleteSystemUser(int id);

    List<SystemUserDto> listSystemUsers();

    SystemUserDto findSystemUser(int id);
}
