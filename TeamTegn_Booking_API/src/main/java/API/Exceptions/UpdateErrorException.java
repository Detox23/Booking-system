package API.Exceptions;

public class UpdateErrorException extends RuntimeException {
    public UpdateErrorException(String msg) {
        super(msg);
    }
}
