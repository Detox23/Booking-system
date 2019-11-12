package API.Controllers;

import API.Services.IAssignmentService;
import Shared.ForCreation.AssignmentForCreationDto;
import Shared.ForCreation.AssignmentForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/assignments")
public class AssignmentController extends BaseController {


    @Autowired
    private IAssignmentService assignmentService;

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam(defaultValue = "id")  String sortBy ,
                                    @RequestParam(defaultValue = "ASC")  Sort.Direction sortDirection )
    {
        return new ResponseEntity<>(assignmentService.getAll(PageRequest.of(pageNumber, pageSize, sortDirection, sortBy)), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value= "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> get(@PathVariable int id){
        return new ResponseEntity<>(assignmentService.get(id), new HttpHeaders(), HttpStatus.OK);
    }


    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody AssignmentForCreationDto assignment) {
        return new ResponseEntity<>(assignmentService.add(assignment), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}",  method = { RequestMethod.DELETE})
    public ResponseEntity<?> delete(@PathVariable int id){
        return new ResponseEntity<>(assignmentService.delete(id), new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value="/{id}",  method = { RequestMethod.PATCH})
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody AssignmentForUpdateDto forUpdate){
        return new ResponseEntity<>(assignmentService.update(id, forUpdate), new HttpHeaders(), HttpStatus.OK);
    }
}