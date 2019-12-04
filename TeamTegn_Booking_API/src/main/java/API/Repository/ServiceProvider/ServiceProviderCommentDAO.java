package API.Repository.ServiceProvider;

import API.Models.Database_Entities.ServiceProviderCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceProviderCommentDAO extends JpaRepository<ServiceProviderCommentEntity, Integer>, ServiceProviderCommentDAOCustom {
    List<ServiceProviderCommentEntity> findAllByServiceProviderIdIs(int serviceProviderID);
}
