package API.Controllers;

import API.Services.ServiceProviderService.IServiceProviderTypeService;
import Shared.ForCreation.ServiceProviderTypeForCreationDto;
import Shared.ForCreation.ServiceProviderTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serviceProviderTypes")
public class ServiceProviderTypeController {

    private IServiceProviderTypeService serviceProviderTypeService;

    @Autowired
    public void setServiceProviderTypeService(IServiceProviderTypeService serviceProviderTypeService) {
        this.serviceProviderTypeService = serviceProviderTypeService;
    }

    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceProviderTypes(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceProviderTypeService.listServiceProviderTypes(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderType(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderTypeService.findServiceProviderType(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addServiceProviderType(@RequestBody ServiceProviderTypeForCreationDto serviceProviderType) {
        return new ResponseEntity<>(serviceProviderTypeService.addServiceProviderType(serviceProviderType), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateServiceProviderType(@RequestBody ServiceProviderTypeForUpdateDto serviceProviderType) {
        return new ResponseEntity<>(serviceProviderTypeService.updateServiceProviderType(serviceProviderType), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteServiceProviderType(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderTypeService.deleteServiceProviderType(id), new HttpHeaders(), HttpStatus.OK);
    }
}
