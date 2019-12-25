package API.Controllers;

import API.Services.AssignmentService.IAssignmentTypeService;
import Shared.ForCreation.AssignmentTypeForCreationDto;
import Shared.ForCreation.AssignmentTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/assignmentTypes")
public class AssignmentTypeController {

    private IAssignmentTypeService assignmentTypeService;

    @Autowired
    public void setAssignmentTypeService(IAssignmentTypeService assignmentTypeService) {
        this.assignmentTypeService = assignmentTypeService;
    }

    /**
     * GET request method that retrieves all assignment types. There is possibility to display deleted records.
     * @param showDeleted <Boolean> Determines whether to display deleted records from database.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all assignment types.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = {RequestMethod.GET})
    public ResponseEntity<?> listAssignmentTypes(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(assignmentTypeService.listAssignmentTypes(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds an assignment type.
     * @param id <Integer> Unique identifier of a type record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found assignmentTypeDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> findAssignmentType(@PathVariable int id) {
        return new ResponseEntity<>(assignmentTypeService.findAssignmentType(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds an assignment type to a database. Allowed only for administrator account.
     * @param assignmentType <AssignmentTypeForCreationDto> An object that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ assignmentTypeName <String> (Unique name of a type)
     * @return If successfully, it returns 201 code (CREATED response) with a created assignmentTypeDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = {RequestMethod.POST})
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssignmentType(@RequestBody @Valid AssignmentTypeForCreationDto assignmentType) {
        return new ResponseEntity<>(assignmentTypeService.addAssignmentType(assignmentType), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes an assignment type from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentType(@PathVariable int id) {
        return new ResponseEntity<>(assignmentTypeService.deleteAssignmentType(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param assignmentType <AssignmentTypeForUpdateDto> An object that need to be passed in order to process the operation.
     *                         Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                         ~ assignmentTypeName <String> (Unique name of a type)
     * @return If successfully, it returns 200 code (OK response) and an assignmentTypeDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = {RequestMethod.PATCH})
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentType(@RequestBody @Valid AssignmentTypeForUpdateDto assignmentType) {
        return new ResponseEntity<>(assignmentTypeService.updateAssignmentType(assignmentType), new HttpHeaders(), HttpStatus.OK);
    }
}
