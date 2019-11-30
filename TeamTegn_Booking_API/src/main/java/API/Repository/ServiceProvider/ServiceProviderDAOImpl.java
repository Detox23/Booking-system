package API.Repository.ServiceProvider;

import API.Configurations.Encryption.EncryptionHandler;
import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.ServiceProviderEntity;
import API.Database_Entities.ServiceProviderServiceProviderCompetencyEntity;
import API.Database_Entities.ServiceProviderServiceProviderTypeEntity;
import API.Exceptions.*;
import API.Repository.Department.DepartmentDAO;
import Shared.ToReturn.ServiceProviderDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Component
public class ServiceProviderDAOImpl implements ServiceProviderDAOCustom {

    private ServiceProviderDAO serviceProviderDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    private EncryptionHandler encryptionHandler;

    private DepartmentDAO departmentDAO;

    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

    private ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO;

    @Autowired
    public void setServiceProviderServiceProviderTypeDAO(ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO) {
        this.serviceProviderServiceProviderTypeDAO = serviceProviderServiceProviderTypeDAO;
    }

    @Autowired
    public void setServiceProviderServiceProviderCompetencyDAO(ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO) {
        this.serviceProviderServiceProviderCompetencyDAO = serviceProviderServiceProviderCompetencyDAO;
    }


    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setEncryptionHandler(EncryptionHandler encryptionHandler) {
        this.encryptionHandler = encryptionHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setServiceProviderDAO(ServiceProviderDAO serviceProviderDAO) {
        this.serviceProviderDAO = serviceProviderDAO;
    }

    @Override
    public List<ServiceProviderDto> listAllServiceProvider() {
        Type listType = new TypeToken<List<ServiceProviderDto>>() {
        }.getType();
        return modelMapper.map(serviceProviderDAO.findAllByDeletedIsFalse(), listType);
    }

    @Override
    public ServiceProviderDto findServiceProvider(int id) {
        try {
            Optional<ServiceProviderEntity> found = serviceProviderDAO.findById(id);
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("Service provider does not exist.");
            }
            return modelMapper.map(found.get(), ServiceProviderDto.class);
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotFoundException(noSuchElementException.getMessage());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceProviderDto addServiceProvider(ServiceProviderEntity serviceProvider, List<Integer> competencies,
                                                 List<Integer> types) {
        try {
            checkDuplicate(serviceProvider);

            //TODO sprawdzić czy można usunąć
            if (!departmentDAO.findById(serviceProvider.getDepartmentId()).isPresent()) {
                throw new NotFoundException("Department was not found");
            }
            encryptCpr(serviceProvider);
            ServiceProviderEntity saved = serviceProviderDAO.save(serviceProvider);
            addCompetenciesOfServiceProvider(competencies, saved.getId());
            addTypesOfServiceProvider(types, saved.getId());
            return modelMapper.map(saved, ServiceProviderDto.class);
        } catch (NoSuchElementException noSuchElementException) {
            throw new NotEnoughDataException("Department does not exist.");
        } catch (InvalidDataAccessApiUsageException invalidDataAccessApiUsageException) {
            throw new NotEnoughDataException("There are missing information to create a service provider");
        }
    }

    @Override
    public ServiceProviderDto updateServiceProvider(ServiceProviderEntity serviceProvider, List<Integer> competencies, List<Integer> types) {
        try {
            Optional<ServiceProviderEntity> found = serviceProviderDAO.findById(serviceProvider.getId());
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException(String.format("Service provider with id %d was not found", serviceProvider.getId()));
            }
            if (serviceProvider.getCpr() != null) {
                encryptCpr(serviceProvider);
            }
            patcherHandler.fillNotNullFields(found.get(), serviceProvider);
            addCompetenciesOfServiceProvider(competencies, found.get().getId());
            addTypesOfServiceProvider(types, found.get().getId());
            if (!departmentDAO.findById(serviceProvider.getDepartmentId()).isPresent()) {
                throw new NotFoundException(String.format("Department with %d was not found.", serviceProvider.getDepartmentId()));
            }
            ServiceProviderEntity updated = serviceProviderDAO.save(found.get());
            return modelMapper.map(updated, ServiceProviderDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating a service provider [PATCHING].");
        }
    }

    @Override
    public boolean deleteServiceProvider(int id) {
        try {
            Optional<ServiceProviderEntity> found = serviceProviderDAO.findById(id);
            if (!found.isPresent()) {
                throw new NotFoundException("The service provider was not found.");
            }
            ServiceProviderEntity toDelete = found.get();
            toDelete.setDeleted(true);
            ServiceProviderEntity deletionResult = serviceProviderDAO.save(toDelete);
            if (deletionResult.isDeleted()) {
                return true;
            } else {
                return false;
            }
        } catch (NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }

    private void addTypesOfServiceProvider(List<Integer> types, int id) {
        try {
            if (types != null) {
                serviceProviderServiceProviderTypeDAO.deleteAllByServiceProviderId(id);
                for (Integer type : types) {
                    ServiceProviderServiceProviderTypeEntity spType = new ServiceProviderServiceProviderTypeEntity();
                    spType.setServiceProviderTypeId(type);
                    spType.setServiceProviderId(id);
                    ServiceProviderServiceProviderTypeEntity resultOfAdding = serviceProviderServiceProviderTypeDAO.save(spType);
                    if (resultOfAdding == null) {
                        throw new UnknownAddingException("Provided type does not exists.");
                    }

                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void addCompetenciesOfServiceProvider(List<Integer> competencies, int id) {
        try {
            if (competencies != null) {
                serviceProviderServiceProviderCompetencyDAO.deleteAllByServiceProviderId(id);
                for (Integer competency : competencies) {
                    ServiceProviderServiceProviderCompetencyEntity spCompetency = new ServiceProviderServiceProviderCompetencyEntity();
                    spCompetency.setCompetencyId(competency);
                    spCompetency.setServiceProviderId(id);
                    ServiceProviderServiceProviderCompetencyEntity resultOfAdding = serviceProviderServiceProviderCompetencyDAO.save(spCompetency);
                    if (resultOfAdding == null) {
                        throw new UnknownAddingException("Provided competency does not exists.");
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void encryptCpr(ServiceProviderEntity serviceProvider) {
        String cpr = serviceProvider.getCpr();
        String hashed = encryptionHandler.encrypt(cpr);
        serviceProvider.setCpr(hashed);
    }

    private boolean checkDuplicate(ServiceProviderEntity serviceProvider) {
        List<ServiceProviderEntity> checkDuplicate = serviceProviderDAO.findAllByFirstNameAndMiddleNameAndLastName(
                serviceProvider.getFirstName(),
                serviceProvider.getMiddleName(),
                serviceProvider.getLastName()
        );
        if (checkDuplicate.size() > 0) {
            throw new DuplicateException(String.format(
                    "The service provider with name: %s, middle name: $s and last name: %s already exists.",
                    serviceProvider.getFirstName(),
                    serviceProvider.getMiddleName(),
                    serviceProvider.getLastName()
            ));
        }
        return true;
    }

}
