package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderCompetencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderCompetencyDAO extends JpaRepository<ServiceProviderCompetencyEntity, Integer>, ServiceProviderCompetencyCustom {
    List<ServiceProviderCompetencyEntity> findAllByCompetencyIs(String name);


}
