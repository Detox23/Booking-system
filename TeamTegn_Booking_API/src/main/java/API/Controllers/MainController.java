package API.Controllers;
import Objects.Factory.PersonFactory;
import Objects.Factory.Account;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/main")
@RestController
public class MainController extends BaseController {
    private PersonFactory pf = new PersonFactory();
    private Account acc1 = (Account) pf.getPerson("Account", "Adam", 23);

    public String index(){
        return "Hello";
    }

    @RequestMapping(value="/account", method = RequestMethod.GET)
    public String see_account(){
        return acc1.getName();
    }

    @RequestMapping(value= "/{fooId}/{name}", method = {RequestMethod.GET, RequestMethod.POST})
    public String changePerson(@PathVariable String fooId, @PathVariable String name){
        acc1.setName(name);
        return acc1.getName();
    }

}

