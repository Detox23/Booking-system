package API.Repository.ServiceUser;

import API.Configurations.Encryption.EncryptionHandler;
import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UnknownAddingException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.CityPostcodesEntity;
import API.Models.Database_Entities.ServiceUserAccountEntity;
import API.Models.Database_Entities.ServiceUserEntity;
import API.Models.Database_Entities.WiPostcodeEntity;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
import Shared.ToReturn.ServiceUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceUserDAOImpl implements ServiceUserDAOCustom {

    private final int update = 1;
    private final int insert = 0;

    private ServiceUserAccountsDAO serviceUserAccountsDAO;

    private ServiceUserDAO serviceUserDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    private EncryptionHandler encryptionHandler;

    private CityPostcodesDAO cityPostcodesDAO;

    private WI_PostcodeDAO wiPostcodeDAO;

    @Autowired
    public void setWiPostcodeDAO(WI_PostcodeDAO wiPostcodeDAO) {
        this.wiPostcodeDAO = wiPostcodeDAO;
    }

    @Autowired
    public void setCityPostcodesDAO(CityPostcodesDAO cityPostcodesDAO) {
        this.cityPostcodesDAO = cityPostcodesDAO;
    }

    @Autowired
    public void setEncryptionHandler(EncryptionHandler encryptionHandler) {
        this.encryptionHandler = encryptionHandler;
    }

    @Autowired
    public void setServiceUserAccountsDAO(ServiceUserAccountsDAO serviceUserAccountsDAO) {
        this.serviceUserAccountsDAO = serviceUserAccountsDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setServiceUserDAO(ServiceUserDAO serviceUserDAO) {
        this.serviceUserDAO = serviceUserDAO;
    }


    @Override
    public ServiceUserDto addServiceUser(ServiceUserEntity serviceUser, List<Integer> accounts) {
        try {
            checkIfExistsByName(serviceUser, insert);
            checkAndFillPostcodeAndCity(serviceUser);
            addStateRegion(serviceUser);
            encryptCpr(serviceUser);
            ServiceUserEntity saved = serviceUserDAO.save(serviceUser);
            addServiceUsersAccounts(accounts, saved.getId());
            return modelMapper.map(saved, ServiceUserDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteServiceUser(int id) {
        try {
            ServiceUserEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            ServiceUserEntity deletionResult = serviceUserDAO.save(found);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Page<ServiceUserDto> listServiceUsers(Pageable pageable) {
        try {
            Page<ServiceUserDto> pageToReturn = serviceUserDAO.findAllByDeletedFalse(pageable).map(x -> modelMapper.map(x, ServiceUserDto.class));
            return pageToReturn;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceUserDto findServiceUser(int id) {
        try {
            ServiceUserEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, ServiceUserDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceUserDto updateServiceUser(ServiceUserEntity serviceUser, List<Integer> accounts) {
        try {
            encryptCpr(serviceUser);
            checkAndFillPostcodeAndCity(serviceUser);
            addStateRegion(serviceUser);
            ServiceUserEntity found = findIfExistsAndReturn(serviceUser.getId());
            patcherHandler.fillNotNullFields(found, serviceUser);
            checkIfExistsByName(found, update);
            addServiceUsersAccounts(accounts, serviceUser.getId());
            ServiceUserEntity result = serviceUserDAO.save(found);
            return modelMapper.map(result, ServiceUserDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an assignment title. [PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }


    private ServiceUserEntity findIfExistsAndReturn(int id) {
        Optional<ServiceUserEntity> found = serviceUserDAO.findByIdIsAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Service user with id: %d was not found.", id));
        }
        return found.get();
    }

    private void addServiceUsersAccounts(List<Integer> accounts, int id) {
        try {
            if (accounts != null) {
                serviceUserAccountsDAO.deleteAllByServiceUserIdIs(id);
                for (Integer account : accounts) {
                    ServiceUserAccountEntity serviceUserAccount = new ServiceUserAccountEntity();
                    serviceUserAccount.setAccountId(account);
                    serviceUserAccount.setServiceUserId(id);
                    ServiceUserAccountEntity saved = serviceUserAccountsDAO.save(serviceUserAccount);
                    if (saved == null) {
                        throw new UnknownAddingException(String.format("There was a problem with adding service user."));
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void checkIfExistsByName(ServiceUserEntity serviceUser, int allowedFound) {
        int count = serviceUserDAO.countAllByFirstNameIsAndMiddleNameIsAndLastNameIs(serviceUser.getFirstName(), serviceUser.getMiddleName(), serviceUser.getLastName());
        if (count > allowedFound) {
            if (serviceUser.getFirstName() == null) {
                serviceUser.setFirstName("");
            } else if (serviceUser.getMiddleName() == null) {
                serviceUser.setMiddleName("");
            } else if (serviceUser.getLastName() == null) {
                serviceUser.setLastName("");
            }
            throw new DuplicateException(String.format("The is already service user with name: %s %s %s.", serviceUser.getFirstName(),
                    serviceUser.getMiddleName(), serviceUser.getLastName()));
        }
    }

    private void checkAndFillPostcodeAndCity(ServiceUserEntity serviceUser) {

        if (serviceUser.getCity() == null) {
            Optional<CityPostcodesEntity> found = cityPostcodesDAO.findFirstByPostcodeIs(serviceUser.getPostcode());
            if (found.isPresent()) {
                serviceUser.setCity(found.get().getCity());
            }
        }
    }

    private void addStateRegion(ServiceUserEntity serviceUser) {
        if (serviceUser.getStateRegion() == null) {
            Optional<WiPostcodeEntity> wiPostcode = wiPostcodeDAO.findByPostcodeIs(serviceUser.getPostcode());
            if (wiPostcode.isPresent()) {
                if (wiPostcode.get().getArhus()) {
                    serviceUser.setStateRegion("Aarhus");
                } else if (wiPostcode.get().getCopenhagen()) {
                    serviceUser.setStateRegion("Copenhagen");
                } else if (wiPostcode.get().getFredericia()) {
                    serviceUser.setStateRegion("Fredericia");
                }
            }
        }
    }


    private void encryptCpr(ServiceUserEntity serviceProvider) {
        String cpr = serviceProvider.getCpr();
        String hashed = encryptionHandler.encrypt(cpr);
        serviceProvider.setCpr(hashed);
    }

}

