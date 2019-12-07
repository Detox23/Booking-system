package API.Repository.AbsenceType;

import API.Models.Database_Entities.AbsenceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceTypeDAO extends JpaRepository<AbsenceTypeEntity, Integer>, AbsenceTypeDAOCustom {
    List<AbsenceTypeEntity> findAllByDeletedIsFalse();
    int countAllByAbsenceTypeNameIs(String absenceTypeName);
    int countAllByAbsenceTypeNameIsAndIdIsNot(String absenceTypeName, int id);
    Optional<AbsenceTypeEntity> findByIdAndDeletedIsFalse(int id);
    Optional<AbsenceTypeEntity> findByAbsenceTypeName(String absenceTypeName);
}
