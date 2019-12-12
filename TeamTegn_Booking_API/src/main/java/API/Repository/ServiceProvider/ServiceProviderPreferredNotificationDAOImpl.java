package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.ServiceProviderPreferredNotificationEntity;
import Shared.ToReturn.ServiceProviderPreferredNotificationDto;
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
        try {
            checkIfNotificationExist(serviceProviderNotification);
            ServiceProviderPreferredNotificationEntity saved = serviceProviderPreferredNotificationDAO.save(serviceProviderNotification);
            return modelMapper.map(saved, ServiceProviderPreferredNotificationDto.class);
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public ServiceProviderPreferredNotificationDto updateServiceProviderNotification(ServiceProviderPreferredNotificationEntity serviceProviderNotification) {
        try {
            ServiceProviderPreferredNotificationEntity found = findIfExistsAndReturn(serviceProviderNotification.getId());
            patcherHandler.fillNotNullFields(found, serviceProviderNotification);
            checkIfNotificationExist(found);
            ServiceProviderPreferredNotificationEntity updated = serviceProviderPreferredNotificationDAO.save(found);
            return modelMapper.map(updated, ServiceProviderPreferredNotificationDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating the notification. [PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteServiceProviderNotification(int id) {
        try {
            ServiceProviderPreferredNotificationEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            ServiceProviderPreferredNotificationEntity deleted = serviceProviderPreferredNotificationDAO.save(found);
            return deleted.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<ServiceProviderPreferredNotificationDto> listServiceProviderNotifications(boolean showDeleted) {
        if (showDeleted) {
            try {
                Type listType = new TypeToken<List<ServiceProviderPreferredNotificationDto>>() {
                }.getType();
                return modelMapper.map(serviceProviderPreferredNotificationDAO.findAll(), listType);
            } catch (Exception e) {
                throw e;
            }
        } else {
            try {
                Type listType = new TypeToken<List<ServiceProviderPreferredNotificationDto>>() {
                }.getType();
                return modelMapper.map(serviceProviderPreferredNotificationDAO.findAllByDeletedIsFalse(), listType);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public ServiceProviderPreferredNotificationDto findServiceProviderNotification(int id) {
        try {
            ServiceProviderPreferredNotificationEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, ServiceProviderPreferredNotificationDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    private void checkIfNotificationExist(ServiceProviderPreferredNotificationEntity serviceProviderPreferredNotification) {
        if (serviceProviderPreferredNotification.getId() == 0) {
            if (serviceProviderPreferredNotificationDAO.countAllByNotificationTypeIs(serviceProviderPreferredNotification.getNotificationType()) > 0) {
                throw new DuplicateException(String.format("The notification type: %s already exists", serviceProviderPreferredNotification.getNotificationType()));
            }
        } else {
            if (serviceProviderPreferredNotificationDAO.countAllByNotificationTypeIsAndIdIsNot(serviceProviderPreferredNotification.getNotificationType(), serviceProviderPreferredNotification.getId()) > 0) {
                throw new DuplicateException(String.format("The notification type: %s already exists", serviceProviderPreferredNotification.getNotificationType()));
            }
        }
    }

    private ServiceProviderPreferredNotificationEntity findIfExistsAndReturn(int id) {
        Optional<ServiceProviderPreferredNotificationEntity> found = serviceProviderPreferredNotificationDAO.findByIdIsAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Service provider preferred notification with id: %d was not found.", id));
        }
        return found.get();
    }
}
