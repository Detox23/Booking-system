package API.Configurations;

import API.Models.Database_Entities.*;
import Shared.ForCreation.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        PropertyMap<AccountForCreationDto, AccountEntity> addingAccountMap = new PropertyMap<AccountForCreationDto, AccountEntity>() {
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<ServiceProviderForCreationDto, ServiceProviderEntity> addingServiceProviderMap = new PropertyMap<ServiceProviderForCreationDto, ServiceProviderEntity>() {
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<AssignmentForCreationDto, AssignmentEntity> addingAssignmentMap = new PropertyMap<AssignmentForCreationDto, AssignmentEntity>() {
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<ServiceProviderAbsenceForCreationDto, ServiceProviderAbsenceEntity> addingServiceProviderAbsenceMap = new PropertyMap<ServiceProviderAbsenceForCreationDto, ServiceProviderAbsenceEntity>() {
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<ServiceProviderEveningWorkForCreationDto, ServiceProviderEveningWorkEntity> addingEveningWorkMap = new PropertyMap<ServiceProviderEveningWorkForCreationDto, ServiceProviderEveningWorkEntity>() {
            protected void configure() {
                skip().setId(0);
            }
        };

        mapper.addMappings(addingEveningWorkMap);
        mapper.addMappings(addingServiceProviderAbsenceMap);
        mapper.addMappings(addingServiceProviderMap);
        mapper.addMappings(addingAccountMap);
        mapper.addMappings(addingAssignmentMap);
        return mapper;
    }
}
