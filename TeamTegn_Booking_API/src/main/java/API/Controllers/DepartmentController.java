package API.Controllers;

import API.Exceptions.AccountNotExistsUpdateException;
import API.Exceptions.UpdateErrorException;
import API.Services.DepartmentService.IDepartmentService;
import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ToReturn.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController extends BaseController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping(value="/list", method= RequestMethod.GET)
    public ResponseEntity<?> seeAllDepartments(){
        return new ResponseEntity<>(departmentService.seeAllDepartments(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/find/{name}", method=RequestMethod.GET)
    public ResponseEntity<?> findDepartment(@PathVariable String name){
        return new ResponseEntity<>(departmentService.findDepartment(name), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/delete/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDepartment(@PathVariable String name)  {
        try{
            return new ResponseEntity<>(departmentService.deleteDepartment(name), new HttpHeaders(), HttpStatus.OK);

        }catch (AccountNotExistsUpdateException e){
            return new ResponseEntity<>("Could not delete not existing department.", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentForCreationDto department){
        return new ResponseEntity<>(departmentService.addDepartment(department), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value="/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentDto department){
        return new ResponseEntity<>(departmentService.updateDepartment(department), new HttpHeaders(), HttpStatus.OK);
    }
}
