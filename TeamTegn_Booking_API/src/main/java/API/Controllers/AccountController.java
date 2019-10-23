package API.Controllers;
import DAO.IAccountDAO;
import Objects.Factory.Account;
import DAO.AccountDAO;
import org.hibernate.ObjectNotFoundException;
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

    //Retrives all accounts
    @RequestMapping(value="", method = RequestMethod.GET)
    public List<Account> seeAllAccounts() {
        return accountDao.list();
    }

    //Retrives one account
    @RequestMapping(value= "/{name}", method = {RequestMethod.GET, RequestMethod.POST})
    public Account seeAccount(@PathVariable String name){ return accountDao.findAccountByName(name);}

    //Creates an account [Requires sending a json file send]
    @RequestMapping(value="", method=RequestMethod.POST)
    public boolean createAccount(@RequestBody Account account){
        return accountDao.addAccount(account);
    }

    //Deletes an account
    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value="", method=RequestMethod.DELETE)
    public boolean deleteAccount(@RequestBody String Id){
        return accountDao.deleteAccount(Id);
    }

}

