package mk.ukim.finki.vp.proekt.vpproekt.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException(){
        super("Passwords do not match!");
    }
}
