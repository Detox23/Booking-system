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


    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAllServiceProviders(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(serviceProviderService.listAllServiceProviders(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProvider(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderService.findServiceProvider(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addServiceProvider(@RequestBody @Valid ServiceProviderForCreationDto serviceProvider) {
        return new ResponseEntity<>(serviceProviderService.addServiceProvider(serviceProvider), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateServiceProvider(@RequestBody @Valid ServiceProviderForUpdateDto serviceProvider) {
        return new ResponseEntity<>(serviceProviderService.updateServiceProvider(serviceProvider), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceProvider(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderService.deleteServiceProvider(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderComments(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderCommentService.findServiceProviderComments(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{commentID}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderComment(@PathVariable int commentID) {
        return new ResponseEntity<>(serviceProviderCommentService.findServiceProviderComment(commentID), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/comment/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceProviderComment(@PathVariable int commentID) {
        return new ResponseEntity<>(serviceProviderCommentService.deleteServiceProviderComment(commentID), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.POST)
    public ResponseEntity<?> addServiceProviderComment(@PathVariable int id, @RequestBody @Valid ServiceProviderCommentForCreationDto comment) {
        comment.setServiceProviderId(id);
        return new ResponseEntity<>(serviceProviderCommentService.addServiceProviderComment(comment), new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateServiceProviderComment(@PathVariable int id, @RequestBody @Valid ServiceProviderCommentForUpdateDto comment) {
        comment.setServiceProviderId(id);
        return new ResponseEntity<>(serviceProviderCommentService.updateServiceProviderComment(comment), new HttpHeaders(), HttpStatus.OK);

    }
}
