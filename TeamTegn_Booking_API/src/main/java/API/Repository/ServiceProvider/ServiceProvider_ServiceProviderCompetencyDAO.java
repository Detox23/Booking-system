package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderServiceProviderCompetencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProvider_ServiceProviderCompetencyDAO extends JpaRepository<ServiceProviderServiceProviderCompetencyEntity, Integer>, ServiceProvider_ServiceProviderCompetencyCustom {
    List<ServiceProviderServiceProviderCompetencyEntity> findByServiceProviderId(int serviceProviderID);

    void deleteAllByServiceProviderId(int serviceProviderID);

    void deleteAllByServiceProviderIdAndCompetencyId(int serviceProviderID, int competencyID);

    void deleteAllByCompetencyId(int competencyID);
}
