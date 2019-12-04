package API.Controllers;

import API.Services.AssignmentService.IAssignmentTypeService;
import Shared.ForCreation.AssignmentTypeForCreationDto;
import Shared.ForCreation.AssignmentTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/assignmentTypes")
public class AssignmentTypeController {

    private IAssignmentTypeService assignmentTypeService;

    @Autowired
    public void setAssignmentTypeService(IAssignmentTypeService assignmentTypeService) {
        this.assignmentTypeService = assignmentTypeService;
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> getForAssignment(@PathVariable int id) {
        return new ResponseEntity<>(assignmentTypeService.findAssignmentType(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(assignmentTypeService.listAssignmentType(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST})
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> add(@RequestBody @Valid AssignmentTypeForCreationDto assignmentType) {
        return new ResponseEntity<>(assignmentTypeService.addAssignmentType(assignmentType), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(assignmentTypeService.deleteAssignmentType(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = {RequestMethod.PATCH})
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> update(@RequestBody @Valid AssignmentTypeForUpdateDto assignmentType) {
        return new ResponseEntity<>(assignmentTypeService.updateAssignmentType(assignmentType), new HttpHeaders(), HttpStatus.OK);
    }
}
