package API.Controllers;


import API.Services.AssignmentService.IAssignmentStukYearCodeService;
import Shared.ForCreation.AssignmentStukYearCodeForCreationDto;
import Shared.ForCreation.AssignmentStukYearCodeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stukYearCodes")
public class AssignmentStukYearCodeController {

    private IAssignmentStukYearCodeService assignmentStukYearCodeService;

    @Autowired
    public void setAssignmentStukYearCodeService(IAssignmentStukYearCodeService assignmentStukYearCodeService) {
        this.assignmentStukYearCodeService = assignmentStukYearCodeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssigmentStukYearCode(@RequestBody AssignmentStukYearCodeForCreationDto stukYearCode) {
        return new ResponseEntity<>(assignmentStukYearCodeService.addAssigmentStukYearCode(stukYearCode), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentStukYearCodes() {
        return new ResponseEntity<>(assignmentStukYearCodeService.listAssignmentStukYearCodes(), new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentStukYearCode(@PathVariable int id) {
        return new ResponseEntity<>(assignmentStukYearCodeService.deleteAssignmentStukYearCode(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentStukYearCode(@PathVariable int id) {
        return new ResponseEntity<>(assignmentStukYearCodeService.findAssignmentStukYearCode(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentStukYearCode(@RequestBody AssignmentStukYearCodeForUpdateDto stukYearCode) {
        return new ResponseEntity<>(assignmentStukYearCodeService.updateAssignmentStukYearCode(stukYearCode), new HttpHeaders(), HttpStatus.OK);
    }
}
