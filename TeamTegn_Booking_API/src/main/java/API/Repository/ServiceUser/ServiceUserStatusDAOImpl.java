package API.Repository.ServiceUser;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.ServiceUserStatusEntity;
import Shared.ToReturn.ServiceUserStatusDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceUserStatusDAOImpl implements ServiceUserStatusDAOCustom {

    private ModelMapper modelMapper;

    private ServiceUserStatusDAO serviceUserStatusDAO;

    private PatcherHandler patcherHandler;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceUserStatusDAO(ServiceUserStatusDAO serviceUserStatusDAO) {
        this.serviceUserStatusDAO = serviceUserStatusDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Override
    public ServiceUserStatusDto addServiceUserStatus(ServiceUserStatusEntity serviceUserStatus) {
        try {
            checkIfExistsByStatusName(serviceUserStatus);
            ServiceUserStatusEntity saved = serviceUserStatusDAO.save(serviceUserStatus);
            return modelMapper.map(saved, ServiceUserStatusDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceUserStatusDto updateServiceUserStatus(ServiceUserStatusEntity serviceUserStatus) {
        try{
            ServiceUserStatusEntity found = findIfExistsAndReturn(serviceUserStatus.getId());
            patcherHandler.fillNotNullFields(found, serviceUserStatus);
            checkIfExistsByStatusName(found);
            ServiceUserStatusEntity updated  = serviceUserStatusDAO.save(found);
            return modelMapper.map(updated, ServiceUserStatusDto.class);
        }catch(IntrospectionException introspectionException){
            throw new UpdatePatchException("There was a problem while updating service user status. [PATCHING]");
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public boolean deleteServiceUserStatus(int id) {
        try{
            ServiceUserStatusEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            ServiceUserStatusEntity deleted = serviceUserStatusDAO.save(found);
            return deleted.isDeleted();
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public ServiceUserStatusDto findServiceUserStatus(int id) {
       try{
           ServiceUserStatusEntity found = findIfExistsAndReturn(id);
           return modelMapper.map(found, ServiceUserStatusDto.class);
       }catch(Exception e){
           throw e;
       }
    }

    @Override
    public List<ServiceUserStatusDto> listServiceUserStatuses(boolean showDeleted) {
        if(showDeleted){
            try{
                Type listType = new TypeToken<List<ServiceUserStatusDto>>() {}.getType();
                return modelMapper.map(serviceUserStatusDAO.findAll(), listType);
            }catch(Exception e){
                throw e;
            }
        }else{
            try{
                Type listType = new TypeToken<List<ServiceUserStatusDto>>() {}.getType();
                return modelMapper.map(serviceUserStatusDAO.findAllByDeletedIsFalse(), listType);
            }catch(Exception e){
                throw e;
            }
        }
    }

    private ServiceUserStatusEntity findIfExistsAndReturn(int id) {
        Optional<ServiceUserStatusEntity> found = serviceUserStatusDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Assignment status with id: %d was not found.", id));
        }
        return found.get();
    }

    private void checkIfExistsByStatusName(ServiceUserStatusEntity serviceUserStatus){
        if (serviceUserStatusDAO.countAllByStatusIs(serviceUserStatus.getStatus()) > 0) {
            throw new DuplicateException(String.format("There is already service user's status with name %s.", serviceUserStatus.getStatus()));
        }
    }
}
