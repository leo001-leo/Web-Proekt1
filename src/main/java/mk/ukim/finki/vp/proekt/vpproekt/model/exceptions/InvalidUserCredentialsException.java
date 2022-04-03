package mk.ukim.finki.vp.proekt.vpproekt.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException(){
        super("Invalid User credentials exception");
    }
}
