package API.Controllers;

import API.Services.ServiceUserService.IServiceUserPreferencesService;
import Shared.ForCreation.ServiceUserPreferencesForCreationDto;
import Shared.ForCreation.ServiceUserPreferencesForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/serviceUsers/{serviceUser}/preferences")
public class ServiceUserPreferencesController {

    private IServiceUserPreferencesService serviceUserPreferencesService;

    @Autowired
    public void setServiceUserPreferencesService(IServiceUserPreferencesService serviceUserPreferencesService) {
        this.serviceUserPreferencesService = serviceUserPreferencesService;
    }

    /**
     * GET request method that lists service users preferences.
     *
     * @param serviceUser [Path variable] <Integer> Unique identifier of a service user.
     * @return If successfully, it returns code 302 (FOUND response) and a list of found preferences of specified service user.
     * Otherwise, error with suitable message.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceUserPreferences(@PathVariable int serviceUser) {
        return new ResponseEntity<>(serviceUserPreferencesService.listServiceUserPreferences(serviceUser), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds preference between service provider and service user.
     *
     * @param serviceUser     [Path variable] <Integer> Unique identifier of a service user.
     * @param serviceProvider [Path variable] <Integer> Unique identifier of a service provider.
     * @return If successfully, it returns code 302 (FOUND response) together with a found serviceProviderPreferencesDto object
     * defining relation between these two users. Otherwise, error with suitable message.
     */
    @RequestMapping(value = "/{serviceProvider}", method = {RequestMethod.GET})
    public ResponseEntity<?> findServiceProviderAndUser(@PathVariable int serviceUser, @PathVariable int serviceProvider) {
        return new ResponseEntity<>(serviceUserPreferencesService.findServiceProviderAndUser(serviceUser, serviceProvider), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds service user preference to a database.
     *
     * @param serviceUser            [Path variable] <Integer> Unique identifier of a service user.
     * @param serviceUserPreferences [Request body variable] <ServiceUserPreferencesForCreationDto> Object that is needed to complete the operation.
     *                               Object's required fields:
     *                               ~ rating <Integer> (Grate between service user and service provider)
     *                               ~ serviceProviderId <Integer> (Unique identifier of a service provider)
     * @return If successfully, it returns code 201 (CREATED response) together with created serviceProviderPreferencesDto object
     * with filled information. Otherwise error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addServiceUserPreference(@PathVariable int serviceUser, @RequestBody @Valid ServiceUserPreferencesForCreationDto serviceUserPreferences) {
        return new ResponseEntity<>(serviceUserPreferencesService.addServiceUserPreference(serviceUser, serviceUserPreferences), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method removes service user preference from a database.
     *
     * @param id [Path variable] <Integer> Unique identifier of a service user.
     * @return If successfully, it returns code 200(OK response) and true value. Otherwise false value or error with
     * suitable message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceUserPreference(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserPreferencesService.deleteServiceUserPreference(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method updates existing service user preference in a database.
     *
     * @param serviceUserPreferences [Request body variable] <ServiceUserPreferencesForUpdateDto> Object that need to be
     *                               passed to complete the operation. Object's required fields:
     *                               ~ id <Integer> (Unique identifier of a record from a database)
     *                               ~ rating <Integer> (Grate between service user and service provider)
     * @return If successfully, it return code 200 (OK response) together with serviceUserPreferencesDto object, filled
     * with updated information. Otherwise error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateServiceUserPreference(@RequestBody @Valid ServiceUserPreferencesForUpdateDto serviceUserPreferences) {
        return new ResponseEntity<>(serviceUserPreferencesService.updateServiceUserPreference(serviceUserPreferences), new HttpHeaders(), HttpStatus.OK);
    }

}
