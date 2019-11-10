package API.Controllers;

import API.Exceptions.AccountNotFoundWhileAddingEANNumberException;
import API.Exceptions.AddingTheSameEANNumberToSameAccountException;
import API.Exceptions.MappingAccountDatabseToDtoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}