/*
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

@RestController
@RequestMapping("/api/serviceUsers/comment")
public class ServiceUserCommentController {

    private IServiceUserCommentService serviceUserCommentService;

    @Autowired
    public void setService(IServiceUserCommentService serviceUserService) {
        this.serviceUserCommentService = serviceUserService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<?> list(@PathVariable int serviceUserId)  {
        return new ResponseEntity<>(serviceUserCommentService.findServiceUserComments(serviceUserId), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/{commentId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int serviceUserId, @PathVariable int commentId ){
        return new ResponseEntity<>(serviceUserCommentService.delete(serviceUserId, commentId), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrieves one
    @RequestMapping(value= "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int serviceUserId, @PathVariable int id){
        return new ResponseEntity<>(serviceUserCommentService.find(serviceUserId, id), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody ServiceUserCommentForCreationDto comment, @PathVariable int serviceUserId ) {
        return new ResponseEntity<>(serviceUserCommentService.add(serviceUserId, comment), new HttpHeaders(), HttpStatus.OK);
    }


    //Updates
    @RequestMapping(value="/{id}", method= RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@PathVariable int id,@RequestBody ServiceUserCommentForUpdateDto su) {
        return new ResponseEntity<>(serviceUserCommentService.update(id, su), new HttpHeaders(), HttpStatus.OK);
    }

}
*/
