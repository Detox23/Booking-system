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

    /**
     * GET request method that retrieves all assignment statuses. There is possibility to display deleted records.
     * @param showDeleted [Path variable] <Boolean> Determines whether to display deleted records from database.
     *                    true -> display all records,
     *                    false -> display only not deleted records.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all assignment statuses.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentStatuses(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(assignmentService.listAssignmentStatuses(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds an assignment status.
     * @param id [Path variable] <Integer> Unique identifier of an status record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found assignmentStatusDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentStatus(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.findAssignmentStatus(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds an assignment status to a database. Allowed only for administrator account.
     * @param assignmentStatus [Request body variable] <AssignmentStatusForCreationDto> An object that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ assignmentStatusName <String> (Unique name of a status)
     * @return If successfully, it returns 201 code (CREATED response) with a created assignmentStatusDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssignmentStatus(@RequestBody @Valid AssignmentStatusForCreationDto assignmentStatus) {
        return new ResponseEntity<>(assignmentService.addAssignmentStatus(assignmentStatus), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes an assignment status from a database. In fact it just changes one of the record's
     * values. Allowed only for administrator account.
     * @param id <Integer> [Path variable] Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentStatus(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.deleteAssignmentStatus(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param assignmentStatus [Request body variable] <AssignmentStatusForUpdateDto> An object that need to be passed
     *                        in order to process the operation. Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                         ~ assignmentStatusName <String> (Unique name of a status)
     * @return If successfully, it returns 200 code (OK response) and an assignmentStatusDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentStatus(@RequestBody @Valid AssignmentStatusForUpdateDto assignmentStatus) {
        return new ResponseEntity<>(assignmentService.updateAssignmentStatus(assignmentStatus), new HttpHeaders(), HttpStatus.OK);
    }
}

