package API.Controllers;

import API.Services.ServiceProviderService.IServiceProviderAbsence;
import Shared.ForCreation.ServiceProviderAbsenceForCreationDto;
import Shared.ForCreation.ServiceProviderCompetencyForCreationDto;
import Shared.ForCreation.ServiceProviderCompetencyForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serviceProviderAbsence")
public class ServiceProviderAbsenceController {

    private IServiceProviderAbsence serviceProviderAbsence;

    @Autowired
    public void setServiceProviderAbsence(IServiceProviderAbsence serviceProviderAbsence) {
        this.serviceProviderAbsence = serviceProviderAbsence;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllCompetencies() {
        return new ResponseEntity<>(serviceProviderAbsence.listAllServiceProviderAbsences(), new HttpHeaders(), HttpStatus.FOUND);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> addOneCompetency(@RequestBody ServiceProviderAbsenceForCreationDto absence) {
        return new ResponseEntity<>(serviceProviderAbsence.addServiceProviderAbsence(absence), new HttpHeaders(), HttpStatus.CREATED);
    }




}
