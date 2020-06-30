package API.Exceptions;

public class AddingTheSameEANNumberToSameAccountException extends Exception {
    public AddingTheSameEANNumberToSameAccountException(String errorMessage){
        super(errorMessage);
    }
}
