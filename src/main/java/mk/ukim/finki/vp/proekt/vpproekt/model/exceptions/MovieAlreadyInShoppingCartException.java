package mk.ukim.finki.vp.proekt.vpproekt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class MovieAlreadyInShoppingCartException extends RuntimeException{
    public MovieAlreadyInShoppingCartException(Long id, String username){
        super(String.format("Movie with id %d already exists in the shopping cart for user with username %s", id, username));
    }
}
