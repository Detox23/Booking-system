package API.Controllers;
import API.Exceptions.*;
import API.Services.IAccountService;
import Shared.ForCreation.AccountEanForCreationDto;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ToReturn.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends BaseController {


    @Autowired
    private IAccountService accountService;



    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ResponseEntity<?> seeAllAccounts() {
        return new ResponseEntity<>(accountService.list(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/find/{accountID}/comment/delete/{commentID}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccountComment(@PathVariable int accountID, @PathVariable int commentID){
        return new ResponseEntity<>(accountService.deleteAccountComment(accountID, commentID), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrieves one account
    @RequestMapping(value= "/find/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> seeAccount(@PathVariable int id){
        return new ResponseEntity<>(accountService.findAccount(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account [Requires sending a json file send]
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody AccountForCreationDto account) throws NoAccountIDAfterSavingException, MappingAccountDatabseToDtoException, AccountNotFoundWhileAddingEANNumberException {
        return new ResponseEntity<>(accountService.addAccount(account), new HttpHeaders(), HttpStatus.OK);
    }

    //Deletes an account
    @RequestMapping(value="/delete/{id}",  method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable int id){
        return new ResponseEntity<>(accountService.deleteAccount(id), new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    //Updates an account
    @RequestMapping(value="/update", method= RequestMethod.PUT)
    public ResponseEntity<?> updateAccount(@RequestBody AccountDto account) throws AccountNotExistsUpdateException, UpdateErrorException {
        return new ResponseEntity<>(accountService.update(account), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/eanNumber/delete/{accoundID}/{eanNumber}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEanNumber(@PathVariable int accoundID, @PathVariable String eanNumber) {
        return new ResponseEntity<>(accountService.deleteEAN(accoundID, eanNumber), new HttpHeaders(), HttpStatus.OK);
    }

    //Adds ean number to existing account.
    @RequestMapping(value="/eanNumber/add", method = RequestMethod.POST)
    public ResponseEntity<?> addEanNumber(@RequestBody AccountEanForCreationDto accountEan) throws AccountNotFoundWhileAddingEANNumberException, AddingTheSameEANNumberToSameAccountException {
        return new ResponseEntity<>(accountService.addEAN(accountEan), new HttpHeaders(), HttpStatus.OK);
    }

}

