package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Models.Database_Entities.ServiceProviderCommentEntity;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.ServiceProviderCommentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceProviderCommentDAOImpl implements ServiceProviderCommentDAOCustom {

    private ModelMapper modelMapper;

    private ServiceProviderCommentDAO serviceProviderCommentDAO;

    private PatcherHandler patcherHandler;


    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderCommentDAO(ServiceProviderCommentDAO serviceProviderCommentDAO) {
        this.serviceProviderCommentDAO = serviceProviderCommentDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Override
    public ServiceProviderCommentDto addServiceProviderComment(ServiceProviderCommentEntity serviceProviderComment) {
        return modelMapper.map(serviceProviderCommentDAO.save(serviceProviderComment), ServiceProviderCommentDto.class);
    }

    @Override
    public ServiceProviderCommentDto updateServiceProviderComment(ServiceProviderCommentEntity serviceProviderComment)  {
        try {
            ServiceProviderCommentEntity found = findIfExistsAndReturn(serviceProviderComment.getId());
            patcherHandler.fillNotNullFields(found, serviceProviderComment);
            ServiceProviderCommentEntity updated = serviceProviderCommentDAO.save(found);
            return modelMapper.map(updated, ServiceProviderCommentDto.class);
        }catch(IntrospectionException introspectionException){
            throw new UpdatePatchException("There was a problem with updating a comment.[PATCHING]");
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public ServiceProviderCommentDto findServiceProviderComment(int commentID) {
        try{
            ServiceProviderCommentEntity found = findIfExistsAndReturn(commentID);
            return modelMapper.map(found, ServiceProviderCommentDto.class);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public List<ServiceProviderCommentDto> findServiceProviderComments(int serviceProviderID) {
        try{
            Type listType = new TypeToken<List<ServiceProviderCommentDto>>() {}.getType();
            List<ServiceProviderCommentEntity> listOfComments = serviceProviderCommentDAO.findAllByServiceProviderIdIs(serviceProviderID);
            return modelMapper.map(listOfComments, listType);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public boolean deleteServiceProviderComment(int commentID) {
        try{
            ServiceProviderCommentEntity found = findIfExistsAndReturn(commentID);
            serviceProviderCommentDAO.deleteById(found.getId());
            Optional<ServiceProviderCommentEntity> assure = serviceProviderCommentDAO.findById(commentID);
            return assure.isPresent();
        }catch(Exception e){
            throw e;
        }
    }

    private ServiceProviderCommentEntity findIfExistsAndReturn(int id) {
        Optional<ServiceProviderCommentEntity> found = serviceProviderCommentDAO.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Service provider comment with id: %d was not found.", id));
        }
        return found.get();
    }
}
