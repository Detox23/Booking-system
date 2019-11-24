package API.Repository.ServiceProvider;

import API.Configurations.Encryption.EncryptionHandler;
import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.DepartmentEntity;
import API.Database_Entities.ServiceProviderEntity;
import API.Database_Entities.ServiceProviderServiceProviderCompetencyEntity;
import API.Database_Entities.ServiceProviderServiceProviderTypeEntity;
import API.Exceptions.*;
import API.Repository.Department.DepartmentDAO;
import Shared.ForCreation.ServiceProviderServiceProviderCompetencyForCreationDto;
import Shared.ForCreation.ServiceProviderServiceProviderTypeForCreationDto;
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
    public List<ServiceProviderDto> listAll() {
        Type listType = new TypeToken<List<ServiceProviderDto>>() {
        }.getType();
        return modelMapper.map(serviceProviderDAO.findAll(), listType);
    }

    @Override
    public ServiceProviderDto findOne(int id) {
        ServiceProviderEntity found = serviceProviderDAO.findById(id).get();
        return modelMapper.map(found, ServiceProviderDto.class);
    }

    @Override
    public ServiceProviderDto addServiceProvider(ServiceProviderEntity serviceProvider, List<Integer> competencies,
                                                 List<Integer> types) {
        try {
            List<ServiceProviderEntity> checkDuplicate =
                    serviceProviderDAO.findAllByFirstNameAndMiddleNameAndLastName(
                            serviceProvider.getFirstName(),
                            serviceProvider.getMiddleName(),
                            serviceProvider.getLastName()
                    );
            if (checkDuplicate.size() > 0){
                throw new DuplicateException(
                        "The service provider with the exact first name, middle name and last name already exists."
                );
            }
            DepartmentEntity result = departmentDAO.findById(serviceProvider.getDepartmentId()).get();
            serviceProvider.setCpr(encryptionHandler.encrypt(serviceProvider.getCpr()));
            ServiceProviderEntity saved = serviceProviderDAO.save(serviceProvider);
            saved.setCpr(encryptionHandler.decrypt(saved.getCpr()));
            if(competencies != null){
                addCompetenciesOfServiceProvider(competencies, saved.getId());
            }
            if(types != null){
                addTypesOfServiceProvider(types, saved.getId());
            }
            return modelMapper.map(saved, ServiceProviderDto.class);
        }catch(NoSuchElementException noSuchElementException){
            throw new NotEnoughDataException("Department does not exist.");
        }catch(InvalidDataAccessApiUsageException invalidDataAccessApiUsageException){
            throw new NotEnoughDataException("There are missing information to create a service provider");
        }
    }

    private void addTypesOfServiceProvider(List<Integer> types, int id){
        if (types.size() > 0) {
            for(Integer type: types){
                ServiceProviderServiceProviderTypeForCreationDto serviceProviderSPType = new ServiceProviderServiceProviderTypeForCreationDto();
                serviceProviderSPType.setServiceProviderTypeId(type);
                serviceProviderSPType.setServiceProviderId(id);
                boolean resultOfAdding = serviceProviderServiceProviderTypeDAO.addServiceProviderServiceProviderType(
                        modelMapper.map(
                                serviceProviderSPType,
                                ServiceProviderServiceProviderTypeEntity.class
                        )
                );
                if (!resultOfAdding){
                    throw new UnknownAddingException("Provided type does not exists.");
                }

            }
        }
    }

    private void addCompetenciesOfServiceProvider(List<Integer> competencies, int id) {
        if (competencies.size() > 0) {
            for (Integer competency : competencies) {
                ServiceProviderServiceProviderCompetencyForCreationDto serviceProviderSPCompetency = new ServiceProviderServiceProviderCompetencyForCreationDto();
                serviceProviderSPCompetency.setCompetencyId(competency);
                serviceProviderSPCompetency.setServiceProviderId(id);
                boolean resultOfAdding = serviceProviderServiceProviderCompetencyDAO.addServiceProviderServiceProviderCompetency(
                        modelMapper.map(
                                serviceProviderSPCompetency,
                                ServiceProviderServiceProviderCompetencyEntity.class
                        )
                );
                if (!resultOfAdding) {
                    throw new UnknownAddingException("Provided competency does not exists.");
                }
            }
        }
    }

    @Override
    public ServiceProviderDto updateServiceProvider(ServiceProviderEntity serviceProvider, List<Integer> competencies,
                                                    List<Integer> types) {
        try {
            serviceProviderServiceProviderCompetencyDAO.deleteAllByServiceProviderId(serviceProvider.getId());
            serviceProviderServiceProviderTypeDAO.deleteAllByServiceProviderId(serviceProvider.getId());
            ServiceProviderEntity found = serviceProviderDAO.findById(serviceProvider.getId()).get();
            if (serviceProvider.getCpr() != null) {
                serviceProvider.setCpr(encryptionHandler.encrypt(serviceProvider.getCpr()));
            }
            patcherHandler.fillNotNullFields(found, serviceProvider);
            if(competencies != null){
                addCompetenciesOfServiceProvider(competencies, found.getId());
            }
            if(types != null){
                addTypesOfServiceProvider(types, found.getId());
            }
            ServiceProviderEntity updated = serviceProviderDAO.save(found);
            if (updated.getCpr() != null) {
                updated.setCpr(encryptionHandler.decrypt(updated.getCpr()));
            }
            return modelMapper.map(updated, ServiceProviderDto.class);
        } catch (IntrospectionException interspectionException) {
            throw new UpdatePatchException("There was an error while updating a service provider [PATCHING].");
        }
    }

    @Override
    public boolean deleteServiceProvider(int id) {
        try {
            ServiceProviderEntity found = serviceProviderDAO.findById(id).get();
            found.setDeleted(true);
            ServiceProviderEntity deleted = serviceProviderDAO.save(found);
            return deleted.isDeleted();
        }catch(Exception e){
            e.printStackTrace();
            throw new DeletionException("Unknown error while deleting");
        }
    }


}
