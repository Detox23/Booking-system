package API.Repository.ServiceProvider;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.ServiceProviderCompetencyEntity;
import API.Exceptions.*;
import Shared.ForCreation.ServiceProviderServiceProviderCompetencyForCreationDto;
import Shared.ToReturn.ServiceProviderCompetencyDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ServiceProviderCompetencyDAOImpl implements ServiceProviderCompetencyCustom {

    private ServiceProviderCompetencyDAO serviceProviderCompetencyDAO;

    private ModelMapper modelMapper;

    private PatcherHandler patcherHandler;

    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

    @Autowired
    public void setServiceProviderServiceProviderCompetencyDAO(ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO) {
        this.serviceProviderServiceProviderCompetencyDAO = serviceProviderServiceProviderCompetencyDAO;
    }


    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderCompetencyDAO(ServiceProviderCompetencyDAO serviceProviderCompetencyDAO) {
        this.serviceProviderCompetencyDAO = serviceProviderCompetencyDAO;
    }

    @Override
    public List<ServiceProviderCompetencyDto> listAllCompetencies() {
        try {
            Type listType = new TypeToken<List<ServiceProviderCompetencyDto>>() {
            }.getType();
            return modelMapper.map(serviceProviderCompetencyDAO.findAll(), listType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error.");
        }
    }

    @Override
    public ServiceProviderCompetencyDto getOneCompetency(int id) {
        try {
            ServiceProviderCompetencyEntity found = serviceProviderCompetencyDAO.findById(id).get();
            return modelMapper.map(found, ServiceProviderCompetencyDto.class);
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException("Competency was not found.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error.");
        }
    }

    @Override
    public ServiceProviderCompetencyDto addOneCompetency(ServiceProviderCompetencyEntity serviceProviderCompetency) {
        if (serviceProviderCompetencyDAO.findAllByCompetencyIs(serviceProviderCompetency.getCompetency()).size() > 0) {
            throw new DuplicateException("Competency with exact name already exists.");
        }
        if (serviceProviderCompetency.getCompetency() == null || serviceProviderCompetency.getCompetency().length() == 0) {
            throw new NotEnoughDataException("You provided to little information to create the competency.");
        }
        ServiceProviderCompetencyEntity saved = serviceProviderCompetencyDAO.save(serviceProviderCompetency);
        if (saved.getId() > 0) {
            return modelMapper.map(saved, ServiceProviderCompetencyDto.class);
        } else {
            throw new UnknownAddingException("There was a problem with adding");
        }
    }

    @Override
    public ServiceProviderCompetencyDto updateOneCompetency(ServiceProviderCompetencyEntity serviceProviderCompetency) {
        try {
            ServiceProviderCompetencyEntity found = serviceProviderCompetencyDAO.findById(serviceProviderCompetency.getId()).get();
            patcherHandler.fillNotNullFields(found, serviceProviderCompetency);
            ServiceProviderCompetencyEntity updated = serviceProviderCompetencyDAO.save(found);
            return modelMapper.map(updated, ServiceProviderCompetencyDto.class);
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException("Competency was not found while an attempt of making update.");
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating a competency [PATCHING].");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }

    @Override
    public boolean deleteOneCompetency(int id) {
        try {
            serviceProviderServiceProviderCompetencyDAO.deleteAllByCompetencyId(id);
            serviceProviderCompetencyDAO.deleteById(id);
            try {
                serviceProviderCompetencyDAO.findById(id).get();
                return false;
            } catch (NoSuchElementException noSuchElementExceptionAssure) {
                return true;
            }
        } catch (NotFoundException emptyResult) {
            throw new NotFoundException("Competency you wanted to delete does not exist.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }



}
