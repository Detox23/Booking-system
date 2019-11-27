package API.Repository.SystemUser;

import API.Database_Entities.SystemUserEntity;
import Shared.ForCreation.SystemUserForCreationDto;
import Shared.ForCreation.SystemUserForUpdateDto;
import Shared.ToReturn.SystemUserDto;

import java.util.List;

public interface SystemUserDAOCustom {
    SystemUserDto addSystemUser(SystemUserEntity systemUser);

    SystemUserDto updateSystemUser(SystemUserEntity systemUser);

    boolean deleteSystemUser(int id);

    List<SystemUserDto> listSystemUsers();

    SystemUserDto findSystemUser(int id);
}
