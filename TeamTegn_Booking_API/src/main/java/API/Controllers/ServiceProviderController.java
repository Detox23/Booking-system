package API.Controllers;

import API.Services.ServiceProviderService.IServiceProviderCommentService;
import API.Services.ServiceProviderService.IServiceProviderService;
import Shared.ForCreation.ServiceProviderCommentForCreationDto;
import Shared.ForCreation.ServiceProviderCommentForUpdateDto;
import Shared.ForCreation.ServiceProviderForCreationDto;
import Shared.ForCreation.ServiceProviderForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/serviceProviders")
public class ServiceProviderController {

    private IServiceProviderService serviceProviderService;

    private IServiceProviderCommentService serviceProviderCommentService;

    @Autowired
    public void setServiceProviderCommentService(IServiceProviderCommentService serviceProviderCommentService) {
        this.serviceProviderCommentService = serviceProviderCommentService;
    }

    @Autowired
    public void setServiceProviderService(IServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }

    /**
     * GET request method that retrieves all service providers. There is possibility to display deleted records.
     *
     * @param showDeleted <Boolean> Determines whether to display deleted records from database.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all service providers.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAllServiceProviders(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceProviderService.listAllServiceProviders(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds a service provider.
     *
     * @param id <Integer> Unique identifier of a service provider record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found serviceProviderDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProvider(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderService.findServiceProvider(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds an service provider to a database.
     *
     * @param serviceProvider <ServiceProviderForCreationDto> An object that is needed to complete the operation.
     *                        Object's required fields;
     *                        ~ firstName <String> (First name of a service provider)
     *                        ~ lastName <String> (Last name of a service provider)
     *                        ~ cpr <String> (Service provider cpr number)
     *                        ~ source <Integer> (Id of the service provider's source)
     *                        ~ departmentId <Integer> (Id of the department where the service provider belongs)
     *                        ~ preferredNotificationId <Integer> (Id of the preferred notifications)
     *                        ~ transportId <Integer> (Id of the service provider's kind of transport)
     * @return If successfully, it returns 201 code (CREATED response) with a created serviceProviderDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addServiceProvider(@RequestBody @Valid ServiceProviderForCreationDto serviceProvider) {
        return new ResponseEntity<>(serviceProviderService.addServiceProvider(serviceProvider), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes a service provider competency from a database. In fact it just change one of the record's
     * values.
     *
     * @param id <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceProvider(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderService.deleteServiceProvider(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database.
     * @param serviceProvider <ServiceProviderForUpdateDto> An object that need to be passed in order to process the operation.
     *                         Required object's fields:
     *                         ~ id <Integer> (Unique identifier of the record in the database)
     *                        ~ firstName <String> (First name of a service provider)
     *                        ~ lastName <String> (Last name of a service provider)
     *                        ~ cpr <String> (Service provider cpr number)
     *                        ~ source <Integer> (Id of the service provider's source)
     *                        ~ departmentId <Integer> (Id of the department where the service provider belongs)
     *                        ~ preferredNotificationId <Integer> (Id of the preferred notifications)
     *                        ~ transportId <Integer> (Id of the service provider's kind of transport)
     * @return If successfully, it returns 200 code (OK response) and a serviceProviderDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateServiceProvider(@RequestBody @Valid ServiceProviderForUpdateDto serviceProvider) {
        return new ResponseEntity<>(serviceProviderService.updateServiceProvider(serviceProvider), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * GET request method that finds all comments for a specified service provider.
     *
     * @param id <Integer> Unique identifier of a service provider.
     * @return If successfully, it returns code 302 (FOUND response), together with a list of comments for the specified
     * service provider. Otherwise, error with appreciate message.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderComments(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderCommentService.findServiceProviderComments(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds a service provider comment.
     * @param commentID  <Integer> Unique identifier of a comment.
     * @return If successfully, it returns code 302 (FOUND response) together with a found serviceProviderCommentDto
     * object with filled information. Otherwise, error with appreciate message.
     */
    @RequestMapping(value = "/comment/{commentID}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderComment(@PathVariable int commentID) {
        return new ResponseEntity<>(serviceProviderCommentService.findServiceProviderComment(commentID), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds comment for a service provider.
     *
     * @param id <Integer> Unique identifier of a service provider.
     * @param comment <ServiceProviderCommentForCreationDto> Comment object for creation that need to be passed to
     *                complete the operation. Object's required fields:
     *                ~ serviceProviderId <Integer> (Unique identifier of a service provider)
     *                ~ commentText <String> (Text of a comment that will be added)
     * @return If successfully, it returns code 201 (CREATED response) with filled serviceProviderCommentDto object of
     * added information. Otherwise error with appreciate message.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.POST)
    public ResponseEntity<?> addServiceProviderComment(@PathVariable int id, @RequestBody @Valid ServiceProviderCommentForCreationDto comment) {
        comment.setServiceProviderId(id);
        return new ResponseEntity<>(serviceProviderCommentService.addServiceProviderComment(comment), new HttpHeaders(), HttpStatus.CREATED);

    }

    /**
     * DELETE request method that removes service provider comment from a database.
     *
     * @param commentID <Integer> Unique identifier of a comment record from a database.
     * @return If successfully, it return code 200 (OK response) with true value. Otherwise false value or error with
     * appreciate message.
     */
    @RequestMapping(value = "/comment/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceProviderComment(@PathVariable int commentID) {
        return new ResponseEntity<>(serviceProviderCommentService.deleteServiceProviderComment(commentID), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing records in a database.
     * @param id <Integer> Unique identifier of a service provider in a database.
     * @param comment <ServiceProviderCommentForUpdateDto> Comment object used to update existing values. Object's
     *                required fields:
     *                ~ id <Integer> (Unique identifier of a comment object)
     *                ~ serviceProviderId <Integer> (Unique identifier of a service provider)
     *                ~ commentText <String> (Text of a comment that will be added)
     * @return If successfully, it returns code 200 (OK response) together with serviceProviderCommentDto object filled
     * with updated information. Otherwise error with appreciate message.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateServiceProviderComment(@PathVariable int id, @RequestBody @Valid ServiceProviderCommentForUpdateDto comment) {
        comment.setServiceProviderId(id);
        return new ResponseEntity<>(serviceProviderCommentService.updateServiceProviderComment(comment), new HttpHeaders(), HttpStatus.OK);

    }
}
