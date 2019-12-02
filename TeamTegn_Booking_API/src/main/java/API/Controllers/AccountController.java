package API.Controllers;

import API.Services.AccountService.IAccountCommentService;
import API.Services.AccountService.IAccountService;
import Shared.ForCreation.AccountCommentForCreationDto;
import Shared.ForCreation.AccountCommentForUpdateDto;
import Shared.ForCreation.AccountForCreationDto;
import Shared.ForCreation.AccountForUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController extends BaseController {

    private IAccountService accountService;

    private IAccountCommentService accountCommentService;

    @Autowired
    public void setAccountCommentService(IAccountCommentService accountCommentService) {
        this.accountCommentService = accountCommentService;
    }

    @Autowired
    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?>  getAll(){

        return new ResponseEntity<>(accountService.listAccounts(), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> seeAccount(@PathVariable int id) {
        return new ResponseEntity<>(accountService.findAccount(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountForCreationDto account) {
        return new ResponseEntity<>(accountService.addAccount(account), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable int id) {
        return new ResponseEntity<>(accountService.deleteAccount(id), new HttpHeaders(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@RequestBody @Valid AccountForUpdateDto account) {
        return new ResponseEntity<>(accountService.updateAccount(account), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.GET)
    public ResponseEntity<?> listAccountComments(@PathVariable int id){
        return new ResponseEntity<>(accountCommentService.listAccountComments(id), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/{commentID}", method = RequestMethod.GET)
    public ResponseEntity<?> findAccountComment(@PathVariable int id, @PathVariable int commentID){
        return new ResponseEntity<>(accountCommentService.findAccountComment(commentID), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccountComment(@PathVariable int id, @PathVariable int commentID){
        return new ResponseEntity<>(accountCommentService.deleteAccountComment(id, commentID), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.POST)
    public ResponseEntity<?> addAccountComment(@PathVariable int id, @RequestBody AccountCommentForCreationDto comment){
        comment.setAccountId(id);
        return new ResponseEntity<>(accountCommentService.addAccountComment(comment), new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAccountComment(@PathVariable int id, @RequestBody AccountCommentForUpdateDto comment){
        comment.setAccountId(id);
        return new ResponseEntity<>(accountCommentService.updateAccountComment(comment), new HttpHeaders(), HttpStatus.OK);

    }
}

