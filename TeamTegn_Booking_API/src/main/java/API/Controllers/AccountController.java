package API.Controllers;
import API.Services.AccountService;
import DAO.IAccountDAO;
import Objects.Factory.Account;
import DAO.AccountDAO;
import Shared.AccountForCreationDto;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends BaseController {

    private IAccountDAO accountDao = new AccountDAO();
    @Autowired
    private AccountService accountService;
    //Retrives all accounts
    @RequestMapping(value="/list", method = RequestMethod.GET)
    public ResponseEntity<?> seeAllAccounts() {
        return new ResponseEntity<>(accountDao.list(), new HttpHeaders(), HttpStatus.OK);
    }

    //Retrives one account
    @RequestMapping(value= "/find/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> seeAccount(@PathVariable String id){
        return new ResponseEntity<>(accountDao.findAccountByID(Integer.parseInt(id)), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account [Requires sending a json file send]
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody AccountForCreationDto account) {
        return new ResponseEntity<>(accountService.addAccount(account), new HttpHeaders(), HttpStatus.OK);
    }

    //Deletes an account
    @RequestMapping(value="/delete/{id}",  method = {RequestMethod.GET, RequestMethod.DELETE})
    public ResponseEntity<?> deleteAccount(@PathVariable String id){
        return new ResponseEntity<>(accountDao.deleteAccount(Integer.parseInt(id)), new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    //Updates an account
    @RequestMapping(value="/update", method= RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@RequestBody Account account){
        return new ResponseEntity<>(accountDao.update(account), new HttpHeaders(), HttpStatus.OK);
    }

}

