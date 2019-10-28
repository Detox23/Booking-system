package API.Controllers;
import API.Services.AccountService;
import API.DAO.IAccountDAO;
import API.DAO.AccountDAO;
import Shared.ForCreation.AccountForCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends BaseController {

    private IAccountDAO accountDao = new AccountDAO();
    @Autowired
    private AccountService accountService;
    //Retrives all accounts
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ResponseEntity<?> seeAllAccounts() {
        return new ResponseEntity<>(accountService.list(), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrives one account
    @RequestMapping(value= "/find/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> seeAccount(@PathVariable int id){
        return new ResponseEntity<>(accountService.findAccount(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account [Requires sending a json file send]
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody AccountForCreationDto account) {
        return new ResponseEntity<>(accountService.addAccount(account), new HttpHeaders(), HttpStatus.OK);
    }

    //Deletes an account
    @RequestMapping(value="/delete/{id}",  method = { RequestMethod.DELETE})
    public ResponseEntity<?> deleteAccount(@PathVariable int id){
        return new ResponseEntity<>(accountService.deleteAccount(id), new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    //Updates an account
    @RequestMapping(value="/update", method= RequestMethod.PATCH, consumes = "application/json-patch+json")
    public ResponseEntity<?> updateAccount(@RequestBody AccountForCreationDto account) {
        return new ResponseEntity<>(accountService.update(account), new HttpHeaders(), HttpStatus.OK);
    }
}

