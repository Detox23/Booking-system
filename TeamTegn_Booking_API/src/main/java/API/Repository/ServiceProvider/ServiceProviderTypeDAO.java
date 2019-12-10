package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderTypeDAO extends JpaRepository<ServiceProviderTypeEntity, Integer>, ServiceProviderTypeDAOCustom {
    int countAllByProviderTypeIs(String providerType);

    int countAllByProviderTypeIsAndIdIsNot(String providerType, int id);

    Optional<ServiceProviderTypeEntity> findByIdIsAndDeletedIsFalse(int id);

    List<ServiceProviderTypeEntity> findAllByDeletedIsFalse();
}
