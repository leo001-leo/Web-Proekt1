package mk.ukim.finki.vp.proekt.vpproekt.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username){
        super(String.format("User with username: %s already exists", username));
    }
}
