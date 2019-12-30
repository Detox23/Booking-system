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


/**
 * The controller is responsible for managing assignment importance records from a database. There are operations
 * which allows to create, update, list and delete assignment importance objects.
 */
@RestController
@RequestMapping("/api/assignmentImportance")
public class AssignmentImportanceController {

    private IAssignmentImportanceService assignmentImportanceService;

    @Autowired
    public void setAssignmentImportanceService(IAssignmentImportanceService assignmentImportanceService) {
        this.assignmentImportanceService = assignmentImportanceService;
    }

    /**
     * GET request method that retrieves all assignment importance. There is possibility to display deleted records.
     * @param showDeleted [Path variable] <Boolean> Determines whether to display deleted records from database.
     *                    True -> show all records
     *                    False -> show only not deleted records
     * @return If successfully, it returns 302 code (FOUND response) with a list of all assignment importance. Otherwise
     * it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentImportance(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(assignmentImportanceService.listAssignmentImportance(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds an assignment importance.
     * @param id [Path variable] <Integer> Unique identifier of a importance record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found assignmentImportanceDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentImportance(@PathVariable int id) {
        return new ResponseEntity<>(assignmentImportanceService.findAssignmentImportance(id), new HttpHeaders(), HttpStatus.FOUND);
    }


    /**
     * POST request method that adds an assignment importance to a database. Allowed only for administrator account.
     * @param assignmentStatus [Request body variable] <AssignmentImportanceForCreationDto> An object that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ importanceName <String> (Unique name of an importance)
     * @return If successfully, it returns 201 code (CREATED response) with a created assignmentImportanceDto filled with
     * information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssignmentImportance(@RequestBody @Valid AssignmentImportanceForCreationDto assignmentStatus) {
        return new ResponseEntity<>(assignmentImportanceService.addAssignmentImportance(assignmentStatus), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes an assignment importance from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id [Path variable] <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentImportance(@PathVariable int id) {
        return new ResponseEntity<>(assignmentImportanceService.deleteAssignmentImportance(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param assignmentStatus [Request body variable] <AssignmentImportanceForUpdateDto> An object that need to be
     *                         passed in order to process the operation.
     *                         Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                         ~ importanceName <String> (Name of the importance from a database)
     * @return If successfully, it returns 200 code (OK response) and an assignmentImportanceDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentImportance(@RequestBody @Valid AssignmentImportanceForUpdateDto assignmentStatus) {
        return new ResponseEntity<>(assignmentImportanceService.updateAssignmentImportance(assignmentStatus), new HttpHeaders(), HttpStatus.OK);
    }
}
