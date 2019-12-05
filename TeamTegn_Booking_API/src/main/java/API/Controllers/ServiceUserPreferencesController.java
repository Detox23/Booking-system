package API.Controllers;

import API.Services.ServiceUserService.IServiceUserPreferencesService;
import Shared.ForCreation.ServiceUserPreferencesForCreationDto;
import Shared.ForCreation.ServiceUserPreferencesForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/serviceUsers/{serviceUser}/preferences")
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
    public ResponseEntity<?> delete(@PathVariable int id){
        return new ResponseEntity<>(serviceUserPreferencesService.deleteServiceUserPreference(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrieves one
    @RequestMapping(value= "/{serviceProvider}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int serviceUser, @PathVariable int serviceProvider){
        return new ResponseEntity<>(serviceUserPreferencesService.findServiceProviderAndUser(serviceUser, serviceProvider), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account //W
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable int serviceUser, @RequestBody @Valid ServiceUserPreferencesForCreationDto serviceUserPreferences) {
        return new ResponseEntity<>(serviceUserPreferencesService.addServiceUserPreference(serviceUser, serviceUserPreferences), new HttpHeaders(), HttpStatus.OK);
    }


    //Updates
    @RequestMapping(value="/", method= RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@RequestBody @Valid ServiceUserPreferencesForUpdateDto serviceUserPreferences) {
        return new ResponseEntity<>(serviceUserPreferencesService.updateServiceUserPreference(serviceUserPreferences), new HttpHeaders(), HttpStatus.OK);
    }

}
