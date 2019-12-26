package API.Controllers;

import API.Services.ServiceUserService.IServiceUserStatusService;
import Shared.ForCreation.ServiceUserStatusForCreationDto;
import Shared.ForCreation.ServiceUserStatusForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serviceUserStatuses")
public class ServiceUserStatusController {

    private IServiceUserStatusService serviceUserStatusService;

    @Autowired
    public void setServiceUserStatusService(IServiceUserStatusService serviceUserStatusService) {
        this.serviceUserStatusService = serviceUserStatusService;
    }

    /**
     * GET request method that retrieves all statuses. There is possibility to display deleted records.
     * @param showDeleted <Boolean> Determines whether to display deleted records from database.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all statuses.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceUserStatuses(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceUserStatusService.listServiceUserStatuses(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }


    /**
     * GET request method that finds a status.
     * @param id <Integer> Unique identifier of a status record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found serviceUserStatusDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceUserStatus(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserStatusService.findServiceUserStatus(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds an service user status to a database. Allowed only for administrator account.
     * @param serviceUserStatus <ServiceUserStatusForCreationDto> An object that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ status <String> (Unique name of a status)
     * @return If successfully, it returns 201 code (CREATED response) with a created serviceUserStatusDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addServiceUserStatus(@RequestBody ServiceUserStatusForCreationDto serviceUserStatus) {
        return new ResponseEntity<>(serviceUserStatusService.addServiceUserStatus(serviceUserStatus), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes a service user status from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteServiceUserStatus(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserStatusService.deleteServiceUserStatus(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param serviceUserStatus <ServiceUserStatusForUpdateDto> An object that need to be passed in order to process the operation.
     *                         Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                         ~ status <String> (Unique name of a status)
     * @return If successfully, it returns 200 code (OK response) and a serviceUserStatusDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateServiceUserStatus(@RequestBody ServiceUserStatusForUpdateDto serviceUserStatus) {
        return new ResponseEntity<>(serviceUserStatusService.updateServiceUserStatus(serviceUserStatus), new HttpHeaders(), HttpStatus.OK);
    }
}
