package API.Configurations;

import API.Database_Entities.AccountEntity;
import Shared.ForCreation.AccountForCreationDto;
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
        mm.addMappings(addingAccountMap);
        return mm;
    }
}
