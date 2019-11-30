package API.Controllers;

import API.Services.AssignmentService.IAssignmentService;
import Shared.ForCreation.AssignmentForCreationDto;
import Shared.ForCreation.AssignmentForUpdateDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@RestController
@RequestMapping("/api/assignments")
public class AssignmentController extends BaseController {

    private IAssignmentService assignmentService;

    @Autowired
    public void setAssignmentService(IAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAssignments(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam(defaultValue = "id") String sortBy,
                                    @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection) {
        return new ResponseEntity<>(assignmentService.listAssignmentsPage(PageRequest.of(pageNumber, pageSize, sortDirection, sortBy)), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> findAssignment(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.findAssignment(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/date/{date}", method = {RequestMethod.GET})
    public ResponseEntity<?> listAssignmentsDate(@PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd") Date date) {
        return new ResponseEntity<>(assignmentService.listAssignmentsDate(date), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addAssignment(@RequestBody AssignmentForCreationDto assignment) {
        return new ResponseEntity<>(assignmentService.addAssignment(assignment), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public ResponseEntity<?> deleteAssignment(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.deleteAssignment(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = {RequestMethod.PATCH})
    public ResponseEntity<?> updateAssignment(@RequestBody AssignmentForUpdateDto forUpdate) {
        return new ResponseEntity<>(assignmentService.updateAssignment(forUpdate), new HttpHeaders(), HttpStatus.OK);
    }
}