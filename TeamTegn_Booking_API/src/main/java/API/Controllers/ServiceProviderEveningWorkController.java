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

    /**
     * PATCH and POST request method that either updates or adds service provider evening work information
     * @param serviceProviderEveningWorkForCreationDto [Request body variable] <ServiceProviderEveningWorkForUpdateDto>
     *                                                 Object that is essential for completing the operation. Required fields:
     *                                                 ~ id (Unique identifier of the object)
     *                                                 ~ serviceProviderId (Id of a service provider)
     *                                                 ~ weekDay (Name of the week day)
     *                                                 ~ eveningWorkPrioritisationId (Prioritisation of working on a specific day)
     * @return If successfully, it returns code 201 (CREATED response) together with created or updated serviceProviderEveningWorkDto
     * object. Otherwise, error with appreciate message.
     */
    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<?> addOrUpdateServiceProviderEveningWork(@RequestBody ServiceProviderEveningWorkForUpdateDto serviceProviderEveningWorkForCreationDto) {
        return new ResponseEntity<>(serviceProviderEveningWorkService.addOrUpdateServiceProviderEveningWork(serviceProviderEveningWorkForCreationDto), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * GET request method that finds service provider's evening works prioritisation.
     * @param id [Path variable] <Integer> Unique identifier of the service provider.
     * @return If successfully, it returns code 302 (FOUND response) with a list of service provider's evening work prioritisation.
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> getServiceProviderEveningWork(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderEveningWorkService.listServiceProviderEveningWork(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds evening work prioritisation for a specific day and service provider
     * @param day [Path variable] <String> Name of a day.
     * @param id [Path variable] <Integer> Unique identifier of a service provider.
     * @return If successfully, it returns code 302 (FOUND response) with found service provider's evening work prioritisation.
     */
    @RequestMapping(value = "/{day}/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> getServiceProviderEveningWorkForSpecificDay(@PathVariable String day, @PathVariable int id) {
        return new ResponseEntity<>(serviceProviderEveningWorkService.getServiceProviderEveningWorkForSpecificDay(day, id), new HttpHeaders(), HttpStatus.FOUND);
    }
}
