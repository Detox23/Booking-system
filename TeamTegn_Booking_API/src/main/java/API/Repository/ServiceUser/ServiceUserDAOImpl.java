package API.Repository.ServiceUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AccountEntity;
import API.Database_Entities.ServiceUserEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdateErrorException;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.beans.IntrospectionException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class ServiceUserDAOImpl implements ServiceUserDAOCustom {

    @Autowired
    private ServiceUserDAO jpaRepository;
    @Autowired
    private PatcherHandler patcherHandler;

    @Override
    public ServiceUserEntity add(ServiceUserEntity userEntity) {
        //TODO: in service/ controller set current user as creator
        userEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
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

        try {
            ServiceUserEntity found = jpaRepository.findFirstByIdAndAndDeletedIsFalse(userEntity.getId());
            if(found != null)
            {
            patcherHandler.fillNotNullFields(found, userEntity);
            ServiceUserEntity result = jpaRepository.save(found);
            return result;
            }
            return null;
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Account was not found while an attempt of making update.");
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        } catch (Exception e) {
            throw e;
    }
    }
}

