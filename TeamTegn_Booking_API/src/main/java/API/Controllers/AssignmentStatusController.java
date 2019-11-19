package API.Controllers;

import API.Services.AssignmentService.IAssignmentStatusService;
import Shared.ForCreation.AssignmentStatusForCreationDto;
import Shared.ForCreation.AssignmentStatusForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignmentsStatus")
public class AssignmentStatusController {


    @Autowired
    private IAssignmentStatusService assignmentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(assignmentService.list(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.get(id), new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody AssignmentStatusForCreationDto assignment) {
        return new ResponseEntity<>(assignmentService.add(assignment), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.delete(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH})
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody AssignmentStatusForUpdateDto forUpdate) {
        return new ResponseEntity<>(assignmentService.update(id, forUpdate), new HttpHeaders(), HttpStatus.OK);
    }
}

