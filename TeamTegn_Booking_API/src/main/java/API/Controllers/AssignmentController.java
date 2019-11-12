package API.Controllers;

import API.Services.AssignmentService;
import Shared.ForCreation.AssignmentForCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController extends BaseController {


    @Autowired
    private AssignmentService assignmentService;

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<?> getAll(Pageable pageable) {
        return new ResponseEntity<>(assignmentService.getAll(pageable), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value= "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int id){

        return new ResponseEntity<>(assignmentService.get(id), new HttpHeaders(), HttpStatus.OK);
    }

    //Creates an account [Requires sending a json file send]
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody AssignmentForCreationDto assignment) {
        return new ResponseEntity<>(assignmentService.add(assignment), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/check",  method = { RequestMethod.GET})
    public ResponseEntity<?> delete(){
        assignmentService.check();

        return new ResponseEntity<>(assignmentService.get(9909009), new HttpHeaders(), HttpStatus.OK);
    }

 /*   @RequestMapping( method= RequestMethod.PATCH)
    public ResponseEntity<?> update(@RequestBody Account account){
        return new ResponseEntity<>(assignmentService.update(account), new HttpHeaders(), HttpStatus.OK);
    }*/



}