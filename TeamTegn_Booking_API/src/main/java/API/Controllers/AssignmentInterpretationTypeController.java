package API.Controllers;


import API.Services.AssignmentService.IAssignmentInterpretationTypeService;
import Shared.ForCreation.AssignmentInterpretationTypeForCreationDto;
import Shared.ForCreation.AssignmentInterpretationTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/interpretationTypes")
public class AssignmentInterpretationTypeController {

    private IAssignmentInterpretationTypeService assignmentInterpretationTypeService;

    @Autowired
    public void setAssignmentInterpretationTypeService(IAssignmentInterpretationTypeService assignmentInterpretationTypeService) {
        this.assignmentInterpretationTypeService = assignmentInterpretationTypeService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentInterpretationTypes() {
        return new ResponseEntity<>(assignmentInterpretationTypeService.listAssignmentInterpretationTypes(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentInterpretationType(@PathVariable int id) {
        return new ResponseEntity<>(assignmentInterpretationTypeService.findAssignmentInterpretationType(id), new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssignmentStatus(@RequestBody @Valid AssignmentInterpretationTypeForCreationDto assignmentInterpretationType) {
        return new ResponseEntity<>(assignmentInterpretationTypeService.addAssignmentInterpretationType(assignmentInterpretationType), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentImportance(@PathVariable int id) {
        return new ResponseEntity<>(assignmentInterpretationTypeService.deleteAssignmentInterpretationType(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentImportance(@RequestBody @Valid AssignmentInterpretationTypeForUpdateDto assignmentInterpretationType) {
        return new ResponseEntity<>(assignmentInterpretationTypeService.updateAssignmentInterpretationType(assignmentInterpretationType), new HttpHeaders(), HttpStatus.OK);
    }
}
