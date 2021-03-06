package API.Services.ServiceUserService;

import API.Models.Database_Entities.ServiceUserCommentEntity;
import API.Repository.ServiceUser.ServiceUserCommentDAO;
import Shared.ForCreation.ServiceUserCommentForCreationDto;
import Shared.ForCreation.ServiceUserCommentForUpdateDto;
import Shared.ToReturn.ServiceUserCommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Throwable.class)
    public ServiceUserCommentDto addServiceUserComment(ServiceUserCommentForCreationDto serviceProviderComment) {
        return serviceUserCommentDAO.addServiceUserComment(modelMapper.map(serviceProviderComment, ServiceUserCommentEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceUserCommentDto updateServiceUserComment(ServiceUserCommentForUpdateDto serviceProviderComment) {
        return serviceUserCommentDAO.updateServiceUserComment(modelMapper.map(serviceProviderComment, ServiceUserCommentEntity.class));
    }

    @Override
    public ServiceUserCommentDto findServiceUserComment(int commentID) {
        return serviceUserCommentDAO.findServiceUserComment(commentID);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceUserComment(int commentID) {
        return serviceUserCommentDAO.deleteServiceUserComment(commentID);
    }

    @Override
    public List<ServiceUserCommentDto> listServiceUserComments(int id) {
        return serviceUserCommentDAO.listServiceUserComments(id);
    }
}
