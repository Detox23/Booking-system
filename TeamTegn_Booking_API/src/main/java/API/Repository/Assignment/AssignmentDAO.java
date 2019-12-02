package API.Repository.Assignment;

import API.Models.Database_Entities.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentDAO extends PagingAndSortingRepository<AssignmentEntity, Integer>, JpaRepository<AssignmentEntity, Integer>,AssignmentDAOCustom {

    Optional<AssignmentEntity> findByIdAndDeletedIsFalse(int id);

    List<AssignmentEntity> findAllByAssignmentDateEquals(Date date);
}