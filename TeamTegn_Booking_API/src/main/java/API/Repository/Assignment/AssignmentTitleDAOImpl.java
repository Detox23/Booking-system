package API.Repository.Assignment;

import API.Configurations.Patcher.PatcherHandler;
import API.Exceptions.DuplicateException;
import API.Exceptions.NotFoundException;
import API.Exceptions.UpdatePatchException;
import API.Models.Database_Entities.AssignmentTitleEntity;
import Shared.ToReturn.AssignmentTitleDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.util.List;
import java.util.Optional;

@Component
public class AssignmentTitleDAOImpl implements AssignmentTitleDAOCustom {

    private AssignmentTitleDAO assignmentTitleDAO;

    private PatcherHandler patcherHandler;

    private ModelMapper modelMapper;

    @Autowired
    public void setAssignmentTitleDAO(AssignmentTitleDAO assignmentTitleDAO) {
        this.assignmentTitleDAO = assignmentTitleDAO;
    }

    @Autowired
    public void setPatcherHandler(PatcherHandler patcherHandler) {
        this.patcherHandler = patcherHandler;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AssignmentTitleDto addAssignmentTitle(AssignmentTitleEntity assignmentTitle) {
        try {
            checkIfAssignmentTitleExists(assignmentTitle);
            AssignmentTitleEntity saved = assignmentTitleDAO.save(assignmentTitle);
            return modelMapper.map(saved, AssignmentTitleDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentTitleDto updateAssignmentTitle(AssignmentTitleEntity assignmentTitle) {
        try {
            AssignmentTitleEntity found = findIfExistsAndReturn(assignmentTitle.getId());
            patcherHandler.fillNotNullFields(found, assignmentTitle);
            checkIfAssignmentTitleExists(found);
            AssignmentTitleEntity result = assignmentTitleDAO.save(found);
            return modelMapper.map(result, AssignmentTitleDto.class);
        } catch (IntrospectionException introspectionException) {
            throw new UpdatePatchException("There was an error while updating an assignment title. [PATCHING]");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public AssignmentTitleDto findAssignmentTitle(int id) {
        try {
            AssignmentTitleEntity found = findIfExistsAndReturn(id);
            return modelMapper.map(found, AssignmentTitleDto.class);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<AssignmentTitleDto> listAssignmentTitles(boolean showDeleted) {
        if(showDeleted){
            try{
                return modelMapper.map(assignmentTitleDAO.findAll(), new TypeToken<List<AssignmentTitleDto>>() {
                }.getType());
            }catch (Exception e){
                throw e;
            }
        }else{
            try{
                return modelMapper.map(assignmentTitleDAO.findAllByDeletedIsFalse(), new TypeToken<List<AssignmentTitleDto>>() {
                }.getType());
            }catch (Exception e){
                throw e;
            }
        }
    }

    @Override
    public boolean deleteAssignmentTitle(int id) {
        try {
            AssignmentTitleEntity found = findIfExistsAndReturn(id);
            found.setDeleted(true);
            AssignmentTitleEntity deletionResult = assignmentTitleDAO.save(found);
            return deletionResult.getDeleted();
        } catch (Exception e) {
            throw e;
        }
    }

    private AssignmentTitleEntity findIfExistsAndReturn(int id) {
        Optional<AssignmentTitleEntity> found = assignmentTitleDAO.findByIdAndDeletedIsFalse(id);
        if (!found.isPresent()) {
            throw new NotFoundException(String.format("Assignment title with id: %d was not found.", id));
        }
        return found.get();
    }


    private void checkIfAssignmentTitleExists(AssignmentTitleEntity assignmentTitle){
        if(assignmentTitle.getId()==0){
            if(assignmentTitleDAO.countAllByTitleIs(assignmentTitle.getTitle())> 0){
                throw new DuplicateException(String.format("The title: %s already exists", assignmentTitle.getTitle()));
            }
        }else{
            if(assignmentTitleDAO.countAllByTitleIsAndIdIsNot(assignmentTitle.getTitle(), assignmentTitle.getId())> 0){
                throw new DuplicateException(String.format("The title: %s already exists", assignmentTitle.getTitle()));
            }
        }
    }
}
