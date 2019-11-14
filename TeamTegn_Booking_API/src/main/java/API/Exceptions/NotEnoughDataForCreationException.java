package API.Exceptions;

public class NotEnoughDataForCreationException extends RuntimeException {
    public NotEnoughDataForCreationException(String message){
        super(message);
    }
}
