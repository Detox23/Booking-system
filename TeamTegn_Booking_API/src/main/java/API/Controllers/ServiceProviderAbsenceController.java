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
@RequestMapping("/api/serviceProviderAbsences")
public class ServiceProviderAbsenceController {

    private IServiceProviderAbsenceService serviceProviderAbsence;

    @Autowired
    public void setServiceProviderAbsence(IServiceProviderAbsenceService serviceProviderAbsence) {
        this.serviceProviderAbsence = serviceProviderAbsence;
    }

    /**
     * GET request method that returns list of all service providers' absences
     * @return If successfully, it returns code 302 (FOUND response) with list of all absences. Otherwise error with
     * appreciate message.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllServiceProviderAbsences() {
        return new ResponseEntity<>(serviceProviderAbsence.listAllServiceProviderAbsences(), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that returns absences of a specific service provider.
     * @param id <Integer> Unique identifier of a service provider from database.
     * @return If successfully, it returns code 302 (FOUND response) with list of found service provider absences.
     * Otherwise error with appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderAbsencesForServiceProvider(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderAbsence.findServiceProviderAbsencesForServiceProvider(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that adds service provider absence to a database.
     * @param absence <ServiceProviderAbsenceForCreationDto> Object that need to be passed to complete the operation
     *                of addition. Required object's fields:
     *                ~ serviceProviderId <Integer> (Unique identifier of a service provider to which the absence will be added)
     *                ~ absenceTypeId <Integer> (Unique identifier of an absence type)
     *                ~ absenceReason <String> (Reason of an absence)
     *                ~ fromDate <Date> (Start date of an absence)
     *                ~ fromTime <Time> (Start time of an absence)
     *                ~ toDate <Date> (Finish date of an absence)
     *                ~ toTime <Time> (Finish time of an absence)
     * @return If successfully, it returns code 201 (CREATED response) together with a ServiceProviderAbsenceDto object
     * with filled information.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addServiceProviderAbsence(@RequestBody ServiceProviderAbsenceForCreationDto absence) {
        return new ResponseEntity<>(serviceProviderAbsence.addServiceProviderAbsence(absence), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that removes service provider's absence from a database.
     * @param id <Integer> Unique identifier of an absence from a database.
     * @return If successfully, it returns code 200 (OK response) and true value, otherwise false value or error with
     * appreciate message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteServiceProviderAbsence(@PathVariable int id) {
        return new ResponseEntity<>(serviceProviderAbsence.deleteServiceProviderAbsence(id), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * GET request method that finds service providers absences in specific time.
     * @param startTime <Time> Start time from which absences will be filtered.
     * @param endTime <Time> End time to which absences will be filtered.
     * @return If successfully it returns code 302 (FOUND response) together with a list of found absences.
     * Otherwise error with appreciate error.
     */
    @RequestMapping(value = "/time/{startTime}/{endTime}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderAbsencesInTime(
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss") Time startTime,
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss") Time endTime) {
        return new ResponseEntity<>(serviceProviderAbsence.findServiceProviderAbsencesInTime(startTime, endTime), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds absences for a specific service provider in desired time.
     * @param startTime <Time> Start time from which absences will be filtered.
     * @param endTime <Time> End time to which absences will be filtered.
     * @param id <Integer> Unique identifier of a service provider from a database.
     * @return If successfully, it returns code 302 (FOUND response) together with a list of found absences.
     * Otherwise error with appreciate error.
     */
    @RequestMapping(value = "/time/{startTime}/{endTime}/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderAbsencesForServiceProviderInTime(
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss") Time startTime,
            @PathVariable @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss") Time endTime,
            @PathVariable int id) {
        return new ResponseEntity<>(serviceProviderAbsence.findServiceProviderAbsencesForServiceProviderInTime(startTime, endTime, id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds service providers absences in specific date.
     * @param startDate <Date> Start date from which absences will be filtered.
     * @param endDate <Date> End date from which absences will be filtered.
     * @return If successfully, it returns code 302 (FOUND response) together with a list of found absences.
     * Otherwise error with appreciate error.
     */
    @RequestMapping(value = "/date/{startDate}/{endDate}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderAbsencesInPeriod(
            @PathVariable @JsonFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathVariable @JsonFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return new ResponseEntity<>(serviceProviderAbsence.findServiceProviderAbsencesInPeriod(startDate, endDate), new HttpHeaders(), HttpStatus.FOUND);
    }


    /**
     * GET request method that finds absences for a specific service provider in desired time.
     * @param startDate <Date> Start date from which absences will be filtered.
     * @param endDate <Date> End date from which absences will be filtered.
     * @param id <Integer> Unique identifier of a service provider from a database.
     * @return If successfully, it returns code 302 (FOUND response) together with a list of found absences.
     * Otherwise error with appreciate error.
     */
    @RequestMapping(value = "/date/{startDate}/{endDate}/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findServiceProviderAbsencesForServiceProviderInPeriod(
            @PathVariable @JsonFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathVariable @JsonFormat(pattern = "yyyy-MM-dd") Date endDate,
            @PathVariable int id) {
        return new ResponseEntity<>(serviceProviderAbsence.findServiceProviderAbsencesForServiceProviderInPeriod(startDate, endDate, id), new HttpHeaders(), HttpStatus.FOUND);
    }


}
