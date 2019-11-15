package API.Controllers;

import API.Services.ServiceProviderService.IServiceProviderCompetencyService;
import Shared.ForCreation.ServiceProviderCompetencyForCreationDto;
import Shared.ForCreation.ServiceProviderCompetencyForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competency")
public class ServiceProviderCompetencyController extends BaseController {

    private IServiceProviderCompetencyService serviceProviderCompetencyService;

    @Autowired
    public void setServiceProviderCompetencyService(IServiceProviderCompetencyService serviceProviderCompetencyService) {
        this.serviceProviderCompetencyService = serviceProviderCompetencyService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllCompetencies() {
        return new ResponseEntity<>(serviceProviderCompetencyService.listAllCompetencies(), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneCompetency(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderCompetencyService.getOneCompetency(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addOneCompetency(@RequestBody ServiceProviderCompetencyForCreationDto serviceProviderCompetency) {
        return new ResponseEntity<>(serviceProviderCompetencyService.addOneCompetency(serviceProviderCompetency), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateOneCompetency(@RequestBody ServiceProviderCompetencyForUpdateDto serviceProviderCompetency) {
        return new ResponseEntity<>(serviceProviderCompetencyService.updateOneCompetency(serviceProviderCompetency), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOneCompetency(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderCompetencyService.deleteOneCompetency(id), new HttpHeaders(), HttpStatus.OK);
    }

}
