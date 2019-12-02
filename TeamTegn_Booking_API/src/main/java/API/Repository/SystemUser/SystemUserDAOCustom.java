package API.Repository.SystemUser;

import API.Database_Entities.SystemUserEntity;
import Shared.ToReturn.SystemUserDto;

import java.util.List;

public interface SystemUserDAOCustom {
    SystemUserDto addSystemUser(SystemUserEntity systemUser , List<Integer> departments);

    SystemUserDto updateSystemUser(SystemUserEntity systemUser, List<Integer> departments);

    boolean deleteSystemUser(int id);

    List<SystemUserDto> listSystemUsers();

    SystemUserDto findSystemUser(int id);

    boolean logIn(String login, String password);
}
