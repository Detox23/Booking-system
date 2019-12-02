package API.Services.ServiceUserService;

import API.Database_Entities.ServiceUserCommentEntity;
import API.Repository.ServiceUser.ServiceUserCommentDAO;
import Shared.ForCreation.ServiceUserCommentForCreationDto;
import Shared.ForCreation.ServiceUserCommentForUpdateDto;
import Shared.ToReturn.ServiceUserCommentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceUserCommentService implements IServiceUserCommentService {
    private ModelMapper mapper;
    private ServiceUserCommentDAO serviceUserCommentDAO;

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setServiceUserCommentDAO(ServiceUserCommentDAO serviceUserCommentDAO) {
        this.serviceUserCommentDAO = serviceUserCommentDAO;
    }

    @Override
    public ServiceUserCommentDto add(int serviceUserId, ServiceUserCommentForCreationDto serviceProviderComment) {
        ServiceUserCommentEntity userCommentEntity = mapper.map(serviceProviderComment, ServiceUserCommentEntity.class);
        userCommentEntity.setServiceUserId(serviceUserId);
        ServiceUserCommentEntity added = serviceUserCommentDAO.addOnce(userCommentEntity);
        ServiceUserCommentDto mapped = mapper.map(added, ServiceUserCommentDto.class);
        return mapped;
    }

    @Override
    public ServiceUserCommentDto update(int id, ServiceUserCommentForUpdateDto serviceProviderComment) {
        ServiceUserCommentEntity userCommentEntity = mapper.map(serviceProviderComment, ServiceUserCommentEntity.class);
        userCommentEntity.setId(id);
        ServiceUserCommentEntity updated = serviceUserCommentDAO.update(userCommentEntity);
        ServiceUserCommentDto mapped = mapper.map(updated, ServiceUserCommentDto.class);
        return mapped;
    }

    @Override
    public ServiceUserCommentDto find(int id, int commentID) {
        Optional<ServiceUserCommentEntity> userCommentEntity = serviceUserCommentDAO.findByServiceUserIdIsAndIdIs(id, commentID);
        if (userCommentEntity.isPresent()) {
            ServiceUserCommentDto mapped = mapper.map(userCommentEntity, ServiceUserCommentDto.class);
            return mapped;
        }
        return null;
    }

    @Override
    public boolean delete(int id, int commentID) {
        return serviceUserCommentDAO.deleteOne(id, commentID);
    }

    @Override
    public List<ServiceUserCommentDto> findServiceUserComments(int id) {
        try {
            Type listType = new TypeToken<List<ServiceUserCommentDto>>() {
            }.getType();
            Iterable<ServiceUserCommentEntity> entities = serviceUserCommentDAO.findAllByServiceUserId(id);
            return mapper.map(entities, listType);
        } catch (Exception e) {
            throw e;
        }
    }
}
