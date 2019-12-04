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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addServiceProviderNotification(@RequestBody ServiceProviderPreferredNotificationForCreationDto serviceProviderNotification) {
        return new ResponseEntity<>(serviceProviderPreferredNotificationService.addServiceProviderNotification(serviceProviderNotification), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteServiceProviderNotification(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderPreferredNotificationService.deleteServiceProviderNotification(id), new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderNotification(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderPreferredNotificationService.findServiceProviderNotification(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceProviderNotification(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceProviderPreferredNotificationService.listServiceProviderNotifications(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateServiceProviderNotification(@RequestBody ServiceProviderPreferredNotificationForUpdateDto serviceProviderNotification) {
        return new ResponseEntity<>(serviceProviderPreferredNotificationService.updateServiceProviderNotification(serviceProviderNotification), new HttpHeaders(), HttpStatus.ACCEPTED);
    }
}
