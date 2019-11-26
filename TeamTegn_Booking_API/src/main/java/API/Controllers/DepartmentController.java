package API.Controllers;


import API.Services.DepartmentService.IDepartmentService;
import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ForCreation.DepartmentForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/department")
public class DepartmentController extends BaseController {

    private IDepartmentService departmentService;

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> seeAllDepartments() {
        return new ResponseEntity<>(departmentService.seeAllDepartments(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> findDepartment(@PathVariable String name) {
        return new ResponseEntity<>(departmentService.findDepartment(name), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDepartment(@PathVariable int id) {
        return new ResponseEntity<>(departmentService.deleteDepartment(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addDepartment(@RequestBody @Valid DepartmentForCreationDto department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentForUpdateDto department) {
        return new ResponseEntity<>(departmentService.updateDepartment(department), new HttpHeaders(), HttpStatus.OK);
    }
}
