package API.Services.ServiceUserService;

import API.Models.Database_Entities.ServiceUserCommentEntity;
import API.Repository.ServiceUser.ServiceUserCommentDAO;
import Shared.ForCreation.ServiceUserCommentForCreationDto;
import Shared.ForCreation.ServiceUserCommentForUpdateDto;
import Shared.ToReturn.ServiceUserCommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUserCommentService implements IServiceUserCommentService {
    private ModelMapper modelMapper;
    private ServiceUserCommentDAO serviceUserCommentDAO;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceUserCommentDAO(ServiceUserCommentDAO serviceUserCommentDAO) {
        this.serviceUserCommentDAO = serviceUserCommentDAO;
    }

    @Override
    public ServiceUserCommentDto addServiceUserComment(ServiceUserCommentForCreationDto serviceProviderComment) {
        return serviceUserCommentDAO.addServiceUserComment(modelMapper.map(serviceProviderComment, ServiceUserCommentEntity.class));
    }

    @Override
    public ServiceUserCommentDto updateServiceUserComment(ServiceUserCommentForUpdateDto serviceProviderComment) {
        return serviceUserCommentDAO.updateServiceUserComment(modelMapper.map(serviceProviderComment, ServiceUserCommentEntity.class));
    }

    @Override
    public ServiceUserCommentDto findServiceUserComment(int commentID) {
        return serviceUserCommentDAO.findServiceUserComment(commentID);
    }

    @Override
    public boolean deleteServiceUserComment(int commentID) {
        return serviceUserCommentDAO.deleteServiceUserComment(commentID);
    }

    @Override
    public List<ServiceUserCommentDto> listServiceUserComments(int id) {
        try {
            return serviceUserCommentDAO.listServiceUserComments(id);
        } catch (Exception e) {
            throw e;
        }
    }
}
