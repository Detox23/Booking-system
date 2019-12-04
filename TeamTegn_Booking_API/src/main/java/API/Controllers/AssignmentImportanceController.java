package API.Controllers;

import API.Services.AssignmentService.IAssignmentImportanceService;
import Shared.ForCreation.AssignmentImportanceForCreationDto;
import Shared.ForCreation.AssignmentImportanceForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/assignmentImportance")
public class AssignmentImportanceController {

    private IAssignmentImportanceService assignmentImportanceService;

    @Autowired
    public void setAssignmentImportanceService(IAssignmentImportanceService assignmentImportanceService) {
        this.assignmentImportanceService = assignmentImportanceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentImportance() {
        return new ResponseEntity<>(assignmentImportanceService.listAssignmentImportance(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentImportance(@PathVariable int id) {
        return new ResponseEntity<>(assignmentImportanceService.findAssignmentImportance(id), new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssignmentStatus(@RequestBody @Valid AssignmentImportanceForCreationDto assignmentStatus) {
        return new ResponseEntity<>(assignmentImportanceService.addAssignmentImportance(assignmentStatus), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentImportance(@PathVariable int id) {
        return new ResponseEntity<>(assignmentImportanceService.deleteAssignmentImportance(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentImportance(@RequestBody @Valid AssignmentImportanceForUpdateDto assignmentStatus) {
        return new ResponseEntity<>(assignmentImportanceService.updateAssignmentImportance(assignmentStatus), new HttpHeaders(), HttpStatus.OK);
    }
}
