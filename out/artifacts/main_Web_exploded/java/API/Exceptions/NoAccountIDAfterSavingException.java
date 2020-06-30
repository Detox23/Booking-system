package API.Exceptions;

public class NoAccountIDAfterSavingException extends NullPointerException {
    public NoAccountIDAfterSavingException(String message){
        super(message);
    }
}
