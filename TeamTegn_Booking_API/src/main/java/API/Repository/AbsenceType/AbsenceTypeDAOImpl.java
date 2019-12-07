package API.Repository.AbsenceType;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.*;
import API.Models.Database_Entities.AbsenceTypeEntity;
import Shared.ToReturn.AbsenceTypeDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
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
        try {
            checkIfExistsByAbsenceName(absenceTypeEntity);
            absenceTypeEntity.setDeleted(false);
            AbsenceTypeEntity saved = absenceTypeDAO.save(absenceTypeEntity);
            return modelMapper.map(saved, AbsenceTypeDto.class);
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public AbsenceTypeDto updateAbsenceType(AbsenceTypeEntity absenceTypeEntity) {
        try {
            AbsenceTypeEntity found = findIfExistsAndReturn(absenceTypeEntity.getId());
            patcherHandler.fillNotNullFields(found, absenceTypeEntity);
            checkIfExistsByAbsenceName(found);
            AbsenceTypeEntity updated = absenceTypeDAO.save(found);
            return modelMapper.map(updated, AbsenceTypeDto.class);
        } catch (IntrospectionException e) {
            throw new UpdatePatchException("There was an error while updating a absence type. [PATCHING]");
        } catch (Exception e) {
            throw new UnknownException(e.getMessage());
        }
    }

    @Override
    public AbsenceTypeDto findAbsenceType(int id) {
        try {
            AbsenceTypeEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AbsenceTypeDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AbsenceTypeDto> listAbsenceTypes(boolean showDeleted) {
        try {
            if (showDeleted) {
                Type listType = new TypeToken<List<AbsenceTypeDto>>() {
                }.getType();
                return modelMapper.map(absenceTypeDAO.findAll(), listType);
            } else {
                Type listType = new TypeToken<List<AbsenceTypeDto>>() {
                }.getType();
                return modelMapper.map(absenceTypeDAO.findAllByDeletedIsFalse(), listType);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteAbsenceType(int id) {
        try {
            AbsenceTypeEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            AbsenceTypeEntity deletionResult = absenceTypeDAO.save(found);
            return deletionResult.isDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    private AbsenceTypeEntity findIfExistsAndReturn(int id) {
        Optional<AbsenceTypeEntity> found = absenceTypeDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Absence type with id: %d was not found.", id));
        }
        return found.get();
    }

    private void checkIfExistsByAbsenceName(AbsenceTypeEntity absenceType){
        if(absenceType.getId() == 0){
            if (absenceTypeDAO.countAllByAbsenceTypeNameIs(absenceType.getAbsenceTypeName()) > 0){
                throw new DuplicateException(String.format("The account type with name: %s already exists", absenceType.getAbsenceTypeName()));
            }
        }else{
            if (absenceTypeDAO.countAllByAbsenceTypeNameIsAndIdIsNot(absenceType.getAbsenceTypeName(), absenceType.getId()) > 0) {
                throw new DuplicateException(String.format("The account type with name: %s already exists", absenceType.getAbsenceTypeName()));
            }
        }
    }
}
