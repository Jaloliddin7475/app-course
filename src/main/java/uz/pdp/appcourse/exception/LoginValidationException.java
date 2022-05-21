package uz.pdp.appcourse.exception;

public class LoginValidationException extends RuntimeException{
    public LoginValidationException(String message){
        super(message);
    }
}
