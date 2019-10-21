package API.Controllers;
import Objects.Factory.PersonFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    @RequestMapping("/")
    public String index(){
        return "Hello";
    }

    @GetMapping("/api/people")
    @ResponseBody
    public String getPeople(@RequestParam String id){
        return "ID: "+ id;
    }

    @PostMapping("/api/people")
    @ResponseBody
    public String PersonController(@RequestParam("id") String fooId, @RequestParam String name){
        return "ID: " + fooId + " Name: " + name;
    }

}

