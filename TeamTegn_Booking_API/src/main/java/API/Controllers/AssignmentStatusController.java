package API.Controllers;

import API.Services.AssignmentService.IAssignmentStatusService;
import Shared.ForCreation.AssignmentStatusForCreationDto;
import Shared.ForCreation.AssignmentStatusForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/assignmentsStatuses")
public class AssignmentStatusController {

    private IAssignmentStatusService assignmentService;

    @Autowired
    public void setAssignmentService(IAssignmentStatusService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentStatuses() {
        return new ResponseEntity<>(assignmentService.listAssignmentStatuses(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentStatus(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.findAssignmentStatus(id), new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssignmentStatus(@RequestBody @Valid AssignmentStatusForCreationDto assignmentStatus) {
        return new ResponseEntity<>(assignmentService.addAssignmentStatus(assignmentStatus), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentStatus(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.deleteAssignmentStatus(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentStatus(@RequestBody @Valid AssignmentStatusForUpdateDto assignmentStatus) {
        return new ResponseEntity<>(assignmentService.updateAssignmentStatus(assignmentStatus), new HttpHeaders(), HttpStatus.OK);
    }
}

