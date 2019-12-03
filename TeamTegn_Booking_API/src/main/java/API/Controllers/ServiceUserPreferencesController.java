package API.Controllers;

import API.Services.ServiceUserService.IServiceUserCommentService;
import API.Services.ServiceUserService.IServiceUserPreferenecesService;
import Shared.ForCreation.ServiceUserCommentForCreationDto;
import Shared.ForCreation.ServiceUserCommentForUpdateDto;
import Shared.ForCreation.ServiceUserPreferencesForCreationDto;
import Shared.ForCreation.ServiceUserPreferenesForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ServiceUsers/{serviceUser}/preferences")
public class ServiceUserPreferencesController {


    private IServiceUserPreferenecesService serviceUserPreferenecesService;

    @Autowired
    public void setService(IServiceUserPreferenecesService serviceUserService) {
        this.serviceUserPreferenecesService = serviceUserService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<?> list(@PathVariable int serviceUserId)  {
        return new ResponseEntity<>(serviceUserPreferenecesService.findAllByServiceUser(serviceUserId), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int serviceUserId, @PathVariable int id ){
        return new ResponseEntity<>(serviceUserPreferenecesService.delete(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrieves one
    @RequestMapping(value= "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int serviceUserId, @PathVariable int id){
        return new ResponseEntity<>(serviceUserPreferenecesService.findByServiceProviderAndUser(serviceUserId, id), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody ServiceUserPreferencesForCreationDto forCreationDto ) {
        return new ResponseEntity<>(serviceUserPreferenecesService.add(forCreationDto), new HttpHeaders(), HttpStatus.OK);
    }


    //Updates
    @RequestMapping(value="/{id}", method= RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@PathVariable int id,@RequestBody ServiceUserPreferenesForUpdateDto su) {
        return new ResponseEntity<>(serviceUserPreferenecesService.update(id, su), new HttpHeaders(), HttpStatus.OK);
    }

}
