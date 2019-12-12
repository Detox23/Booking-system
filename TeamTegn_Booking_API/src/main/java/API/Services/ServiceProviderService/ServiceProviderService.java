package API.Services.ServiceProviderService;

import API.Configurations.Encryption.EncryptionHandler;
import API.Exceptions.NotFoundException;
import API.Models.Database_Entities.ServiceProviderEntity;
import API.Models.Database_Entities.ServiceProviderServiceProviderCompetencyEntity;
import API.Models.Database_Entities.ServiceProviderServiceProviderTypeEntity;
import API.Repository.ServiceProvider.*;
import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.ServiceProviderForUpdateDto;
import Shared.ToReturn.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ServiceProviderService implements IServiceProviderService {

    private ServiceProviderDAO serviceProviderDAO;

    private ModelMapper modelMapper;

    private EncryptionHandler encryptionHandler;

    private ServiceProviderTypeDAO serviceProviderTypeDAO;

    private ServiceProviderCompetencyDAO serviceProviderCompetencyDAO;

    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

    private ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO;

    @Autowired
    public void setServiceProviderCompetencyDAO(ServiceProviderCompetencyDAO serviceProviderCompetencyDAO) {
        this.serviceProviderCompetencyDAO = serviceProviderCompetencyDAO;
    }

    @Autowired
    public void setServiceProviderTypeDAO(ServiceProviderTypeDAO serviceProviderTypeDAO) {
        this.serviceProviderTypeDAO = serviceProviderTypeDAO;
    }

    @Autowired
    public void setEncryptionHandler(EncryptionHandler encryptionHandler) {
        this.encryptionHandler = encryptionHandler;
    }

    @Autowired
    public void setServiceProviderServiceProviderTypeDAO(ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO) {
        this.serviceProviderServiceProviderTypeDAO = serviceProviderServiceProviderTypeDAO;
    }

    @Autowired
    public void setServiceProviderServiceProviderCompetencyDAO(ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO) {
        this.serviceProviderServiceProviderCompetencyDAO = serviceProviderServiceProviderCompetencyDAO;
    }

    @Autowired
    public void setServiceProviderDAO(ServiceProviderDAO serviceProviderDAO) {
        this.serviceProviderDAO = serviceProviderDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ServiceProviderDto> listAllServiceProviders(boolean showDeleted) {
        Map<Integer, ServiceProviderTypeDto> helperTypeMap = new HashMap<>();
        Map<Integer, ServiceProviderCompetencyDto> helperCompetencyMap = new HashMap<>();
        List<ServiceProviderDto> foundList = serviceProviderDAO.listAllServiceProvider(showDeleted);
        for (ServiceProviderDto serviceProvider : foundList) {
            decryptCpr(serviceProvider);
            fillCompetencyListToReturn(serviceProvider, helperCompetencyMap);
            fillTypeListToReturn(serviceProvider, helperTypeMap);
        }
        helperCompetencyMap.clear();
        helperTypeMap.clear();
        return foundList;
    }

    @Override
    public ServiceProviderDto findServiceProvider(int id) {
        Map<Integer, ServiceProviderTypeDto> helperTypeMap = new HashMap<>();
        Map<Integer, ServiceProviderCompetencyDto> helperCompetencyMap = new HashMap<>();
        try {
            ServiceProviderDto found = serviceProviderDAO.findServiceProvider(id);
            decryptCpr(found);
            fillCompetencyListToReturn(found, helperCompetencyMap);
            fillTypeListToReturn(found, helperTypeMap);
            helperCompetencyMap.clear();
            helperTypeMap.clear();
            return found;
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Service provider not found");
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderDto addServiceProvider(ServiceProviderForCreationDto serviceProvider) {
        Map<Integer, ServiceProviderTypeDto> helperTypeMap = new HashMap<>();
        Map<Integer, ServiceProviderCompetencyDto> helperCompetencyMap = new HashMap<>();

        ServiceProviderDto added = serviceProviderDAO.addServiceProvider(modelMapper.map(serviceProvider, ServiceProviderEntity.class), serviceProvider.getCompetences(), serviceProvider.getTypes());

        decryptCpr(added);
        fillCompetencyListToReturn(added, helperCompetencyMap);
        fillTypeListToReturn(added, helperTypeMap);
        helperCompetencyMap.clear();
        helperTypeMap.clear();

        return added;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ServiceProviderDto updateServiceProvider(ServiceProviderForUpdateDto serviceProvider) {
        Map<Integer, ServiceProviderTypeDto> helperTypeMap = new HashMap<>();
        Map<Integer, ServiceProviderCompetencyDto> helperCompetencyMap = new HashMap<>();
        ServiceProviderDto updated = serviceProviderDAO.updateServiceProvider(modelMapper.map(serviceProvider, ServiceProviderEntity.class), serviceProvider.getCompetences(), serviceProvider.getTypes());
        decryptCpr(updated);
        fillCompetencyListToReturn(updated, helperCompetencyMap);
        fillTypeListToReturn(updated, helperTypeMap);
        return updated;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteServiceProvider(int id) {
        return serviceProviderDAO.deleteServiceProvider(id);
    }


    private void decryptCpr(ServiceProviderDto serviceProvider) {
        String decrypted = encryptionHandler.decrypt(serviceProvider.getCpr());
        serviceProvider.setCpr(decrypted);
    }

    private void fillTypeListToReturn(ServiceProviderDto serviceProvider, Map<Integer, ServiceProviderTypeDto> helperTypeMap) {
        serviceProvider.setTypes(new ArrayList<>());
        List<ServiceProviderServiceProviderTypeEntity> listOfTypesEntity = serviceProviderServiceProviderTypeDAO.findAllByServiceProviderId(serviceProvider.getId());
        List<ServiceProviderServiceProviderTypeDto> listOfTypes = modelMapper.map(listOfTypesEntity, new TypeToken<List<ServiceProviderServiceProviderTypeDto>>() {
        }.getType());
        for (ServiceProviderServiceProviderTypeDto type : listOfTypes) {
            if (helperTypeMap.get(type.getServiceProviderTypeId()) == null) {
                ServiceProviderTypeDto found = serviceProviderTypeDAO.findServiceProviderType(type.getServiceProviderTypeId());
                helperTypeMap.put(found.getId(), found);
                serviceProvider.getTypes().add(found);
            } else {
                serviceProvider.getTypes().add(helperTypeMap.get(type.getServiceProviderTypeId()));
            }
        }
    }

    private void fillCompetencyListToReturn(ServiceProviderDto serviceProvider, Map<Integer, ServiceProviderCompetencyDto> helperCompetencyMap) {
        serviceProvider.setCompetences(new ArrayList<>());
        List<ServiceProviderServiceProviderCompetencyEntity> listOfCompetencyEntity = serviceProviderServiceProviderCompetencyDAO.findAllByServiceProviderId(serviceProvider.getId());
        List<ServiceProviderServiceProviderCompetencyDto> listOfCompetency = modelMapper.map(listOfCompetencyEntity, new TypeToken<List<ServiceProviderServiceProviderCompetencyDto>>() {
        }.getType());
        for (ServiceProviderServiceProviderCompetencyDto competency : listOfCompetency) {
            if (helperCompetencyMap.get(competency.getCompetencyId()) == null) {
                ServiceProviderCompetencyDto found = serviceProviderCompetencyDAO.findServiceProviderCompetency(competency.getCompetencyId());
                helperCompetencyMap.put(competency.getCompetencyId(), found);
                serviceProvider.getCompetences().add(found);
            } else {
                serviceProvider.getCompetences().add(helperCompetencyMap.get(competency.getCompetencyId()));
            }
        }
    }
}
