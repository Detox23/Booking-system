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

    /**
     * GET request method that retrieves list of systemUsers.
     *
     * @param showDeleted [Path variable] <Boolean> Determines whether to display deleted records from database.
     *                    true -> show all records,
     *                    false -> show only not deleted records.
     * @return If successfully, it returns 302 code (FOUND response) with a list of all system users.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listSystemUsers(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(systemUserService.listSystemUsers(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds a record in a database.
     *
     * @param id [Path variable] <Integer> Unique identifier of a record in a database.
     * @return If successfully, it returns code 302 (FOUND response) with a serviceUserDto object with filled information.
     * Otherwise, error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findSystemUser(@PathVariable int id) {
        return new ResponseEntity<>(systemUserService.findSystemUser(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds system user to a database.
     *
     * @param systemUser [Request body variable] <SystemUserForCreationDto> An object that is required for
     *                   completing the operation. Required object's fields:
     *                   ~ firstName <String> (First name of a user)
     *                   ~ lastName <String> (last name of a user)
     *                   ~ telephoneNumber <String> (Telephone number of a user)
     *                   ~ userName <String> (Username of a user)
     *                   ~ password <String> (Password of a user)
     *                   ~ roleId <Integer> (Id of a role from a database)
     *                   ~ departments <List<Integer>> (List of departments the user belongs to)
     * @return If successfully, returns 201 code (CREATED response) with systemUserDto object returned with filled fields
     * with added information.
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> addSystemUser(@RequestBody @Valid SystemUserForCreationDto systemUser) {
        return new ResponseEntity<>(systemUserService.addSystemUser(systemUser), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes record from a database. In fact it just changes one of the field to false.
     *
     * @param id [Path variable] <Integer> Unique identifier of a record in a database.
     * @return If successfully, it returns code 200 (OK response) with true value. Otherwise false or error with
     * appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSystemUser(@PathVariable int id) {
        return new ResponseEntity<>(systemUserService.deleteSystemUser(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database.
     *
     * @param systemUser [Request body variable] <SystemUserForUpdateDto> An object that is required for completing
     *                   the operation. Required object's fields:
     *                   ~ id <Integer> (Unique identifier of a system user from database)
     *                   ~ firstName <String> (First name of a user)
     *                   ~ lastName <String> (last name of a user)
     *                   ~ telephoneNumber <String> (Telephone number of a user)
     *                   ~ userName <String> (Username of a user)
     *                   ~ password <String> (Password of a user)
     *                   ~ roleId <Integer> (Id of a role from a database)
     *                   ~ departments <List<Integer>> (List of departments the user belongs to)
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateSystemUser(@RequestBody @Valid SystemUserForUpdateDto systemUser) {
        return new ResponseEntity<>(systemUserService.updateSystemUser(systemUser), new HttpHeaders(), HttpStatus.OK);
    }


}
