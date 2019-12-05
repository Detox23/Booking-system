package API.Controllers;

import API.Services.ServiceProviderService.IServiceProviderCompetencyService;
import Shared.ForCreation.ServiceProviderCompetencyForCreationDto;
import Shared.ForCreation.ServiceProviderCompetencyForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/competencies")
public class ServiceProviderCompetencyController {

    private IServiceProviderCompetencyService serviceProviderCompetencyService;

    @Autowired
    public void setServiceProviderCompetencyService(IServiceProviderCompetencyService serviceProviderCompetencyService) {
        this.serviceProviderCompetencyService = serviceProviderCompetencyService;
    }

    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceProviderCompetencies(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceProviderCompetencyService.listServiceProviderCompetencies(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderCompetency(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderCompetencyService.findServiceProviderCompetency(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addServiceProviderCompetency(@RequestBody ServiceProviderCompetencyForCreationDto serviceProviderCompetency) {
        return new ResponseEntity<>(serviceProviderCompetencyService.addServiceProviderCompetency(serviceProviderCompetency), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateServiceProviderCompetency(@RequestBody ServiceProviderCompetencyForUpdateDto serviceProviderCompetency) {
        return new ResponseEntity<>(serviceProviderCompetencyService.updateServiceProviderCompetency(serviceProviderCompetency), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteServiceProviderCompetency(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderCompetencyService.deleteServiceProviderCompetency(id), new HttpHeaders(), HttpStatus.OK);
    }

}
