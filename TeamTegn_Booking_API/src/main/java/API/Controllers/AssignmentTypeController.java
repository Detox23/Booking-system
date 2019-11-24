package API.Controllers;

import API.Services.AssignmentService.IAssignmentTypeService;
import Shared.ForCreation.AssignmentTypeForCreationDto;
import Shared.ForCreation.AssignmentTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(assignmentTypeService.get(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(assignmentTypeService.getAll(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST})
    public ResponseEntity<?> add(@RequestBody AssignmentTypeForCreationDto forCreation) {
        return new ResponseEntity<>(assignmentTypeService.add(forCreation), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(assignmentTypeService.delete(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT})
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody AssignmentTypeForUpdateDto assignmentTypeForUpdateDto) {
        return new ResponseEntity<>(assignmentTypeService.update(id, assignmentTypeForUpdateDto), new HttpHeaders(), HttpStatus.OK);
    }
}
