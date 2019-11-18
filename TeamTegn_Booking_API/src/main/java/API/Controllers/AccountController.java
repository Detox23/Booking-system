package API.Controllers;

import API.Services.AccountService.IAccountService;
import Shared.ForCreation.AccountEanForCreationDto;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.AccountForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends BaseController {


    private IAccountService accountService;

    @Autowired
    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> seeAllAccounts() {
        return new ResponseEntity<>(accountService.list(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/find/{accountID}/comment/delete/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccountComment(@PathVariable int accountID, @PathVariable int commentID) {
        return new ResponseEntity<>(accountService.deleteAccountComment(accountID, commentID), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrieves one account
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> seeAccount(@PathVariable int id) {
        return new ResponseEntity<>(accountService.findAccount(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account [Requires sending a json file send]
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody AccountForCreationDto account) {
        return new ResponseEntity<>(accountService.addAccount(account), new HttpHeaders(), HttpStatus.OK);
    }

    //Deletes an account
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable int id) {
        return new ResponseEntity<>(accountService.deleteAccount(id), new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    //Updates an account
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@RequestBody AccountForUpdateDto account) {
        return new ResponseEntity<>(accountService.update(account), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/eanNumber/{accountID}/{eanNumber}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEanNumber(@PathVariable int accountID, @PathVariable String eanNumber) {
        return new ResponseEntity<>(accountService.deleteEAN(accountID, eanNumber), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/eanNumber/{accountID}", method = RequestMethod.GET)
    public ResponseEntity<?> findEansForAccount(@PathVariable int accountID) {
        return new ResponseEntity<>(accountService.findEANNumber(accountID), new HttpHeaders(), HttpStatus.OK);
    }

    //Adds ean number to existing account.
    @RequestMapping(value = "/eanNumber", method = RequestMethod.POST)
    public ResponseEntity<?> addEanNumber(@RequestBody AccountEanForCreationDto accountEan) {
        return new ResponseEntity<>(accountService.addEAN(accountEan), new HttpHeaders(), HttpStatus.OK);
    }

}

