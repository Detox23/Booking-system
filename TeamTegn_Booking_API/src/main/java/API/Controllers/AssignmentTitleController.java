package API.Controllers;

import API.Services.AssignmentService.IAssignmentTitleService;
import Shared.ForCreation.AssignmentTitleForCreationDto;
import Shared.ForCreation.AssignmentTitleForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * GET request method that retrieves all assignment titles. There is possibility to display deleted records.
     * @param showDeleted <Boolean> Determines whether to display deleted records from database.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all assignment titles.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentTitles(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(assignmentTitleService.listAssignmentTitles(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds an assignment title.
     * @param id <Integer> Unique identifier of a title record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found assignmentTitleDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentTitle(@PathVariable int id) {
        return new ResponseEntity<>(assignmentTitleService.findAssignmentTitle(id), new HttpHeaders(), HttpStatus.FOUND);
    }


    /**
     * POST request method that adds an assignment title to a database. Allowed only for administrator account.
     * @param assignmentTitle <AssignmentTitleForCreationDto> An object that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ title <String> (Unique name of a title)
     * @return If successfully, it returns 201 code (CREATED response) with a created assignmentTitleDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssignmentTitle(@RequestBody @Valid AssignmentTitleForCreationDto assignmentTitle) {
        return new ResponseEntity<>(assignmentTitleService.addAssignmentTitle(assignmentTitle), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes an assignment title from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentTitle(@PathVariable int id) {
        return new ResponseEntity<>(assignmentTitleService.deleteAssignmentTitle(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param assignmentTitle <AssignmentTitleForUpdateDto> An object that need to be passed in order to process the operation.
     *                         Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                         ~ title <String> (Unique name of a title)
     * @return If successfully, it returns 200 code (OK response) and an assignmentTitleDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentTitle(@RequestBody @Valid AssignmentTitleForUpdateDto assignmentTitle) {
        return new ResponseEntity<>(assignmentTitleService.updateAssignmentTitle(assignmentTitle), new HttpHeaders(), HttpStatus.OK);
    }
}
