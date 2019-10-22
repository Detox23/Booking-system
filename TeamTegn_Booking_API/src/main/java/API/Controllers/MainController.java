package API.Controllers;
import Objects.Factory.PersonFactory;
import Objects.Factory.Account_Entity;
import DAO.AccountDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/main")
@RestController
public class MainController extends BaseController {
    private PersonFactory pf = new PersonFactory();
    private AccountDAO accountdao = new AccountDAO();
    private Account_Entity acc1 = (Account_Entity) pf.getPerson("Account", "Adam", 23);

    public String index(){
        return "Hello";
    }

    @RequestMapping(value="/accounts", method = RequestMethod.GET)
    public List<Account_Entity> see_account() {
        return accountdao.list();
    }

    @RequestMapping(value= "/{fooId}/{name}", method = {RequestMethod.GET, RequestMethod.POST})
    public String changePerson(@PathVariable String fooId, @PathVariable String name){
        acc1.setAccountName(name);
        return acc1.getAccountName();
    }

}

