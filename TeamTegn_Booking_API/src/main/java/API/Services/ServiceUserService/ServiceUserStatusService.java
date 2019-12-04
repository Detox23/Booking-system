package API.Services.ServiceUserService;

import API.Models.Database_Entities.ServiceUserStatusEntity;
import API.Repository.ServiceUser.ServiceUserStatusDAO;
import Shared.ForCreation.ServiceUserStatusForCreationDto;
import Shared.ForCreation.ServiceUserStatusForUpdateDto;
import Shared.ToReturn.ServiceUserStatusDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceUserStatusService implements IServiceUserStatusService{

    private ServiceUserStatusDAO serviceUserStatusDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setServiceUserStatusDAO(ServiceUserStatusDAO serviceUserStatusDAO) {
        this.serviceUserStatusDAO = serviceUserStatusDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceUserStatusDto addServiceUserStatus(ServiceUserStatusForCreationDto serviceUserStatus) {
        return serviceUserStatusDAO.addServiceUserStatus(modelMapper.map(serviceUserStatus, ServiceUserStatusEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceUserStatusDto updateServiceUserStatus(ServiceUserStatusForUpdateDto serviceUserStatus) {
        return serviceUserStatusDAO.updateServiceUserStatus(modelMapper.map(serviceUserStatus, ServiceUserStatusEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceUserStatus(int id) {
        return serviceUserStatusDAO.deleteServiceUserStatus(id);
    }

    @Override
    public ServiceUserStatusDto findServiceUserStatus(int id) {
        return serviceUserStatusDAO.findServiceUserStatus(id);
    }

    @Override
    public List<ServiceUserStatusDto> listServiceUserStatuses(boolean showDeleted) {
        return serviceUserStatusDAO.listServiceUserStatuses(showDeleted);
    }
}
