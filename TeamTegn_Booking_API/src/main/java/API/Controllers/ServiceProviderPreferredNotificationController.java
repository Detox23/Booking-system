package API.Controllers;


import API.Services.ServiceProviderService.IServiceProviderPreferredNotificationService;
import Shared.ForCreation.ServiceProviderPreferredNotificationForCreationDto;
import Shared.ForCreation.ServiceProviderPreferredNotificationForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serviceProviderNotifications")
public class ServiceProviderPreferredNotificationController {

    private IServiceProviderPreferredNotificationService serviceProviderPreferredNotificationService;

    @Autowired
    public void setServiceProviderPreferredNotificationService(IServiceProviderPreferredNotificationService serviceProviderPreferredNotificationService) {
        this.serviceProviderPreferredNotificationService = serviceProviderPreferredNotificationService;
    }

    /**
     * GET request method that retrieves all notifications. There is possibility to display deleted records.
     * @param showDeleted [Path variable] <Boolean> Determines whether to display deleted records from database.
     *                    true -> display all records,
     *                    false -> display only not deleted records
     * @return If successfully, it returns 302 code (FOUND response) with a list of all notifications.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceProviderNotification(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceProviderPreferredNotificationService.listServiceProviderNotifications(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds a notification.
     * @param id [Path variable] <Integer> Unique identifier of a notification record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found serviceProviderNotificationDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderNotification(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderPreferredNotificationService.findServiceProviderNotification(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds an service provider notification to a database. Allowed only for administrator account.
     * @param serviceProviderNotification [Request body variable] <ServiceProviderPreferredNotificationForCreationDto>
     *                                    An object that is needed to complete the operation. Object's required fields;
     *                                    ~ notificationType <String> (Unique name of a notification)
     * @return If successfully, it returns 201 code (CREATED response) with a created serviceProviderNotificationDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addServiceProviderNotification(@RequestBody ServiceProviderPreferredNotificationForCreationDto serviceProviderNotification) {
        return new ResponseEntity<>(serviceProviderPreferredNotificationService.addServiceProviderNotification(serviceProviderNotification), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes a service provider notification from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id [Path variable] <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteServiceProviderNotification(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderPreferredNotificationService.deleteServiceProviderNotification(id), new HttpHeaders(), HttpStatus.OK);
    }


    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param serviceProviderNotification [Request body variable] <ServiceProviderPreferredNotificationForUpdateDto>
     *                                   An object that need to be passed in order to process the operation.
     *                                   Required object's fields:
     *                                   ~ id <Integer> (Unique identifier of the record in the database)
     *                                   ~ notificationType <String> (Unique name of a notification)
     * @return If successfully, it returns 200 code (OK response) and a serviceProviderNotificationDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateServiceProviderNotification(@RequestBody ServiceProviderPreferredNotificationForUpdateDto serviceProviderNotification) {
        return new ResponseEntity<>(serviceProviderPreferredNotificationService.updateServiceProviderNotification(serviceProviderNotification), new HttpHeaders(), HttpStatus.OK);
    }
}
