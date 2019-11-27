package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.ServiceProviderPreferredNotificationEntity;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import Shared.ToReturn.ServiceProviderPreferredNotificationDto;
import Shared.ToReturn.ServiceProviderSourceDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceProviderPreferredNotificationDAOImpl implements ServiceProviderPreferredNotificationDAOCustom {

    private ServiceProviderPreferredNotificationDAO serviceProviderPreferredNotificationDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    @Autowired
    public void setServiceProviderPreferredNotificationDAO(ServiceProviderPreferredNotificationDAO serviceProviderPreferredNotificationDAO) {
        this.serviceProviderPreferredNotificationDAO = serviceProviderPreferredNotificationDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ServiceProviderPreferredNotificationDto addServiceProviderNotification(ServiceProviderPreferredNotificationEntity serviceProviderNotification) {
        serviceProviderNotification.setDeleted(false);
        if(serviceProviderPreferredNotificationDAO.findByNotificationTypeAndDeletedIsFalse(serviceProviderNotification.getNotificationType()).isPresent()){
            throw new DuplicateException("There is already notification with exact notification type.");
        }
        return modelMapper.map(serviceProviderPreferredNotificationDAO.save(serviceProviderNotification), ServiceProviderPreferredNotificationDto.class);
    }

    @Override
    public ServiceProviderPreferredNotificationDto updateServiceProviderNotification(ServiceProviderPreferredNotificationEntity serviceProviderNotification) {
        try {
            Optional<ServiceProviderPreferredNotificationEntity> found = serviceProviderPreferredNotificationDAO.findById(serviceProviderNotification.getId());
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("The notification does not exist");
            }
            patcherHandler.fillNotNullFields(found.get(), serviceProviderNotification);
            ServiceProviderPreferredNotificationEntity updated = serviceProviderPreferredNotificationDAO.save(found.get());
            return modelMapper.map(updated, ServiceProviderPreferredNotificationDto.class);
        }catch(IntrospectionException introspectionExcpetion){
            throw new UpdatePatchException("There was an error while updating the notification. [PATCHING]");
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public boolean deleteServiceProviderNotification(int id) {
        try {
            Optional<ServiceProviderPreferredNotificationEntity> found = serviceProviderPreferredNotificationDAO.findById(id);
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("The notification was not found.");
            }
            found.get().setDeleted(true);
            ServiceProviderPreferredNotificationEntity updated = serviceProviderPreferredNotificationDAO.save(found.get());
            if (updated.isDeleted()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ServiceProviderPreferredNotificationDto> listServiceProviderNotifications() {
        try {
            Type listType = new TypeToken<List<ServiceProviderPreferredNotificationDto>>() {}.getType();
            return modelMapper.map(serviceProviderPreferredNotificationDAO.findAllByDeletedIsFalse(), listType);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceProviderPreferredNotificationDto findServiceProviderNotification(int id) {
        try {
            Optional<ServiceProviderPreferredNotificationEntity> found = serviceProviderPreferredNotificationDAO.findById(id);
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("The notification was not found.");
            }
            return modelMapper.map(found.get(), ServiceProviderPreferredNotificationDto.class);
        } catch (Exception e) {
            throw e;
        }
    }
}
