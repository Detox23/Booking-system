package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderCompetencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderCompetencyDAO extends JpaRepository<ServiceProviderCompetencyEntity, Integer>, ServiceProviderCompetencyDAOCustom {
    int countAllByCompetencyIs(String name);
    Optional<ServiceProviderCompetencyEntity> findByIdIsAndDeletedIsFalse(int id);
    List<ServiceProviderCompetencyEntity> findAllByDeletedIsFalse();



}
