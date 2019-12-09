package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderServiceProviderCompetencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ServiceProvider_ServiceProviderCompetencyDAO extends JpaRepository<ServiceProviderServiceProviderCompetencyEntity, Integer>{
    List<ServiceProviderServiceProviderCompetencyEntity> findAllByServiceProviderId(int serviceProviderID);

    void deleteAllByServiceProviderId(int serviceProviderID);
}
