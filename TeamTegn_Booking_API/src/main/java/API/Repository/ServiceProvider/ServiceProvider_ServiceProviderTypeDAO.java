package API.Repository.ServiceProvider;


import API.Models.Database_Entities.ServiceProviderServiceProviderTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProvider_ServiceProviderTypeDAO extends JpaRepository<ServiceProviderServiceProviderTypeEntity, Integer> {
    List<ServiceProviderServiceProviderTypeEntity> findAllByServiceProviderId(int serviceProviderID);

    void deleteAllByServiceProviderId(int serviceProviderID);
}
