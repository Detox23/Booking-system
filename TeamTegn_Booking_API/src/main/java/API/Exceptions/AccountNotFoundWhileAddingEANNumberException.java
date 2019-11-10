package API.Exceptions;



public class AccountNotFoundWhileAddingEANNumberException extends Exception {
    public AccountNotFoundWhileAddingEANNumberException(String errorMessage){
        super(errorMessage);
    }
}
