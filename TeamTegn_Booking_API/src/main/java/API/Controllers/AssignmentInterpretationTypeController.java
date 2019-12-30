package API.Controllers;


import API.Services.AssignmentService.IAssignmentInterpretationTypeService;
import Shared.ForCreation.AssignmentInterpretationTypeForCreationDto;
import Shared.ForCreation.AssignmentInterpretationTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * The controller is responsible for managing assignment interpretation type's records from a database. There are operations
 * which allows to create, update, list and delete assignment interpretation type objects.
 */
@RestController
@RequestMapping("/api/interpretationTypes")
public class AssignmentInterpretationTypeController {

    private IAssignmentInterpretationTypeService assignmentInterpretationTypeService;

    @Autowired
    public void setAssignmentInterpretationTypeService(IAssignmentInterpretationTypeService assignmentInterpretationTypeService) {
        this.assignmentInterpretationTypeService = assignmentInterpretationTypeService;
    }

    /**
     * GET request method that retrieves all assignment interpretation types. There is possibility to display deleted records.
     * @param showDeleted [Path variable] <Boolean> Determines whether to display deleted records from database.
     *                    true -> display all records,
     *                    false -> display only not deleted records.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all assignment interpretation types.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentInterpretationTypes(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(assignmentInterpretationTypeService.listAssignmentInterpretationTypes(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds an assignment interpretation type.
     * @param id <Integer> Unique identifier of an interpretation type record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found assignmentInterpretationTypeDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAssignmentInterpretationType(@PathVariable int id) {
        return new ResponseEntity<>(assignmentInterpretationTypeService.findAssignmentInterpretationType(id), new HttpHeaders(), HttpStatus.FOUND);
    }



    /**
     * POST request method that adds an assignment interpretation type to a database. Allowed only for administrator account.
     * @param assignmentInterpretationType [Request body variable] <AssignmentInterpretationTypeForCreationDto> An object
     *                         that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ interpretationTypeName <String> (Unique name of an interpretation type)
     * @return If successfully, it returns 201 code (CREATED response) with a created assignmentInterpretationTypeDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssignmentInterpretationType(@RequestBody @Valid AssignmentInterpretationTypeForCreationDto assignmentInterpretationType) {
        return new ResponseEntity<>(assignmentInterpretationTypeService.addAssignmentInterpretationType(assignmentInterpretationType), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes an assignment interpretation type from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id [Path variable] <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentInterpretationType(@PathVariable int id) {
        return new ResponseEntity<>(assignmentInterpretationTypeService.deleteAssignmentInterpretationType(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param assignmentInterpretationType [Request body variable] <AssignmentInterpretationTypeForUpdateDto> An object
     *                         that need to be passed in order to process the operation.
     *                         Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                         ~ interpretationTypeName <String> (Unique name of an interpretation type)
     * @return If successfully, it returns 200 code (OK response) and an assignmentInterpretationTypeDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentInterpretationType(@RequestBody @Valid AssignmentInterpretationTypeForUpdateDto assignmentInterpretationType) {
        return new ResponseEntity<>(assignmentInterpretationTypeService.updateAssignmentInterpretationType(assignmentInterpretationType), new HttpHeaders(), HttpStatus.OK);
    }
}
