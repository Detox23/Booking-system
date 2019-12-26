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

    /**
     * GET request method that retrieves all competencies. There is possibility to display deleted records.
     * @param showDeleted <Boolean> Determines whether to display deleted records from database.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all competencies.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceProviderCompetencies(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceProviderCompetencyService.listServiceProviderCompetencies(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds a competency.
     * @param id <Integer> Unique identifier of a title record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found serviceProviderCompetencyDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderCompetency(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderCompetencyService.findServiceProviderCompetency(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds an service provider competency to a database. Allowed only for administrator account.
     * @param serviceProviderCompetency <ServiceProviderCompetencyForCreationDto> An object that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ competency <String> (Unique name of a competency)
     * @return If successfully, it returns 201 code (CREATED response) with a created serviceProviderCompetencyDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addServiceProviderCompetency(@RequestBody ServiceProviderCompetencyForCreationDto serviceProviderCompetency) {
        return new ResponseEntity<>(serviceProviderCompetencyService.addServiceProviderCompetency(serviceProviderCompetency), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes a service provider competency from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteServiceProviderCompetency(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderCompetencyService.deleteServiceProviderCompetency(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param serviceProviderCompetency <ServiceProviderCompetencyForUpdateDto> An object that need to be passed in order to process the operation.
     *                         Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                         ~ competency <String> (Unique name of a competency)
     * @return If successfully, it returns 200 code (OK response) and a serviceProviderCompetencyDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateServiceProviderCompetency(@RequestBody ServiceProviderCompetencyForUpdateDto serviceProviderCompetency) {
        return new ResponseEntity<>(serviceProviderCompetencyService.updateServiceProviderCompetency(serviceProviderCompetency), new HttpHeaders(), HttpStatus.OK);
    }

}
