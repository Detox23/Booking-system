package API.Controllers;

import API.Exceptions.*;
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

    @ExceptionHandler(value=UnknownException.class)
    protected  ResponseEntity<Object> handleUnknownException(UnknownException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value= NotFoundException.class)
    protected  ResponseEntity<Object> handleNoSuchElementException(NotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(value= RuntimeException.class)
//    protected  ResponseEntity<Object> handleRuntimeException(RuntimeException exception){
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(value= NotEnoughDataException.class)
    protected  ResponseEntity<Object> handleNotEnoughDataForCreationException(NotEnoughDataException exception){
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

    @ExceptionHandler(value=UpdateErrorException.class)
    protected  ResponseEntity<Object> handleUpdateErrorException(UpdateErrorException exeption){
        return new ResponseEntity<>(exeption.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value=UnknownAddingException.class)
    protected  ResponseEntity<Object> handleUnknownAddingException(UnknownAddingException exeption){
        return new ResponseEntity<>(exeption.getMessage(), HttpStatus.CONFLICT);
    }
}