package API.Controllers;

import API.Exceptions.*;
import org.hibernate.sql.Update;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
@EnableWebMvc
public class ResponseExceptionsHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(value={IllegalArgumentException.class, IllegalStateException.class})
//    protected ResponseEntity<Object> handleConflict(
//            RuntimeException ex, WebRequest request){
//        String bodyOfResponse = "This should be application specific";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
//
//    @ExceptionHandler(value= {ObjectNotFoundException.class, NullPointerException.class})
//    protected ResponseEntity<Object> handleObjectNotFoundException(
//            RuntimeException ex, WebRequest request){
//        String bodyOfResponse = "The record was not found.";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
//    }
    @ExceptionHandler(value={AccountNotFoundWhileAddingEANNumberException.class})
    protected  ResponseEntity<Object> handleAccountNotFoundWhileAddingEanNumberException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "You tried to add an ean number to an not existing account.";
        return  handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value={AddingTheSameEANNumberToSameAccountException.class})
    protected  ResponseEntity<Object> handleAddingTheSameEANNumberToSameAccountException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "You can not add two same ean numbers to same account.";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value= MappingAccountDatabseToDtoException.class)
    protected  ResponseEntity<Object> handleMappingAccountDatabseToDtoException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "Null value occured while mapping objects.";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value= AccountNotExistsUpdateException.class)
    protected  ResponseEntity<Object> handleAccountNotExistsUpdateException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "Object that you wanted to update is not in the database.";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value= NotFoundException.class)
    protected  ResponseEntity<Object> handleNoSuchElementException(NotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value= NotEnoughDataForCreationException.class)
    protected  ResponseEntity<Object> handleNotEnoughDataForCreationException(NotEnoughDataForCreationException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value= DuplicateException.class)
    protected  ResponseEntity<Object> handleDuplicateException(DuplicateException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value=UpdatePatchException.class)
    protected  ResponseEntity<Object> handleUpdatePatchException(UpdatePatchException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}