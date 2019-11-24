package API.Exceptions;

public class UnknownException extends RuntimeException {
    public UnknownException(String message){
        super(message);
    }
}
