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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> list(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize,
                                  @RequestParam(defaultValue = "id") String sortBy,
                                  @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection) {
        return new ResponseEntity<>(serviceUserService.listServiceUsers(PageRequest.of(pageNumber, pageSize, sortDirection, sortBy)), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserService.deleteServiceUser(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrieves one account
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserService.findServiceUser(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody @Valid ServiceUserForCreationDto serviceUser) {
        return new ResponseEntity<>(serviceUserService.addServiceUser(serviceUser), new HttpHeaders(), HttpStatus.OK);
    }


    //Updates an account
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@RequestBody @Valid ServiceUserForUpdateDto serviceUser) {
        return new ResponseEntity<>(serviceUserService.updateServiceUser(serviceUser), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.GET)
    public ResponseEntity<?> listServiceUserComments(@PathVariable int id) {
        return new ResponseEntity<>(serviceUserCommentService.listServiceUserComments(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{commentID}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceUserComment(@PathVariable int commentID) {
        return new ResponseEntity<>(serviceUserCommentService.findServiceUserComment(commentID), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceUserComment(@PathVariable int commentID) {
        return new ResponseEntity<>(serviceUserCommentService.deleteServiceUserComment(commentID), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.POST)
    public ResponseEntity<?> addServiceUserComment(@PathVariable int id, @RequestBody @Valid ServiceUserCommentForCreationDto comment) {
        comment.setServiceUserId(id);
        return new ResponseEntity<>(serviceUserCommentService.addServiceUserComment(comment), new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateServiceUserComment(@PathVariable int id, @RequestBody @Valid ServiceUserCommentForUpdateDto comment) {
        comment.setServiceUserId(id);
        return new ResponseEntity<>(serviceUserCommentService.updateServiceUserComment(comment), new HttpHeaders(), HttpStatus.OK);
    }


}

