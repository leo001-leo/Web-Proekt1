package mk.ukim.finki.vp.proekt.vpproekt.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CastNotFoundException extends RuntimeException {
    public CastNotFoundException(Long id) {
        super(String.format("Cast with id: %d is not found", id));
    }
}
