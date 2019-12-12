package API.Service.ServiceProviderService;

import API.Configurations.Encryption.EncryptionHandler;
import API.Configurations.SpringBeanMockUtil;
import API.MainApplicationClass;
import API.Models.Database_Entities.ServiceProviderEntity;
import API.Models.Database_Entities.ServiceProviderServiceProviderCompetencyEntity;
import API.Models.Database_Entities.ServiceProviderServiceProviderTypeEntity;
import API.Repository.ServiceProvider.*;
import API.Services.ServiceProviderService.ServiceProviderService;
import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.ServiceProviderForUpdateDto;
import Shared.ToReturn.ServiceProviderCompetencyDto;
import Shared.ToReturn.ServiceProviderDto;
import Shared.ToReturn.ServiceProviderTypeDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@ActiveProfiles("test")
public class ServiceProviderServiceTest {

    @Autowired
    private ServiceProviderService serviceProviderService;

    private ServiceProviderDto getServiceProviderDto(){
        ServiceProviderDto toReturn = new ServiceProviderDto();
        toReturn.setCpr("Cpr test");
        toReturn.setFirstName("First name");
        return toReturn;
    }

    private List<ServiceProviderDto> getServiceProviderDtoList(){
        List<ServiceProviderDto> returnList = new ArrayList<>();
        returnList.add(new ServiceProviderDto());
        returnList.add(new ServiceProviderDto());
        returnList.add(new ServiceProviderDto());
        returnList.add(new ServiceProviderDto());
        returnList.add(new ServiceProviderDto());
        for(ServiceProviderDto serviceProvider: returnList){
            serviceProvider.setCpr("Cpr test");
        }
        return returnList;
    }

    private List<ServiceProviderServiceProviderTypeEntity> getServiceProviderServiceProviderTypeList(){
        List<ServiceProviderServiceProviderTypeEntity> returnList = new ArrayList<>();
        returnList.add(new ServiceProviderServiceProviderTypeEntity());
        returnList.add(new ServiceProviderServiceProviderTypeEntity());
        returnList.add(new ServiceProviderServiceProviderTypeEntity());
        returnList.add(new ServiceProviderServiceProviderTypeEntity());
        returnList.add(new ServiceProviderServiceProviderTypeEntity());
        return returnList;
    }

    private ServiceProviderTypeDto getServiceProviderTypeDto(){
        ServiceProviderTypeDto toReturn = new ServiceProviderTypeDto();
        toReturn.setProviderType("ProviderType");
        return toReturn;
    }

    private List<ServiceProviderServiceProviderCompetencyEntity> getServiceProviderServiceProviderCompetencyList(){
        List<ServiceProviderServiceProviderCompetencyEntity> returnList = new ArrayList<>();
        returnList.add(new ServiceProviderServiceProviderCompetencyEntity());
        returnList.add(new ServiceProviderServiceProviderCompetencyEntity());
        returnList.add(new ServiceProviderServiceProviderCompetencyEntity());
        returnList.add(new ServiceProviderServiceProviderCompetencyEntity());
        returnList.add(new ServiceProviderServiceProviderCompetencyEntity());
        return returnList;
    }

    private ServiceProviderCompetencyDto getServiceProviderCompetencyDto(){
        ServiceProviderCompetencyDto toReturn = new ServiceProviderCompetencyDto();
        toReturn.setCompetency("Competency");
        return toReturn;
    }

    @Test
    public void testAddingServiceProviderNameShouldMatch(){

        ServiceProviderDAO mockServiceProvider = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderDAO.class);
        ServiceProvider_ServiceProviderTypeDAO mockServiceProviderServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderTypeDAO.class);
        ServiceProvider_ServiceProviderCompetencyDAO mockServiceProviderServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderCompetencyDAO.class);
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderTypeDAO.class);
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderCompetencyDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, EncryptionHandler.class);

        MockitoAnnotations.initMocks(this);

        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProvider).addServiceProvider(any(ServiceProviderEntity.class), anyList(), anyList());
        Mockito.doReturn(getServiceProviderServiceProviderTypeList()).when(mockServiceProviderServiceProviderType).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderTypeDto()).when(mockServiceProviderType).findServiceProviderType(anyInt());
        Mockito.doReturn(getServiceProviderServiceProviderCompetencyList()).when(mockServiceProviderServiceProviderCompetency).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderCompetencyDto()).when(mockServiceProviderCompetency).findServiceProviderCompetency(anyInt());
        Mockito.doReturn("Encrypted").when(mockEncryptionHandler).decrypt(anyString());

        ServiceProviderForCreationDto toAdd = new ServiceProviderForCreationDto();
        toAdd.setTypes(new ArrayList<>());
        toAdd.setCompetences(new ArrayList<>());

        ServiceProviderDto added = serviceProviderService.addServiceProvider(toAdd);
        Assert.assertEquals("First name", added.getFirstName());
    }

    @Test
    public void testAddingServiceProviderNumberOfCompetenciesShouldBeFive(){

        ServiceProviderDAO mockServiceProvider = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderDAO.class);
        ServiceProvider_ServiceProviderTypeDAO mockServiceProviderServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderTypeDAO.class);
        ServiceProvider_ServiceProviderCompetencyDAO mockServiceProviderServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderCompetencyDAO.class);
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderTypeDAO.class);
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderCompetencyDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, EncryptionHandler.class);

        MockitoAnnotations.initMocks(this);

        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProvider).addServiceProvider(any(ServiceProviderEntity.class), anyList(), anyList());
        Mockito.doReturn(getServiceProviderServiceProviderTypeList()).when(mockServiceProviderServiceProviderType).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderTypeDto()).when(mockServiceProviderType).findServiceProviderType(anyInt());
        Mockito.doReturn(getServiceProviderServiceProviderCompetencyList()).when(mockServiceProviderServiceProviderCompetency).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderCompetencyDto()).when(mockServiceProviderCompetency).findServiceProviderCompetency(anyInt());
        Mockito.doReturn("Encrypted").when(mockEncryptionHandler).decrypt(anyString());

        ServiceProviderForCreationDto toAdd = new ServiceProviderForCreationDto();
        toAdd.setTypes(new ArrayList<>());
        toAdd.setCompetences(new ArrayList<>());

        ServiceProviderDto added = serviceProviderService.addServiceProvider(toAdd);
        Assert.assertEquals(5, added.getCompetences().size());
    }

    @Test
    public void testAddingServiceProviderNumberOfTypesShouldBeFive(){

        ServiceProviderDAO mockServiceProvider = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderDAO.class);
        ServiceProvider_ServiceProviderTypeDAO mockServiceProviderServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderTypeDAO.class);
        ServiceProvider_ServiceProviderCompetencyDAO mockServiceProviderServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderCompetencyDAO.class);
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderTypeDAO.class);
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderCompetencyDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, EncryptionHandler.class);

        MockitoAnnotations.initMocks(this);

        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProvider).addServiceProvider(any(ServiceProviderEntity.class), anyList(), anyList());
        Mockito.doReturn(getServiceProviderServiceProviderTypeList()).when(mockServiceProviderServiceProviderType).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderTypeDto()).when(mockServiceProviderType).findServiceProviderType(anyInt());
        Mockito.doReturn(getServiceProviderServiceProviderCompetencyList()).when(mockServiceProviderServiceProviderCompetency).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderCompetencyDto()).when(mockServiceProviderCompetency).findServiceProviderCompetency(anyInt());
        Mockito.doReturn("Encrypted").when(mockEncryptionHandler).decrypt(anyString());

        ServiceProviderForCreationDto toAdd = new ServiceProviderForCreationDto();
        toAdd.setTypes(new ArrayList<>());
        toAdd.setCompetences(new ArrayList<>());

        ServiceProviderDto added = serviceProviderService.addServiceProvider(toAdd);
        Assert.assertEquals(5, added.getTypes().size());
    }

    @Test
    public void testDeletingServiceProviderShouldPass(){
        ServiceProviderDAO mockServiceProvider = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderDAO.class);
        MockitoAnnotations.initMocks(this);
        Mockito.doReturn(true).when(mockServiceProvider).deleteServiceProvider(anyInt());
        Assert.assertTrue(serviceProviderService.deleteServiceProvider(1));
    }

    @Test
    public void testListingServiceProvidersListSizeShouldBeFive(){
        ServiceProviderDAO mockServiceProvider = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderDAO.class);
        ServiceProvider_ServiceProviderTypeDAO mockServiceProviderServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderTypeDAO.class);
        ServiceProvider_ServiceProviderCompetencyDAO mockServiceProviderServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderCompetencyDAO.class);
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderTypeDAO.class);
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderCompetencyDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, EncryptionHandler.class);

        MockitoAnnotations.initMocks(this);

        Mockito.doReturn(getServiceProviderDtoList()).when(mockServiceProvider).listAllServiceProvider(anyBoolean());
        Mockito.doReturn(getServiceProviderServiceProviderTypeList()).when(mockServiceProviderServiceProviderType).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderTypeDto()).when(mockServiceProviderType).findServiceProviderType(anyInt());
        Mockito.doReturn(getServiceProviderServiceProviderCompetencyList()).when(mockServiceProviderServiceProviderCompetency).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderCompetencyDto()).when(mockServiceProviderCompetency).findServiceProviderCompetency(anyInt());
        Mockito.doReturn("Encrypted").when(mockEncryptionHandler).decrypt(anyString());

        Assert.assertEquals(5, serviceProviderService.listAllServiceProviders(true).size());
    }

    @Test
    public void testUpdatingServiceProviderNameShouldMatch(){
        ServiceProviderDAO mockServiceProvider = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderDAO.class);
        ServiceProvider_ServiceProviderTypeDAO mockServiceProviderServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderTypeDAO.class);
        ServiceProvider_ServiceProviderCompetencyDAO mockServiceProviderServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderCompetencyDAO.class);
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderTypeDAO.class);
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderCompetencyDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, EncryptionHandler.class);

        MockitoAnnotations.initMocks(this);

        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProvider).updateServiceProvider(any(ServiceProviderEntity.class), anyList(), anyList());
        Mockito.doReturn(getServiceProviderServiceProviderTypeList()).when(mockServiceProviderServiceProviderType).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderTypeDto()).when(mockServiceProviderType).findServiceProviderType(anyInt());
        Mockito.doReturn(getServiceProviderServiceProviderCompetencyList()).when(mockServiceProviderServiceProviderCompetency).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderCompetencyDto()).when(mockServiceProviderCompetency).findServiceProviderCompetency(anyInt());
        Mockito.doReturn("Encrypted").when(mockEncryptionHandler).decrypt(anyString());
        ServiceProviderForUpdateDto toUpdate = new ServiceProviderForUpdateDto();
        toUpdate.setTypes(new ArrayList<>());
        toUpdate.setCompetences(new ArrayList<>());

        Assert.assertEquals("First name", serviceProviderService.updateServiceProvider(toUpdate).getFirstName());
    }

    @Test
    public void testFindingServiceProviderNameShouldMatch(){
        ServiceProviderDAO mockServiceProvider = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderDAO.class);
        ServiceProvider_ServiceProviderTypeDAO mockServiceProviderServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderTypeDAO.class);
        ServiceProvider_ServiceProviderCompetencyDAO mockServiceProviderServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProvider_ServiceProviderCompetencyDAO.class);
        ServiceProviderTypeDAO mockServiceProviderType = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderTypeDAO.class);
        ServiceProviderCompetencyDAO mockServiceProviderCompetency = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, ServiceProviderCompetencyDAO.class);
        EncryptionHandler mockEncryptionHandler = SpringBeanMockUtil.mockFieldOnBean(serviceProviderService, EncryptionHandler.class);

        MockitoAnnotations.initMocks(this);

        Mockito.doReturn(getServiceProviderDto()).when(mockServiceProvider).findServiceProvider(anyInt());
        Mockito.doReturn(getServiceProviderServiceProviderTypeList()).when(mockServiceProviderServiceProviderType).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderTypeDto()).when(mockServiceProviderType).findServiceProviderType(anyInt());
        Mockito.doReturn(getServiceProviderServiceProviderCompetencyList()).when(mockServiceProviderServiceProviderCompetency).findAllByServiceProviderId(anyInt());
        Mockito.doReturn(getServiceProviderCompetencyDto()).when(mockServiceProviderCompetency).findServiceProviderCompetency(anyInt());
        Mockito.doReturn("Encrypted").when(mockEncryptionHandler).decrypt(anyString());

        Assert.assertEquals("First name", serviceProviderService.findServiceProvider(1).getFirstName());
    }
}
