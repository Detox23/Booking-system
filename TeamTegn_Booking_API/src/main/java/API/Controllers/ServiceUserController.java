package API.Controllers;
import API.Services.ServiceUserService.IServiceUserService;
import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/serviceUsers")
public class ServiceUserController extends BaseController {

    private IServiceUserService serviceUserService;

    @Autowired
    public void setService(IServiceUserService serviceUserService) {
        this.serviceUserService = serviceUserService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(serviceUserService.listServiceUsers(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/{serviceUserId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int serviceUserId){
        return new ResponseEntity<>(serviceUserService.deleteServiceUser(serviceUserId), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrieves one account
    @RequestMapping(value= "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int id){
        return new ResponseEntity<>(serviceUserService.findServiceUser(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody ServiceUserForCreationDto account) {
        return new ResponseEntity<>(serviceUserService.addServiceUser(account), new HttpHeaders(), HttpStatus.OK);
    }


    //Updates an account
    @RequestMapping(value="/{id}", method= RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@PathVariable int id,@RequestBody ServiceUserForUpdateDto su) {
        return new ResponseEntity<>(serviceUserService.updateServiceUser(id, su), new HttpHeaders(), HttpStatus.OK);
    }


}

