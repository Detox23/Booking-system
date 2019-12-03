package API.Repository.ServiceUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Models.Database_Entities.ServiceUserPreferencesEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

public class ServiceUserPreferencesDAOImpl  implements  ServiceUserPreferencesDAOCustom{

    private ServiceUserPreferencesDAO serviceUserPreferencesDAO;
    private PatcherHandler patcherHandler;

    @Autowired
    public void setServiceUserPreferencesDAO(ServiceUserPreferencesDAO serviceUserPreferencesDAO) {
        this.serviceUserPreferencesDAO = serviceUserPreferencesDAO;
    }

    @Override
    public ServiceUserPreferencesEntity add(ServiceUserPreferencesEntity a) {
        try
        {
            if(a == null)
            {
                return  null;
            }
            return serviceUserPreferencesDAO.save(a);

        }
        catch (Exception e)
        {throw e;}

    }

    @Override
    public boolean deleteById(int id) {
        try
        {
            Optional<ServiceUserPreferencesEntity> found = serviceUserPreferencesDAO.findById(id);
            if(!found.isPresent() ){
                throw new NotFoundException("Comment is not found");
            }
            serviceUserPreferencesDAO.deleteById(found.get().getId());
            Optional<ServiceUserPreferencesEntity> assure = serviceUserPreferencesDAO.findById(id);
            if(!assure.isPresent()){
                return true;
            }
            return false;
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public List<ServiceUserPreferencesEntity> findAllByServiceUser(int id) {
        return  serviceUserPreferencesDAO.findAllByServiceUserIdOrderByRatingAsc(id);
    }

    @Override
    public ServiceUserPreferencesEntity findByServiceProviderAndUser(int serviceProviderId, int serviceUserId) {
        try {
            Optional<ServiceUserPreferencesEntity> found = serviceUserPreferencesDAO.findByServiceUserIdAndServiceProviderId(serviceUserId, serviceProviderId);
            if (!found.isPresent()) {
                throw new NotFoundException("The comment was not found");
            }
            return found.get();
        }
        catch(Exception e)
        {
            throw  e;
        }
    }


    @Override
    public ServiceUserPreferencesEntity update(ServiceUserPreferencesEntity pref) {
        try {
            Optional<ServiceUserPreferencesEntity> found = serviceUserPreferencesDAO.findById(pref.getId());
            if (!found.isPresent()) {
                throw new NotFoundException("The comment was not found");
            }
            patcherHandler.fillNotNullFields(found.get(), pref);
            ServiceUserPreferencesEntity updated = serviceUserPreferencesDAO.save(found.get());
            return updated;
        }catch(IntrospectionException introspectionException){
            throw new UpdatePatchException("There was a problem with updating a comment.[PATCHING]");
        }catch (Exception e){
            throw e;
        }
    }



}

