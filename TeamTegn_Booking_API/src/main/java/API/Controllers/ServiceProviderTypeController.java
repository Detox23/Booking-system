package API.Controllers;

import API.Services.ServiceProviderService.IServiceProviderTypeService;
import Shared.ForCreation.ServiceProviderTypeForCreationDto;
import Shared.ForCreation.ServiceProviderTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serviceProviderType")
public class ServiceProviderTypeController {

    private IServiceProviderTypeService serviceProviderTypeService;

    @Autowired
    public void setServiceProviderTypeService(IServiceProviderTypeService serviceProviderTypeService) {
        this.serviceProviderTypeService = serviceProviderTypeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(serviceProviderTypeService.listServiceProviderTypes(), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderTypeService.findServiceProviderType(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addOne(@RequestBody ServiceProviderTypeForCreationDto serviceProviderType) {
        return new ResponseEntity<>(serviceProviderTypeService.addServiceProviderType(serviceProviderType), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateOne(@RequestBody ServiceProviderTypeForUpdateDto serviceProviderType) {
        return new ResponseEntity<>(serviceProviderTypeService.updateServiceProviderType(serviceProviderType), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOne(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderTypeService.deleteServiceProviderType(id), new HttpHeaders(), HttpStatus.OK);
    }
}
