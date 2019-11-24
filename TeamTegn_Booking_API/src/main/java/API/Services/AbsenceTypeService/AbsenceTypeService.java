package API.Services.AbsenceTypeService;

import API.Database_Entities.AbsenceTypeEntity;
import API.Repository.AbsenceType.AbsenceTypeDAO;
import Shared.ForCreation.AbsenceTypeForCreationDto;
import Shared.ForCreation.AbsenceTypeForUpdateDto;
import Shared.ToReturn.AbsenceTypeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AbsenceTypeService implements IAbsenceTypeService {

    private AbsenceTypeDAO absenceTypeDAO;

    private ModelMapper modelMapper;

    @Autowired
    public void setAbsenceTypeDAO(AbsenceTypeDAO absenceTypeDAO) {
        this.absenceTypeDAO = absenceTypeDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AbsenceTypeDto addAbsenceType(AbsenceTypeForCreationDto absenceTypeEntity) {
        return absenceTypeDAO.addAbsenceType(modelMapper.map(absenceTypeEntity, AbsenceTypeEntity.class));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public AbsenceTypeDto updateAbsenceType(AbsenceTypeForUpdateDto absenceTypeEntity) {
        return absenceTypeDAO.updateAbsenceType(modelMapper.map(absenceTypeEntity, AbsenceTypeEntity.class));
    }

    @Override
    public AbsenceTypeDto findAbsenceType(int id) {
        return absenceTypeDAO.findAbsenceType(id);
    }

    @Override
    public List<AbsenceTypeDto> listAbsenceTypes() {
        return absenceTypeDAO.listAbsenceTypes();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean deleteAbsenceType(int id) {
        return absenceTypeDAO.deleteAbsenceType(id);
    }


}
