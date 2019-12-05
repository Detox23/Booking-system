package API.Controllers;

import API.Services.AbsenceTypeService.IAbsenceTypeService;
import Shared.ForCreation.AbsenceTypeForCreationDto;
import Shared.ForCreation.AbsenceTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/absenceType")
public class AbsenceTypeController {

    private IAbsenceTypeService absenceTypeService;

    @Autowired
    public void setAbsenceTypeService(IAbsenceTypeService absenceTypeService) {
        this.absenceTypeService = absenceTypeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> seeAllAbsenceTypes() {
        return new ResponseEntity<>(absenceTypeService.listAbsenceTypes(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAbsenceType(@PathVariable int id) {
        return new ResponseEntity<>(absenceTypeService.findAbsenceType(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addAbsenceType(@RequestBody AbsenceTypeForCreationDto absenceType) {
        return new ResponseEntity<>(absenceTypeService.addAbsenceType(absenceType), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteAbsenceType(@PathVariable int id) {
        return new ResponseEntity<>(absenceTypeService.deleteAbsenceType(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateAbsenceType(@RequestBody AbsenceTypeForUpdateDto absenceType) {
        return new ResponseEntity<>(absenceTypeService.updateAbsenceType(absenceType), new HttpHeaders(), HttpStatus.OK);
    }
}
