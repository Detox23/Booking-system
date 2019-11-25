package API.Repository.ServiceProvider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderSourceDAO extends JpaRepository<ServiceProviderSourceEntity, Integer>, ServiceProviderSourceDAOCustom {
    List<ServiceProviderSourceEntity> findAllByProviderSource(String providerSource);
}
