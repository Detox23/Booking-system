package API.Controllers;


import API.Services.AssignmentService.IAssignmentStukYearCodeService;
import Shared.ForCreation.AssignmentStukYearCodeForCreationDto;
import Shared.ForCreation.AssignmentStukYearCodeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stukYearCodes")
public class AssignmentStukYearCodeController {

    private IAssignmentStukYearCodeService assignmentStukYearCodeService;

    @Autowired
    public void setAssignmentStukYearCodeService(IAssignmentStukYearCodeService assignmentStukYearCodeService) {
        this.assignmentStukYearCodeService = assignmentStukYearCodeService;
    }

    /**
     * GET request method that retrieves all assignment stuk year codes. There is possibility to display deleted records.
     * @param showDeleted <Boolean> Determines whether to display deleted records from database.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all assignment stuk year codes.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentStukYearCodes(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(assignmentStukYearCodeService.listAssignmentStukYearCodes(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds an assignment stuk year code.
     * @param id <Integer> Unique identifier of a stuk year code record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found assignmentStukYearCodeDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentStukYearCode(@PathVariable int id) {
        return new ResponseEntity<>(assignmentStukYearCodeService.findAssignmentStukYearCode(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds an assignment stuk year code to a database. Allowed only for administrator account.
     * @param stukYearCode <AssignmentStukYearCodeForCreationDto> An object that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ stukYearCodeName <String> (Unique name of a stuk year code)
     * @return If successfully, it returns 201 code (CREATED response) with a created assignmentStukYearCodeDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssigmentStukYearCode(@RequestBody AssignmentStukYearCodeForCreationDto stukYearCode) {
        return new ResponseEntity<>(assignmentStukYearCodeService.addAssigmentStukYearCode(stukYearCode), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes an assignment stuk year code from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentStukYearCode(@PathVariable int id) {
        return new ResponseEntity<>(assignmentStukYearCodeService.deleteAssignmentStukYearCode(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param stukYearCode <AssignmentStukYearCodeForUpdateDto> An object that need to be passed in order to process the operation.
     *                         Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                         ~ stukYearCodeName <String> (Unique name of a stuk year code)
     * @return If successfully, it returns 200 code (OK response) and an assignmentStukYearCodeDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentStukYearCode(@RequestBody AssignmentStukYearCodeForUpdateDto stukYearCode) {
        return new ResponseEntity<>(assignmentStukYearCodeService.updateAssignmentStukYearCode(stukYearCode), new HttpHeaders(), HttpStatus.OK);
    }
}
