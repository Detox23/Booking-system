package API.Repository.ServiceUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.ServiceUserEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class        ServiceUserDAOImpl implements ServiceUserDAOCustom {

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setServiceUserDAO(ServiceUserDAO serviceUserDAO) {
        this.serviceUserDAO = serviceUserDAO;
    }

    private ServiceUserDAO serviceUserDAO;


    private PatcherHandler patcherHandler;

    @Override
    public ServiceUserEntity add(ServiceUserEntity userEntity) {
        //TODO: in service/ controller set current user as creator
        userEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        return serviceUserDAO.save(userEntity);
    }

    @Override
    public boolean deleteById(int id) {
        try {
            Optional<ServiceUserEntity> found = serviceUserDAO.findById(id);
            if (!found.isPresent()) {
                throw new NotFoundException("The service user was not found.");
            }
            ServiceUserEntity toDelete = found.get();
            toDelete.setDeleted(true);
            ServiceUserEntity deletionResult = serviceUserDAO.save(toDelete);
            if (deletionResult.isDeleted()) {
                return true;
            } else {
                return false;
            }
        }catch (NotFoundException notFoundException){
            throw notFoundException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }

    @Override
    public Iterable<ServiceUserEntity> list() {
        ServiceUserEntity userEntity = new ServiceUserEntity();
        userEntity.setDeleted(false);
        Example<ServiceUserEntity> usExample = Example.of(userEntity);

        return serviceUserDAO.findAll(usExample);
    }

    @Override
    public ServiceUserEntity findByID(int id) {
        ServiceUserEntity userEntity = new ServiceUserEntity();
        userEntity.setDeleted(false);
        userEntity.setId(id);
        Example<ServiceUserEntity> uExample = Example.of(userEntity);

        return serviceUserDAO.findOne(uExample).get();
    }

    @Override
    public ServiceUserEntity update(ServiceUserEntity userEntity) {
        //TODO: in service/ controller set current user as editor

        try {
            ServiceUserEntity found = serviceUserDAO.findFirstByIdAndDeletedIsFalse(userEntity.getId());
            if(found != null)
            {
            patcherHandler.fillNotNullFields(found, userEntity);
            return serviceUserDAO.save(found);
            }
            return null;
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Account was not found while an attempt of making update.");
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an account [PATCHING].");
        }
    }
}

