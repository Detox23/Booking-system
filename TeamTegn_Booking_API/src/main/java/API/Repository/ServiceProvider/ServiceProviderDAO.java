package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderDAO extends JpaRepository<ServiceProviderEntity, Integer>, ServiceProviderDAOCustom {
    int countAllByFirstNameIsAndMiddleNameIsAndLastNameIs(String firstName, String middleName, String lastName);

    int countAllByFirstNameIsAndMiddleNameIsAndLastNameIsAndIdIsNot(String firstName, String middleName, String lastName, int id);

    ServiceProviderEntity findMiddleNameAndFirstNameAndLastNameAndServiceProviderInitialsById(int id);

    List<ServiceProviderEntity> findAllByDeletedIsFalse();

    Optional<ServiceProviderEntity> findByIdIsAndDeletedIsFalse(int id);
}
