package API.Repository.SystemUser;

import API.Models.Database_Entities.SystemUserEntity;
import Shared.ToReturn.SystemUserDto;

import java.util.List;

public interface SystemUserDAOCustom {
    SystemUserDto addSystemUser(SystemUserEntity systemUser, List<Integer> departments);

    SystemUserDto updateSystemUser(SystemUserEntity systemUser, List<Integer> departments);

    SystemUserDto findSystemUser(String userName);

    boolean deleteSystemUser(int id);

    List<SystemUserDto> listSystemUsers(boolean showDeleted);

    SystemUserDto findSystemUser(int id);

}
