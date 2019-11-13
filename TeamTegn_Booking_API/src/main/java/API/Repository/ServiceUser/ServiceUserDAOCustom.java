package API.Repository.ServiceUser;

import API.Database_Entities.ServiceUserEntity;

public interface ServiceUserDAOCustom {
    ServiceUserEntity add(ServiceUserEntity a);
    boolean deleteById(int Id);
    Iterable<ServiceUserEntity> list();
    ServiceUserEntity findByID(int id);
    ServiceUserEntity update(ServiceUserEntity a);
}
