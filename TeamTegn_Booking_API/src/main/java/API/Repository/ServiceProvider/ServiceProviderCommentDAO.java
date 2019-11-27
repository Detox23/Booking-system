package API.Repository.ServiceProvider;

import API.Database_Entities.ServiceProviderCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceProviderCommentDAO extends JpaRepository<ServiceProviderCommentEntity, Integer>, ServiceProviderCommentDAOCustom {
    List<ServiceProviderCommentEntity> findAllByServiceProviderIdIs(int serviceProviderID);
    Optional<ServiceProviderCommentEntity> findByServiceProviderIdIsAndIdIs(int serviceProviderID, int id);
}
