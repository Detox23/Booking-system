package API.Controllers;

import API.Services.ServiceUserService.IServiceUserCommentService;
import API.Services.ServiceUserService.IServiceUserService;
import Shared.ForCreation.ServiceUserCommentForCreationDto;
import Shared.ForCreation.ServiceUserCommentForUpdateDto;
import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/serviceUsers")
public class ServiceUserController {

    private IServiceUserService serviceUserService;

    private IServiceUserCommentService serviceUserCommentService;

    @Autowired
    public void setServiceUserCommentService(IServiceUserCommentService serviceUserCommentService) {
        this.serviceUserCommentService = serviceUserCommentService;
    }

    @Autowired
    public void setService(IServiceUserService serviceUserService) {
        this.serviceUserService = serviceUserService;
    }

    /**
     * GET request method that retrieves a page of service users.
     *
     * @param pageNumber    [Request parameter] <String> (Number of page)
     * @param pageSize      [Request parameter] <String> (Size of a page)
     * @param sortBy        [Request parameter] <String> (Determines what is the factor the result is sorted by)
     * @param sortDirection [Request parameter] <String> (Ascending or descending sorting)
     * @return If successfully, it returns code 302 (FOUND response) with a resulting page containing serviceUserDto objects.
     * Otherwise error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceUsers(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize,
                                              @RequestParam(defaultValue = "id") String sortBy,
                                              @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection) {
        return new ResponseEntity<>(serviceUserService.listServiceUsers(PageRequest.of(pageNumber, pageSize, sortDirection, sortBy)), new HttpHeaders(), HttpStatus.FOUND);
    }


    /**
     * GET request method that finds specific service user.
     *
     * @param id [Path variable] <Integer> Unique identifier of a service user.
     * @return If successfully, it returns code 302 (FOUND request) with a found serviceUserDto object filled with found
     * information. Otherwise error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> findServiceUser(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserService.findServiceUser(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds service user to a database.
     *
     * @param serviceUser [Request body variable] <ServiceUserForCreationDto> Required object of service user, that is
     *                    needed to complete the operation. Required fields:
     *                    ~ firstName <String> (First name of a user)
     *                    ~ lastName <String> (Last name of a user)
     *                    ~ cpr <String> (User's cpr number)
     *                    ~ street <String> (Street address of a user house)
     *                    ~ postcode <String> (User's city postcode)
     *                    ~ departmentId <Integer> (Id of the department, the user belongs to)
     *                    ~ serviceUserStatusId <Integer> (Id of a user's status)
     * @return If successfully, returns code 201 (CREATED response) together with ServiceUserDto object with added information.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addServiceUser(@RequestBody @Valid ServiceUserForCreationDto serviceUser) {
        return new ResponseEntity<>(serviceUserService.addServiceUser(serviceUser), new HttpHeaders(), HttpStatus.CREATED
        );
    }

    /**
     * DELETE request method that removes a record from a database. In fact the record's isDeleted value is changed.
     *
     * @param id [Path variable] <Integer> Unique identifier of a service user.
     * @return If successfully, it returns code 200 (OK response) and true value. Otherwise might return false or error
     * with a message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceUser(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserService.deleteServiceUser(id), new HttpHeaders(), HttpStatus.OK);
    }


    /**
     * PATCH request method that updates an existing record in a database.
     *
     * @param serviceUser [Request body variable] <ServiceUserForUpdateDto> An object that need to be passed in order
     *                    to complete the operation. Required fields of the object:
     *                    ~ id <Integer> (Unique identifier of the record)
     *                    ~ firstName <String> (First name of a user)
     *                    ~ lastName <String> (Last name of a user)
     *                    ~ cpr <String> (User's cpr number)
     *                    ~ street <String> (Street address of a user house)
     *                    ~ postcode <String> (User's city postcode)
     *                    ~ departmentId <Integer> (Id of the department, the user belongs to)
     *                    ~ serviceUserStatusId <Integer> (Id of a user's status)
     * @return If successfully, it returns code 200 (OK response) with a ServiceUserDto object with updated information.
     * Otherwise, error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateServiceUser(@RequestBody @Valid ServiceUserForUpdateDto serviceUser) {
        return new ResponseEntity<>(serviceUserService.updateServiceUser(serviceUser), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * GET request method that retrieves list of specified service user's comments.
     *
     * @param id [Path variable] <Integer> Unique identifier of a service user record from a database.
     * @return If successfully, it returns code 302 (FOUND response) with a list of ServiceUserCommentDto objects.
     * Otherwise, error with appreciate message.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceUserComments(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserCommentService.listServiceUserComments(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds a service user's comment.
     *
     * @param commentID [Path variable] <Integer> Unique identifier of a comment record from a database.
     * @return If successfully, it returns code 302 (FOUND response) with found ServiceUserCommentDto object. Otherwise,
     * error with appreciate message.
     */
    @RequestMapping(value = "/comment/{commentID}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceUserComment(@PathVariable int commentID) {
        return new ResponseEntity<>(serviceUserCommentService.findServiceUserComment(commentID), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * DELETE request method removes comment from a database.
     *
     * @param commentID [Path variable] <Integer> Unique identifier of a comment record from a database.
     * @return If successfully, it returns code 200 (OK response) with true value. Otherwise false value is returned or
     * error with appreciate message.
     */
    @RequestMapping(value = "/comment/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceUserComment(@PathVariable int commentID) {
        return new ResponseEntity<>(serviceUserCommentService.deleteServiceUserComment(commentID), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * POST request method, that adds service provider's comment to a database.
     *
     * @param id      [Path variable] <Integer> Unique identifier of a service user.
     * @param comment [Request body variable] <ServiceUserCommentForCreationDto> An object that is needed to complete
     *                the operation. Required object's fields:
     *                ~ commentText <String> (Content of a comment)
     * @return If successfully, return code 302 (CREATED response) with ServiceUserCommentDto object with fields filled with
     * added information. Otherwise, error with appreciate message.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.POST)
    public ResponseEntity<?> addServiceUserComment(@PathVariable int id, @RequestBody @Valid ServiceUserCommentForCreationDto comment) {
        comment.setServiceUserId(id);
        return new ResponseEntity<>(serviceUserCommentService.addServiceUserComment(comment), new HttpHeaders(), HttpStatus.CREATED);

    }

    /**
     * PATCH request method that updates existing record in a database.
     *
     * @param id      [Path variable] <Integer> Unique identifier of a service user.
     * @param comment [Request body variable] <ServiceUserCommentForCreationDto> An object that is needed to complete
     *                the operation. Required object's fields:
     *                ~ id <Integer> (Unique identifier of a comment record from a database)
     *                ~ commentText <String> (Content of a comment)
     * @return If successfully, return code 200 (OK response) with ServiceUserCommentDto object with fields filled with
     * updated information. Otherwise, error with appreciate message.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateServiceUserComment(@PathVariable int id, @RequestBody @Valid ServiceUserCommentForUpdateDto comment) {
        comment.setServiceUserId(id);
        return new ResponseEntity<>(serviceUserCommentService.updateServiceUserComment(comment), new HttpHeaders(), HttpStatus.OK);
    }


}

