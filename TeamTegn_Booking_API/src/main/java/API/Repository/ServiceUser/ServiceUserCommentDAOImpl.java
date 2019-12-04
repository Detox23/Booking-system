package API.Repository.ServiceUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.ServiceUserCommentEntity;
import Shared.ToReturn.ServiceUserCommentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceUserCommentDAOImpl implements ServiceUserCommentDAOCustom {

    private ServiceUserCommentDAO serviceUserCommentDAO;
    private PatcherHandler patcherHandler;
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceUserCommentDAO(ServiceUserCommentDAO serviceUserCommentDAO) {
        this.serviceUserCommentDAO = serviceUserCommentDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }


    @Override
    public List<ServiceUserCommentDto> listServiceUserComments(int serviceUserId) {
        try{
            Type listType = new TypeToken<List<ServiceUserCommentDto>>() {}.getType();
            List<ServiceUserCommentEntity> listOfComments = serviceUserCommentDAO.findAllByServiceUserIdIs(serviceUserId);
            return modelMapper.map(listOfComments, listType);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public ServiceUserCommentDto findServiceUserComment(int id) {
        try {
            ServiceUserCommentEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, ServiceUserCommentDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceUserCommentDto addServiceUserComment(ServiceUserCommentEntity serviceProvider) {
        try {
            return modelMapper.map(serviceUserCommentDAO.save(serviceProvider), ServiceUserCommentDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceUserCommentDto updateServiceUserComment(ServiceUserCommentEntity serviceProviderComment) {
        try {
            serviceProviderComment.setUserId(null);
            ServiceUserCommentEntity found = findIfExistsAndReturn(serviceProviderComment.getId());
            patcherHandler.fillNotNullFields(found, serviceProviderComment);
            ServiceUserCommentEntity updated = serviceUserCommentDAO.save(found);
            return modelMapper.map(updated, ServiceUserCommentDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was a problem with updating a comment.[PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteServiceUserComment(int commentID) {
        try {
            ServiceUserCommentEntity found = findIfExistsAndReturn(commentID);
            serviceUserCommentDAO.deleteById(found.getId());
            Optional<ServiceUserCommentEntity> assure = serviceUserCommentDAO.findById(commentID);
            return assure.isPresent();
        } catch (Exception e) {
            throw e;
        }
    }

    private ServiceUserCommentEntity findIfExistsAndReturn(int id) {
        Optional<ServiceUserCommentEntity> found = serviceUserCommentDAO.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Service user comment with id: %d was not found.", id));
        }
        return found.get();
    }
}
