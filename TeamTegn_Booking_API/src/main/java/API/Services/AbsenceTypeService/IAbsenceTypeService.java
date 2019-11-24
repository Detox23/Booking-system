package API.Services.AbsenceTypeService;

import Shared.ForCreation.AbsenceTypeForCreationDto;
import Shared.ForCreation.AbsenceTypeForUpdateDto;
import Shared.ToReturn.AbsenceTypeDto;

import java.util.List;

public interface IAbsenceTypeService {
    AbsenceTypeDto addAbsenceType(AbsenceTypeForCreationDto absenceTypeEntity);
    AbsenceTypeDto updateAbsenceType(AbsenceTypeForUpdateDto absenceTypeEntity);
    AbsenceTypeDto findAbsenceType(int id);
    List<AbsenceTypeDto> listAbsenceTypes();
    boolean deleteAbsenceType(int id);
}
