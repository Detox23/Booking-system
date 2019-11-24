package API.Repository.AbsenceType;

import API.Database_Entities.AbsenceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbsenceTypeDAO extends JpaRepository<AbsenceTypeEntity, Integer>, AbsenceTypeDAOCustom {

    Optional<AbsenceTypeEntity> findByAbsenceTypeName(String absenceTypeName);
}
