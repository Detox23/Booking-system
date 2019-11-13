package API.Repository.ServiceUser;

import API.Database_Entities.ServiceUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ServiceUserDAOImpl implements ServiceUserDAOCustom {

    @Autowired
    private ServiceUserDAO jpaRepository;

    @Override
    public ServiceUserEntity add(ServiceUserEntity userEntity) {
        //TODO: in service/ controller set curren user as creator
        userEntity.setCreatedDate( Timestamp.valueOf(LocalDateTime.now()));
        return jpaRepository.save(userEntity);
    }

    @Override
    public boolean deleteById(int id) {
         jpaRepository.deleteById(id);
         return jpaRepository.findById(id).get() == null;
    }

    @Override
    public Iterable<ServiceUserEntity> list() {
        ServiceUserEntity userEntity = new ServiceUserEntity();
        userEntity.setDeleted(false);
        Example<ServiceUserEntity> usExample = Example.of(userEntity);

        return jpaRepository.findAll(usExample);
    }

    @Override
    public ServiceUserEntity findByID(int id) {
        ServiceUserEntity userEntity = new ServiceUserEntity();
        userEntity.setDeleted(false);
        userEntity.setId(id);
        Example<ServiceUserEntity> uExample = Example.of(userEntity);

        return jpaRepository.findOne(uExample).get();
    }

    @Override
    public ServiceUserEntity update(ServiceUserEntity userEntity) {
        //TODO: in service/ controller set curren user as editor

        ServiceUserEntity orginal = jpaRepository.findById(userEntity.getId()).get();
        userEntity.setCreatedBy(orginal.getCreatedBy());
        userEntity.setCreatedDate(orginal.getCreatedDate());
        userEntity.setDeleted(orginal.isDeleted());
        userEntity.setLastModified(Timestamp.valueOf(LocalDateTime.now()));

        return jpaRepository.save(userEntity);

    }
}
