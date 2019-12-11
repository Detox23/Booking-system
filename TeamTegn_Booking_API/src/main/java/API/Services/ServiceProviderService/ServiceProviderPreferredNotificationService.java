package API.Services.ServiceProviderService;

import API.Models.Database_Entities.ServiceProviderPreferredNotificationEntity;
import API.Repository.ServiceProvider.ServiceProviderPreferredNotificationDAO;
import Shared.ForCreation.ServiceProviderPreferredNotificationForCreationDto;
import Shared.ForCreation.ServiceProviderPreferredNotificationForUpdateDto;
import Shared.ToReturn.ServiceProviderPreferredNotificationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceProviderPreferredNotificationService implements IServiceProviderPreferredNotificationService {

    private ModelMapper modelMapper;

    private ServiceProviderPreferredNotificationDAO serviceProviderPreferredNotificationDAO;

    @Autowired
    public void setServiceProviderPreferredNotificationDAO(ServiceProviderPreferredNotificationDAO serviceProviderPreferredNotificationDAO) {
        this.serviceProviderPreferredNotificationDAO = serviceProviderPreferredNotificationDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderPreferredNotificationDto addServiceProviderNotification(ServiceProviderPreferredNotificationForCreationDto serviceProviderNotification) {
        return serviceProviderPreferredNotificationDAO.addServiceProviderNotification(modelMapper.map(serviceProviderNotification, ServiceProviderPreferredNotificationEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderPreferredNotificationDto updateServiceProviderNotification(ServiceProviderPreferredNotificationForUpdateDto serviceProviderNotification) {
        return serviceProviderPreferredNotificationDAO.updateServiceProviderNotification(modelMapper.map(serviceProviderNotification, ServiceProviderPreferredNotificationEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceProviderNotification(int id) {
        return serviceProviderPreferredNotificationDAO.deleteServiceProviderNotification(id);
    }

    @Override
    public List<ServiceProviderPreferredNotificationDto> listServiceProviderNotifications(boolean showDeleted) {
        return serviceProviderPreferredNotificationDAO.listServiceProviderNotifications(showDeleted);
    }

    @Override
    public ServiceProviderPreferredNotificationDto findServiceProviderNotification(int id) {
        return serviceProviderPreferredNotificationDAO.findServiceProviderNotification(id);
    }
}
