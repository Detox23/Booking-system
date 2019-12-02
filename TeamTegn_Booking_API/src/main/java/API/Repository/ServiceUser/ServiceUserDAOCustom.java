package API.Repository.ServiceUser;

import API.Models.Database_Entities.ServiceUserEntity;
import Shared.ToReturn.ServiceUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceUserDAOCustom {
    ServiceUserDto addServiceUser(ServiceUserEntity serviceUser, List<Integer> accounts);

    boolean deleteServiceUser(int Id);

    Page<ServiceUserDto> listServiceUsers(Pageable pageable);

    ServiceUserDto findServiceUser(int id);

    ServiceUserDto updateServiceUser(ServiceUserEntity serviceUser, List<Integer> accounts);
}
