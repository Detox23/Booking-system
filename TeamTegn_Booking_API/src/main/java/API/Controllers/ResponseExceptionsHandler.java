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

    IEventLogService eventLogService;

    @Autowired
    public void setEventLogService(IEventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }


    /**
     * Exception handler for AccessDeniedException
     * @param exception <AccessDeniedException> Caught exception.
     * @return Appreciate error message with forbidden response.
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>("You have no access for that operation.", HttpStatus.FORBIDDEN);
    }

    /**
     * Exception handler for UnknownException
     * @param exception <UnknownException> Caught exception.
     * @return Appreciate error message with conflict response.
     */
    @ExceptionHandler(value = UnknownException.class)
    protected ResponseEntity<Object> handleUnknownException(UnknownException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Exception handler for NotFoundException
     * @param exception <NotFoundException> Caught exception.
     * @return Appreciate error message with not found response.
     */
    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NotFoundException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for RuntimeException
     * @param exception <RuntimeException> Caught exception.
     * @return Appreciate error message with internal server exception response.
     */
    @ExceptionHandler(value= RuntimeException.class)
    protected  ResponseEntity<Object> handleRuntimeException(RuntimeException exception){
    eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for NotEnoughDataException
     * @param exception <NotEnoughDataException> Caught exception.
     * @return Appreciate error message with conflict response.
     */
    @ExceptionHandler(value = NotEnoughDataException.class)
    protected ResponseEntity<Object> handleNotEnoughDataForCreationException(NotEnoughDataException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Exception handler for ExpiredTokenException
     * @param exception <ExpiredTokenException> Caught exception.
     * @return Appreciate error message with conflict response.
     */
    @ExceptionHandler(value = ExpiredTokenException.class)
    protected ResponseEntity<Object> handleExpiredTokenException(ExpiredTokenException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for DuplicateException
     * @param exception <DuplicateException> Caught exception.
     * @return Appreciate error message with conflict response.
     */
    @ExceptionHandler(value = DuplicateException.class)
    protected ResponseEntity<Object> handleDuplicateException(DuplicateException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Exception handler for UpdatePatchException
     * @param exception <UpdatePatchException> Caught exception.
     * @return Appreciate error message with internal server error response.
     */
    @ExceptionHandler(value = UpdatePatchException.class)
    protected ResponseEntity<Object> handleUpdatePatchException(UpdatePatchException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for UpdateErrorException
     * @param exception <UpdateErrorException> Caught exception.
     * @return Appreciate error message with conflict response.
     */
    @ExceptionHandler(value = UpdateErrorException.class)
    protected ResponseEntity<Object> handleUpdateErrorException(UpdateErrorException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Exception handler for UnknownAddingException
     * @param exception <UnknownAddingException> Caught exception.
     * @return Appreciate error message with conflict response.
     */
    @ExceptionHandler(value = UnknownAddingException.class)
    protected ResponseEntity<Object> handleUnknownAddingException(UnknownAddingException exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }


    /**
     * Exception handler for Exception error.
     * @param exception  <Exception> Caught exception.
     * @return Appreciate error message with conflict response.
     */
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleException(Exception exception) {
        eventLogService.addLog(new EventLogDto(exception.getMessage(), exception.getStackTrace().toString()));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

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