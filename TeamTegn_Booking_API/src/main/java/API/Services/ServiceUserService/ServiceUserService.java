package API.Services.ServiceUserService;

import API.Database_Entities.ServiceUserAccountEntity;
import API.Database_Entities.ServiceUserEntity;
import API.Repository.Account.AccountDAO;
import API.Repository.ServiceUser.ServiceUserAccountsDAO;
import API.Repository.ServiceUser.ServiceUserDAO;
import API.Database_Entities.ServiceUserEntity;
import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import Shared.ToReturn.AccountDto;
import Shared.ToReturn.ServiceUserDto;
import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        if(userForCreationDto.getAccountsIds() != null)
        {
            userForCreationDto.getAccountsIds()
                    .forEach((id)->   serviceUserAccountsDAO.save(new ServiceUserAccountEntity(result.getId(), id)));

        }
        return mapper.map(result, ServiceUserDto.class);
    }

    @Override
    public ServiceUserDto findServiceUser(int id) {
        return mapper.map(serviceUserDAO.findByID(id), ServiceUserDto.class);

    }

    @Override
    public List<ServiceUserDto> listServiceUsers() {
        List<ServiceUserEntity> elements = Lists.newArrayList(serviceUserDAO.list());
        List<ServiceUserDto> dtos =  mapper.map(elements, new TypeToken<List<ServiceUserDto>>() {
        }.getType());
        dtos.forEach((u)-> u.setAccounts(showServiceUserAccounts(u)) );

        return dtos;

    }
    private List<AccountDto> showServiceUserAccounts(ServiceUserDto user)
    {
            List<AccountDto> accountsDtos = new ArrayList<>();
            List<ServiceUserAccountEntity> useraccounts = serviceUserAccountsDAO.findAllByServiceUserId(user.getId());
            useraccounts.forEach((accountServiceUser)->accountsDtos.add(mapper.map(accountDAO.findById(accountServiceUser.getAccountId()), AccountDto.class)));
    return  accountsDtos;
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
