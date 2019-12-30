package API.Controllers;

import API.Services.AccountService.IAccountTypeService;
import Shared.ForCreation.AccountTypeForCreationDto;
import Shared.ForCreation.AccountTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The controller is responsible for managing account types. There are operations responsible for creating, updating,
 * listing and deleting account types.
 */
@RestController
@RequestMapping("/api/accountTypes")
public class AccountTypeController {

    private IAccountTypeService accountTypeService;

    @Autowired
    public void setAccountTypeService(IAccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    /**
     * GET request method that is responsible for retrieving a list of account types. Depending on the choice, it can
     * also displays deleted records.
     * @param showDeleted [Path variable] <boolean> Determines whether displays deleted records in the return list.
     *                    True -> display all values
     *                    False -> display only not deleted values
     * @return If successfully, it returns code 302 (FOUND response) together with a list of AccountTypeDto objects.
     * Otherwise it returns error response with an appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAccountTypes(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(accountTypeService.listAccountTypes(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that is responsible for finding specific account type record from a database.
     * @param id [Path variable] <Integer> An unique identifier of a records from a database.
     * @return If successfully, it returns code 302 (FOUND response) and a found AccountTypeDto object with filled
     * fields. Otherwise it returns error response with an appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAccountType(@PathVariable int id) {
        return new ResponseEntity<>(accountTypeService.findAccountType(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that is responsible for adding account type to a database. Available only for an
     * administrator account.
     * @param accountType [Body variable] <AccountTypeForCreationDto> An object that is required for completing the operation.
     *                   The objects' required field is:
     *                    ~ <String> accountType (an unique name of an account type)
     * @return If successfully, the method returns code 201 (CREATED response) and an AccountTypeDto object with
     * information that has been added. Otherwise it returns error response with an appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAccountType(@RequestBody @Valid AccountTypeForCreationDto accountType) {
        return new ResponseEntity<>(accountTypeService.addAccountType(accountType), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * PATCH request method that is responsible for updating an existing account type from a database. Available only
     * for an administrator account.
     * @param accountType [Body variable] <AccountTypeForUpdateDto> An object that is required for completing the operation.
     *                   The objects' required fields are:
     *                    ~ <Integer> id (An unique identifier of an existing record in a database),
     *                    ~ <String> accountType (an unique name of an account type)
     * @return If successfully, the method returns code 200 (OK response) together with AccountTypeDto object with updated information.
     * Otherwise it returns error response with an appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAccountType(@RequestBody @Valid AccountTypeForUpdateDto accountType) {
        return new ResponseEntity<>(accountTypeService.updateAccountType(accountType), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * DELETE request method that is responsible for removing record in a database. Available only for administrator account.
     * @param id [Path variable] <Integer> An unique identifier of an record in a database that is used to remove it.
     * @return If successfully, the method returns code 200 (OK response) together with true value. Otherwise it returns
     * false value or error response with an appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAccountType(@PathVariable int id) {
        return new ResponseEntity<>(accountTypeService.deleteAccountType(id), new HttpHeaders(), HttpStatus.OK);
    }
}
