package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.AttributeConverter;

@Repository
public interface ServiceProviderDAO extends JpaRepository<ServiceProviderEntity, Integer>, ServiceProviderDAOCustom{


}
