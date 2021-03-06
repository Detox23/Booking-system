package API.Services.ServiceProviderService;

import API.Models.Database_Entities.ServiceProviderCommentEntity;
import API.Repository.ServiceProvider.ServiceProviderCommentDAO;
import Shared.ForCreation.ServiceProviderCommentForCreationDto;
import Shared.ForCreation.ServiceProviderCommentForUpdateDto;
import Shared.ToReturn.ServiceProviderCommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceProviderCommentService implements IServiceProviderCommentService {

    private ModelMapper modelMapper;

    private ServiceProviderCommentDAO serviceProviderCommentDAO;

    @Autowired
    public void setServiceProviderCommentDAO(ServiceProviderCommentDAO serviceProviderCommentDAO) {
        this.serviceProviderCommentDAO = serviceProviderCommentDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderCommentDto addServiceProviderComment(ServiceProviderCommentForCreationDto serviceProviderComment) {
        return serviceProviderCommentDAO.addServiceProviderComment(modelMapper.map(serviceProviderComment, ServiceProviderCommentEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderCommentDto updateServiceProviderComment(ServiceProviderCommentForUpdateDto serviceProviderComment) {
        return serviceProviderCommentDAO.updateServiceProviderComment(modelMapper.map(serviceProviderComment, ServiceProviderCommentEntity.class));
    }

    @Override
    public ServiceProviderCommentDto findServiceProviderComment(int commentID) {
        return serviceProviderCommentDAO.findServiceProviderComment(commentID);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceProviderComment(int commentID) {
        return serviceProviderCommentDAO.deleteServiceProviderComment(commentID);
    }

    @Override
    public List<ServiceProviderCommentDto> findServiceProviderComments(int id) {
        return serviceProviderCommentDAO.findServiceProviderComments(id);
    }
}
