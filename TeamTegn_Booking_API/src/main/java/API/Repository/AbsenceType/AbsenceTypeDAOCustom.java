package API.Repository.AbsenceType;

import Shared.ToReturn.AbsenceTypeDto;

import java.util.List;

public interface AbsenceTypeDAOCustom {
    AbsenceTypeDto addAbsenceType(AbsenceTypeEntity absenceTypeEntity);
    AbsenceTypeDto updateAbsenceType(AbsenceTypeEntity absenceTypeEntity);
    AbsenceTypeDto findAbsenceType(int id);
    List<AbsenceTypeDto> listAbsenceTypes();
    boolean deleteAbsenceType(int id);

}
