package API.Controllers;

import API.Services.AbsenceTypeService.IAbsenceTypeService;
import Shared.ForCreation.AbsenceTypeForCreationDto;
import Shared.ForCreation.AbsenceTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * The controller is responsible for managing absence types. There are operations responsible for Creating, Updating,
 * Deleting and Finding absence types.
 */
@RestController
@RequestMapping("/api/absenceType")
public class AbsenceTypeController {

    private IAbsenceTypeService absenceTypeService;

    @Autowired
    public void setAbsenceTypeService(IAbsenceTypeService absenceTypeService) {
        this.absenceTypeService = absenceTypeService;
    }

    /**
     * GET request method that retrieves a list of absence types. Depending on the choice it can also displays deleted records.
     * @param showDeleted [Path variable] <boolean> Defines whether or not display deleted values.
     *                    True -> displays all values
     *                    False -> displays only not deleted value
     * @return If successfully it returns code 302 (FOUND response) with list of absence types. Otherwise appreciate error
     * response.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAbsenceTypes(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(absenceTypeService.listAbsenceTypes(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that retrieves an absence type object by an id.
     * @param id [Path variable] <Integer> Unique identifier of an absence type in a database.
     * @return If found, it returns code 302 (FOUND response) with a found absence type object. Otherwise appreciate error message.
     * response.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAbsenceType(@PathVariable int id) {
        return new ResponseEntity<>(absenceTypeService.findAbsenceType(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds an absence type with unique absenceTypeName to a database.
     * Only available for Administrator Role
     * @param absenceType [Body variable] <AbsenceTypeForCreationDto> Required object that need to be passed to complete the operation.
     *                   Required fields:
     *                    ~ absenceTypeName <String> Unique name of an absence type.
     * @return If successfully it returns code 201 (CREATED response) and created AbsenceTypeDto object with filled fields.
     * Otherwise, appreciate error message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAbsenceType(@RequestBody AbsenceTypeForCreationDto absenceType) {
        return new ResponseEntity<>(absenceTypeService.addAbsenceType(absenceType), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that deletes an absence type from a database by its id. In fact it just sets its property isDeleted to true.
     * Only available for Administrator Role.
     * @param id [Path variable] <Integer> Unique id of the object in a database that is used for deletion.
     * @return  If successfully, it returns code 200 (OK response) and true value. Otherwise false or appreciate error
     * message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAbsenceType(@PathVariable int id) {
        return new ResponseEntity<>(absenceTypeService.deleteAbsenceType(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates absence type with a new values, leaving not changed values untouched. Only available
     * for Administrator Role.
     * @param absenceType [Body variable] Required object that need to be passed to complete the operation. Required fields:
     *                    ~ id <Integer> (Unique identifier of an absence type record)
     *                    ~ absenceTypeName <String> (Unique name of an absence type)
     * @return If successfully, it returns code 200 (OK response) and an object with updated fields. Otherwise
     * appreciate error message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAbsenceType(@RequestBody AbsenceTypeForUpdateDto absenceType) {
        return new ResponseEntity<>(absenceTypeService.updateAbsenceType(absenceType), new HttpHeaders(), HttpStatus.OK);
    }
}
