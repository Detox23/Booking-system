package API.Services.ServiceUserService;

import API.Database_Entities.AccountEntity;
import API.Database_Entities.ServiceUserAccountEntity;
import API.Database_Entities.ServiceUserEntity;
import API.Repository.Account.AccountDAO;
import API.Repository.ServiceUser.ServiceUserAccountsDAO;
import API.Repository.ServiceUser.ServiceUserDAO;
import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.ServiceUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceUserService implements IServiceUserService {
    private ServiceUserDAO serviceUserDAO;
    private AccountDAO accountDAO;
    private ServiceUserAccountsDAO serviceUserAccountsDAO;
    private ModelMapper mapper;


    @Autowired
    public void setServiceUserDAO(ServiceUserDAO serviceUserDAO) {
        this.serviceUserDAO = serviceUserDAO;
    }

    @Autowired
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Autowired
    public void setServiceUserAccountsDAO(ServiceUserAccountsDAO serviceUserAccountsDAO) {
        this.serviceUserAccountsDAO = serviceUserAccountsDAO;
    }

    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public ServiceUserDto addServiceUser(ServiceUserForCreationDto userForCreationDto) {

        ServiceUserEntity entity = mapper.map(userForCreationDto, ServiceUserEntity.class);
        ServiceUserEntity result = serviceUserDAO.add(entity);
        if (userForCreationDto.getAccountsIds() != null) {
            userForCreationDto.getAccountsIds()
                    .forEach((id) -> serviceUserAccountsDAO.save(new ServiceUserAccountEntity(result.getId(), id)));

        }
        return mapper.map(result, ServiceUserDto.class);
    }

    @Override
    public ServiceUserDto findServiceUser(int id) {
        ServiceUserDto dto = mapper.map(serviceUserDAO.findByID(id), ServiceUserDto.class);
        List<AccountDto> accountDtos = new ArrayList<>();
        List<ServiceUserAccountEntity> accounts = serviceUserAccountsDAO.findAllByServiceUserId(id);

        if (accounts != null) {
            accounts.forEach((accountServiceUser) ->
            {
                Optional<AccountEntity> account = accountDAO.findById(accountServiceUser.getAccountId());
                if (account.isPresent()) {
                    accountDtos.add(mapper.map(account.get(), AccountDto.class));
                }
            });
        }
        dto.setAccounts(accountDtos);
        return dto;
    }

    @Override
    public Page<ServiceUserDto> listServiceUsers(Pageable pageable) {
        Page<ServiceUserEntity> elements = serviceUserDAO.list(pageable);

        Page<ServiceUserDto> dtos = elements.map(x -> mapper.map(x, ServiceUserDto.class));
        dtos.toList().forEach((u) -> u.setAccounts(showServiceUserAccounts(u)));

        return dtos;

    }

    private List<AccountDto> showServiceUserAccounts(ServiceUserDto user) {
        List<ServiceUserAccountEntity> accounts = serviceUserAccountsDAO.findAllByServiceUserId(user.getId());
        List<AccountDto> accountDtos = new ArrayList<>();
        if (accounts != null) {
            accounts.forEach((accountServiceUser) ->
            {
                Optional<AccountEntity> account = accountDAO.findById(accountServiceUser.getAccountId());
                if (account.isPresent()) {
                    accountDtos.add(mapper.map(account.get(), AccountDto.class));
                }
            });
        }
        return accountDtos;
    }

    @Override
    public boolean deleteServiceUser(int id) {
        return serviceUserDAO.deleteById(id);
    }


    @Override
    public ServiceUserDto updateServiceUser(int id, ServiceUserForUpdateDto forUpdateDto) {
        ServiceUserEntity entity = mapper.map(forUpdateDto, ServiceUserEntity.class);
        entity.setId(id);
        ServiceUserEntity result = serviceUserDAO.update(entity);
        return mapper.map(result, ServiceUserDto.class);
    }
}
