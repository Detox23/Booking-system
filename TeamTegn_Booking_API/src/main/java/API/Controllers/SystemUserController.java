package API.Controllers;

import API.Services.SystemUserService.ISystemUserService;
import Shared.ForCreation.SystemUserForCreationDto;
import Shared.ForCreation.SystemUserForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/systemUsers")
public class SystemUserController {
    private ISystemUserService systemUserService;

    @Autowired
    public void setSystemUserService(ISystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> addSystemUser(@RequestBody @Valid SystemUserForCreationDto systemUser) {
        return new ResponseEntity<>(systemUserService.addSystemUser(systemUser), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSystemUser(@PathVariable int id) {
        return new ResponseEntity<>(systemUserService.deleteSystemUser(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findSystemUser(@PathVariable int id) {
        return new ResponseEntity<>(systemUserService.findSystemUser(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listSystemUsers(@PathVariable boolean showDeleted) {

        return new ResponseEntity<>(systemUserService.listSystemUsers(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateSystemUser(@RequestBody @Valid SystemUserForUpdateDto systemUser) {
        return new ResponseEntity<>(systemUserService.updateSystemUser(systemUser), new HttpHeaders(), HttpStatus.OK);
    }


}
