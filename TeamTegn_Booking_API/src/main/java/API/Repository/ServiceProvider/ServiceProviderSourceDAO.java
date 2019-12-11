package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderSourceDAO extends JpaRepository<ServiceProviderSourceEntity, Integer>, ServiceProviderSourceDAOCustom {
    int countAllByProviderSourceIs(String providerSource);

    int countAllByProviderSourceIsAndIdIsNot(String providerSource, int id);

    Optional<ServiceProviderSourceEntity> findByIdAndDeletedIsFalse(int id);

    List<ServiceProviderSourceEntity> findAllByDeletedIsFalse();
}
