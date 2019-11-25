package API.Repository.ServiceUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DeletionException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Component
public class ServiceUserDAOImpl implements ServiceUserDAOCustom {

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setJpaRepository(ServiceUserDAO jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    private ServiceUserDAO jpaRepository;


    private PatcherHandler patcherHandler;

    @Override
    public ServiceUserEntity add(ServiceUserEntity userEntity) {
        //TODO: in service/ controller set current user as creator
        userEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        return jpaRepository.save(userEntity);
    }

    @Override
    public boolean deleteById(int id) {
        try {
            jpaRepository.deleteById(id);
            jpaRepository.findById(id).get();
            return false;
        }catch (NoSuchElementException noSuchElementException){
            return true;
        }catch (Exception e){
            throw new DeletionException("There was an error while deleting service user.");
        }
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
        //TODO: in service/ controller set current user as editor

        try {
            ServiceUserEntity found = jpaRepository.findFirstByIdAndDeletedIsFalse(userEntity.getId());
            if(found != null)
            {
            patcherHandler.fillNotNullFields(found, userEntity);
            return jpaRepository.save(found);
            }
            return null;
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Account was not found while an attempt of making update.");
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        }
    }
}

