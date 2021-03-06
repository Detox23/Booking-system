package API.Controllers;


import API.Services.DepartmentService.IDepartmentService;
import Shared.ForCreation.DepartmentForCreationDto;
import Shared.ForCreation.DepartmentForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private IDepartmentService departmentService;

    @Autowired
    public void setDepartmentService(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * GET request method that retrieves all departments. There is possibility to display deleted records.
     * @param showDeleted [Path variable] <Boolean> Determines whether to display deleted records from database.
     *                    true -> display all records
     *                    false -> display only not deleted records
     * @return If successfully, it returns 302 code (FOUND response) with a list of all departments.
     * Otherwise it returns error message with appreciate message.
     */
    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> seeAllDepartments(@PathVariable boolean showDeleted) {
        return new ResponseEntity<>(departmentService.seeAllDepartments(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds a department.
     * @param name [Path variable] <String> Unique name of a department record from a database.
     * @return If successfully, it returns 302 code (FOUND response) with a found departmentDto object and
     * fields filled. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> findDepartment(@PathVariable String name) {
        return new ResponseEntity<>(departmentService.findDepartment(name), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds a department to a database. Allowed only for administrator account.
     * @param department [Request body variable] <DepartmentForCreationDto> An object that is needed to complete the operation.
     *                         Object's required fields;
     *                         ~ departmentName <String> (Unique name of the department)
     *                         ~ street <String> (Department street)
     *                         ~ postcode <String> (Department postcode)
     *                         ~ telephoneNumber <String> (Department telephoneNumber)
     * @return If successfully, it returns 201 code (CREATED response) with a created departmentDto
     * object filled with information that was added to a database. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addDepartment(@RequestBody @Valid DepartmentForCreationDto department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes a department from a database. In fact it just change one of the record's
     * values. Allowed only for administrator account.
     * @param id [Path variable] <Integer> Unique identifier of the record in a database.
     * @return If successfully, it returns 200 code (OK response) and true value. Otherwise it might return false or
     * error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id) {
        return new ResponseEntity<>(departmentService.deleteDepartment(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * PATCH request method that updates existing record in the database. Allowed only for administrator account.
     * @param department [Request body variable] <DepartmentForUpdateDto> An object that need to be passed in order
     *                   to process the operation. Object's required fields:
     *                         ~ id <Integer> (Unique identifier of the department)
     *                         ~ departmentName <String> (Unique name of the department)
     *                         ~ street <String> (Department street)
     *                         ~ postcode <String> (Department postcode)
     *                         ~ telephoneNumber <String> (Department telephoneNumber)
     * @return If successfully, it returns 200 code (OK response) and an departmentDto object with updated
     * information. Otherwise it returns error with appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateDepartment(@RequestBody DepartmentForUpdateDto department) {
        return new ResponseEntity<>(departmentService.updateDepartment(department), new HttpHeaders(), HttpStatus.OK);
    }
}
