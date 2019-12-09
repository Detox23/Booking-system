package API.Repository.Department;


import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.CityPostcodesEntity;
import API.Models.Database_Entities.DepartmentEntity;
import API.Models.Database_Entities.WiPostcodeEntity;
import API.Repository.CityPostcodes.CityPostcodesDAO;
import API.Repository.CityPostcodes.WI_PostcodeDAO;
import Shared.ToReturn.DepartmentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentDAOImpl implements DepartmentDAOCustom {

    private ModelMapper modelMapper;

    private DepartmentDAO departmentDAO;

    private PatcherHandler patcherHandler;

    private WI_PostcodeDAO wiPostcodeDAO;

    private CityPostcodesDAO cityPostcodesDAO;

    @Autowired
    public void setCityPostcodesDAO(CityPostcodesDAO cityPostcodesDAO) {
        this.cityPostcodesDAO = cityPostcodesDAO;
    }

    @Autowired
    public void setWiPostcodeDAO(WI_PostcodeDAO wiPostcodeDAO) {
        this.wiPostcodeDAO = wiPostcodeDAO;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Override
    public List<DepartmentDto> listAllDepartments(boolean showDeleted) {
        if(showDeleted){
            try {
                Type listType = new TypeToken<List<DepartmentDto>>() {
                }.getType();
                return modelMapper.map(departmentDAO.findAll(), listType);
            } catch (Exception e) {
                throw e;
            }
        }else{
            try {
                Type listType = new TypeToken<List<DepartmentDto>>() {
                }.getType();
                return modelMapper.map(departmentDAO.findAllByDeletedIsFalse(), listType);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public DepartmentDto findOneDepartment(String name) {
        try {
            Optional<DepartmentEntity> found = departmentDAO.findByDepartmentName(name);
            if (!found.isPresent() || found.get().isDeleted()) {
                throw new NotFoundException("Department does not exists.");
            }
            return modelMapper.map(found.get(), DepartmentDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DepartmentDto addOneDepartment(DepartmentEntity departmentEntity) {
        try {
            checkIfDepartmentExists(departmentEntity);
            checkAndFillPostcodeAndCity(departmentEntity);
            addStateRegion(departmentEntity);
            departmentEntity.setDeleted(false);
            DepartmentEntity added = departmentDAO.save(departmentEntity);
            return modelMapper.map(added, DepartmentDto.class);
        } catch (DuplicateException duplicateException) {
            throw duplicateException;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public DepartmentDto updateOneDepartment(DepartmentEntity departmentEntity) {
        try {
            checkAndFillPostcodeAndCity(departmentEntity);
            addStateRegion(departmentEntity);
            DepartmentEntity found = findIfExistsAndReturn(departmentEntity.getId());
            patcherHandler.fillNotNullFields(found, departmentEntity);
            checkIfDepartmentExists(found);
            DepartmentEntity updated = departmentDAO.save(found);
            return modelMapper.map(updated, DepartmentDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating a competency [PATCHING].");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean deleteOneDepartment(int id) {
        try {
            DepartmentEntity toDelete = findIfExistsAndReturn(id);
            toDelete.setDeleted(true);
            DepartmentEntity deletionResult = departmentDAO.save(toDelete);
            return deletionResult.isDeleted();
        } catch (NotFoundException notFoundException) {
            throw notFoundException;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unknown error");
        }
    }

    @Override
    public DepartmentDto findDepartmentByID(int id) {
        try{
            DepartmentEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, DepartmentDto.class);
        }catch (Exception e){
            throw e;
        }
    }


    private void addStateRegion(DepartmentEntity department){
        if(department.getStateRegion() == null){
            Optional<WiPostcodeEntity> wiPostcode = wiPostcodeDAO.findByPostcodeIs(department.getPostcode());
            if(wiPostcode.isPresent()){
                if(wiPostcode.get().getArhus()){
                    department.setStateRegion("Aarhus");
                }else if(wiPostcode.get().getCopenhagen()){
                    department.setStateRegion("Copenhagen");
                }else if(wiPostcode.get().getFredericia()){
                    department.setStateRegion("Fredericia");
                }
            }
        }
    }

    private void checkIfDepartmentExists(DepartmentEntity departmentEntity){
        if(departmentEntity.getId() == 0){
            if(departmentDAO.countAllByDepartmentNameIs(departmentEntity.getDepartmentName())> 0){
                throw new DuplicateException(String.format("The department name: %s already exists", departmentEntity.getDepartmentName()));
            }
        }else{
            if(departmentDAO.countAllByDepartmentNameIsAndIdIsNot(departmentEntity.getDepartmentName(), departmentEntity.getId())> 0){
                throw new DuplicateException(String.format("The department name: %s already exists", departmentEntity.getDepartmentName()));
            }
        }
    }

    private void checkAndFillPostcodeAndCity(DepartmentEntity departmentEntity) {
        if (departmentEntity.getCity() == null) {
            Optional<CityPostcodesEntity> found = cityPostcodesDAO.findFirstByPostcodeIs(departmentEntity.getPostcode());
            if (found.isPresent()) {
                departmentEntity.setCity(found.get().getCity());
            }
        }
    }


    private DepartmentEntity findIfExistsAndReturn(int id) {
        Optional<DepartmentEntity> found = departmentDAO.findByIdIsAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("There was no result found for department with id %d", id));
        }
        return found.get();
    }
}
