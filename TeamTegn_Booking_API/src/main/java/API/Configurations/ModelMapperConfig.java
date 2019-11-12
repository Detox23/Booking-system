package API.Configurations;

import API.Database_Entities.AccountEntity;
import API.Database_Entities.AssignmentEntity;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.AssignmentForCreationDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        PropertyMap<AccountForCreationDto, AccountEntity> addingAccountMap = new PropertyMap<AccountForCreationDto, AccountEntity>(){
            protected void configure(){
                skip().setId(0);
                skip().setEan(null);
            }
        };
        PropertyMap<AssignmentForCreationDto, AssignmentEntity> adddingAssignment = new PropertyMap<AssignmentForCreationDto, AssignmentEntity>(){
            protected void configure(){
                skip().setId(0);
            }
        };


        mm.addMappings(addingAccountMap);
        mm.addMappings(adddingAssignment);
        return mm;
    }
}
