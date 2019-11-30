package API.Controllers;


import API.Services.ServiceProviderService.IServiceProviderEveningWorkService;
import Shared.ForCreation.ServiceProviderEveningWorkForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serviceProviderEveningWorks")
public class ServiceProviderEveningWorkController {

    private IServiceProviderEveningWorkService serviceProviderEveningWorkService;

    @Autowired
    public void setServiceProviderEveningWorkService(IServiceProviderEveningWorkService serviceProviderEveningWorkService) {
        this.serviceProviderEveningWorkService = serviceProviderEveningWorkService;
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<?> addOrUpdateServiceProviderEveningWork(@RequestBody ServiceProviderEveningWorkForUpdateDto serviceProviderEveningWorkForCreationDto) {
        return new ResponseEntity<>(serviceProviderEveningWorkService.addOrUpdateServiceProviderEveningWork(serviceProviderEveningWorkForCreationDto), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value= "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> getServiceProviderEveningWork(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderEveningWorkService.listServiceProviderEveningWork(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value= "/{day}/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> getServiceProviderEveningWork(@PathVariable String day, @PathVariable int id) {
        return new ResponseEntity<>(serviceProviderEveningWorkService.getServiceProviderEveningWorkForSpecificDay(day, id), new HttpHeaders(), HttpStatus.FOUND);
    }
}
