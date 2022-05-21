package uz.pdp.appcourse.exception;

public class JwtExpiredTokenException extends RuntimeException {
    public JwtExpiredTokenException(String message) {
        super(message);
    }
}
