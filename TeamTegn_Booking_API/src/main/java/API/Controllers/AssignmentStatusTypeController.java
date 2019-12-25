package API.Controllers;

import API.Services.AssignmentService.IAssignmentStatusTypeService;
import Shared.ForCreation.AssignmentStatusTypeForCreationDto;
import Shared.ForCreation.AssignmentStatusTypeForUpdateDto;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
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

    /**
     * GET request method that retrieves all assignment status types. There is possibility to display deleted records.
     * @param showDeleted <Boolean> Determines whether to display deleted records from database.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all assignment status types.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAssignmentStatusTypes(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(assignmentService.listAssignmentStatusTypes(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds an assignment status type.
     * @param id <Integer> Unique identifier of an status type record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found assignmentStatusTypeDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> findAssignmentStatusType(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.findAssignmentStatusType(id), new HttpHeaders(), HttpStatus.FOUND);
    }


    /**
     * POST request method that adds an assignment status type to a database. Allowed only for administrator account.
     * @param assignmentStatusType <AssignmentStatusTypeForCreationDto> An object that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ assignmentStatusTypeName <String> (Unique name of a status type)
     *                         ~ displayOrder <Integer> (Determines place where the value is displayed)
     * @return If successfully, it returns 201 code (CREATED response) with a created assignmentStatusTypeDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAssignmentStatusType(@RequestBody @Valid AssignmentStatusTypeForCreationDto assignmentStatusType) {
        return new ResponseEntity<>(assignmentService.addAssignmentStatusType(assignmentStatusType), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes an assignment status type from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAssignmentStatusType(@PathVariable int id) {
        return new ResponseEntity<>(assignmentService.deleteAssignmentStatusType(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param assignmentStatusType <AssignmentStatusTypeForUpdateDto> An object that need to be passed in order to process the operation.
     *                         Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                         ~ assignmentStatusTypeName <String> (Unique name of a status type)
     *                         ~ displayOrder <Integer> (Determines place where the value is displayed)
     * @return If successfully, it returns 200 code (OK response) and an assignmentStatusTypeDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = {RequestMethod.PATCH})
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAssignmentStatusType(@RequestBody @Valid AssignmentStatusTypeForUpdateDto assignmentStatusType) {
        return new ResponseEntity<>(assignmentService.updateAssignmentStatusType(assignmentStatusType), new HttpHeaders(), HttpStatus.OK);
    }
}
