package API.Repository.ServiceProvider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderCompetencyDAO extends JpaRepository<ServiceProviderCompetencyEntity, Integer>, ServiceProviderCompetencyDAOCustom {
    List<ServiceProviderCompetencyEntity> findAllByCompetencyIs(String name);
    ServiceProviderCompetencyEntity findByCompetencyIs(String name);



}
