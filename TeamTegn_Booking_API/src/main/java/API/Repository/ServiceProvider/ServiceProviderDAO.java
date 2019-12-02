package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderDAO extends JpaRepository<ServiceProviderEntity, Integer>, ServiceProviderDAOCustom {
    List<ServiceProviderEntity> findAllByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);

    ServiceProviderEntity findMiddleNameAndFirstNameAndLastNameAndServiceProviderInitialsById(int id);

    List<ServiceProviderEntity> findAllByDeletedIsFalse();
}
