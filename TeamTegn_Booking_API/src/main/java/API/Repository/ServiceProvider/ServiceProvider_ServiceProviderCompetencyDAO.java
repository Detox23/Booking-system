package API.Repository.ServiceProvider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProvider_ServiceProviderCompetencyDAO extends JpaRepository<ServiceProviderServiceProviderCompetencyEntity, Integer>, ServiceProvider_ServiceProviderCompetencyDAOCustom {
    List<ServiceProviderServiceProviderCompetencyEntity> findAllByServiceProviderId(int serviceProviderID);

    ServiceProviderServiceProviderCompetencyEntity findAllByServiceProviderIdAndCompetencyId(int serviceProviderID, int competencyID);

    void deleteAllByServiceProviderId(int serviceProviderID);

    void deleteAllByServiceProviderIdAndCompetencyId(int serviceProviderID, int competencyID);

    void deleteAllByCompetencyId(int competencyID);
}