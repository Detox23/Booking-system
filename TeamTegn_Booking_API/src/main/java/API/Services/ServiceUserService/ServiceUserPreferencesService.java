package API.Services.ServiceUserService;

import API.Models.Database_Entities.ServiceUserPreferencesEntity;
import API.Repository.ServiceUser.ServiceUserPreferencesDAO;
import API.Services.ServiceProviderService.ServiceProviderService;
import Shared.ForCreation.ServiceUserPreferencesForCreationDto;
import Shared.ForCreation.ServiceUserPreferenesForUpdateDto;
import Shared.ToReturn.ServiceUserCommentDto;
import Shared.ToReturn.ServiceUserPreferencesDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
@Service
public class ServiceUserPreferencesService implements IServiceUserPreferenecesService {

    private ServiceUserPreferencesDAO userPreferencesDAO;
    private ModelMapper mapper;
    private ServiceProviderService providerService;

    @Autowired
    public void setUserPreferencesDAO(ServiceUserPreferencesDAO userPreferencesDAO) {
        this.userPreferencesDAO = userPreferencesDAO;
    }
    @Autowired
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    @Autowired
    public void setProviderService(ServiceProviderService providerService) {
        this.providerService = providerService;
    }

    @Override
    public ServiceUserPreferencesDto add(ServiceUserPreferencesForCreationDto preferencesForCreationDto) {
        ServiceUserPreferencesEntity entity = mapper.map(preferencesForCreationDto, ServiceUserPreferencesEntity.class );
        ServiceUserPreferencesEntity added =userPreferencesDAO.add(entity);
        ServiceUserPreferencesDto mapped = mapper.map(added, ServiceUserPreferencesDto.class);
        return mapped;
    }

    @Override
    public ServiceUserPreferencesDto update(int id, ServiceUserPreferenesForUpdateDto pereferencesForUpdate) {
        ServiceUserPreferencesEntity entity = mapper.map(pereferencesForUpdate, ServiceUserPreferencesEntity.class );
        entity.setId(id);
        ServiceUserPreferencesEntity added =userPreferencesDAO.update(entity);
        ServiceUserPreferencesDto mapped = mapper.map(added, ServiceUserPreferencesDto.class);
        return mapped;
    }

    @Override
    public ServiceUserPreferencesDto findByServiceProviderAndUser(int serviceProvider, int serviceUser) {
        ServiceUserPreferencesEntity entity =userPreferencesDAO.findByServiceProviderAndUser(serviceProvider, serviceUser);
        ServiceUserPreferencesDto mapped = mapper.map(entity, ServiceUserPreferencesDto.class);
        return mapped;
    }

    @Override
    public boolean delete(int id) {
        return userPreferencesDAO.deleteById(id);
    }

    @Override
    public List<ServiceUserPreferencesDto> findAllByServiceUser(int serviceUserId)
    {
        Type listType = new TypeToken<List<ServiceUserCommentDto>>() {}.getType();

        List<ServiceUserPreferencesEntity> foundList = userPreferencesDAO.findAllByServiceUser(serviceUserId);
        List<ServiceUserPreferencesDto> foundListDtos = mapper.map(foundList, listType);
        for(ServiceUserPreferencesDto preferencesDto: foundListDtos){
                preferencesDto.setServiceProvider(providerService.findServiceProvider(preferencesDto.getServiceProviderId()));
        }
        return foundListDtos;
    }
}

