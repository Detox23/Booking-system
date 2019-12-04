package API.Controllers;

import API.Services.ServiceProviderService.IServiceProviderSourceService;
import Shared.ForCreation.ServiceProviderSourceForCreationDto;
import Shared.ForCreation.ServiceProviderSourceForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serviceProviderSources")
public class ServiceProviderSourceController {

    private IServiceProviderSourceService serviceProviderSourceService;

    @Autowired
    public void setServiceProviderSourceService(IServiceProviderSourceService serviceProviderSourceService) {

        this.serviceProviderSourceService = serviceProviderSourceService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(serviceProviderSourceService.listServiceProviderSources(), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderSourceService.findServiceProviderSource(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addOne(@RequestBody ServiceProviderSourceForCreationDto serviceProviderSource) {
        return new ResponseEntity<>(serviceProviderSourceService.addServiceProviderSource(serviceProviderSource), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateOne(@RequestBody ServiceProviderSourceForUpdateDto serviceProviderSource) {
        return new ResponseEntity<>(serviceProviderSourceService.updateServiceProviderSource(serviceProviderSource), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteOne(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderSourceService.deleteServiceProviderSource(id), new HttpHeaders(), HttpStatus.OK);
    }
}
