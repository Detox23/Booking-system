package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderEntity;
import API.Database_Entities.ServiceProviderTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderTypeDAO extends JpaRepository<ServiceProviderTypeEntity, Integer>, ServiceProviderTypeCustom {
    List<ServiceProviderTypeEntity> findAllByProviderType(String providerType);
}
