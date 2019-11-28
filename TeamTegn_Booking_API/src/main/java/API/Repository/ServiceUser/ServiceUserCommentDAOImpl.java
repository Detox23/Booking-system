package API.Repository.ServiceUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.ServiceUserCommentEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.Optional;
@Component
public class ServiceUserCommentDAOImpl implements ServiceUserCommentDAOCustom {

   private ServiceUserCommentDAO serviceUserCommentDAO;
   private PatcherHandler patcherHandler;

   @Autowired
    public void setServiceUserCommentDAO(ServiceUserCommentDAO serviceUserCommentDAO) {
        this.serviceUserCommentDAO = serviceUserCommentDAO;
    }
    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Override
   public Iterable<ServiceUserCommentEntity> listAll() {
      return  serviceUserCommentDAO.findAll();

   }

   @Override
   public Iterable<ServiceUserCommentEntity> findAllByServiceUserId(int serviceUserId) {
       return serviceUserCommentDAO.findAllByServiceUserId(serviceUserId);
   }

   @Override
   public ServiceUserCommentEntity findOne(int id) {
       try {
           Optional<ServiceUserCommentEntity> found = serviceUserCommentDAO.findById(id);
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
   public ServiceUserCommentEntity addOnce(ServiceUserCommentEntity serviceProvider) {
       try {
           if (serviceProvider == null) {
               return null;
           }
           return serviceUserCommentDAO.save(serviceProvider);
       }
       catch (Exception e)
       {
           throw e;
       }
   }

   @Override
   public ServiceUserCommentEntity update(ServiceUserCommentEntity serviceProviderComment)  {
       try {
           Optional<ServiceUserCommentEntity> found = serviceUserCommentDAO.findById(serviceProviderComment.getId());
           if (!found.isPresent()) {
               throw new NotFoundException("The comment was not found");
           }
           patcherHandler.fillNotNullFields(found.get(), serviceProviderComment);
           ServiceUserCommentEntity updated = serviceUserCommentDAO.save(found.get());
           return updated;
       }catch(IntrospectionException introspectionException){
           throw new UpdatePatchException("There was a problem with updating a comment.[PATCHING]");
       }catch (Exception e){
           throw e;
       }
   }

   @Override
   public boolean deleteOne(int id, int commentID) {
       try
       {
       Optional<ServiceUserCommentEntity> found = serviceUserCommentDAO.findByServiceUserIdIsAndIdIs(id, commentID);
       if(!found.isPresent() ){
           throw new NotFoundException("Comment is not found");
       }
           serviceUserCommentDAO.deleteById(found.get().getId());
       Optional<ServiceUserCommentEntity> assure = serviceUserCommentDAO.findById(id);
       if(!assure.isPresent()){
           return true;
       }
       return false;
       }catch(Exception e){
           throw e;
       }    }
}
