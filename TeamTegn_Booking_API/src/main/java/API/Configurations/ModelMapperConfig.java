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
            @Override
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<ServiceProviderForCreationDto, ServiceProviderEntity> addingServiceProviderMap = new PropertyMap<ServiceProviderForCreationDto, ServiceProviderEntity>() {
            @Override
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<AssignmentForCreationDto, AssignmentEntity> addingAssignmentMap = new PropertyMap<AssignmentForCreationDto, AssignmentEntity>() {
            @Override
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<ServiceProviderAbsenceForCreationDto, ServiceProviderAbsenceEntity> addingServiceProviderAbsenceMap = new PropertyMap<ServiceProviderAbsenceForCreationDto, ServiceProviderAbsenceEntity>() {
            @Override
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<ServiceProviderEveningWorkForCreationDto, ServiceProviderEveningWorkEntity> addingEveningWorkMap = new PropertyMap<ServiceProviderEveningWorkForCreationDto, ServiceProviderEveningWorkEntity>() {
            @Override
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<EventLogDto, EventLogEntity> addingEventLogMap = new PropertyMap<EventLogDto, EventLogEntity>() {
            @Override
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<ServiceUserPreferencesForCreationDto, ServiceUserPreferencesEntity> addingServiceUserPreferencesMap = new PropertyMap<ServiceUserPreferencesForCreationDto, ServiceUserPreferencesEntity>() {
            @Override
            protected void configure() {
                skip().setId(0);
                skip().setServiceUserId(0);
            }
        };

        PropertyMap<ServiceUserForCreationDto, ServiceUserEntity> addingServiceUser = new PropertyMap<ServiceUserForCreationDto, ServiceUserEntity>() {
            @Override
            protected void configure() {
                skip().setId(0);
            }
        };

        PropertyMap<ServiceUserCommentForCreationDto, ServiceUserCommentEntity> addingServiceUserComment = new PropertyMap<ServiceUserCommentForCreationDto, ServiceUserCommentEntity>() {
            @Override
            protected void configure() {
                skip().setUserId(0);
            }
        };

        mapper.addMappings(addingServiceUserComment);
        mapper.addMappings(addingServiceUser);
        mapper.addMappings(addingServiceUserPreferencesMap);
        mapper.addMappings(addingEventLogMap);
        mapper.addMappings(addingEveningWorkMap);
        mapper.addMappings(addingServiceProviderAbsenceMap);
        mapper.addMappings(addingServiceProviderMap);
        mapper.addMappings(addingAccountMap);
        mapper.addMappings(addingAssignmentMap);
        return mapper;
    }
}
