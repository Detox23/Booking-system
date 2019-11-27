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


@RestController
@RequestMapping("/api/serviceProviders")
public class ServiceProviderController extends BaseController {

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


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(serviceProviderService.list(), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderService.findServiceProvider(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addOne(@RequestBody ServiceProviderForCreationDto serviceProvider) {
        return new ResponseEntity<>(serviceProviderService.addServiceProvider(serviceProvider), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateOne(@RequestBody ServiceProviderForUpdateDto serviceProvider) {
        return new ResponseEntity<>(serviceProviderService.updateServiceProvider(serviceProvider), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOne(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderService.deleteServiceProvider(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderComments(@PathVariable int id){
        return new ResponseEntity<>(serviceProviderCommentService.findServiceProviderComments(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/{commentID}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderComment(@PathVariable int id, @PathVariable int commentID){
        return new ResponseEntity<>(serviceProviderCommentService.findServiceProviderComment(id, commentID), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceProviderComment(@PathVariable int id, @PathVariable int commentID){
        return new ResponseEntity<>(serviceProviderCommentService.deleteServiceProviderComment(id, commentID), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.POST)
    public ResponseEntity<?> addServiceProviderComment(@PathVariable int id, @RequestBody ServiceProviderCommentForCreationDto comment){
        comment.setServiceProviderId(id);
        return new ResponseEntity<>(serviceProviderCommentService.addServiceProviderComment(comment), new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateServiceProviderComment(@PathVariable int id, @RequestBody ServiceProviderCommentForUpdateDto comment){
        comment.setServiceProviderId(id);
        return new ResponseEntity<>(serviceProviderCommentService.updateServiceProviderComment(comment), new HttpHeaders(), HttpStatus.OK);

    }
}
