package API.Repository.AbsenceType;

import API.Models.Database_Entities.AbsenceTypeEntity;
import Shared.ToReturn.AbsenceTypeDto;

import java.util.List;

public interface AbsenceTypeDAOCustom {
    AbsenceTypeDto addAbsenceType(AbsenceTypeEntity absenceTypeEntity);

    AbsenceTypeDto updateAbsenceType(AbsenceTypeEntity absenceTypeEntity);

    AbsenceTypeDto findAbsenceType(int id);

    List<AbsenceTypeDto> listAbsenceTypes(boolean showDeleted);

    boolean deleteAbsenceType(int id);
}
