package API.Controllers;
import API.Services.IServiceUserService;
import Shared.ForCreation.ServiceUserForCreationDto;
import Shared.ForCreation.ServiceUserForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ServiceUserController extends BaseController {


    @Autowired
    private IServiceUserService service;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(service.list(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/{serviceUserId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int serviceUserId){
        return new ResponseEntity<>(service.delete(serviceUserId), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrieves one account
    @RequestMapping(value= "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int id){
        return new ResponseEntity<>(service.get(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody ServiceUserForCreationDto account) {
        return new ResponseEntity<>(service.add(account), new HttpHeaders(), HttpStatus.OK);
    }


    //Updates an account
    @RequestMapping(value="/{id}", method= RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@PathVariable int id,@RequestBody ServiceUserForUpdateDto su) {
        return new ResponseEntity<>(service.update(id, su), new HttpHeaders(), HttpStatus.OK);
    }


}

