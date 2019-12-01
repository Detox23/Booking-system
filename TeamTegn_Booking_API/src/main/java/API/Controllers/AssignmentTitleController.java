package API.Controllers;

import API.Services.AssignmentService.IAssignmentTitleService;
import Shared.ForCreation.AssignmentTitleForCreationDto;
import Shared.ForCreation.AssignmentTitleForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/assignmentTitles")
public class AssignmentTitleController {

    private IAssignmentTitleService assignmentTitleService;

    @Autowired
    public void setAssignmentTitleService(IAssignmentTitleService assignmentTitleService) {
        this.assignmentTitleService = assignmentTitleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentTitles() {
        return new ResponseEntity<>(assignmentTitleService.listAssignmentTitles(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentTitle(@PathVariable int id) {
        return new ResponseEntity<>(assignmentTitleService.findAssignmentTitle(id), new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addAssignmentTitle(@RequestBody @Valid AssignmentTitleForCreationDto assignmentTitle) {
        return new ResponseEntity<>(assignmentTitleService.addAssignmentTitle(assignmentTitle), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAssignmentTitle(@PathVariable int id) {
        return new ResponseEntity<>(assignmentTitleService.deleteAssignmentTitle(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAssignmentTitle(@RequestBody @Valid AssignmentTitleForUpdateDto assignmentTitle) {
        return new ResponseEntity<>(assignmentTitleService.updateAssignmentTitle(assignmentTitle), new HttpHeaders(), HttpStatus.OK);
    }
}
