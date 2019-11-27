package API.Repository.ServiceUser;

import API.Database_Entities.ServiceUserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceUserDAOCustom {
    ServiceUserEntity add(ServiceUserEntity a);

    boolean deleteById(int Id);

    Page<ServiceUserEntity> list(Pageable pageable);

    ServiceUserEntity findByID(int id);

    ServiceUserEntity update(ServiceUserEntity a);
}
