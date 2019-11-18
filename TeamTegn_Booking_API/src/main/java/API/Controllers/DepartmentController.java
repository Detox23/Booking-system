package API.Controllers;


import API.Services.DepartmentService.IDepartmentService;
import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ToReturn.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController extends BaseController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> seeAllDepartments() {
        return new ResponseEntity<>(departmentService.seeAllDepartments(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> findDepartment(@PathVariable String name) {
        return new ResponseEntity<>(departmentService.findDepartment(name), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDepartment(@PathVariable String name) {
        return new ResponseEntity<>(departmentService.deleteDepartment(name), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addDepartment(@RequestBody @Valid DepartmentForCreationDto department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentDto department) {
        return new ResponseEntity<>(departmentService.updateDepartment(department), new HttpHeaders(), HttpStatus.OK);
    }
}