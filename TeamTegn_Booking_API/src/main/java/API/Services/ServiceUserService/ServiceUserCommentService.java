package API.Services.ServiceUserService;

import API.Database_Entities.ServiceUserCommentEntity;
import API.Database_Entities.ServiceUserEntity;
import API.Exceptions.NotFoundException;
import API.Repository.ServiceUser.ServiceUserCommentDAO;
import Shared.ForCreation.ServiceUserCommentForCreationDto;
import Shared.ForCreation.ServiceUserCommentForUpdateDto;
import Shared.ToReturn.ServiceUserCommentDto;
import Shared.ToReturn.ServiceUserDto;
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
        ServiceUserCommentEntity userComentEntity = mapper.map(serviceProviderComment, ServiceUserCommentEntity.class );
        userComentEntity.setServiceUserId(serviceUserId);
        ServiceUserCommentEntity added =serviceUserCommentDAO.addOnce(userComentEntity);
        ServiceUserCommentDto mapped = mapper.map(added, ServiceUserCommentDto.class);
        return mapped;
    }

    @Override
    public ServiceUserCommentDto update(int id, ServiceUserCommentForUpdateDto serviceProviderComment) {
        ServiceUserCommentEntity userComentEntity = mapper.map(serviceProviderComment, ServiceUserCommentEntity.class );
        userComentEntity.setId(id);
        ServiceUserCommentEntity updated =serviceUserCommentDAO.update(userComentEntity);
        ServiceUserCommentDto mapped = mapper.map(updated, ServiceUserCommentDto.class);
        return mapped;
    }

    @Override
    public ServiceUserCommentDto find(int id, int commentID) {
        Optional<ServiceUserCommentEntity> userComentEntity = serviceUserCommentDAO.findByServiceUserIdIsAndIdIs(id, commentID);
        if(userComentEntity.isPresent())
        {
            ServiceUserCommentDto mapped = mapper.map(userComentEntity, ServiceUserCommentDto.class);
            return  mapped;
        }
        return null;
    }

    @Override
    public boolean delete(int id, int commentID) {
        return serviceUserCommentDAO.deleteOne(id, commentID);
    }

    @Override
    public List<ServiceUserCommentDto> findServiceUserComments(int id) {
        try{
            Type listType = new TypeToken<List<ServiceUserCommentDto>>() {}.getType();
            Iterable<ServiceUserCommentEntity> entities = serviceUserCommentDAO.findAllByServiceUserId(id);
            return mapper.map(entities, listType);
        }catch (Exception e){
            throw e;
        }
    }
}
