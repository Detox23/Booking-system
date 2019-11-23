package API.Controllers;

import API.Services.ServiceProviderService.IServiceProviderService;
import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.ServiceProviderForUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/serviceProvider")
public class ServiceProviderController extends BaseController {

    private IServiceProviderService serviceProviderService;

    @Autowired
    public void setServiceProviderService(IServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(serviceProviderService.list(), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderService.findServiceProvider(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addOne(@RequestBody ServiceProviderForCreationDto serviceProvider) {
        return new ResponseEntity<>(serviceProviderService.addServiceProvider(serviceProvider), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateOne(@RequestBody ServiceProviderForUpdate serviceProvider) {
        return new ResponseEntity<>(serviceProviderService.updateServiceProvider(serviceProvider), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOne(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderService.deleteServiceProvider(id), new HttpHeaders(), HttpStatus.OK);
    }


}
