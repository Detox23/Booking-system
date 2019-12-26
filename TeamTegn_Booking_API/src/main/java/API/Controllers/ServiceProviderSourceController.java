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

    /**
     * GET request method that retrieves all sources. There is possibility to display deleted records.
     * @param showDeleted <Boolean> Determines whether to display deleted records from database.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all sources.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceProviderSources(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceProviderSourceService.listServiceProviderSources(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds a source.
     * @param id <Integer> Unique identifier of a title record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found serviceProviderSourceDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderSource(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderSourceService.findServiceProviderSource(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds an service provider source to a database. Allowed only for administrator account.
     * @param serviceProviderSource <ServiceProviderSourceForCreationDto> An object that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ providerSource <String> (Unique name of a source)
     * @return If successfully, it returns 201 code (CREATED response) with a created serviceProviderSourceDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addServiceProviderSource(@RequestBody ServiceProviderSourceForCreationDto serviceProviderSource) {
        return new ResponseEntity<>(serviceProviderSourceService.addServiceProviderSource(serviceProviderSource), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes a service provider source from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteServiceProviderSource(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderSourceService.deleteServiceProviderSource(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param serviceProviderSource <ServiceProviderSourceForUpdateDto> An object that need to be passed in order to process the operation.
     *                         Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                         ~ providerSource <String> (Unique name of a source)
     * @return If successfully, it returns 200 code (OK response) and a serviceProviderSourceDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateServiceProviderSource(@RequestBody ServiceProviderSourceForUpdateDto serviceProviderSource) {
        return new ResponseEntity<>(serviceProviderSourceService.updateServiceProviderSource(serviceProviderSource), new HttpHeaders(), HttpStatus.OK);
    }
}
