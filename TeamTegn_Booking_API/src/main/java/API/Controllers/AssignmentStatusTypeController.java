package API.Controllers;

import API.Services.AssignmentService.IAssignmentStatusTypeService;
import Shared.ForCreation.AssignmentStatusTypeForCreationDto;
import Shared.ForCreation.AssignmentStatusTypeForUpdateDto;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/assignmentStatusTypes")
public class AssignmentStatusTypeController extends Serializers.Base {

    private IAssignmentStatusTypeService assignmentService;

    @Autowired
    public void setAssignmentService(IAssignmentStatusTypeService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(assignmentService.listAssignmentStatusTypes(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.findAssignmentStatusType(id), new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid AssignmentStatusTypeForCreationDto assignmentStatusType) {
        return new ResponseEntity<>(assignmentService.addAssignmentStatusType(assignmentStatusType), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.deleteAssignmentStatusType(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = {RequestMethod.PATCH})
    public ResponseEntity<?> update(@RequestBody @Valid AssignmentStatusTypeForUpdateDto assignmentStatusType) {
        return new ResponseEntity<>(assignmentService.updateAssignmentStatusType(assignmentStatusType), new HttpHeaders(), HttpStatus.OK);
    }
}
