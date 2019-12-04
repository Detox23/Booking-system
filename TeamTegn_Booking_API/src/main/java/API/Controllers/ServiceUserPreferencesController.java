package API.Controllers;

import API.Services.ServiceUserService.IServiceUserPreferencesService;
import Shared.ForCreation.ServiceUserPreferencesForCreationDto;
import Shared.ForCreation.ServiceUserPreferencesForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ServiceUsers/{serviceUser}/preferences")
public class ServiceUserPreferencesController {

    private IServiceUserPreferencesService serviceUserPreferencesService;

    @Autowired
    public void setServiceUserPreferencesService(IServiceUserPreferencesService serviceUserPreferencesService) {
        this.serviceUserPreferencesService = serviceUserPreferencesService;
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<?> list(@PathVariable int serviceUser)  {
        return new ResponseEntity<>(serviceUserPreferencesService.listServiceUserPreferences(serviceUser), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable int serviceUser, @PathVariable int id ){
        return new ResponseEntity<>(serviceUserPreferencesService.deleteServiceUserPreference(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrieves one
    @RequestMapping(value= "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int serviceUser, @PathVariable int id){
        return new ResponseEntity<>(serviceUserPreferencesService.findServiceProviderAndUser(serviceUser, id), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody ServiceUserPreferencesForCreationDto serviceUserPreferences) {
        return new ResponseEntity<>(serviceUserPreferencesService.addServiceUserPreference(serviceUserPreferences), new HttpHeaders(), HttpStatus.OK);
    }


    //Updates
    @RequestMapping(value="/{id}", method= RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@PathVariable int id, @RequestBody ServiceUserPreferencesForUpdateDto serviceUserPreferences) {
        return new ResponseEntity<>(serviceUserPreferencesService.updateServiceUserPreference(id, serviceUserPreferences), new HttpHeaders(), HttpStatus.OK);
    }

}
