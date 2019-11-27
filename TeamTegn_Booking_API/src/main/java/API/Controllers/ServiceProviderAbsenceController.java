package API.Controllers;

import API.Services.ServiceProviderService.IServiceProviderAbsenceService;
import Shared.ForCreation.ServiceProviderAbsenceForCreationDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;

@RestController
@RequestMapping("/api/serviceProviderAbsence")
public class ServiceProviderAbsenceController {

    private IServiceProviderAbsenceService serviceProviderAbsence;

    @Autowired
    public void setServiceProviderAbsence(IServiceProviderAbsenceService serviceProviderAbsence) {
        this.serviceProviderAbsence = serviceProviderAbsence;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllAbsences() {
        return new ResponseEntity<>(serviceProviderAbsence.listAllServiceProviderAbsences(), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findAbsencesForServiceProvider(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderAbsence.findServiceProviderAbsencesForServiceProvider(id), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addOneAbsence(@RequestBody ServiceProviderAbsenceForCreationDto absence) {
        return new ResponseEntity<>(serviceProviderAbsence.addServiceProviderAbsence(absence), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteOneAbsence(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderAbsence.deleteServiceProviderAbsence(id), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/time/{startTime}/{endTime}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderAbsencesInTime(
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss") Time startTime,
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss") Time endTime) {
        return new ResponseEntity<>(serviceProviderAbsence.findServiceProviderAbsencesInTime(startTime, endTime), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/time/{startTime}/{endTime}/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderAbsenceInTime(
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss") Time startTime,
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss") Time endTime,
            @PathVariable int id) {
        return new ResponseEntity<>(serviceProviderAbsence.findServiceProviderAbsencesForServiceProviderInTime(startTime, endTime, id), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/date/{startDate}/{endDate}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderAbsencesInDate(
            @PathVariable @JsonFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable @JsonFormat(pattern="yyyy-MM-dd") Date endDate) {
        return new ResponseEntity<>(serviceProviderAbsence.findServiceProviderAbsencesInPeriod(startDate, endDate), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/date/{startDate}/{endDate}/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderAbsenceInDate(
            @PathVariable @JsonFormat(pattern="yyyy-MM-dd") Date startDate,
            @PathVariable @JsonFormat(pattern="yyyy-MM-dd") Date endDate,
            @PathVariable int id) {
        return new ResponseEntity<>(serviceProviderAbsence.findServiceProviderAbsencesForServiceProviderInPeriod(startDate, endDate, id), new HttpHeaders(), HttpStatus.CREATED);
    }


}
