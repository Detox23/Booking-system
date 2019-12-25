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

/**
 * The controller is responsible for managing accounts. There are operations responsible for Creating, Updating,
 * Deleting and Finding accounts as well as accounts' comments.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

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

    /**
     * GET request method that retrieves a list of all active accounts.
     * @return If successfully, it returns code 302 (FOUND response) together with a list of AccountDto objects that
     * are not deleted from a database. Otherwise appreciate error message.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(accountService.listAccounts(), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that find an Account by its id.
     * @param id <Integer> Unique identifier of a record. It is used to find it in the database.
     * @return If successfully, it returned code 302 (FOUND response) together with a found AccountDto object. Otherwise,
     * returns appreciate error message.
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ResponseEntity<?> seeAccount(@PathVariable int id) {
        return new ResponseEntity<>(accountService.findAccount(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * POST request method that add an Account to a database.
     * @param account <AccountForCreationDto> Required account object that need to be passed in order to complete the operation.
     *               Required object's fields:
     *                ~ accountName <String> (A name of an account),
     *                ~ accountTypeId <Integer> (An Id of an account's type),
     *                ~ parentId <Integer> (An Id of a parent Id),
     *                ~ primaryContactId <Integer> (An Id of a primary contact),
     *                ~ departmentId <Integer> (An Id of a department from database),
     *                ~ cvrNumber <String> (Company's CVR number)
     * @return If successfully, it returns code 201 (CREATED response) and a created AccountDto object with its fields
     * filled. Otherwise appreciate error message.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@RequestBody @Valid AccountForCreationDto account) {
        return new ResponseEntity<>(accountService.addAccount(account), new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * DELETE request method that deletes Account from a database. In fact the record is not deleted, only its field
     * isDeleted is set to True.
     * @param id <Integer> An unique identifier of a record from a database. It is a number thanks to which the field is found and
     *           deleted.
     * @return If successfully, it returns code 200 (OK response) and true value. Otherwise, false value or error message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable int id) {
        return new ResponseEntity<>(accountService.deleteAccount(id), new HttpHeaders(), HttpStatus.OK);
    }


    /**
     * PATCH request method that updated found Account in a database. The account is found by an id value and changed,
     * none empty fields are updated.
     * @param account Required account object that need to be passed to complete the operation. Required object's fields:
     *                ~ id <Integer> (Unique identifier of a record in a database),
     *                ~ accountName <String> (A name of an account),
     *                ~ accountTypeId <Integer> (An Id of an account's type),
     *                ~ parentId <Integer> (An Id of a parent Id),
     *                ~ primaryContactId <Integer> (An Id of a primary contact),
     *                ~ departmentId <Integer> (An Id of a department from database),
     *                ~ cvrNumber <String> (Company's CVR number)
     * @return If successfully, it returns code 200 (OK response) together with a updated AccountDto object with
     * updated fields. Otherwise appreciate error message.
     */
    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAccount(@RequestBody @Valid AccountForUpdateDto account) {
        return new ResponseEntity<>(accountService.updateAccount(account), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * GET request method that finds all account's comments.
     * @param id <Integer> An unique identifier of an account from a database.
     * @return If successfully, it returns code 302 (FOUND response) together with a retrieved list of AccountCommentDto objects.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.GET)
    public ResponseEntity<?> listAccountComments(@PathVariable int id) {
        return new ResponseEntity<>(accountCommentService.listAccountComments(id), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * GET request method that finds a comment of an account.
     * @param id <Integer> An unique identifier of an account from a database.
     * @param commentID <Integer> An unique identifier of an comment from a database.
     * @return If successfully, it returns code 302 (FOUND response) together with a found AccountCommentDto object with
     * all fields filled.
     */
    @RequestMapping(value = "/{id}/comment/{commentID}", method = RequestMethod.GET)
    public ResponseEntity<?> findAccountComment(@PathVariable int id, @PathVariable int commentID) {
        return new ResponseEntity<>(accountCommentService.findAccountComment(commentID), new HttpHeaders(), HttpStatus.FOUND);
    }

    /**
     * DELETE request method that removes a specific comment of an account from a database.
     * @param id <Integer> An unique identifier of an account from a database.
     * @param commentID <Integer> An unique identifier of an comment from a database.
     * @return If successfully, it returns code 200 (OK response) and true value. Otherwise it returns false or error
     * message.
     */
    @RequestMapping(value = "/{id}/comment/{commentID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccountComment(@PathVariable int id, @PathVariable int commentID) {
        return new ResponseEntity<>(accountCommentService.deleteAccountComment(id, commentID), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * POST request method that adds comment to an account.
     * @param id <Integer> An unique identifier of an account.
     * @param comment <AccountCommentForCreationDto> A creation object that need to be passed in order to complete the operation.
     *                Required fields:
     *                ~ accountId <Integer> (An id of an account to which the comment will be added)
     *                ~ commentText <String> (A comment text that will be added)
     * @return If successfully, it returns code 201 (CREATED response) and a created AccountCommentDto object with added information.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.POST)
    public ResponseEntity<?> addAccountComment(@PathVariable int id, @RequestBody @Valid AccountCommentForCreationDto comment) {
        comment.setAccountId(id);
        return new ResponseEntity<>(accountCommentService.addAccountComment(comment), new HttpHeaders(), HttpStatus.CREATED);

    }

    /**
     * PATCH request method that updated existing comment of an account with new information.
     * @param id <Integer> An unique identifier of an account.
     * @param comment <AccountCommentForUpdateDto> An update object that need to be passed in order to complete the operation.
     *                Required fields:
     *                ~ id <Integer> (Unique identifier of a record in a database)
     *                ~ accountId <Integer> (An id of an account to which the comment will be added)
     *                ~ commentText <String> (A comment text that will be added)
     *
     * @return If successfully, it returns code 200 (OK response) together with an updated object of type AccountCommentDto.
     * Not changed information maintain the same.
     */
    @RequestMapping(value = "/{id}/comment/", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateAccountComment(@PathVariable int id, @RequestBody @Valid AccountCommentForUpdateDto comment) {
        comment.setAccountId(id);
        return new ResponseEntity<>(accountCommentService.updateAccountComment(comment), new HttpHeaders(), HttpStatus.OK);

    }
}

