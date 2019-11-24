package API.Repository.ServiceProvider;


import API.Database_Entities.ServiceProviderServiceProviderTypeEntity;
import Shared.ToReturn.ServiceProviderServiceProviderTypeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProvider_ServiceProviderTypeDAO extends JpaRepository<ServiceProviderServiceProviderTypeEntity, Integer>, ServiceProvider_ServiceProviderTypeCustom {
    List<ServiceProviderServiceProviderTypeEntity> findAllByServiceProviderId(int serviceProviderID);

    ServiceProviderServiceProviderTypeEntity findAllByServiceProviderIdAndServiceProviderTypeId(int serviceProviderID, int serviceProviderTypeID);

    void deleteAllByServiceProviderId(int serviceProviderID);

    void deleteAllByServiceProviderIdAndServiceProviderTypeId(int serviceProviderID, int serviceProviderTypeID);

    void deleteAllByServiceProviderTypeId(int serviceProviderTypeID);

}
