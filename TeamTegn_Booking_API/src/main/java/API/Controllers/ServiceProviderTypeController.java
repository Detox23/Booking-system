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

    /**
     * GET request method that retrieves all types. There is possibility to display deleted records.
     * @param showDeleted [Path variable] <Boolean> Determines whether to display deleted records from database.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all types.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceProviderTypes(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceProviderTypeService.listServiceProviderTypes(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds a type.
     * @param id [Path variable] <Integer> Unique identifier of a title record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found serviceProviderTypeDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderType(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderTypeService.findServiceProviderType(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds an service provider type to a database. Allowed only for administrator account.
     * @param serviceProviderType [Request body variable] <ServiceProviderTypeForCreationDto> An object that is needed to
     *                           complete the operation. Object's required fields;
     *                           ~ providerType <String> (Unique name of a type)
     * @return If successfully, it returns 201 code (CREATED response) with a created serviceProviderTypeDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addServiceProviderType(@RequestBody ServiceProviderTypeForCreationDto serviceProviderType) {
        return new ResponseEntity<>(serviceProviderTypeService.addServiceProviderType(serviceProviderType), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes a service provider type from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id [Path variable] <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteServiceProviderType(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderTypeService.deleteServiceProviderType(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param serviceProviderType [Request body variable] <ServiceProviderTypeForUpdateDto> An object that need to be
     *                           passed in order to process the operation. Required object's fields:
     *                           ~ id <Integer> (Unique identifier of the record in the database)
     *                           ~ providerType <String> (Unique name of a type)
     * @return If successfully, it returns 200 code (OK response) and a serviceProviderTypeDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateServiceProviderType(@RequestBody ServiceProviderTypeForUpdateDto serviceProviderType) {
        return new ResponseEntity<>(serviceProviderTypeService.updateServiceProviderType(serviceProviderType), new HttpHeaders(), HttpStatus.OK);
    }
}
