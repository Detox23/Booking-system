package API.Controllers;

import API.Services.AccountService.IAccountTypeService;
import Shared.ForCreation.AccountTypeForCreationDto;
import Shared.ForCreation.AccountTypeForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accountTypes")
public class AccountTypeController {

    private IAccountTypeService accountTypeService;

    @Autowired
    public void setAccountTypeService(IAccountTypeService accountTypeService) {
        this.accountTypeService = accountTypeService;
    }

    @RequestMapping(value = "/all/{showDeleted}", method = RequestMethod.GET)
    public ResponseEntity<?> listAccounts(boolean showDeleted) {
        return new ResponseEntity<>(accountTypeService.listAccountTypes(showDeleted), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOneCompetency(@PathVariable int id) {
        return new ResponseEntity<>(accountTypeService.findAccountType(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> addOneCompetency(@RequestBody @Valid AccountTypeForCreationDto accountType) {
        return new ResponseEntity<>(accountTypeService.addAccountType(accountType), new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> updateOneCompetency(@RequestBody @Valid AccountTypeForUpdateDto accountType) {
        return new ResponseEntity<>(accountTypeService.updateAccountType(accountType), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_Administrator')")
    public ResponseEntity<?> deleteOneCompetency(@PathVariable int id) {
        return new ResponseEntity<>(accountTypeService.deleteAccountType(id), new HttpHeaders(), HttpStatus.OK);
    }
}
