package API.Controllers;

import API.Exceptions.*;
import API.Services.LogsService.IEventLogService;
import Shared.ForCreation.EventLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

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

    IEventLogService eventLogService;

    @Autowired
    public void setEventLogService(IEventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage().toString(), exception.getStackTrace().toString()));
        return new ResponseEntity<>("You have no access for that operation.", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = UnknownException.class)
    protected ResponseEntity<Object> handleUnknownException(UnknownException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage().toString(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NotFoundException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage().toString(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(value= RuntimeException.class)
//    protected  ResponseEntity<Object> handleRuntimeException(RuntimeException exception){
//    eventLogService.addLog(new EventLogDto(exception.getMessage().toString(), exception.getStackTrace().toString()));
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(value = NotEnoughDataException.class)
    protected ResponseEntity<Object> handleNotEnoughDataForCreationException(NotEnoughDataException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage().toString(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = DuplicateException.class)
    protected ResponseEntity<Object> handleDuplicateException(DuplicateException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage().toString(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UpdatePatchException.class)
    protected ResponseEntity<Object> handleUpdatePatchException(UpdatePatchException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage().toString(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UpdateErrorException.class)
    protected ResponseEntity<Object> handleUpdateErrorException(UpdateErrorException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage().toString(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = UnknownAddingException.class)
    protected ResponseEntity<Object> handleUnknownAddingException(UnknownAddingException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
//    @ExceptionHandler(value = Exception.class)
//    protected ResponseEntity<Object> handleException(Exception exception) {
//        eventLogService.addLog(new EventLogDto(exception.getMessage().toString(), exception.getStackTrace().toString()));
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder builder = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            builder.append(error.getField() + " : " + "can't be empty." + " \n");
        }
        eventLogService.addLog(new EventLogDto(ex.getMessage(), ex.getStackTrace().toString()));

        return new ResponseEntity<>(builder.toString(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}