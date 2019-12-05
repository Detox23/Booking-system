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

    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceUserStatuses(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceUserStatusService.listServiceUserStatuses(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserStatusService.findServiceUserStatus(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addOne(@RequestBody ServiceUserStatusForCreationDto serviceUserStatus) {
        return new ResponseEntity<>(serviceUserStatusService.addServiceUserStatus(serviceUserStatus), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateOne(@RequestBody ServiceUserStatusForUpdateDto serviceUserStatus) {
        return new ResponseEntity<>(serviceUserStatusService.updateServiceUserStatus(serviceUserStatus), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteOne(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserStatusService.deleteServiceUserStatus(id), new HttpHeaders(), HttpStatus.OK);
    }
}
