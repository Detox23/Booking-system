package API.Repository.AbsenceType;

import API.Configurations.Patcher.PatcherHandler;
import API.Database_Entities.AbsenceTypeEntity;
import API.Exceptions.*;
import Shared.ToReturn.AbsenceTypeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class AbsenceTypeDAOImpl implements AbsenceTypeDAOCustom {


    private ModelMapper modelMapper;

    private AbsenceTypeDAO absenceTypeDAO;

    private PatcherHandler patcherHandler;

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAbsenceTypeDAO(AbsenceTypeDAO absenceTypeDAO) {
        this.absenceTypeDAO = absenceTypeDAO;
    }


    @Override
    public AbsenceTypeDto addAbsenceType(AbsenceTypeEntity absenceTypeEntity) {
        if (absenceTypeDAO.findByAbsenceTypeName(absenceTypeEntity.getAbsenceTypeName()).isPresent()) {
            throw new DuplicateException("There is already absence type with exact name.");
        }
        if (absenceTypeEntity.getAbsenceTypeName() == null || absenceTypeEntity.getAbsenceTypeName().length() == 0) {
            throw new NotEnoughDataException("You provided too little information to create the absence type.");
        }
        AbsenceTypeEntity saved = absenceTypeDAO.save(absenceTypeEntity);
        if (saved.getId() > 0) {
            return modelMapper.map(saved, AbsenceTypeDto.class);
        } else {
            throw new UnknownAddingException("There was a problem with adding the absence type.");
        }
    }

    @Override
    public AbsenceTypeDto updateAbsenceType(AbsenceTypeEntity absenceTypeEntity) {
        try {
            Optional<AbsenceTypeEntity> found = absenceTypeDAO.findById(absenceTypeEntity.getId());
            if (!found.isPresent()) {
                throw new NotFoundException("Absence type was not found.");
            }
            patcherHandler.fillNotNullFields(found.get(), absenceTypeEntity);
            AbsenceTypeEntity updated = absenceTypeDAO.save(found.get());
            return modelMapper.map(updated, AbsenceTypeDto.class);
        } catch (IntrospectionException e) {
            throw new UpdatePatchException("There was an error while updating a absence type. [PATCHING]");
        } catch (Exception e) {
            throw new UnknownException(e.getMessage());
        }
    }

    @Override
    public AbsenceTypeDto findAbsenceType(int id) {
        Optional<AbsenceTypeEntity> found = absenceTypeDAO.findById(id);
        if (!found.isPresent()) {
            throw new NotFoundException("There was no absence type found.");
        } else {
            return modelMapper.map(found.get(), AbsenceTypeDto.class);
        }
    }

    @Override
    public List<AbsenceTypeDto> listAbsenceTypes() {
        Type listType = new TypeToken<List<AbsenceTypeDto>>() {
        }.getType();
        return modelMapper.map(absenceTypeDAO.findAll(), listType);
    }

    @Override
    public boolean deleteAbsenceType(int id) {
        try {
            Optional<AbsenceTypeEntity> found = absenceTypeDAO.findById(id);
            if (found.isPresent()) {
                absenceTypeDAO.deleteById(found.get().getId());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new UnknownException(e.getMessage());
        }
    }
}
