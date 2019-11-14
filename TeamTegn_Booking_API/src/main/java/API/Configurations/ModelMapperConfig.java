package API.Configurations;

import API.Database_Entities.AccountEntity;
import API.Database_Entities.ServiceProviderEntity;
import API.Database_Entities.AssignmentEntity;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.AssignmentForCreationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        PropertyMap<AccountForCreationDto, AccountEntity> addingAccountMap = new PropertyMap<AccountForCreationDto, AccountEntity>(){
            protected void configure(){
                skip().setId(0);
                skip().setEan(null);
            }
        };

        PropertyMap<ServiceProviderForCreationDto, ServiceProviderEntity> addingServiceProviderMap = new PropertyMap<ServiceProviderForCreationDto, ServiceProviderEntity>(){
            protected void configure(){
                skip().setId(0);
            }
        };

        PropertyMap<AssignmentForCreationDto, AssignmentEntity> adddingAssignment = new PropertyMap<AssignmentForCreationDto, AssignmentEntity>(){
            protected void configure(){
                skip().setId(0);
            }
        };

        mapper.addMappings(addingServiceProviderMap);
        mapper.addMappings(addingAccountMap);
        mapper.addMappings(adddingAssignment);
        return mapper;
    }
}
