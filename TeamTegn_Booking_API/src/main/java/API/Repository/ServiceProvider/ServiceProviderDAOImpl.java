package API.Repository.ServiceProvider;

import API.Configurations.Encryption.EncryptionHandler;
import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.*;
import API.Models.Database_Entities.*;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
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

    private ServiceProvider_ServiceProviderCompetencyDAO serviceProviderServiceProviderCompetencyDAO;

    private ServiceProvider_ServiceProviderTypeDAO serviceProviderServiceProviderTypeDAO;

    private WI_PostcodeDAO wiPostcodeDAO;

    private CityPostcodesDAO cityPostcodesDAO;

    @Autowired
    public void setWiPostcodeDAO(WI_PostcodeDAO wiPostcodeDAO) {
        this.wiPostcodeDAO = wiPostcodeDAO;
    }

    @Autowired
    public void setCityPostcodesDAO(CityPostcodesDAO cityPostcodesDAO) {
        this.cityPostcodesDAO = cityPostcodesDAO;
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
    public List<ServiceProviderDto> listAllServiceProvider(boolean showDeleted) {
        if (showDeleted) {
            try {
                Type listType = new TypeToken<List<ServiceProviderDto>>() {
                }.getType();
                return modelMapper.map(serviceProviderDAO.findAll(), listType);

            } catch (Exception e) {
                throw e;
            }
        } else {
            try {
                Type listType = new TypeToken<List<ServiceProviderDto>>() {
                }.getType();
                return modelMapper.map(serviceProviderDAO.findAllByDeletedIsFalse(), listType);

            } catch (Exception e) {
                throw e;
            }
        }

    }

    @Override
    public ServiceProviderDto findServiceProvider(int id) {
        try {
            ServiceProviderEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, ServiceProviderDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceProviderDto addServiceProvider(ServiceProviderEntity serviceProvider, List<Integer> competencies, List<Integer> types) {
        try {
            checkDuplicate(serviceProvider);
            checkAndFillPostcodeAndCity(serviceProvider);
            addStateRegion(serviceProvider);
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
            encryptCpr(serviceProvider);
            checkAndFillPostcodeAndCity(serviceProvider);
            addStateRegion(serviceProvider);
            ServiceProviderEntity found = findIfExistsAndReturn(serviceProvider.getId());
            patcherHandler.fillNotNullFields(found, serviceProvider);
            checkDuplicate(found);
            addCompetenciesOfServiceProvider(competencies, found.getId());
            addTypesOfServiceProvider(types, found.getId());
            ServiceProviderEntity updated = serviceProviderDAO.save(found);
            return modelMapper.map(updated, ServiceProviderDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating a service provider [PATCHING].");
        }
    }

    @Override
    public boolean deleteServiceProvider(int id) {
        try {
            ServiceProviderEntity toDelete = findIfExistsAndReturn(id);
            toDelete.setDeleted(true);
            ServiceProviderEntity deletionResult = serviceProviderDAO.save(toDelete);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
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

    private void checkDuplicate(ServiceProviderEntity serviceProvider) {
        if (serviceProvider.getId() == 0) {
            if (serviceProviderDAO.countAllByFirstNameIsAndMiddleNameIsAndLastNameIs(serviceProvider.getFirstName(), serviceProvider.getMiddleName(), serviceProvider.getLastName()) > 0) {
                throw new DuplicateException(String.format(
                        "The service provider with name: %s, middle name: %s and last name: %s already exists.",
                        serviceProvider.getFirstName(),
                        serviceProvider.getMiddleName(),
                        serviceProvider.getLastName()
                ));
            }
        } else {
            if (serviceProviderDAO.countAllByFirstNameIsAndMiddleNameIsAndLastNameIsAndIdIsNot(serviceProvider.getFirstName(), serviceProvider.getMiddleName(), serviceProvider.getLastName(), serviceProvider.getId()) > 0) {
                throw new DuplicateException(String.format(
                        "The service provider with name: %s, middle name: %s and last name: %s already exists.",
                        serviceProvider.getFirstName(),
                        serviceProvider.getMiddleName(),
                        serviceProvider.getLastName()
                ));
            }
        }
    }

    private ServiceProviderEntity findIfExistsAndReturn(int id) {
        Optional<ServiceProviderEntity> found = serviceProviderDAO.findByIdIsAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Service provider with id: %d was not found.", id));
        }
        return found.get();
    }

    private void checkAndFillPostcodeAndCity(ServiceProviderEntity serviceProviderEntity) {
        if (serviceProviderEntity.getCity() == null && serviceProviderEntity.getPostcode() != null) {
            Optional<CityPostcodesEntity> found = cityPostcodesDAO.findFirstByPostcodeIs(serviceProviderEntity.getPostcode());
            if (found.isPresent()) {
                serviceProviderEntity.setCity(found.get().getCity());
            }
        }
    }

    private void addStateRegion(ServiceProviderEntity serviceProviderEntity) {
        if (serviceProviderEntity.getStateRegion() == null && serviceProviderEntity.getPostcode() != null) {
            Optional<WiPostcodeEntity> wiPostcode = wiPostcodeDAO.findByPostcodeIs(serviceProviderEntity.getPostcode());
            if (wiPostcode.isPresent()) {
                if (wiPostcode.get().getArhus()) {
                    serviceProviderEntity.setStateRegion("Aarhus");
                } else if (wiPostcode.get().getCopenhagen()) {
                    serviceProviderEntity.setStateRegion("Copenhagen");
                } else if (wiPostcode.get().getFredericia()) {
                    serviceProviderEntity.setStateRegion("Fredericia");
                }
            }
        }
    }
}
